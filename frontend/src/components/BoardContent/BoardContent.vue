<template>
  <div>
    <h2 class="title">{{ title }}</h2>
    <div class="sub-title">{{ writer }} | {{ transferRegDate }}</div>
    <div class="content">
      {{ content }}
    </div>
  </div>
</template>

<script>
import {mapState} from "vuex";

export default {
  name: "BoardContent",
  computed: {
    ...mapState("boardContent", ["no", "writer", "title", "content", "date", "comments"]),

    transferRegDate() {
      return new Date(this.date).toLocaleString();
    }
  }
}
</script>

<style scoped>
.content {
  padding: 2rem;
  margin: 2rem;
  border: solid 1px;
}
.title {
margin-bottom: 0;
}

</style>