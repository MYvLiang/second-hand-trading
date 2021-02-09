<template>
    <div class="main-border">
        <el-table
                :data="Order"
                stripe
                style="width: 100%;color: #5a5c61;">
            <el-table-column
                    prop="orderNumber"
                    label="订单号"
                    show-overflow-tooltip
                    width="200">
            </el-table-column>
            <el-table-column
                    prop="idleItem.idleName"
                    label="闲置名称"
                    show-overflow-tooltip
                    >
            </el-table-column>
            <el-table-column
                    prop="orderPrice"
                    label="金额"
                    show-overflow-tooltip
                    min-width="100"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="createTime"
                    label="创建时间"
                    show-overflow-tooltip
                    min-width="150"
                    width="200">
            </el-table-column>
            <el-table-column
                    label="订单状态"
                    width="100"
                    show-overflow-tooltip>
                <template slot-scope="scope">
                    <div>{{orderStatus[scope.row.orderStatus]}}</div>
                </template>
            </el-table-column>
            <el-table-column
                    label="支付状态"
                    width="100"
                    show-overflow-tooltip
                    class="cell el-tooltip">
                <template slot-scope="scope">
                    <div>{{paymentStatus[scope.row.paymentStatus]}}</div>
                </template>
            </el-table-column>
            <el-table-column
                    prop="paymentWay"
                    label="支付方式"
                    width="100"
                    show-overflow-tooltip>
            </el-table-column>
            <el-table-column
                    prop="paymentTime"
                    label="支付时间"
                    show-overflow-tooltip>
            </el-table-column>
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button
                            size="mini"
                            type="danger"
                            @click="deleteOrder(scope.$index)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <div class="block">
            <el-pagination
                    @current-change="handleCurrentChange"
                    :current-page.sync="nowPage"
                    :page-size="8"
                    background
                    layout="prev, pager, next,jumper"
                    :total="total">
            </el-pagination>
        </div>
    </div>
</template>

<script>
    export default {
        name: "orderList",
        created() {
            this.getOrder();
        },
        methods:{
            getOrder(){
                this.$api.getOrderList({
                    page: this.nowPage,
                    nums:8
                }).then(res => {
                    if(res.status_code==1){
                        this.Order = res.data.list;
                        this.total = res.data.count;
                    }else {
                        this.$message.error(res.msg)
                    }

                }).catch(e => {
                    console.log(e)
                })
            },
            deleteOrder(index){
                this.$api.deleteOrder({
                    id:this.Order[index].id
                }).then(res=>{
                    if(res.status_code==1){
                        this.getOrder();
                    }else {
                        this.$message.error(res.msg)
                    }

                }).catch(e => {
                    console.log(e)
                })
            },
            handleCurrentChange(val) {
                this.nowPage = val;
                this.getOrder();
            },
        },
        data(){
            return {
                mode:1,
                nowPage: 1,
                total: 0,
                paymentStatus:['未支付','已支付'],
                orderStatus:['待付款','待发货','待收货','已完成','已取消'],
                Order: []
            }
        },
    }
</script>

<style scoped>
    .main-border{
        background-color: #FFF;
        padding: 10px 30px;
        box-shadow: 0 1px 15px -6px rgba(0,0,0,.5);
        border-radius: 5px;
    }
    .block {
        display: flex;
        justify-content:center;
        padding-top: 15px;
        padding-bottom: 10px;
        width: 100%;
    }
</style>