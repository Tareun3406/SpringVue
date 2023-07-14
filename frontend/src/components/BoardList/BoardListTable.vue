<template>
  <table>
    <tr>
      <th>no</th>
      <th>title</th>
      <th>writer</th>
      <th>date</th>
    </tr>
    <tr v-for="board in board_list" :key="board.id" @click="onClickBoardTitle(board.no)">
      <th>{{ board.no }}</th>
      <th>{{ board.title }}</th>
      <th>{{ board.writer }}</th>
      <th>{{ board.regDate }}</th>
    </tr>
    <tr>
      <button type="button" @click="onClickPostButton">게시글 작성</button>
    </tr>
  </table>
</template>

<script>
import {mapState} from "vuex";
import router from "@/router";

export default {
  name: 'BoardListTable',
  computed: {
    ...mapState("boardList",["board_list"]),
    ...mapState(["isLoggedIn"])
  },
  methods: {
    onClickPostButton() {
      if (!this.isLoggedIn) {
        router.push("/login");
        return;
      }
      router.push("/boardPosting");
    },
    onClickBoardTitle(no) {
      router.push("/boardContent/" + no);
    }
  }
}
</script>

<style lang="scss" scoped>

</style>