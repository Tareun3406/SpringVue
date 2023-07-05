<template>
  <div class="login">
    <login-form></login-form>
  </div>
</template>

<script>
  import LoginForm from "@/components/LoginForm.vue";

  export default {
    name: 'LoginView',
    components: {
      LoginForm
    }
  }
</script>

<style scoped>

</style>