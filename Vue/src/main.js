import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

// 引入 font-awesome 图标
import 'font-awesome/css/font-awesome.min.css';

// 引入全局样式 
import "./assets/style.css";

// 引入 ElementUI
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(ElementUI);

Vue.config.productionTip = false;

import initMenu from "./utils/menu.js";

router.beforeEach((to,from,next)=>{
  console.log(to)
  if(to.path == '/'){
    
    next();
    // alert("请登录后查看")
  }else{
    if(store.state.menus.length == 0){

      initMenu(router, store);
      next();
    }else{

      next();
    }
  }
});

new Vue({
  router,
  store,
  render: function (h) { return h(App) }
}).$mount('#app')
