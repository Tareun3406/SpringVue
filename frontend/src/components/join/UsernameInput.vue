<template>
  <div>
    <span>아이디</span>
    <input type="text" name="username" v-model="localUsername">
    <button type="button" @click="duplicateCheck">중복확인</button>
    <p>
      <span v-bind:hidden="!isDuplicatedUsername">중복 아이디입니다.</span>
      <span v-bind:hidden="isDuplicatedUsername">사용 가능한 아이디입니다.</span>
    </p>
  </div>
</template>

<script>
import {mapActions, mapMutations, mapState} from "vuex";

export default {
  name: 'usernameInput',
  data() {
    return {
      localUsername: ''
    }
  },
  computed: {
    ...mapState('joinForm',["username","isDuplicatedUsername"])
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