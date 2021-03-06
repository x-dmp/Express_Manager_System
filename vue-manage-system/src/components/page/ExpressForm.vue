<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-calendar"></i> 表单
                </el-breadcrumb-item>
                <el-breadcrumb-item> 新增用户信息</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="form-box">
                <el-form ref="form" :model="form" label-width="80px">
                    <el-form-item label="商品名">
                        <el-input v-model="form.name"></el-input>
                    </el-form-item>
                    <el-form-item label="价格">
                        <el-input v-model="form.price" type="number"></el-input>
                    </el-form-item>
                    <el-form-item label="描述">
                        <el-input v-model="form.introduce"></el-input>
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
    import { AddExpressInfo} from '../../api/express';

    export default {
        name: 'ExpressForm',
        data() {
            return {
                form: {
                    name:'',
                    price:0,
                    introduce: ''
                },
                imageUrl: '',
                file:'',
            };
        },
        methods: {
            onSubmit() {
                AddExpressInfo(this.form).then(res => {
                        this.$message.success('提交成功！');
                    }
                );
            },
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