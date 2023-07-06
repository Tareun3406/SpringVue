import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    isLoggedIn : false
  },
  getters: {
  },
  mutations: {
    setLoggedIn (state, isLoggedIn) {
      state.isLoggedIn = isLoggedIn;
    }
  },
  actions: {
  },
  modules: {
  }
})
