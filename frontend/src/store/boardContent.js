import axios from "axios";

export default {
    namespaced: true,
    state :{
        no: "",
        writer: "",
        title: "",
        content: "",
        date : "",
    },
    mutations: {
      setStateForResponse(state, responseData) {
          state.no = responseData.no;
          state.writer = responseData.writer;
          state.title = responseData.title;
          state.content = responseData.content;
          state.date = responseData.regDate;
      }
    },
    actions: {
        getContents({ commit }, no) {
            return axios.get("/board?no=" + no)
                .then( response => {
                    commit("setStateForResponse", response.data);
                })
                .catch()
        }
    }
}