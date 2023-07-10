<template>
  <form @submit="doJoinOnSubmit">
    <username-input></username-input>
    <password-input></password-input>
    <email-input></email-input>
    <button>회원가입</button>
  </form>
</template>

<script>
import UsernameInput from "@/components/join/UsernameInput.vue";
import PasswordInput from "@/components/join/PasswordInput.vue";
import EmailInput from "@/components/join/EmailInput.vue";
import {mapActions} from "vuex";

export default {
  name: "JoinForm",
  data() {
    return {
      joinFormStyle: {

      }
    }
  },
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
>>> .invalid-message {
  display: block;
  margin: 0;
}
>>> .isInvalid {
  color: red;
}
>>> .isValid{
  color: blue;
}
>>> .input-name{
  display: inline-block;
  width: 7rem;
}
</style>

