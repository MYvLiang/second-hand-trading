<template>
    <div>
        <app-head></app-head>
        <app-body>
            <div class="order-page-container">
                <div class="idle-info-container" @click="toDetails(orderInfo.idleItem.id)">
                    <el-image
                            style="width: 150px; height: 150px;"
                            :src="orderInfo.idleItem.imgUrl"
                            fit="cover"></el-image>
                    <div class="idle-info-title">{{orderInfo.idleItem.idleName}}</div>
                    <div class="idle-info-price">￥{{orderInfo.orderPrice}}</div>
                </div>
                <div class="address-container" @click.stop="selectAddressDialog">
                    <div class="address-title">收货地址: {{addressInfo.consigneeName}} {{addressInfo.consigneePhone}}</div>
                    <div class="address-detials">{{addressInfo.detailAddress}}</div>
                    <el-button v-if="!addressInfo.detailAddress" @click.stop="selectAddressDialog" type="primary" plain>选择收货地址</el-button>
                </div>
                <el-dialog
                        title="选择地址"
                        :visible.sync="addressDialogVisible"
                        width="60%">
                    <el-table
                            stripe
                            empty-text="无地址信息，请先在个人中心添加地址"
                            :data="addressData"
                            style="width: 100%">
                        <el-table-column
                                prop="consigneeName"
                                label="收货人姓名"
                                width="120">
                        </el-table-column>
                        <el-table-column
                                prop="consigneePhone"
                                label="手机号"
                                width="140">
                        </el-table-column>
                        <el-table-column
                                prop="detailAddressText"
                                label="地址">
                        </el-table-column>
                        <el-table-column label=" " width="120">
                            <template slot-scope="scope">
                                <el-button
                                        size="mini"
                                        @click="selectAddress(scope.$index, scope.row)">选择
                                </el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-dialog>
                <div class="order-info-container">
                    <div class="order-info-title">订单信息（{{orderStatus[orderInfo.orderStatus]}}）：</div>
                    <div class="order-info-item">编号：{{orderInfo.orderNumber}}</div>
                    <div class="order-info-item">支付状态：{{orderInfo.paymentStatus===0?'未支付':'已支付'}}</div>
                    <div class="order-info-item">支付方式：{{orderInfo.paymentWay}}</div>
                    <div class="order-info-item">创建时间：{{orderInfo.createTime.substring(0, 10) + ' ' +
                        orderInfo.createTime.substring(11, 19)}}
                    </div>
                    <div class="order-info-item">支付时间：{{orderInfo.paymentTime?orderInfo.paymentTime.substring(0, 10) + ' ' +
                        orderInfo.paymentTime.substring(11, 19):''}}
                    </div>
                </div>
                <div class="menu">
                    <el-button type="danger" plain @click="changeOrderStatus(4,orderInfo)">取消订单</el-button>
                    <el-button type="primary" plain @click="changeOrderStatus(1,orderInfo)">立即支付</el-button>
                    <el-button type="primary" plain @click="changeOrderStatus(2,orderInfo)">确认发货</el-button>
                    <el-button type="primary" plain @click="changeOrderStatus(3,orderInfo)">确认收货</el-button>
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
        data() {
            return {
                addressDialogVisible:false,
                addressData: [],
                orderStatus: ['待付款', '待发货', '待收货', '已完成', '已取消'],
                orderInfo: {
                    createTime: "",
                    id: 0,
                    idleId: 0,
                    idleItem: {
                        id: '',
                        idleName: '',
                        idleDetails: '',
                        pictureList: [],
                        idlePrice: 0,
                        idlePlace: '',
                        idleLabel: '',
                        idleStatus: -1,
                        userId: '',
                    },
                    orderNumber: "",
                    orderPrice: 0,
                    orderStatus: 0,
                    paymentStatus: 0,
                    paymentTime: "",
                    paymentWay: "",
                    userId: 1
                },
                addressInfo: {
                    update:false,
                    consigneeName: '',
                    consigneePhone: '',
                    detailAddress: ''
                }
            };
        },
        created() {
            let orderId = this.$route.query.id;
            console.log(orderId);
            this.$api.getOrder({
                id: orderId
            }).then(res => {
                console.log(res);
                if (res.status_code === 1) {
                    if (res.data.idleItem) {
                        let imgList = JSON.parse(res.data.idleItem.pictureList);
                        if (imgList.length > 0) {
                            res.data.idleItem.imgUrl = imgList[0];
                        } else {
                            res.data.idleItem.imgUrl = '';
                        }
                    } else {
                        res.data.idleItem = {
                            idleName: '',
                            imgUrl: ''
                        }
                    }
                    this.orderInfo = res.data;
                }
            })
        },
        methods: {
            toDetails(id) {
                this.$router.replace({path: 'details', query: {id: id}});
            },
            selectAddressDialog(){
                if(this.orderInfo.orderStatus===0){
                    this.addressDialogVisible=true;
                    if(this.addressData.length===0){
                        this.$api.getAddress().then(res => {
                            if (res.status_code === 1) {
                                let data = res.data;
                                for (let i = 0; i < data.length; i++) {
                                    data[i].detailAddressText = data[i].provinceName + data[i].cityName + data[i].regionName + data[i].detailAddress;
                                    data[i].defaultAddress = data[i].defaultFlag ? '默认地址' : '设为默认';
                                }
                                console.log(data);
                                this.addressData = data;
                            }
                        })
                    }
                }
            },
            selectAddress(i,item){
                this.addressDialogVisible=false;
                console.log(item);
                this.addressInfo={
                    update:true,
                    consigneeName: item.consigneeName,
                    consigneePhone: item.consigneePhone,
                    detailAddress: item.detailAddressText
                };
            },
            changeOrderStatus(orderStatus, orderInfo) {
                if (orderStatus === 1) {
                    console.log('zhifu');
                    this.$confirm('模拟支付宝支付，是否确认支付', '支付订单', {
                        confirmButtonText: '支付',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        this.$api.updateOrder({
                            id: orderInfo.id,
                            orderStatus: orderStatus,
                            paymentStatus: 1,
                            paymentWay: '支付宝',
                        }).then(res => {
                            if (res.status_code === 1) {
                                this.$message({
                                    message: '支付成功！',
                                    type: 'success'
                                });
                                this.orderInfo.orderStatus = orderStatus;
                                this.orderInfo.paymentStatus = 1;
                                this.orderInfo.paymentWay = '支付宝';
                                this.orderInfo.paymentTime = res.data.paymentTime;
                            }
                        })
                    }).catch(() => {
                    });
                } else {
                    this.$api.updateOrder({
                        id: orderInfo.id,
                        orderStatus: orderStatus,
                    }).then(res => {
                        if (res.status_code === 1) {
                            this.$message({
                                message: '操作成功！',
                                type: 'success'
                            });
                            this.orderInfo.orderStatus = orderStatus;
                        }
                    })
                }
            },
        }

    }
</script>

<style scoped>
    .order-page-container {
        min-height: 80vh;
    }

    .idle-info-container {
        width: 100%;
        display: flex;
        border-bottom: 20px solid #f6f6f6;
        padding: 20px;
        cursor: pointer;
    }

    .idle-info-title {
        font-size: 18px;
        font-weight: 600;
        max-width: 750px;
        margin-left: 10px;
    }

    .idle-info-price {
        font-size: 18px;
        color: red;
        margin-left: 10px;
    }

    .address-container {
        min-height: 60px;
        padding: 20px;
        border-bottom: 20px solid #f6f6f6;
        cursor: pointer;
    }

    .address-title {
        font-size: 18px;
        font-weight: 600;
        margin-bottom: 10px;
    }

    .address-detials {
        font-size: 16px;
        color: #444444;
    }

    .order-info-container {
        padding: 20px;
    }

    .order-info-item {
        margin: 10px 0;
        font-size: 14px;
        color: #444444;
    }

    .menu {
        margin-left: 20px;
    }
</style>