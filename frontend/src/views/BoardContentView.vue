<template>
  <div>
    <board-content></board-content>
    <ul>
      <li>
        <form @submit="onCommentSubmit">
          <textarea v-model="commentInput"></textarea>
          <button>댓글 쓰기</button>
        </form>
      </li>
      <board-comment v-for="comment in comments"
                     :comment="comment" :key="comment.no"></board-comment>
    </ul>
  </div>
</template>

<script>
import BoardContent from "@/components/BoardContent/BoardContent.vue";
import {mapState} from "vuex";
import BoardComment from "@/components/BoardContent/BoardComment.vue";
import axios from "axios";
import router from "@/router";

export default {
  name: "BoardContentView",
  components: {
    BoardComment,
    BoardContent
  },
  data() {
    return {
      commentInput: ""
    }
  },
  computed: {
    ...mapState("boardContent",["comments"])
  },
  methods: {
    onCommentSubmit(event){
      event.preventDefault();

      axios.post("/boardComment", {
        "parentBoardNo": router.currentRoute.params.no,
        "comment": this.commentInput
      }, {
        headers: { "Content-Type" : "application/json" }
      }).then(() => {
        router.go(0);
      }).catch((error) => {
        if (error.response.status === 401) {
          router.push("/login");
        }
        else console.log(error);
      })
    }
  }

}
</script>

<style scoped>
form {
  display: flex;
}
ul {
  width: max-content;
  list-style-type: none;
  margin: auto;
  padding-left: 0;
}
</style>