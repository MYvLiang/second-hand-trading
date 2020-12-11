<template>
    <div>
        <app-head></app-head>
        <app-body>
            <el-tabs v-model="labelName" type="card" @tab-click="handleClick">
                <el-tab-pane label="全部" name="0"></el-tab-pane>
                <el-tab-pane label="类别1" name="1"></el-tab-pane>
                <el-tab-pane label="类别2" name="2"></el-tab-pane>
                <el-tab-pane label="类别3" name="3"></el-tab-pane>
            </el-tabs>
            <div style="margin: 0 20px;">
                <el-row :gutter="30">
                    <el-col :span="6" v-for="(idle,index) in idleList">
                        <div class="idle-card" @click="toDetails">
                            <el-image
                                    style="width: 100%; height: 160px"
                                    src="https://g-search3.alicdn.com/img/bao/uploaded/i4/i1/772352677/O1CN011jxMrP1Ve6uWORNF2_!!0-item_pic.jpg_580x580Q90.jpg_.webp"
                                    fit="contain"></el-image>
                            <div class="idle-title">
                                {{index}}{{idle}}dfsfasdfasdgfdasgsdfhsghfsdh dfsgdfgsdfgsdfgsdf
                            </div>
                            <el-row style="margin: 5px 10px;">
                                <el-col :span="12">
                                    <div class="idle-prive">￥50</div>
                                </el-col>
                                <el-col :span="12">
                                    <div class="idle-place">佛山</div>
                                </el-col>
                            </el-row>
                            <div class="idle-time">2020-10-20</div>
                            <div class="user-info">
                                <el-image
                                        style="width: 30px; height: 30px"
                                        src="https://pic2.zhimg.com/v2-22d4ebddd475020919bb12aa3a6ddaf7_xs.jpg?source=1940ef5c"
                                        fit="contain"></el-image>
                                <div class="user-nickname">shiny</div>
                            </div>
                        </div>
                    </el-col>
                </el-row>
            </div>
            <div class="fenye">
                <el-pagination
                        background
                        @current-change="handleCurrentChange"
                        :current-page.sync="currentPage"
                        :page-size="40"
                        layout="prev, pager, next, jumper"
                        :total="1000">
                </el-pagination>
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
        name: "index",
        components: {
            AppHead,
            AppBody,
            AppFoot
        },
        data() {
            return {
                labelName: '0',
                idleList: [1, 2, 3, 4, 5, 6, 8, 9, 9, 8, 5, 5],
                currentPage: 1
            };
        },
        methods: {
            handleClick(tab, event) {
                console.log(tab, event);
                console.log(this.labelName)
            },
            handleCurrentChange(val) {
                console.log(`当前页: ${val}`);
            },
            toDetails(){
                this.$router.push({path: '/details'});
            }
        }
    }
</script>

<style scoped>
    .idle-card {
        height: 300px;
        border: #eeeeee solid 1px;
        margin-bottom: 15px;
        cursor:pointer;
    }

    .fenye {
        display: flex;
        justify-content: center;
        height: 80px;
        align-items: center;
    }

    .idle-title {
        font-size: 18px;
        font-weight: 600;
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
        margin: 10px;
    }

    .idle-prive {
        font-size: 16px;
        color: red;
    }

    .idle-place {
        font-size: 13px;
        color: #666666;
        float: right;
        padding-right: 20px;

    }

    .idle-time {
        color: #666666;
        font-size: 12px;
        margin: 0 10px;
    }

    .user-nickname {
        color: #999999;
        font-size: 12px;
        display: flex;
        align-items: center;
        height: 30px;
        padding-left: 10px;
    }

    .user-info {
        padding: 5px 10px;
        height: 30px;
        display: flex;
    }
</style>