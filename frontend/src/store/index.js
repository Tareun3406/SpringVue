import Vue from 'vue'
import Vuex from 'vuex'
import axios from "axios";

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    isLoggedIn : false
  },
  getters: {
    GET_IS_LOGGED_IN(state) {
      return state.isLoggedIn;
    }
  },
  mutations: {
    SET_LOGGED_IN (state, isLoggedIn) {
      state.isLoggedIn = isLoggedIn;
    }
  },
  actions: {
    CHECK_SESSION_LOGIN({ commit }) {
      axios.get('/isLoggedIn')
          .then((response)=>{
            commit('SET_LOGGED_IN', response.data);
          })
    }
  },
  modules: {
  }
})
