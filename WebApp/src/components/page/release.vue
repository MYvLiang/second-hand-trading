<template>
    <div>
        <app-head></app-head>
        <app-body>
            <div class="release-idle-container">
                <div class="release-idle-container-title">发布闲置</div>
                <div class="release-idle-container-form">
                    <el-input placeholder="请输入闲置名称" v-model="idleItemInfo.idleName"
                              maxlength="30"
                              show-word-limit>
                    </el-input>
                    <el-input
                            class="release-idle-detiles-text"
                            type="textarea"
                            autosize
                            placeholder="请输入闲置的详细介绍..."
                            v-model="idleItemInfo.idleDetails"
                            maxlength="1000"
                            show-word-limit>
                    </el-input>
                    <div class="release-idle-place">
                        <div class="release-tip">您的地区</div>
                        <el-cascader
                                :options="options"
                                v-model="selectedOptions"
                                @change="handleChange"
                                :separator="' '"
                                style="width: 90%;"
                        >
                        </el-cascader>
                    </div>
                    <div style="display: flex; justify-content: space-between;">
                        <div>
                            <div class="release-tip">闲置类别</div>
                            <el-select  v-model="idleItemInfo.idleLabel" placeholder="请选择类别">
                                <el-option
                                        v-for="item in options2"
                                        :key="item.value"
                                        :label="item.label"
                                        :value="item.value">
                                </el-option>
                            </el-select>
                        </div>
                        <div style="width: 300px;">
                            <el-input-number v-model="idleItemInfo.idlePrice" :precision="2" :step="10" :max="10000000">
                                <div slot="prepend">价格</div>
                            </el-input-number>
                        </div>

                    </div>
                    <div class="release-idle-container-picture">
                        <div class="release-idle-container-picture-title">上传闲置照片</div>
                        <el-upload
                                action="http://localhost:8080/file/"
                                :on-preview="fileHandlePreview"
                                :on-remove="fileHandleRemove"
                                :on-success="fileHandleSuccess"
                                :show-file-list="showFileList"
                                :limit="10"
                                :on-exceed="handleExceed"
                                accept="image/*"
                                drag
                                multiple>
                            <i class="el-icon-upload"></i>
                            <div class="el-upload__text">将图片拖到此处，或<em>点击上传</em></div>
                        </el-upload>
                        <div class="picture-list">
                            <el-image style="width: 600px;margin-bottom: 2px;" fit="contain"
                                      v-for="(img,index) in imgList" :src="img"
                                      :preview-src-list="imgList"></el-image>
                        </div>
                        <el-dialog :visible.sync="imgDialogVisible">
                            <img width="100%" :src="dialogImageUrl" alt="">
                        </el-dialog>
                    </div>
                    <div style="display: flex;justify-content: center;margin-top: 30px;margin-bottom: 30px;">
                        <el-button type="primary" plain @click="releaseButton">确认发布</el-button>
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
    import options from '../common/country-data.js'

    export default {
        name: "release",
        components: {
            AppHead,
            AppBody,
            AppFoot
        },
        data() {
            return {
                imgDialogVisible:false,
                dialogImageUrl:'',
                showFileList:true,
                options:options,
                selectedOptions:[],
                options2: [{
                    value: 1,
                    label: '数码'
                }, {
                    value: 2,
                    label: '家电'
                }, {
                    value: 3,
                    label: '户外'
                }, {
                    value: 4,
                    label: '图书'
                }, {
                    value: 5,
                    label: '其他'
                }],
                imgList:[],
                idleItemInfo:{
                    idleName:'',
                    idleDetails:'',
                    pictureList:'',
                    idlePrice:0,
                    idlePlace:'',
                    idleLabel:''
                }
            };
        },
        methods: {
            handleChange(value) {
                console.log(value);
                this.idleItemInfo.idlePlace=value[1];
            },
            fileHandleRemove(file, fileList) {
                console.log(file, fileList);
                for(let i=0;i<this.imgList.length;i++){
                    if(this.imgList[i]===file.response.data){
                        this.imgList.splice(i,1);
                    }
                }
            },
            fileHandlePreview(file) {
                console.log(file);
                this.dialogImageUrl=file.response.data;
                this.imgDialogVisible=true;
            },
            fileHandleSuccess(response, file, fileList){
                console.log("file:",response,file,fileList);
                this.imgList.push(response.data);
            },
            releaseButton(){
                this.idleItemInfo.pictureList=JSON.stringify(this.imgList);
                console.log(this.idleItemInfo);
                if(this.idleItemInfo.idleName&&
                    this.idleItemInfo.idleDetails&&
                    this.idleItemInfo.idlePlace&&
                    this.idleItemInfo.idleLabel&&
                    this.idleItemInfo.idlePrice){
                    this.$api.addIdleItem(this.idleItemInfo).then(res=>{
                        if (res.status_code === 1) {
                            this.$message({
                                message: '发布成功！',
                                type: 'success'
                            });
                            console.log(res.data);
                            this.$router.replace({path: '/details', query: {id: res.data.id}});
                        } else {
                            this.$message.error('发布失败！'+res.msg);
                        }
                    }).catch(e=>{
                        this.$message.error('请填写完整信息');
                    })
                }else {
                    this.$message.error('请填写完整信息！');
                }

            },
            handleExceed(files, fileList) {
                this.$message.warning(`限制10张图片，本次选择了 ${files.length} 张图，共选择了 ${files.length + fileList.length} 张图`);
            },
        }
    }
</script>

<style scoped>
    .release-idle-container {
        min-height: 85vh;
    }

    .release-idle-container-title {
        font-size: 18px;
        padding: 30px 0;
        font-weight: 600;
        width: 100%;
        text-align: center;
    }

    .release-idle-container-form {
        padding: 0 180px;
    }

    .release-idle-detiles-text {
        margin: 20px 0;
    }
    .release-idle-place{
        margin-bottom: 15px;
    }
    .release-tip{
        color: #555555;
        float: left;
        padding-right: 5px;
        height: 36px;
        line-height: 36px;
        font-size: 14px;
    }
    .release-idle-container-picture{
        margin: 20px 0;

    }
    .release-idle-container-picture-title{
        margin: 10px 0;
        color: #555555;
        font-size: 14px;
    }
    .picture-list {
        margin: 20px 0;
        display: flex;
        flex-direction: column;
        align-items: center;
    }
</style>