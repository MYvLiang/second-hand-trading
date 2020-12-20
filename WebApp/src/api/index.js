import request from '../utils/request';

const api = {
    userLogin(query) {
        return request({
            url: '/user/login',
            method: 'get',
            params: query
        });
    },
    logout(query) {
        return request({
            url: '/user/logout',
            method: 'get',
            params: query
        });
    },
    signIn(data) {
        return request({
            url: '/user/sign-in',
            method: 'post',
            data: data
        });
    },
    getUserInfo(query) {
        return request({
            url: '/user/info',
            method: 'get',
            params: query
        });
    },
    updateNickname(query) {
        return request({
            url: '/user/nickname',
            method: 'get',
            params: query
        });
    },
    updatePassword(query) {
        return request({
            url: '/user/password',
            method: 'get',
            params: query
        });
    },
};


export default api;