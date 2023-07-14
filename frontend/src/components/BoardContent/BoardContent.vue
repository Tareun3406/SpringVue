<template>
  <div>
    <div>
      <h2>{{ title }}</h2>
      <p>{{ writer }} | {{ date }}</p>
      <div>
        {{ content }}
      </div>
    </div>
  </div>
</template>

<script>
import {mapState} from "vuex";

export default {
  name: "BoardContent",
  computed: {
    ...mapState("boardContent", ["no", "writer", "title", "content", "date"]),
  }
}
</script>

<style lang="scss" scoped>

</style>