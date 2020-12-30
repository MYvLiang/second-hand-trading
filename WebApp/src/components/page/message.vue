<template>
    <div>
        <app-head></app-head>
        <app-body>
            <div class="message-container">
                <div class="message-container-title">我的消息</div>
                <div v-for="(mes,index) in meslist" class="message-container-list" @click="toDetails(mes.idle.id)">
                    <div class="message-container-list-left">
                        <el-image
                                style="width: 55px; height: 55px;border-radius: 5px;"
                                :src="mes.fromU.avatar"
                                fit="cover"></el-image>
                        <div class="message-container-list-text">
                            <div class="message-nickname">{{mes.fromU.nickname}}</div>
                            <div class="message-content">{{mes.content}}</div>
                            <div class="message-time">{{mes.createTime}}</div>
                        </div>
                    </div>
                    <div class="message-container-list-right">
                        <el-image
                                style="width:130px; height: 90px;"
                                :src="mes.idle.imgUrl"
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
                meslist:[]
            };
        },
        created(){
            this.$api.getAllMyMessage().then(res=>{
                console.log(res);
                if(res.status_code===1){
                    for(let i=0;i<res.data.length;i++){
                        let imgList=JSON.parse(res.data[i].idle.pictureList);
                        res.data[i].idle.imgUrl=imgList?imgList[0]:'';
                        let contentList=res.data[i].content.split('<br>');
                        let contenHtml=contentList[0];
                        for(let i=1;i<contentList.length;i++){
                            contenHtml+='  '+contentList[i];
                        }
                        res.data[i].content=contenHtml;
                    }
                    this.meslist=res.data;
                }
            })
        },
        methods:{
            toDetails(id){
                this.$router.push({path: '/details',query:{id:id}});
            }
        }
    }
</script>

<style scoped>
    .message-container{
        min-height: 85vh;
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