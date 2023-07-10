import axios from "axios";
import router from "@/router";

export default {
    namespaced: true,
    state: {
        username: "",
        // isInvalidUsername: true,
        isDuplicatedUsername : "",
        isCheckedDuplicated : false,    // true 일때 로그인 허용

        password: "",
        pwCheck: "",
        // IsInvalidPw: true,

        email: "",
        // IsInvalidEmail: true
    },
    getters: {
        getIsSamePw(state) {
            return state.password === state.pwCheck;
        }
    },
    mutations: {
        updateUsername(state, value) {
            state.username = value;
        },
        setIsDuplicatedUsername(state, value) {
            state.isDuplicatedUsername = value;
        },
        setIsCheckedDuplicated(state, value) {
            state.isCheckedDuplicated = value;
        },


        updatePassword(state, value) {
            state.password = value;
        },
        updatePwCheck(state, value) {
            state.pwCheck = value;
        },

        updateEmail(state, value) {
            state.email = value;
        },
        reset(state) {
            state.username = "";
            state.password = "";
            state.pwCheck = "";
            state.email = "";
            state.isCheckedDuplicated = false;
            state.isDuplicatedUsername = "";
        }
    },
    actions: {
        duplicateCheck({ state, commit}) {
            if (state.username !== "") {
                axios.get("/checkIsDuplicateUser?username="+state.username)
                    .then(
                        response => {
                            commit('setIsDuplicatedUsername',response.data);
                            commit('setIsCheckedDuplicated', !response.data);
                        }
                    ).catch(
                    error => console.log(error)
                )
            }
        },

        doJoinOnSubmit({state, getters, commit}, event) {
            event.preventDefault();
            if (getters.getIsSamePw && state.isCheckedDuplicated && !state.isDuplicatedUsername) {
                axios.put("/joinUser",{
                    username:state.username,
                    password:state.password,
                    email:state.email
                }, {
                    headers: {
                        "Content-Type" : "application/json"
                    }
                }).then( () => {
                    commit('reset');
                    router.push("/login");
                }).catch(error => console.log(error))
            }
        }
    }
}