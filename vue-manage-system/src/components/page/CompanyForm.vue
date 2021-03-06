<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-calendar"></i> 表单
                </el-breadcrumb-item>
                <el-breadcrumb-item>基本表单</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="form-box">
                <el-form ref="form" :model="form" label-width="80px">
                    <el-form-item label="公司名称">
                        <el-input v-model="form.name"></el-input>
                    </el-form-item>
                    <el-form-item label="公司地址">
                        <el-input type="textarea" rows="5" v-model="form.address"></el-input>
                    </el-form-item>
                    <el-form-item label="公司简介">
                        <el-input type="textarea" rows="5" v-model="form.introduce"></el-input>
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

    import { AddCompanyInfo } from '../../api/company';

    export default {
        name: 'CompanyForm',
        data() {
            return {
                form: {
                    name: '',
                    address:'',
                    introduce:'',
                }
            };
        },
        methods: {
            onSubmit() {
                AddCompanyInfo(this.form);
                this.$message.success('提交成功！');
            }
        }
    };
</script>