<template>
    <div>
        <app-head></app-head>
        <app-body>
            <div class="idle-details-container">
                <div class="details-header">
                    <div class="details-header-user-info">
                        <el-image
                                style="width: 80px; height: 80px;border-radius: 5px;"
                                src="https://pic2.zhimg.com/v2-22d4ebddd475020919bb12aa3a6ddaf7_xs.jpg?source=1940ef5c"
                                fit="contain"></el-image>
                        <div style="margin-left: 10px;">
                            <div class="details-header-user-info-nickname">nicheng</div>
                            <div class="details-header-user-info-time">2020-2-2加入</div>
                        </div>
                    </div>
                    <div class="details-header-buy">
                        <div style="color: red;font-size: 18px;font-weight: 600;">50.12￥</div>
                        <el-button type="danger" plain>立即购买</el-button>
                        <el-button type="primary" plain>收藏</el-button>
                    </div>
                </div>

                <div class="details-info">
                    <div class="details-info-title">标题标题标题标题标题标题</div>
                    <div class="details-info-main">
                        <p>
                            正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文。</p>
                        <p>
                            正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文正文。</p>
                    </div>
                    <div class="details-picture">
                        <el-image v-for="(a,i) in [1,2]"
                                  style="width: 500px;  max-height: 500px;margin-bottom: 2px;"
                                  src="https://pic2.zhimg.com/v2-22d4ebddd475020919bb12aa3a6ddaf7_xs.jpg?source=1940ef5c"
                                  fit="contain"></el-image>
                        <el-image v-for="(a,i) in [1,2]"
                                  style="width: 500px; max-height: 500px;margin-bottom: 2px;"
                                  src="https://pic4.zhimg.com/80/v2-0aad60bfa8d0dc1ea4315c165594e507_720w.jpg?source=1940ef5c"
                                  fit="contain"></el-image>
                    </div>
                </div>

                <div class="message-container" id="replyMessageLocation">
                    <div class="message-title">全部留言</div>
                    <div class="message-send">
                        <div v-if="isReply" style="padding-bottom: 10px;">
                            <el-button type="info" @click="isReply=false">回复：哈哈哈哈哈哈哈哈哈 @shiny:  <i class="el-icon-close el-icon--right"></i></el-button>
                        </div>
                        <el-input
                                type="textarea"
                                autosize
                                placeholder="留言提问..."
                                v-model="textarea2"
                                maxlength="200"
                                show-word-limit>
                        </el-input>
                        <div class="message-send-button">
                            <el-button plain>发送</el-button>
                        </div>
                    </div>
                    <div>
                        <div v-for="(item,index) in [1,2,3,4,5,6,6,6,8]" class="message-container-list">
                            <div class="message-container-list-left" @click="replyMessage(index)">
                                <el-image
                                        style="width: 55px; height: 55px;border-radius: 5px;"
                                        src="https://pic2.zhimg.com/v2-22d4ebddd475020919bb12aa3a6ddaf7_xs.jpg?source=1940ef5c"
                                        fit="contain"></el-image>
                                <div class="message-container-list-text">
                                    <div class="message-nickname">nicheng</div>
                                    <div class="message-content">哈哈哈哈哈哈哈哈哈哈哈</div>
                                    <div class="message-time">2020-11-11 20:20</div>
                                </div>
                            </div>
                            <div class="message-container-list-right">
                                <el-button style="float: right;"  plain @click="replyMessage(index)">回复</el-button>
                            </div>
                        </div>
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
        name: "idle-details",
        components: {
            AppHead,
            AppBody,
            AppFoot
        },
        data() {
            return {
                textarea2:'',
                isReply:false
            };
        },
        methods: {
            replyMessage(index){
                console.log('回复',index);
                $('html,body').animate({
                    scrollTop: $("#replyMessageLocation").offset().top-600
                }, {duration: 500, easing: "swing"});
                this.isReply=true;
            }
        },
    }
</script>

<style scoped>
    .idle-details-container {
        min-height: 80vh;
    }

    .details-header {
        height: 80px;
        border-bottom: 10px solid #f6f6f6;
        display: flex;
        justify-content: space-between;
        padding: 20px;
        align-items: center;
    }

    .details-header-user-info {
        display: flex;
    }

    .details-header-user-info-nickname {
        font-weight: 600;
        font-size: 18px;
        margin-bottom: 10px;
    }

    .details-header-user-info-time {
        font-size: 12px;
        color: #555555;
    }

    .details-header-buy {
        display: flex;
        align-items: center;
        justify-content: space-between;
        height: 50px;
        width: 280px;
    }

    .details-info {
        padding: 20px;
    }

    .details-info-title {
        font-size: 22px;
        font-weight: 600;
        margin-bottom: 20px;

    }

    .details-info-main {
        font-size: 17px;
        color: #121212;
        line-height: 160%;
        text-indent: 36px;
    }

    .details-picture {
        margin: 20px 0;
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    .message-container {
        min-height: 100px;
        border-top: 10px solid #f6f6f6;
        padding: 20px;
    }

    .message-title {
        font-size: 20px;
        font-weight: 600;
        margin-bottom: 20px;
    }
    .message-send{
        min-height: 60px;
    }
    .message-send-button{
        margin-top: 10px;
        display: flex;
        justify-content: flex-end;
    }
    .message-container-list{
        cursor:pointer;
        min-height: 60px;
        border-top: 1px solid #eeeeee;
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 15px 0;
    }
    .message-container-list:first-child{
        border-top:none;
    }
    .message-container-list-left{
        width: 850px;
        display: flex;
    }
    .message-container-list-right{
        width: 100px;
    }
    .message-container-list-text{
        margin-left: 10px;
    }
    .message-nickname{
        font-weight: 600;
        font-size: 18px;
        padding-bottom: 5px;
    }
    .message-content{
        font-size: 16px;
        padding-bottom: 15px;
        color: #555555;
        width: 770px;
    }
    .message-time{
        font-size: 13px;
        color: #555555;
    }
</style>