<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-cascades"></i> 基础表格
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-input v-model="query.uuid" placeholder="搜索物流单号(精确查询)" class="handle-input mr10"></el-input>
                <el-button type="primary" icon="el-icon-search" @click="handleSearch">搜索</el-button>
            </div>
            <el-table
                    :data="tableData"
                    border
                    class="table"
                    ref="multipleTable"
                    header-cell-class-name="table-header"
                    @selection-change="handleSelectionChange"
            >
                <el-table-column type="selection" width="55" align="center"></el-table-column>
                <el-table-column prop="uuid" label="快递单号" align="center"></el-table-column>
                <el-table-column prop="ex.name" label="商品编号" align="center"></el-table-column>
                <el-table-column prop="price" label="运送费用" align="center"></el-table-column>
                <el-table-column prop="sendTime" label="发送时间" align="center"></el-table-column>
                <el-table-column prop="pUser.username" label="发件用户id" align="center"></el-table-column>
                <el-table-column prop="rUser.username" label="收件用户id" align="center"></el-table-column>
                <el-table-column prop="eCompany.name" label="运送快递公司id" align="center"></el-table-column>
                <el-table-column prop="position" label="当前位置" align="center"></el-table-column>
                <el-table-column prop="pUser.address" label="始发地" align="center"></el-table-column>
                <el-table-column prop="rUser.address" label="目的地" align="center"></el-table-column>
                <el-table-column label="操作" width="180" align="center" v-if="$store.state.power">
                    <template slot-scope="scope">
                        <el-button
                                type="text"
                                icon="el-icon-edit"
                                @click="handleEdit(scope.$index, scope.row)"
                        >编辑
                        </el-button>
                        <el-button
                                type="text"
                                icon="el-icon-delete"
                                class="red"
                                @click="handleDelete(scope.$index, scope.row)"
                        >删除
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="pagination">
                <el-pagination
                        background
                        layout="total, prev, pager, next"
                        :current-page="query.pageIndex"
                        :page-size="query.pageSize"
                        :total="pageTotal"
                        @current-change="handlePageChange"
                ></el-pagination>
            </div>
        </div>

        <!-- 编辑弹出框 -->
        <el-dialog title="编辑" :visible.sync="editVisible" width="30%">
            <el-form ref="form" :model="form" label-width="70px">
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
                <el-form-item label="发货时间">
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
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveEdit">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    import { GetSendInfo, ChangeSendInfo, DeleteSendInfo } from '../../api/send';
    import { GetExpressInfo } from '../../api/express';
    import { GetUserInfo } from '../../api/user';
    import { GetCompanyInfo } from '../../api/company';

    export default {
        name: 'SendTable',
        data() {
            return {
                query: {
                    uuid:'',
                    express:'',
                    price:'',
                    sendTime:'',
                    receiveUser:'',
                    postUser:'',
                    company:'',
                    position:'',
                    fromAddress:'',
                    toAddress:'',
                    pageIndex: 1,
                    pageSize: 5,
                    date1: '',
                    date2: ''
                },
                tableData: [],
                multipleSelection: [],
                delList: [],
                editVisible: false,
                pageTotal: 0,
                form: {},
                idx: -1,
                id: -1,
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
                GetSendInfo(this.query).then(res => {
                    this.tableData = res.data.items;
                    this.pageTotal = res.data.total || 50;
                });
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
            // 触发搜索按钮
            handleSearch() {
                this.$set(this.query, 'pageIndex', 1);
                this.getData();
            },
            // 删除操作
            handleDelete(index, row) {
                // 二次确认删除
                console.log(index);
                console.log(row.id);
                this.$confirm('确定要删除吗？', '提示', {
                    type: 'warning'
                })
                    .then(() => {
                        this.$message.success('删除成功');
                        this.tableData.splice(index, 1);
                        DeleteSendInfo(row.uuid);
                    })
                    .catch(() => {
                    });
            },
            // 多选操作
            handleSelectionChange(val) {
                this.multipleSelection = val;
            },
            delAllSelection() {
                const length = this.multipleSelection.length;
                let str = '';
                this.delList = this.delList.concat(this.multipleSelection);
                for (let i = 0; i < length; i++) {
                    str += this.multipleSelection[i].username + ' ';
                }
                this.$message.error(`删除了${str}`);
                this.multipleSelection = [];
            },
            // 编辑操作
            handleEdit(index, row) {
                this.idx = index;
                this.form = row;
                this.editVisible = true;
            },
            // 保存编辑
            saveEdit() {
                this.editVisible = false;
                this.$message.success(`修改第 ${this.idx + 1} 行成功`);
                this.form.sendTime = this.form.date1 + ' ' + this.form.date2;
                this.$set(this.tableData, this.idx, this.form);
                ChangeSendInfo(this.form).then(res => {
                    this.getData();
                });
            },
            // 分页导航
            handlePageChange(val) {
                this.$set(this.query, 'pageIndex', val);
                this.getData();
            }
        }
    };
</script>

<style scoped>
    .handle-box {
        margin-bottom: 20px;
    }

    .handle-select {
        width: 120px;
    }

    .handle-input {
        width: 300px;
        display: inline-block;
    }

    .table {
        width: 100%;
        font-size: 14px;
    }

    .red {
        color: #ff0000;
    }

    .mr10 {
        margin-right: 10px;
    }

    .table-td-thumb {
        display: block;
        margin: auto;
        width: 40px;
        height: 40px;
    }
</style>