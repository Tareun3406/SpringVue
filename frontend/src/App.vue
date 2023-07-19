<template>
  <div id="app">
    <nav>
      <router-link to="/">Home</router-link> |
      <router-link to="/boardList">Board</router-link> |
      <span v-bind:hidden="GET_IS_LOGGED_IN">
        <router-link to="/login">Login</router-link> |
        <router-link to="/join">Join</router-link>
      </span>
      <span v-bind:hidden="!GET_IS_LOGGED_IN">
        <a href="#" v-on:click="logout">Logout</a> |
        <router-link to="/myPage">MyPage</router-link>
      </span>
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
  border-bottom: solid 1px;
  margin: 1rem 0;
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
import router from "@/router";

export default {
  methods: {
    ...mapMutations(['SET_LOGGED_IN']),
    ...mapActions(['CHECK_SESSION_LOGIN']),
    logout(event) {
      event.preventDefault();

      axios.post('/logout')
          .then(
              () => {
                this.SET_LOGGED_IN(false);
                if (router.currentRoute.path !== "/") router.push("/");
              }
      )
          .catch(
        error => console.log(error)
      );
    }
  },
  computed : {
    ...mapGetters(['GET_IS_LOGGED_IN'])
  }
  ,
  mounted() {
    this.CHECK_SESSION_LOGIN();
  }
}
</script>