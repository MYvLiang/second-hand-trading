<template>
    <div class="main-border">
        <el-menu default-active="1" class="el-menu-demo" mode="horizontal" @select="handleSelect">
            <el-menu-item index="1">上线的闲置</el-menu-item>
            <el-menu-item index="2">下架的闲置</el-menu-item>
        </el-menu>
        <el-table v-if="this.mode == 1"
                :data="onlineGoods"
                stripe
                style="width: 100%;color: #5a5c61;">
            <el-table-column
                    prop="releaseTime"
                    label="发布日期"
                    width="200">
            </el-table-column>
            <el-table-column
                    prop="idleName"
                    label="闲置名称"
                    show-overflow-tooltip
                    >
            </el-table-column>
            <el-table-column
                    prop="user.nickname"
                    label="发布用户"
                    show-overflow-tooltip
                    min-width="100"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="idlePrice"
                    label="价格"
                    show-overflow-tooltip
                    min-width="100"
                    width="100">
            </el-table-column>
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button
                            size="mini"
                            type="danger"
                            @click="makeOfflineGoods(scope.$index)">违规下架</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-table v-show="this.mode == 2"
                  :data="OfflineGoods"
                  stripe
                  style="width: 100%;color: #5a5c61;">
            <el-table-column
                    prop="releaseTime"
                    label="发布日期"
                    width="200">
            </el-table-column>
            <el-table-column
                    prop="idleName"
                    label="闲置名称"
                    show-overflow-tooltip
            >
            </el-table-column>
            <el-table-column
                    prop="user.nickname"
                    label="发布用户"
                    show-overflow-tooltip
                    min-width="100"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="idlePrice"
                    label="价格"
                    show-overflow-tooltip
                    min-width="100"
                    width="100">
            </el-table-column>
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button
                            size="mini"
                            type="danger"
                            @click="deleteGoods(scope.$index)">永久删除</el-button>
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
        name: "IdleGoods",
        data(){
            return {
                mode:1,
                nowPage: 1,
                total: 0,
                onlineGoods: [],
                OfflineGoods:[],
            }
        },
        created() {
            this.getOnlineGoods();
        },
        methods: {
            handleCurrentChange(val) {
                this.nowPage = val;
                if(this.mode == 1){
                    this.getOnlineGoods();
                }
                if(this.mode == 2){
                    this.getOfflineGoods();
                }
            },
            handleSelect(val){
                if(this.mode !== val){
                    this.mode = val;
                    if(val == 1){
                        this.nowPage = 1;
                        this.getOnlineGoods();
                    }
                    if(val == 2){
                        this.nowPage = 1;
                        this.getOfflineGoods();
                    }
                }
            },
            makeOfflineGoods(i){
                this.$api.updateGoods({
                    id: this.onlineGoods[i].id,
                    status:2
                }).then(res => {
                    if(res.status_code==1){
                        this.getOnlineGoods();
                    }else {
                        this.$message.error(res.msg)
                    }
                }).catch(e => {
                    console.log(e)
                })
            },
            deleteGoods(i){
                this.$api.updateGoods({
                    id: this.OfflineGoods[i].id,
                    status:0
                }).then(res => {
                    if(res.status_code==1){
                        this.getOfflineGoods();
                    }else {
                        this.$message.error(res.msg)
                    }

                }).catch(e => {
                    console.log(e)
                })
            },
            getOnlineGoods(){
                this.$api.getGoods({
                    status:1,
                    page: this.nowPage,
                    nums:8
                }).then(res => {
                    if(res.status_code==1){
                        this.onlineGoods = res.data.list;
                        this.total = res.data.count;
                    }else {
                        this.$message.error(res.msg)
                    }
                }).catch(e => {
                    console.log(e)
                })
            },
            getOfflineGoods(){
                this.$api.getGoods({
                    status:2,
                    page: this.nowPage,
                    nums:8
                }).then(res => {
                    if(res.status_code==1){
                        this.OfflineGoods = res.data.list;
                        this.total = res.data.count;
                    }else {
                        this.$message.error(res.msg)
                    }
                }).catch(e => {
                    console.log(e)
                })
            }
        }

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