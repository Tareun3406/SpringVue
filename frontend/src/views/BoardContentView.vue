<template>
  <div>
    <board-content></board-content>
    <ul>
      <board-comment v-for="comment in comments"
                     :comment="comment" :key="comment.no"></board-comment>
    </ul>
  </div>
</template>

<script>
import BoardContent from "@/components/BoardContent/BoardContent.vue";
import {mapState} from "vuex";
import BoardComment from "@/components/BoardContent/BoardComment.vue";

export default {
  name: "BoardContentView",
  components: {
    BoardComment,
    BoardContent
  },
  computed: {
    ...mapState("boardContent",["comments"])
  }

}
</script>

<style lang="scss" scoped>

</style>