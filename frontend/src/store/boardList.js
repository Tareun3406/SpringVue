import axios from "axios";

export default {
    namespaced: true,
    state: {
        board_list : []
    },
    mutations: {
        SET_BOARD_LIST(state, list) {
            state.board_list = list;
        }
    },
    actions: {
        getBoardList({commit}) {
            return axios.get("/boardList").then(response => {
                commit("SET_BOARD_LIST", response.data);
            }).catch();
        }
    }

}