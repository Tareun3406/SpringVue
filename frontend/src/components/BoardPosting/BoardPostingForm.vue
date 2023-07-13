<template>
  <form @submit="postBoard">
    <p>
      title <input type="text" v-model="title">
    </p>
    <p>
      content
      <textarea v-model="content"></textarea>
    </p>

    <p><button>글 작성</button></p>
  </form>
</template>

<script>
import axios from "axios";
import router from "@/router";

export default {
  name:"BoardPostingForm",
  data() {
    return {
      title : "",
      content : ""
    }
  },
  methods: {
    postBoard(event) {
      event.preventDefault();
      if ( this.title.trim() !== "" && this.content.trim() !== "")
      axios.post("/board",{
        "title": this.title,
        "content": this.content
      }, {
        headers: {
          "Content-Type":"application/json"
        }
      }).then(
          ()=> router.push("/boardList")
      ).catch(
          error => {
            if (error.response.status === 401){
              router.push("/login");
            }
            console.log(error)
          }
      )
    },
  }
}
</script>

<style lang="scss" scoped>

</style>