<template>
    <div class="header">
        <div class="header-container">
            <div class="app-name">
                <router-link to="/">二手交易平台</router-link>
            </div>
            <div class="search-container">
                <el-input placeholder="搜闲置..." v-model="searchValue" @change="searchIdle">
                    <el-button slot="append" icon="el-icon-search" @click="searchIdle"></el-button>
                </el-input>
            </div>
            <el-button type="primary" icon="el-icon-plus"  @click="toRelease">发布闲置</el-button>
            <el-button type="primary" icon="el-icon-chat-dot-round" @click="toMessage">消息</el-button>
            <el-dropdown trigger="click">
                <router-link v-if="username==='登录'" class="user-name-text" to="/login">{{username}}</router-link>
                <el-avatar v-else style="cursor:pointer;" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
                <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item><div @click="toMe">个人中心</div></el-dropdown-item>
                    <el-dropdown-item divided style="color: red;"><div @click="loginOut">退出登录</div></el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
        </div>
    </div>
</template>
<script>

    export default {
        name: 'Header',
        props: ['searchInput'],
        data() {
            return {
                searchValue: this.searchInput
            };
        },
        computed: {
            username() {
                let username = localStorage.getItem('sht_username');
                console.log(username);
                return username ? username : '登录';
            }
        },
        methods: {
            searchIdle() {
                if ('/search' !== this.$route.path) {
                    this.$router.push({path: '/search', query: {searchValue: this.searchValue}});
                } else {
                    this.$router.replace({path: '/search', query: {searchValue: this.searchValue}});
                    this.$router.go(0);
                }

            },
            toMe() {
                if ('/me' !== this.$route.path) {
                    this.$router.push({path: '/me'});
                }
            },
            toMessage(){
                if ('/message' !== this.$route.path) {
                    this.$router.push({path: '/message'});
                }
            },
            toRelease(){
                if ('/release' !== this.$route.path) {
                    this.$router.push({path: '/release'});
                }
            },
            loginOut(){
                localStorage.removeItem("sht_username");
                console.log("login out");
                if ('/index' === this.$route.path) {
                    this.$router.go(0);
                }else {
                    this.$router.push({path: '/index'});
                }
            }
        }
    };
</script>
<style scoped>
    .header {
        position: fixed;
        top: 0;
        left: 0;
        right: 0;
        width: 100%;
        height: 58px;
        background: #ffffff;
        display: flex;
        justify-content: center;
        border-bottom: #eeeeee solid 2px;
        z-index: 1000;
    }

    .header-container {
        width: 1000px;
        height: 100%;
        display: flex;
        align-items: center;
        justify-content: space-between;
    }

    .app-name a {
        color: #409EFF;
        font-size: 24px;
        text-decoration: none;
    }

    .search-container {
        width: 300px;
    }
    .user-name-text{
        font-size: 16px;
        font-weight: 600;
        color: #409EFF;
        cursor: pointer;
        text-decoration: none;
    }
</style>
