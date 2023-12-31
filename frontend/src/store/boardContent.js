import axios from "axios";

export default {
    namespaced: true,
    state :{
        no: "",
        writer: "",
        title: "",
        content: "",
        date : "",
        comments : [],
        selectedCommentNo: ""
    },
    mutations: {
        setStateForResponse(state, responseData) {
          state.no = responseData.no;
          state.writer = responseData.writer;
          state.title = responseData.title;
          state.content = responseData.content;
          state.date = responseData.regDate;
          state.comments = responseData.comments;
          },

        setSelectedCommentNo(state, value) {
            state.selectedCommentNo = value;
        }

    },
    actions: {
        getContents({ commit }, no) {
            return axios.get("/board?no=" + no)
                .then( response => {
                    commit("setStateForResponse", response.data);
                    // console.log(response.data);
                })
                .catch()
        }
    }
}