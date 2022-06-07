import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

export default new VueRouter({

  routes: [
    {
      path: '/',
      name: 'login',
      component: require("../views/login.vue").default,
    },
    {
      path: '/home',
      name: 'home',
      component: require("../views/home.vue").default,
    }
  ]
});