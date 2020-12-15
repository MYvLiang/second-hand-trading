<template>
    <div>
        <app-head></app-head>
        <app-body>
            <div class="order-page-container">
                <div class="idle-info-container">
                    <el-image
                            style="width: 150px; height: 150px;"
                            src="https://pic2.zhimg.com/v2-22d4ebddd475020919bb12aa3a6ddaf7_xs.jpg?source=1940ef5c"
                            fit="cover"></el-image>
                    <div class="idle-info-title">标题标题标题题标标题标题标题标题标题标标题标题</div>
                    <div class="idle-info-price">￥50</div>
                </div>
                <div class="address-container">
                    <div class="address-title">收货地址: shiny 135634354654</div>
                    <div class="address-detials">广东省佛山市禅城区江湾一路18号</div>
                </div>
                <div class="order-info-container">
                    <div class="order-info-title">订单信息（待付款|待发货|待收货|已完成）：</div>
                    <div class="order-info-item">编号：1464654564564567</div>
                    <div class="order-info-item">支付状态：已支付</div>
                    <div class="order-info-item">支付方式：支付宝</div>
                    <div class="order-info-item">创建时间：2020-10-10 20:20:20</div>
                    <div class="order-info-item">支付时间：2020-10-11 20:20:20</div>
                </div>
                <div class="menu">
                    <el-button type="danger" plain="">取消订单</el-button>
                    <el-button type="primary" plain>立即支付</el-button>
                    <el-button type="primary" plain>确认发货</el-button>
                    <el-button type="primary" plain>确认收货</el-button>
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
        name: "order",
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

        }

    }
</script>

<style scoped>
    .order-page-container{
        min-height: 80vh;
    }
    .idle-info-container{
        width: 100%;
        display: flex;
        border-bottom: 20px solid #f6f6f6;
        padding: 20px;
    }
    .idle-info-title{
        font-size: 18px;
        font-weight: 600;
        max-width: 750px;
        margin-left: 10px;
    }
    .idle-info-price{
        font-size: 18px;
        color: red;
        margin-left: 10px;
    }
    .address-container{
        min-height: 60px;
        padding: 20px;
        border-bottom: 20px solid #f6f6f6;
    }
    .address-title{
        font-size: 18px;
        font-weight: 600;
        margin-bottom: 10px;
    }
    .address-detials{
        font-size: 16px;
        color: #444444;
    }
    .order-info-container{
        padding: 20px;
    }
    .order-info-item{
        margin: 10px 0;
        font-size: 14px;
        color: #444444;
    }
    .menu{
        margin-left: 20px;
    }
</style>