import Vue from 'vue';
import App from './App.vue';
import router from './router';
import ElementUI from 'element-ui';
import $ from 'jquery'
import 'element-ui/lib/theme-chalk/index.css';
import 'babel-polyfill';

import api from './api/index.js';
Vue.prototype.$api = api;

Vue.config.productionTip = false;

Vue.use(ElementUI, {
    size: 'medium'
});


router.beforeEach((to, from, next) => {
    document.title = `${to.meta.title}`;
    const userInfo = localStorage.getItem('sht_username');
    console.log('userInfo',userInfo);
    if (!userInfo
        &&(to.path === '/me'
        || to.path === '/message'
        || to.path === '/release'
        || to.path === '/order')) {
        next('/login');
    }else{
        next();
    }
});

new Vue({
    router,
    render: h => h(App)
}).$mount('#app');
