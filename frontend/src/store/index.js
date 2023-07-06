import Vue from 'vue'
import Vuex from 'vuex'
import axios from "axios";

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    isLoggedIn : false
  },
  getters: {
    getIsLoggedIn(state) {
      return state.isLoggedIn;
    }
  },
  mutations: {
    setLoggedIn (state, isLoggedIn) {
      state.isLoggedIn = isLoggedIn;
    }
  },
  actions: {
    checkSessionLogin({ commit }) {
      axios.get('/isLoggedIn')
          .then((response)=>{
            console.log(response.data);
            commit('setLoggedIn', response.data);
          })
    }
  },
  modules: {
  }
})
