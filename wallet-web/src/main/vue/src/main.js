// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'

import ElementUI from 'element-ui'
import {myAxios} from './assets/scripts/commons'
import store from './assets/scripts/storage'
import echarts from 'echarts'

Vue.config.productionTip = false

Vue.use(ElementUI);
Vue.prototype.$http = myAxios;
Vue.prototype.$echarts = echarts;


import './assets/styles/cn-style.css'
import 'element-ui/lib/theme-chalk/index.css'

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
