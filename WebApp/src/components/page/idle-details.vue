<template>
    <div>
        <app-head></app-head>
        <app-body>
            <div class="idle-details-container">
                <div class="details-header">
                    <div class="details-header-user-info">
                        <el-image
                                style="width: 80px; height: 80px;border-radius: 5px;"
                                :src="idleItemInfo.user.avatar"
                                fit="contain"></el-image>
                        <div style="margin-left: 10px;">
                            <div class="details-header-user-info-nickname">{{idleItemInfo.user.nickname}}</div>
                            <div class="details-header-user-info-time">{{idleItemInfo.user.signInTime.substring(0,10)}} 加入平台</div>
                        </div>
                    </div>
                    <div class="details-header-buy" :style="'width:'+(isMaster?'150px;':'280px;')">
                        <div style="color: red;font-size: 18px;font-weight: 600;">￥{{idleItemInfo.idlePrice}}</div>
                        <div v-if="!isMaster&&idleItemInfo.idleStatus!==1" style="color: red;font-size: 16px;">闲置已下架或删除</div>
                        <el-button v-if="!isMaster&&idleItemInfo.idleStatus===1" type="danger" plain @click="buyButton(idleItemInfo)">立即购买</el-button>
                        <el-button v-if="!isMaster&&idleItemInfo.idleStatus===1" type="primary" plain @click="favoriteButton(idleItemInfo)">{{isFavorite?'取消收藏':'收藏'}}</el-button>
                        <el-button v-if="isMaster&&idleItemInfo.idleStatus===1" type="danger" @click="changeStatus(idleItemInfo,2)" plain>下架</el-button>
                        <el-button v-if="isMaster&&idleItemInfo.idleStatus===2" type="primary" @click="changeStatus(idleItemInfo,1)" plain>重新上架</el-button>
                    </div>
                </div>

                <div class="details-info">
                    <div class="details-info-title">{{idleItemInfo.idleName}}</div>
                    <div class="details-info-main" v-html="idleItemInfo.idleDetails">
                        {{idleItemInfo.idleDetails}}
                    </div>
                    <div class="details-picture">
                        <el-image v-for="(imgUrl,i) in idleItemInfo.pictureList"
                                  style="width: 90%;margin-bottom: 2px;"
                                  :src="imgUrl"
                                  fit="contain"></el-image>
                    </div>
                </div>

                <div class="message-container" id="replyMessageLocation">
                    <div class="message-title">全部留言</div>
                    <div class="message-send">
                        <div v-if="isReply" style="padding-bottom: 10px;">
                            <el-button type="info" @click="cancelReply">回复：{{replyData.toMessage}} @{{replyData.toUserNickname}} <i class="el-icon-close el-icon--right"></i></el-button>
                        </div>
                        <el-input
                                type="textarea"
                                autosize
                                placeholder="留言提问..."
                                v-model="messageContent"
                                maxlength="200"
                                show-word-limit>
                        </el-input>
                        <div class="message-send-button">
                            <el-button plain @click="sendMessage">发送留言</el-button>
                        </div>
                    </div>
                    <div>
                        <div v-for="(mes,index) in messageList" class="message-container-list">
                            <div class="message-container-list-left">
                                <el-image
                                        style="width: 55px; height: 55px;border-radius: 5px;"
                                        :src="mes.fromU.avatar"
                                        fit="contain"></el-image>
                                <div class="message-container-list-text">
                                    <div class="message-nickname">{{mes.fromU.nickname}}
                                        {{mes.toU.nickname?' @'+mes.toU.nickname+'：'+
                                        mes.toM.content.substring(0,10)+
                                        (mes.toM.content.length>10?'...':''):''}}</div>
                                    <div class="message-content" v-html="mes.content">{{mes.content}}</div>
                                    <div class="message-time">{{mes.createTime}}</div>
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
                messageContent:'',
                toUser:null,
                toMessage:null,
                isReply:false,
                replyData:{
                    toUserNickname:'',
                    toMessage:''
                },
                messageList:[],
                idleItemInfo:{
                    id:'',
                    idleName:'',
                    idleDetails:'',
                    pictureList:[],
                    idlePrice:0,
                    idlePlace:'',
                    idleLabel:'',
                    idleStatus:-1,
                    userId:'',
                    user:{
                        avatar:'',
                        nickname:'',
                        signInTime:''
                    },
                },
                isMaster:false,
                isFavorite:true,
                favoriteId:0
            };
        },
        created(){
            let id=this.$route.query.id;
            this.$api.getIdleItem({
                id:id
            }).then(res=>{
                console.log(res);
                if(res.data){
                    let list=res.data.idleDetails.split(/\r?\n/);
                    let str='';
                    for(let i=0;i<list.length;i++){
                        str+='<p>'+list[i]+'</p>';
                    }
                    res.data.idleDetails=str;
                    res.data.pictureList=JSON.parse(res.data.pictureList);
                    this.idleItemInfo=res.data;
                    console.log(this.idleItemInfo);
                    let userId=this.getCookie('shUserId');
                    console.log('userid',userId)
                    if(userId == this.idleItemInfo.userId){
                        console.log('isMaster');
                        this.isMaster=true;
                    }
                    this.checkFavorite();
                    this.getAllIdleMessage();
                }
                $('html,body').animate({
                    scrollTop: 0
                }, {duration: 500, easing: "swing"});
            });
        },
        methods: {
            getAllIdleMessage(){
                this.$api.getAllIdleMessage({
                    idleId:this.idleItemInfo.id
                }).then(res=>{
                    console.log('getAllIdleMessage',res.data);
                    if(res.status_code===1){
                        this.messageList=res.data;
                    }
                }).catch(()=>{
                })
            },
            checkFavorite(){
                this.$api.checkFavorite({
                    idleId:this.idleItemInfo.id
                }).then(res=>{
                    if(!res.data){
                        this.isFavorite=false;
                    }else {
                        this.favoriteId=res.data;
                    }
                })
            },
            getCookie(cname){
                var name = cname + "=";
                var ca = document.cookie.split(';');
                for(var i=0; i<ca.length; i++)
                {
                    var c = ca[i].trim();
                    if (c.indexOf(name)===0) return c.substring(name.length,c.length);
                }
                return "";
            },
            replyMessage(index){
                $('html,body').animate({
                    scrollTop: $("#replyMessageLocation").offset().top-600
                }, {duration: 500, easing: "swing"});
                this.isReply=true;
                this.replyData.toUserNickname=this.messageList[index].fromU.nickname;
                this.replyData.toMessage=this.messageList[index].content.substring(0,10)+(this.messageList[index].content.length>10?'...':'');
                this.toUser=this.messageList[index].userId;
                this.toMessage=this.messageList[index].id;
            },
            changeStatus(idle,status){
                this.$api.updateIdleItem({
                    id:idle.id,
                    idleStatus:status
                }).then(res=>{
                    console.log(res);
                    if(res.status_code===1){
                        this.idleItemInfo.idleStatus=status;
                    }else {
                        this.$message.error(res.msg)
                    }
                });
            },
            buyButton(idleItemInfo){
                this.$api.addOrder({
                    idleId:idleItemInfo.id,
                    orderPrice:idleItemInfo.idlePrice,
                }).then(res=>{
                    console.log(res);
                    if(res.status_code===1){
                        this.$router.push({path: '/order', query: {id: res.data.id}});
                    }else {
                        this.$message.error(res.msg)
                    }
                }).catch(e=>{

                })
            },
            favoriteButton(idleItemInfo){
                if(this.isFavorite){
                    this.$api.deleteFavorite({
                        id: this.favoriteId
                    }).then(res=>{
                        console.log(res);
                        if(res.status_code===1){
                            this.$message({
                                message: '已取消收藏！',
                                type: 'success'
                            });
                            this.isFavorite=false;
                        }else {
                            this.$message.error(res.msg)
                        }
                    }).catch(e=>{
                    })
                }else {
                    this.$api.addFavorite({
                        idleId:idleItemInfo.id
                    }).then(res=>{
                        console.log(res);
                        if(res.status_code===1){
                            this.$message({
                                message: '已收藏！',
                                type: 'success'
                            });
                            this.isFavorite=true;
                            this.favoriteId=res.data;
                        }else {
                            this.$message.error(res.msg)
                        }
                    }).catch(e=>{
                    })
                }
            },
            cancelReply(){
                this.isReply=false;
                this.toUser=this.idleItemInfo.userId;
                this.toMessage=null;
                this.replyData.toUserNickname='';
                this.replyData.toMessage='';
            },
            sendMessage(){
                let content=this.messageContent.trim();
                if(this.toUser==null){
                    this.toUser=this.idleItemInfo.userId;
                }
                if(content){
                    let contentList=content.split(/\r?\n/);
                    let contenHtml=contentList[0];
                    for(let i=1;i<contentList.length;i++){
                        contenHtml+='<br>'+contentList[i];
                    }
                    this.$api.sendMessage({
                        idleId:this.idleItemInfo.id,
                        content:contenHtml,
                        toUser:this.toUser,
                        toMessage:this.toMessage
                    }).then(res=>{
                        if(res.status_code===1){
                            this.$message({
                                message: '留言成功！',
                                type: 'success'
                            });
                            this.messageContent='';
                            this.cancelReply();
                            this.getAllIdleMessage();
                        }else {
                            this.$message.error("留言失败！"+res.msg);
                        }
                    }).catch(()=>{
                        this.$message.error("留言失败！");
                    });

                }else{
                    this.$message.error("留言为空！");
                }
            }
        },
    }
</script>

<style scoped>
    .idle-details-container {
        min-height: 85vh;
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
        padding: 20px 50px;
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