import Vue from 'vue'
import Router from 'vue-router'
import Welcome from '@/pages/Welcome'
import Console from '@/pages/Console'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Welcome',
      component: Welcome,
      meta: {
        hideHead: true
      }
    },
    {
      path: '/console',
      name: 'Console',
      component: Console
    }
  ]
})
