<template>
  <li>
    <table @click="onClickedComment">
      <tr>
        <td>{{ comment.no }}</td>
        <td>{{ comment.writer }}</td>
        <td>{{ comment.regDate }}</td>
      </tr>
      <tr>
<!--        <td><input type="radio" name="test" :value="comment.no"></td>-->
        <td colspan="3">{{ comment.comment }}</td>
      </tr>
      <tr v-show="selectedCommentNo === comment.no">
        <td colspan="3">
          <form @submit="onCommentSubmit">
            <textarea v-model="commentInput"></textarea>
            <button type="submit">댓글 쓰기</button>
          </form>
        </td>
      </tr>
    </table>
    <ul>
      <board-comment v-for="child in comment.childComments" :key="child.no" :comment="child"></board-comment>
    </ul>
  </li>
</template>

<script>

import axios from "axios";
import router from "@/router";
import {mapMutations, mapState} from "vuex";

export default {
  name: "BoardComment",
  props: ["comment"],
  data() {
    return {
      commentInput: ""
    }
  },
  methods: {
    ...mapMutations("boardContent",["setSelectedCommentNo"]),

    onClickedComment() {
      this.setSelectedCommentNo(this.comment.no);
    },
    onCommentSubmit(event){
      event.preventDefault();

      axios.post("/boardComment", {
        "parentBoardNo": this.comment.parentBoardNo,
        "parentCommentNo": this.comment.no,
        "comment": this.commentInput
      }, {
        headers: { "Content-Type" : "application/json" }
      }).then(() => {
        router.go(0);
      }).catch((error) => {
        console.log(error);
      })

    }
  },
  computed: {
    ...mapState("boardContent",["selectedCommentNo"])
  }
}
</script>

<style scoped>
table {
  border-collapse: collapse;
  margin: 2px;
}
table td{
  border: solid 1px;
  padding: 0px 1ex;
}
table form {
  display: flex;
  justify-content: center;
}
</style>