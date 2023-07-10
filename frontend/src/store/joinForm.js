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

        doJoinOnSubmit({state, getters}, event) {
            event.preventDefault();
            console.log("로그인 버튼 클릭!")
            console.log
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
                    router.push("/login");
                }).catch(error => console.log(error))
            }
        }
    }
}