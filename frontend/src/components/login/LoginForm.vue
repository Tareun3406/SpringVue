<template>
  <form class="login-form" v-on:submit="onSubmitTest">
    <p>
      <span class="input-name">ID</span>
      <input type="text" name="username" v-model="username"></p>
    <p>
      <span class="input-name">PW</span>
      <input type="password" name="password" v-model="password"></p>

    <p class="isInvalid" v-show="isInvalidValue">아이디 또는 비밀번호를 확인해주세요</p>

    <button>로그인</button>

  </form>
</template>

<script>
  import axios from "axios";
  import router from "@/router";
  import {mapMutations} from "vuex";

  export default {
    name: 'LoginForm',
    data() {
      return {
        username: '',
        password: '',
        isInvalidValue: false
      }
    }
    ,
    methods : {
      ...mapMutations(['SET_LOGGED_IN']),
      onSubmitTest(event)
      {
        event.preventDefault();

        let formData = new FormData();
        formData.append('username', this.username);
        formData.append('password', this.password);

        axios.post('/login',formData,{
          headers: {
            "Content-Type":"multipart/form-data"
          }
        }).then( () => {
          this.SET_LOGGED_IN(true);
          router.push('/');
        }).catch( () => {
          this.isInvalidValue = true;
        });
      }
    },
  }


</script>

<style scoped>
.invalid-message {
  display: block;
  margin: 0;
}
.isInvalid {
  color: red;
}
.isValid{
  color: blue;
}
.input-name{
  display: inline-block;
  width: 3rem;
}
</style>