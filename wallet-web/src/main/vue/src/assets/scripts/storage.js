import Vuex from 'vuex'
import Vue from 'vue'
import {addCookie, delCookie, getCookie} from './cookies'

Vue.use(Vuex);

const store = new Vuex.Store({
  state: {
    hasLogin: false,
    token: null,
    nickname: {}
  },
  getters: {
    getToken: state => {
      if (!state.token) {
        state.token = getCookie('OAuth');
      }
      return state.token;
    },
    getNickname: state => {
      if (!state.nickname) {
        state.nickname = getCookie('nickname');
      }
      return state.nickname;
    }
  },
  mutations: {
    setUserInfo(state, payload) {
      state.nickname = payload.nickname;
      addCookie('nickname', state.nickname, 20 * 60)
    },
    setToken(state, payload) {
      state.token = payload.token;
      addCookie('OAuth', state.token, 20 * 60)
    },
    clearUserInfo(state) {
      state.token = null;
      state.nickname = null;
      delCookie('OAuth');
      delCookie('nickname');
    }
  }
});


export default store;
