<template>
  <div>
    <span class="input-name">아이디</span>
    <input type="text" name="username" :value="username()" disabled>
  </div>
</template>

<script>

import {mapState} from "vuex";

export default {
  name: 'usernameInput',
  data() {
    return {
    }
  },
  computed: {
  },
  watch: {
  },
  methods: {
    ...mapState('myPageForm',['username'])
  },
}
</script>

<style scoped>

</style>