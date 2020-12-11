<template>
    <div>
        <app-head></app-head>
        <app-body>
            <div class="release-idle-container">
                <div class="release-idle-container-title">发布闲置</div>
                <div class="release-idle-container-form">
                    <el-input placeholder="请输入闲置名称" v-model="input1"
                              maxlength="30"
                              show-word-limit>
                    </el-input>
                    <el-input
                            class="release-idle-detiles-text"
                            type="textarea"
                            autosize
                            placeholder="请输入闲置的详细介绍..."
                            v-model="textarea"
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
                            <el-select  v-model="value" placeholder="请选择类别">
                                <el-option
                                        v-for="item in options2"
                                        :key="item.value"
                                        :label="item.label"
                                        :value="item.value">
                                </el-option>
                            </el-select>
                        </div>
                        <div style="width: 300px;">
                            <el-input placeholder="请输入价格" v-model="input2" type="number">
                                <div slot="prepend">价格</div>
                            </el-input>
                        </div>

                    </div>
                    <div class="release-idle-container-picture">
                        <div class="release-idle-container-picture-title">上传闲置照片</div>
                        <el-upload
                                class="upload-demo"
                                action="https://jsonplaceholder.typicode.com/posts/"
                                :on-preview="handlePreview"
                                :on-remove="handleRemove"
                                list-type="picture-card">
                            <i class="el-icon-plus"></i>
                        </el-upload>
                    </div>
                    <div style="display: flex;justify-content: center;margin-top: 30px;">
                        <el-button type="primary" plain>确认发布</el-button>
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
                options:options,
                selectedOptions:'',
                options2: [{
                    value: 1,
                    label: '类别1'
                }, {
                    value: 2,
                    label: '类别2'
                }, {
                    value: 3,
                    label: '类别3'
                }, {
                    value: 4,
                    label: '类别4'
                }, {
                    value: 5,
                    label: '类别5'
                }],
                value: '',
                input1: '',
                textarea: '',
                input2:''
            };
        },
        methods: {
            handleChange(value) {
                console.log(value,this.selectedOptions[1]);
            },
            handleRemove(file, fileList) {
                console.log(file, fileList);
            },
            handlePreview(file) {
                console.log(file);
            },
        }
    }
</script>

<style scoped>
    .release-idle-container {
        min-height: 80vh;
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
</style>