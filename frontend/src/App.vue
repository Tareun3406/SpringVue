<template>
  <div id="app">
    <nav>
      <router-link to="/">Home</router-link> |
      <router-link to="/login" v-bind:hidden="getIsLoggedIn">Login</router-link>
      <a href="#" v-on:click="logout" v-bind:hidden="!getIsLoggedIn">Logout</a>

    </nav>
    <router-view/>
  </div>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>

<script>
import axios from "axios";
import {mapActions, mapGetters, mapMutations} from "vuex";

export default {
  methods: {
    ...mapMutations(['setLoggedIn']),
    ...mapActions(['checkSessionLogin']),
    logout(event) {
      event.preventDefault();

      axios.post('/logout')
          .then(
              () => this.setLoggedIn(false)
      )
          .catch(
        error => console.log(error)
      );
    }
  },
  computed : {
    ...mapGetters(['getIsLoggedIn'])
  }
  ,
  mounted() {
    this.checkSessionLogin();
  }
}
</script>