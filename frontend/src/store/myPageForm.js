import axios from "axios";
import router from "@/router";

export default {
    namespaced: true,
    state: {
        username: "",
        password: "",
        pwCheck: "",
        email: "",
    },
    getters: {
        getIsSamePw(state) {
            return state.password === state.pwCheck;
        }
    },
    mutations: {
        updatePassword(state, value) {
            state.password = value;
        },
        updatePwCheck(state, value) {
            state.pwCheck = value;
        },


        updateEmail(state, value) {
            state.email = value;
        },

        setUserInfo(state, value) {
            state.username = value.username;
            state.email = value.email;
        },

    },
    actions: {
        getMyUserInfo({commit}) {
            return axios.get("/userInfo").then(
                response => {
                    let userinfo = response.data;
                    commit('setUserInfo',userinfo);
                }
            ).catch(error => {
                if (error.response.status === 401){
                    router.push("/");
                }else console.log(error);
            })
        },
        doUpdateUserOnSubmit({state, getters}, event) {
            event.preventDefault();
            if (getters.getIsSamePw && state.password !== "") {
                axios.put("/joinUser",{
                    username:state.username,
                    password:state.password,
                    email:state.email
                }, {
                    headers: {
                        "Content-Type" : "application/json"
                    }
                }).then( () => {
                    router.push("/");
                }).catch(error => console.log(error))
            }
        }
    }
}