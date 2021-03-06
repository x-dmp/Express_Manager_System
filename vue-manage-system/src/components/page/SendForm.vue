<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-calendar"></i> 表单
                </el-breadcrumb-item>
                <el-breadcrumb-item> 新增物流信息</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="form-box">
                <el-form ref="form" :model="form" label-width="80px">
                    <el-form-item label="商品">
                        <el-select v-model="form.express" placeholder="请选择">
                            <el-option
                                    v-for="item in expressInfo"
                                    :key="item.id"
                                    :label="item.name"
                                    :value="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="运费">
                        <el-input v-model="form.price" type="number" style="width: 200px"></el-input>
                    </el-form-item>

                    <el-form-item label="发件方">
                        <el-select v-model="form.postUser" placeholder="请选择">
                            <el-option
                                    v-for="item in userInfo"
                                    :key="item.id"
                                    :label="item.username"
                                    :value="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>

                    <el-form-item label="收件方">
                        <el-select v-model="form.receiveUser" placeholder="请选择">
                            <el-option
                                    v-for="item in userInfo"
                                    :key="item.id"
                                    :label="item.username"
                                    :value="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>

                    <el-form-item label="快递公司">
                        <el-select v-model="form.company" placeholder="请选择">
                            <el-option
                                    v-for="item in companyInfo"
                                    :key="item.id"
                                    :label="item.name"
                                    :value="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="当前位置">
                        <el-input v-model="form.position" style="width: 200px"></el-input>
                    </el-form-item>
                    <el-form-item label="发送日期">
                        <el-col :span="11">
                            <el-date-picker
                                    type="date"
                                    placeholder="选择日期"
                                    v-model="form.date1"
                                    value-format="yyyy-MM-dd"
                                    style="width: 100%;"
                            ></el-date-picker>
                        </el-col>
                        <el-col class="line" :span="2">-</el-col>
                        <el-col :span="11">
                            <el-time-picker
                                    placeholder="选择时间"
                                    v-model="form.date2"
                                    value-format="HH:mm:ss"
                                    style="width: 100%;"
                            ></el-time-picker>
                        </el-col>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="onSubmit">表单提交</el-button>
                        <el-button>取消</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </div>
    </div>
</template>

<script>
    import { AddSendInfo } from '../../api/send';
    import { GetExpressInfo } from '../../api/express';
    import { GetUserInfo } from '../../api/user';
    import { GetCompanyInfo } from '../../api/company';

    export default {
        name: 'SendForm',
        data() {
            return {
                form: {
                    uuid: '',
                    express: '',
                    price: '',
                    postUser: '',
                    receiveUser: '',
                    company: '',
                    position: '',
                    sendTime: '',
                    date1: '',
                    date2: ''
                },
                imageUrl: '',
                file: '',
                expressInfo: [],
                userInfo: [],
                companyInfo: []
            };
        },
        created() {
            this.getData();
        },
        methods: {
            getData() {
                GetExpressInfo(1).then(res => {
                    this.expressInfo = res.data.list;
                });
                GetUserInfo(1).then(res => {
                    this.userInfo = res.data.items;
                });
                GetCompanyInfo(1).then(res=>{
                   this.companyInfo = res.data.items;
                });
            },
            onSubmit() {
                this.form.sendTime = this.form.date1 + ' ' + this.form.date2;
                AddSendInfo(this.form).then(res => {
                        this.$message.success('提交成功！');
                    }
                );
            }
        }
    };
</script>

<style>
    .avatar-uploader .el-upload {
        border: 1px dashed #d9d9d9;
        width: 180px;
        border-radius: 6px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
    }

    .avatar-uploader .el-upload:hover {
        border-color: #409EFF;
    }

    .avatar-uploader-icon {
        font-size: 28px;
        color: #8c939d;
        width: 178px;
        height: 178px;
        line-height: 178px;
        text-align: center;
    }

    .avatar {
        width: 178px;
        height: 178px;
        display: block;
    }
</style>