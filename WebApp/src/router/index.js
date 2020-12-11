import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/',
            redirect: '/index'
        },
        {
            path: '/index',
            component: () => import('../components/page/index.vue'),
            meta: { title: '二手交易平台' }
        },
        {
            path: '/search',
            component: () => import('../components/page/search.vue'),
            meta: { title: '二手交易平台' }
        },
        {
            path: '/me',
            component: () => import('../components/page/me.vue'),
            meta: { title: '二手交易平台' }
        },
        {
            path: '/message',
            component: () => import('../components/page/message.vue'),
            meta: { title: '二手交易平台' }
        },
        {
            path: '/release',
            component: () => import('../components/page/release.vue'),
            meta: { title: '二手交易平台' }
        },
        {
            path: '/details',
            component: () => import('../components/page/idle-details.vue'),
            meta: { title: '二手交易平台' }
        }
    ]
});
