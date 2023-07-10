<template>
  <div>
    <span class="input-name">아이디</span>
    <input type="text" name="username" v-model="localUsername">
    <button type="button" @click="duplicateCheck" style="position: absolute">중복확인</button>
    <p class="invalid-message" v-show="isCheckedDuplicated">
      <span class="isInvalid" v-show="isDuplicatedUsername">중복 아이디입니다.</span>
      <span class="isValid" v-show="!isDuplicatedUsername">사용 가능한 아이디입니다.</span>
    </p>
    <p class="invalid-message isInvalid" v-show="!isCheckedDuplicated && localUsername !== ''">중복확인 버튼을 눌러주세요</p>

  </div>
</template>

<script>
import {mapActions, mapMutations, mapState} from "vuex";

export default {
  name: 'usernameInput',
  data() {
    return {
      localUsername: '',
    }
  },
  computed: {
    ...mapState('joinForm',["username","isDuplicatedUsername", "isCheckedDuplicated"])
  },
  watch: {
    localUsername(newVal) {
      this.updateUsername(newVal);
      this.setIsCheckedDuplicated(false);
    }
  },
  methods: {
    ...mapMutations('joinForm',["updateUsername","setIsCheckedDuplicated"]),
    ...mapActions('joinForm', ["duplicateCheck"])
  }
}
</script>

<style scoped>

</style>