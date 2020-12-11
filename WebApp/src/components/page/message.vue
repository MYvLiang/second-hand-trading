<template>
    <div>
        <app-head></app-head>
        <app-body>
            <div class="message-container">
                <div class="message-container-title">我的消息</div>
                <div v-for="(item,index) in [1,2,3,4]" class="message-container-list" @click="toDetails">
                    <div class="message-container-list-left">
                        <el-image
                                style="width: 55px; height: 55px;border-radius: 5px;"
                                src="https://pic2.zhimg.com/v2-22d4ebddd475020919bb12aa3a6ddaf7_xs.jpg?source=1940ef5c"
                                fit="contain"></el-image>
                        <div class="message-container-list-text">
                            <div class="message-nickname">nicheng</div>
                            <div class="message-content">哈哈哈哈哈哈哈哈哈啊啊啊啊啊啊啊啊啊啊啊啊</div>
                            <div class="message-time">2020-11-11 20:20</div>
                        </div>
                    </div>
                    <div class="message-container-list-right">
                        <el-image
                                style="width:130px; height: 90px;"
                                src="https://pic4.zhimg.com/80/v2-0aad60bfa8d0dc1ea4315c165594e507_720w.jpg?source=1940ef5c"
                                fit="contain"></el-image>
                    </div>
                </div>
            </div>
            <app-foot></app-foot>
        </app-body>
    </div>
</template>

<script>
    import AppHead from '../common/AppHeader.vue';
    import AppBody from '../common/AppPageBody.vue'
    import AppFoot from '../common/AppFoot.vue'

    export default {
        name: "message",
        components: {
            AppHead,
            AppBody,
            AppFoot
        },
        data(){
            return{

            };
        },
        methods:{
            toDetails(){
                this.$router.push({path: '/details'});
            }
        }
    }
</script>

<style scoped>
    .message-container{
        min-height: 80vh;
        padding: 0 20px;
    }
    .message-container-title{
        font-size: 16px;
        padding: 20px 0;
        font-weight: 600;
    }
    .message-container-list{
        cursor:pointer;
        height: 110px;
        border-top: 1px solid #eeeeee;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }
    .message-container-list-left{
        width: 800px;
        display: flex;
    }
    .message-container-list-right{
        width: 130px;
    }
    .message-container-list-text{
        margin-left: 10px;
    }
    .message-nickname{
        font-weight: 600;
        font-size: 18px;
        padding-bottom: 5px;
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
    }
    .message-content{
        font-size: 16px;
        padding-bottom: 15px;
        color: #555555;
        width: 710px;
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
    }
    .message-time{
        font-size: 13px;
        color: #555555;
    }
</style>