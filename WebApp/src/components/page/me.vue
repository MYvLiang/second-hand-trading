<template>
    <div>
        <v-head></v-head>
        <v-body>
            <div v-show="!eidtAddress">
                <div class="user-info-container">
                    <div class="user-info-details">
                        <el-image
                                style="width: 120px; height: 120px;border-radius: 10px;"
                                src="https://pic2.zhimg.com/v2-22d4ebddd475020919bb12aa3a6ddaf7_xs.jpg?source=1940ef5c"
                                fit="contain"></el-image>
                        <div class="user-info-details-text">
                            <div class="user-info-details-text-nickname">{{userNickname}}</div>
                            <div class="user-info-details-text-time">2020-10-10加入</div>
                            <div class="user-info-details-text-edit">
                                <el-button type="primary" plain @click="userInfoDialogVisible = true">编辑个人信息</el-button>
                            </div>
                            <el-dialog
                                    @close="finishEdit"
                                    title="编辑个人信息"
                                    :visible.sync="userInfoDialogVisible"
                                    width="400px">
                                <div class="edit-tip">昵称</div>
                                <el-input
                                        v-model="userNickname"
                                        :disabled="notUserNicknameEdit"
                                        @blur="saveUserNickname">
                                    <el-button slot="append" type="warning" icon="el-icon-edit"
                                               @click="notUserNicknameEdit = false">编辑
                                    </el-button>
                                </el-input>

                                <div v-if="userPasswordEdit">
                                    <div class="edit-tip">原密码</div>
                                    <el-input v-model="userPassword1" show-password></el-input>
                                    <div class="edit-tip">新密码</div>
                                    <el-input v-model="userPassword2" show-password></el-input>
                                    <div class="edit-tip">确认新密码</div>
                                    <el-input v-model="userPassword3" show-password></el-input>
                                    <div class="edit-tip"></div>
                                    <el-button @click="savePassword" plain>确认修改</el-button>
                                </div>
                                <div v-else>
                                    <div class="edit-tip">密码</div>
                                    <el-input
                                            value="123456"
                                            :disabled="true"
                                            show-password>
                                        <el-button slot="append" type="warning" icon="el-icon-edit"
                                                   @click="userPasswordEdit = true">编辑
                                        </el-button>
                                    </el-input>
                                </div>
                                <span slot="footer" class="dialog-footer">
                                <el-button @click="userInfoDialogVisible=false">完成</el-button>
                            </span>
                            </el-dialog>
                        </div>
                    </div>
                    <div class="user-info-splace">
                        <el-button type="primary" plain @click="eidtAddress=true">编辑收货地址</el-button>
                    </div>
                </div>
                <div class="idle-container">
                    <el-tabs v-model="activeName" @tab-click="handleClick">
                        <el-tab-pane label="我发布的" name="1"></el-tab-pane>
                        <el-tab-pane label="我下架的" name="2"></el-tab-pane>
                        <el-tab-pane label="我收藏的" name="3"></el-tab-pane>
                        <el-tab-pane label="我卖出的" name="4"></el-tab-pane>
                        <el-tab-pane label="我买到的" name="5"></el-tab-pane>
                    </el-tabs>
                    <div class="idle-container-list">
                        <div v-for="(item,index) in dataList[activeName]" class="idle-container-list-item">
                            <div class="idle-container-list-item-detile">
                                <el-image
                                        style="width: 100px; height: 100px;"
                                        src="https://pic2.zhimg.com/v2-22d4ebddd475020919bb12aa3a6ddaf7_xs.jpg?source=1940ef5c"
                                        fit="contain"></el-image>
                                <div class="idle-container-list-item-text">
                                    <div class="idle-container-list-title">
                                        {{index}}{{item}}称名称名称名称名称名称名称名称名称名称名称名称名称名称名称名称名称名称名称名称名称名称名称名称名
                                    </div>
                                    <div class="idle-container-list-idle-details">
                                        详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情
                                    </div>
                                    <div class="idle-container-list-idle-time">2020-10-10</div>
                                    <div class="idle-prive">￥50 {{activeName==='5'?orderStatus:''}}</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div v-show="eidtAddress" class="address-container">
                <el-page-header class="address-container-back" @back="eidtAddress=false"
                                content="收货地址"></el-page-header>
                <div class="address-container-add">
                    <div class="address-container-add-title">新增收货地址</div>
                    <div class="address-container-add-item">
                        <el-input placeholder="请输入收货人姓名" v-model="input1">
                            <div slot="prepend">收货人姓名</div>
                        </el-input>
                    </div>
                    <div class="address-container-add-item">
                        <el-input placeholder="请输入收货人手机号" v-model="input1">
                            <div slot="prepend">手机号</div>
                        </el-input>
                    </div>

                    <div class="address-container-add-item">
                        <span class="demonstration">省/市/区</span>
                        <el-cascader
                                :options="options"
                                v-model="selectedOptions"
                                @change="handleChange"
                                :separator="' '"
                        >
                        </el-cascader>
                    </div>
                    <div class="address-container-add-item">
                        <el-input placeholder="请输入详细地址（如道路、门牌号、小区、楼栋号等信息）" v-model="input1">
                            <div slot="prepend">详细地址</div>
                        </el-input>
                    </div>
                    <el-checkbox v-model="input1">设置为默认地址</el-checkbox>
                    <el-button style="margin-left: 20px;">保存</el-button>
                </div>
                <div class="address-container-list">
                    <div style="color: #409EFF;font-size: 15px;padding-left: 10px;">已有收货地址</div>
                    <el-table
                            stripe
                            :data="tableData"
                            style="width: 100%">
                        <el-table-column
                                prop="name"
                                label="收货人姓名"
                                width="100">
                        </el-table-column>
                        <el-table-column
                                prop="phone"
                                label="手机号"
                                width="120">
                        </el-table-column>
                        <el-table-column
                                prop="address"
                                label="地址"
                                width="270">
                        </el-table-column>
                        <el-table-column label="操作">
                            <template slot-scope="scope">
                                <el-button
                                        size="mini"
                                        @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                                <el-button
                                        size="mini"
                                        type="danger"
                                        @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                            </template>
                        </el-table-column>
                        <el-table-column
                                prop="name"
                                label=" "
                                width="110">
                        </el-table-column>
                    </el-table>
                </div>
            </div>
            <v-foot></v-foot>
        </v-body>
    </div>
