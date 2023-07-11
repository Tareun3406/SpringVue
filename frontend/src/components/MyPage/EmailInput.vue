<template>
  <div>
    <p>
      <span class="input-name">이메일</span>
      <input type="email" name="email" v-model="localEmail">
    </p>
  </div>
</template>

<script>
import {mapMutations, mapState} from "vuex";

export default {
  name: "emailInput",
  data() {
    return {
      localEmail : ""
    }
  },
  watch : {
    localEmail(value) {
      this.updateEmail(value);
    }
  },
  computed: {
    ...mapState('myPageForm', ["email"])
  },
  methods: {
    ...mapMutations('myPageForm', ["updateEmail"]),
  },
  mounted() {
    this.localEmail = this.email;
  }
}
</script>

<style scoped>

</style>