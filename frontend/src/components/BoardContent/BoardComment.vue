<template>
  <li>
    <table>
      <tr>
        <td>{{ comment.no }}</td>
        <td>{{ comment.writer }}</td>
        <td>{{ comment.regDate }}</td>
      </tr>
      <tr>
        <td><input type="radio" name="test"></td>
        <td colspan="2">{{ comment.comment }}</td>
      </tr>
      <tr>
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

export default {
  name: "BoardComment",
  props: ["comment"],
  data() {
    return {
      onClicked : false,
      commentInput: ""
    }
  },
  methods: {
    onChangeClicked(event) {
      this.onClicked = event.check;
    },
    onCommentSubmit(event){
      event.preventDefault();

      axios.post("/boardComment", {
        "parentBoardNo": this.comment.parentBoardNo,
        "parentCommentNo": this.comment.no,
        "depth": this.comment.depth+1,
        "comment": this.commentInput
      }, {
        headers: { "Content-Type" : "application/json" }
      }).then(() => {
        router.go(0);
      }).catch((error) => {
        console.log(error);
      })

    }
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