<template>
    <div class="login-container">
        <el-card class="box-card">
            <div class="login-body">
                <div class="login-title" @click="toIndex">MYL的二手交易平台</div>
                <el-input placeholder="请输入手机号..." v-model="username" class="login-input">
                    <template slot="prepend">
                        <div class="el-icon-user-solid"></div>
                    </template>
                </el-input>
                <el-input placeholder="请输入密码..." v-model="username" class="login-input">
                    <template slot="prepend">
                        <div class="el-icon-lock"></div>
                    </template>
                </el-input>
                <div class="login-submit">
                    <el-button type="primary" @click="login">登录</el-button>
                </div>
                <div class="sign-in-container">
                    <router-link to="/sign-in" class="sign-in-text">注册</router-link>
                </div>
            </div>
        </el-card>
    </div>
</template>

<script>
    export default {
        name: "login",
        data() {
            return {
                username: ''
            };
        },
        methods:{
            login(){
                localStorage.setItem('sht_username', 'aaa');
                this.$router.replace({path: '/index'});
            },
            toIndex(){
                this.$router.replace({path: '/index'});
            }
        }
    }
</script>

<style scoped>
    .login-container {
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        width: 100%;
        background-color: #f1f1f1;
    }

    .login-body {
        padding: 30px;
        width: 400px;
        height: 100%;
    }

    .login-title {
        padding-bottom: 30px;
        text-align: center;
        font-weight: 600;
        font-size: 20px;
        color: #409EFF;
        cursor: pointer;
    }

    .login-input {
        margin-bottom: 20px;
    }
    .login-submit{
        display: flex;
        justify-content: center;
    }
    .sign-in-container{
        padding: 0 10px;
    }
    .sign-in-text{
        color: #409EFF;
        font-size: 16px;
        text-decoration: none;
    }
</style>