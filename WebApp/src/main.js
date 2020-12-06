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
new Vue({
    router,
    render: h => h(App)
}).$mount('#app');
