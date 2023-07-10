<template>
  <div>
    <p>
      <span>이메일</span>
      <input type="email" name="email" v-model="localEmail">
    </p>
  </div>
</template>

<script>
import {mapMutations} from "vuex";

export default {
  name: "emailInput",
  data() {
    return {
      localEmail: "",
    }
  },
  watch : {
    localEmail(value) {
      this.updateEmail(value);
    }
  },
  methods: {
    ...mapMutations('joinForm', ["updateEmail"])
  }
}
</script>

<style>

</style>