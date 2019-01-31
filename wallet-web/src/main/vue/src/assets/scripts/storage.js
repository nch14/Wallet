import Vuex from 'vuex'
import Vue from 'vue'

Vue.use(Vuex);

const store = new Vuex.Store({
  state: {
    headIsShow: true,
    hasLogin: false,
    userId: 1,
    token: null,
    userInfo: {}
  },
  getters: {
    getToken: state => {
      return state.token;
    },
    getHeadIsShow: state => {
      return state.headIsShow;
    }
  },
  mutations: {
    login: state => {
      state.hasLogin = true;
    },
    showFoot: state => {
      state.headIsShow = true, console.log(state.headIsShow)
    },
    hideFoot: state => {
      state.headIsShow = false, console.log(state.headIsShow)
    },
    setUserId(state, payload) {
      state.userId = payload.userId;
      state.hasLogin = true;
    },
    setUserInfo(state, payload) {
      state.userInfo = payload.userInfo;
      state.userId = state.userInfo.id;
      state.hasLogin = true;
    },

  }
});

export default store;
