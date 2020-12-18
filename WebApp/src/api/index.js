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
    getUserInfo(query) {
        return request({
            url: '/user/info',
            method: 'get',
            params: query
        });
    }
};


export default api;