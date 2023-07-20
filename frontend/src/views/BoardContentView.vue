<template>
  <div>
    <board-content></board-content>
    <ul>
      <li>
        <form @submit.prevent="onCommentSubmit">
          <textarea v-model="commentInput" :disabled="!isLoggedIn" :placeholder="needLoginMessage"></textarea>
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
import {mapActions, mapState} from "vuex";
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
    ...mapState("boardContent",["comments"]),
    ...mapState(["isLoggedIn"]),
    needLoginMessage() {
      if (this.isLoggedIn){
        return "";
      }
      return "로그인이 필요합니다."
    }
  },
  methods: {
    ...mapActions("boardContent",["getContents"]),

    onCommentSubmit(){
      axios.post("/boardComment", {
        "parentBoardNo": router.currentRoute.params.no,
        "comment": this.commentInput
      }, {
        headers: { "Content-Type" : "application/json" }
      }).then(() => {
        this.getContents(router.currentRoute.params.no);
      }).catch((error) => {
        if (error.response.status === 401) {
          alert("로그인이 필요합니다.");
        }
        else {
          console.log(error);
        }
      })
    },
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