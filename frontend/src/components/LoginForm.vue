<template>
  <form class="login-form" v-on:submit="onSubmitTest">
    <p>ID <input type="text" name="username"></p>
    <p>PW <input type="password" name="password"></p>

    <button>로그인</button>
  </form>
</template>

<script>
  export default {
    name: 'LoginForm',
    methods : {
      onSubmitTest(event)
      {
        event.preventDefault();
        console.log("test");
      }
    }
  }


</script>

<style scoped>

</style>