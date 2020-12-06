<template>
    <div class="header">
        <div class="header-container">
            <div class="app-name">二手交易平台</div>
            <div class="search-container">
                <el-input placeholder="搜闲置..." v-model="input3">
                    <el-button slot="append" icon="el-icon-search"></el-button>
                </el-input>
            </div>
            <el-button type="primary" icon="el-icon-plus">发布闲置</el-button>
            <el-button type="primary" icon="el-icon-chat-dot-round">消息</el-button>
            <el-dropdown trigger="click">
                <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
                <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item>个人中心</el-dropdown-item>
                    <el-dropdown-item divided style="color: red;">退出登录</el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
        </div>
    </div>
</template>
<script>

    export default {
        name: 'Header',
        data() {
            return {
                input3:''
            };
        },
        computed: {},
        methods: {}
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

    .app-name {
        color: #409EFF;
        font-size: 24px;
    }

    .search-container{
        width: 300px;
    }
</style>
