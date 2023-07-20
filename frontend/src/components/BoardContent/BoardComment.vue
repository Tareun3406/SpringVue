<template>
  <li>
    <table @click="onClickedComment">
      <tr>
        <td>{{ comment.no }}</td>
        <td>{{ comment.writer }}</td>
        <td>{{ transferRegDate }}</td>
      </tr>
      <tr>
<!--        <td><input type="radio" name="test" :value="comment.no"></td>-->
        <td colspan="3">{{ comment.comment }}</td>
      </tr>
      <tr v-show="selectedCommentNo === comment.no">
        <td colspan="3">
          <form @submit.prevent="onCommentSubmit">
            <textarea v-model="commentInput" :disabled="!isLoggedIn" :placeholder="needLoginMessage"></textarea>
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
import {mapActions, mapMutations, mapState} from "vuex";

export default {
  name: "BoardComment",
  props: ["comment"],
  data() {
    return {
      commentInput: "",
    }
  },
  methods: {
    ...mapMutations("boardContent",["setSelectedCommentNo"]),
    ...mapActions("boardContent",["getContents"]),

    onClickedComment() {
      this.setSelectedCommentNo(this.comment.no);
    },
    onCommentSubmit(){

      axios.post("/boardComment", {
        "parentBoardNo": this.comment.parentBoardNo,
        "parentCommentNo": this.comment.no,
        "comment": this.commentInput
      }, {
        headers: { "Content-Type" : "application/json" }
      }).then(() => {
        this.getContents(this.comment.parentBoardNo);
      }).catch((error) => {
        if (error.response.status === 401) {
          alert("로그인이 필요합니다.");
        } else {
          console.error(error);
        }
      });

    }
  },
  computed: {
    ...mapState("boardContent",["selectedCommentNo"]),
    ...mapState(["isLoggedIn"]),
    transferRegDate() {
      return new Date(this.comment.regDate).toLocaleString();
    },
    needLoginMessage() {
      if (this.isLoggedIn){
        return "";
      }
      return "로그인이 필요합니다."
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