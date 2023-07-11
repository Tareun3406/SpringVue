<template>
  <form @submit="doUpdateUserOnSubmit">
    <username-input></username-input>
    <password-input></password-input>
    <email-input></email-input>
    <button type="submit">회원가입</button>
  </form>
</template>

<script>
import UsernameInput from "@/components/MyPage/UsernameInput.vue";
import PasswordInput from "@/components/MyPage/PasswordInput.vue";
import EmailInput from "@/components/MyPage/EmailInput.vue";
import {mapActions} from "vuex";

export default {
  name: "MyPageForm",
  data() {
    return {
    }
  },
  components: {
    UsernameInput,
    PasswordInput,
    EmailInput
  },
  methods: {
    ...mapActions("myPageForm",["doUpdateUserOnSubmit"])
  },
  beforeUpdate() {
    console.log("beforeUpdate")
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

