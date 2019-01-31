import Vue from 'vue'
import Router from 'vue-router'
import Console from '@/pages/Console'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Console',
      component: Console
    }
  ]
})
