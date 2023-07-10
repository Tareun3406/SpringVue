<template>
  <form @submit="doJoinOnSubmit">
    <username-input></username-input>
    <password-input></password-input>
    <email-input></email-input>
    <button>로그인</button>
  </form>
</template>

<script>
import UsernameInput from "@/components/join/UsernameInput.vue";
import PasswordInput from "@/components/join/PasswordInput.vue";
import EmailInput from "@/components/join/EmailInput.vue";
import {mapActions} from "vuex";

export default {
  name: "JoinForm",
  components: {
    UsernameInput,
    PasswordInput,
    EmailInput
  },
  methods: {
    ...mapActions("joinForm",["doJoinOnSubmit"])
  }
}
</script>

<style scoped>

</style>