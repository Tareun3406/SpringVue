<template>
  <div>
    <p>
      <span class="input-name">비밀번호</span>
      <input type="password" name="password" v-model="localPassword">
    </p>
    <p>
      <span class="input-name">비밀번호 확인</span>
      <input type="password" name="pwCheck" v-model="localPwCheck">
      <span v-show="!getIsSamePw" class="invalid-message isInvalid">비밀번호가 같지 않습니다.</span>
    </p>
  </div>
</template>

<script>
import {mapGetters, mapMutations} from "vuex";

export default {
  name: "passwordInput",
  data() {
    return {
      localPassword : "",
      localPwCheck : "",
    }
  },
  computed: {
    ...mapGetters("joinForm",["getIsSamePw"]),
  },
  watch: {
    localPassword(value) {
      this.updatePassword(value);
    },
    localPwCheck(value) {
      this.updatePwCheck(value);
    }
  },
  methods: {
    ...mapMutations("joinForm", ["updatePassword", "updatePwCheck"])
  }
}
</script>

<style scoped>

</style>