</template>

<script>
    import vHead from '../common/Header.vue';
    import vBody from '../common/PageBody.vue'
    import vFoot from '../common/Foot.vue'
    import options from '../common/country-data.js'

    export default {
        name: "me",
        components: {
            vHead,
            vBody,
            vFoot
        },
        data() {
            return {
                activeName: '1',
                tableData: [
                    {
                        name: '王小虎',
                        phone:12345678901,
                        address: '上海市普陀区金沙江路上海市普陀区金沙江路 1518 弄'
                    }, {
                        name: '王小虎',
                        phone:12345678901,
                        address: '上海市普陀区金沙江路 1517 弄'
                    }, {
                        name: '王小虎',
                        phone:12345678901,
                        address: '上海市普陀区金沙江路 1519 弄'
                    }, {
                        name: '王小虎',
                        phone:12345678901,
                        address: '上海市普陀区金沙江路 1516 弄'
                    }
                ],
                dataList: [
                    [],
                    [1, 2, 3, 4, 5, 6],
                    [2, 4, 5, 6],
                    [2, 4, 5],
                    [2],
                    [5, 6],
                    [],
                    [],
                ],
                orderStatus: '已支付',
                userInfoDialogVisible: false,
                userNickname: 'SHINY',
                notUserNicknameEdit: true,
                userPasswordEdit: false,
                userPassword1: '',
                userPassword2: '',
                userPassword3: '',
                eidtAddress: false,
                input1: '',
                selectedOptions: [],//存放默认值
                options: options   //存放城市数据
            };
        },
        methods: {
            handleClick(tab, event) {
                // console.log(tab, event);
                console.log(this.activeName);
            },
            saveUserNickname() {
                this.notUserNicknameEdit = true;

            },
            savePassword() {
                this.userPasswordEdit = false;
                console.log(this.userPassword1)
            },
            finishEdit() {
                this.notUserNicknameEdit = true;
                this.userInfoDialogVisible = false;
                this.userPasswordEdit = false;
            },
            handleChange(value) {
                console.log(value);
            },
            handleEdit(index, row) {
                console.log(index, row);
            },
            handleDelete(index, row) {
                console.log(index, row);
            }
        }
    }
</script>

<style scoped>
    .user-info-container {
        width: 100%;
        height: 200px;
        border-bottom: 15px solid #f6f6f6;
        display: flex;
        align-items: center;
        justify-content: space-between;
    }

    .user-info-details {
        display: flex;
        height: 140px;
        align-items: center;
        margin: 20px 40px;
    }

    .user-info-details-text {
        margin-left: 20px;
    }

    .user-info-details-text-nickname {
        font-size: 26px;
        font-weight: 600;
        margin: 10px 0;
    }

    .user-info-details-text-time {
        font-size: 14px;
        margin-bottom: 10px;
    }

    .user-info-splace {
        margin-right: 90px;
    }

    .idle-container {
        padding: 0 20px;
    }

    .idle-container-list {
        min-height: 45vh;
    }

    .idle-container-list-item {
        border-bottom: 1px solid #eeeeee;
    }

    .idle-container-list-item:last-child {
        border-bottom: none;
    }

    .idle-container-list-item-detile {
        height: 120px;
        display: flex;
        align-items: center;
    }

    .idle-container-list-item-text {
        margin-left: 10px;
        height: 100px;
        max-width: 800px;
    }

    .idle-container-list-title {
        font-weight: 600;
        font-size: 18px;
        overflow: hidden;
        display: -webkit-box;
        -webkit-box-orient: vertical;
        -webkit-line-clamp: 1;
    }

    .idle-container-list-idle-details {
        font-size: 14px;
        color: #555555;
        padding-top: 5px;
        overflow: hidden;
        display: -webkit-box;
        -webkit-box-orient: vertical;
        -webkit-line-clamp: 1;
    }

    .idle-container-list-idle-time {
        font-size: 13px;
        padding-top: 5px;
    }

    .idle-prive {
        font-size: 15px;
        padding-top: 5px;
        color: red;
    }

    .edit-tip {
        font-size: 14px;
        margin: 10px 5px;
    }

    .address-container {
        padding: 10px 20px;
    }

    .address-container-back {
        margin-bottom: 10px;
    }

    .address-container-add-title {
        font-size: 15px;
        color: #409EFF;
        padding: 10px;
    }

    .address-container-add-item {
        margin-bottom: 20px;
    }

    .demonstration {
        color: #666666;
        font-size: 14px;
        padding: 10px;
    }
    .address-container-add{
        padding: 0 200px;
    }
    .address-container-list{
        padding: 30px 100px;
    }
</style>