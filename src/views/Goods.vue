<template>
    <div>
        <div style="float: left;">
            <el-input
                placeholder="请输入货物名称"
                v-model="input"
                clearable
                style="width: 300px; text-align: center;">
            </el-input>
            <el-button icon="el-icon-search" circle style="margin-left: 10px;"
             @click="getInfoByName"></el-button>       
        </div>
        <div style="float: right;">
            <el-button type="primary" round @click="openInsertDialog">添加货物</el-button>
        </div>

        <br />
        <br />
        <br />
        
        <el-table :data="tableData" v-horizontal-scroll="'always'"
                  style="width: 100%"
                  :default-sort = "{prop: 'goodsId', order: 'descending'}">
            <el-table-column prop="goodsId" label="货物编号" width="180" header-align="center" align="center">
            </el-table-column>
            <el-table-column prop="goodsName" label="货物名称" sortable width="200" header-align="center" align="center">
            </el-table-column>
            <el-table-column prop="price" label="单价" width="100" sortable header-align="center" align="center">
            </el-table-column>
            <el-table-column prop="weight" label="重量" width="120" sortable header-align="center" align="center">
            </el-table-column>
            <el-table-column prop="stock" label="库存量" width="100" sortable header-align="center" align="center">
            </el-table-column>
            <!-- <el-table-column prop="email" label="邮箱" width="180" header-align="center" align="center">
            </el-table-column>
            <el-table-column prop="area_id" label="管理区域id" :formatter="areaFormatter" sortable width="100" header-align="center" align="center">
            </el-table-column>
            <el-table-column prop="area_name" label="管理区域" sortable width="100" header-align="center" align="center">
            </el-table-column> -->
            <!-- <el-table-column prop="status" label="身份" :formatter="statusFormatter" sortable width="90" header-align="center" align="center">
                <template slot-scope="scope">
                    {{scope.row.status == 1 ? '超级管理员' : '普通管理员'}}
                </template>
            </el-table-column> -->
            <el-table-column prop="operations" label="操作" header-align="center" align="center">
                <template slot-scope="scope">
                    <el-button type="success" size="small" @click="openModifyDialog(scope.$index, scope.row)">编辑</el-button>
                    <el-button type="danger" size="small" @click="deleteInfo(scope.$index, scope.row)">删除</el-button>
                    <el-button type="primary" size="small" @click="addToWaitingList(scope.$index, scope.row)">添加到清单</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination
            background
            layout="prev, pager, next"
            :total="1000">
        </el-pagination>

        <!-- 添加记录对话框 -->
        <el-dialog title="添加货物" :visible.sync="insertDialogFormVisible" width="400px">
            <el-form :model="form">
                <el-form-item label="货物名称" :label-width="formLabelWidth">
                    <el-input v-model="form.goodsName" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="单价" :label-width="formLabelWidth">
                    <el-input v-model="form.price" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="重量" :label-width="formLabelWidth">
                    <el-input v-model="form.weight" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="库存量" :label-width="formLabelWidth">
                    <el-input v-model="form.stock" autocomplete="off"></el-input>
                </el-form-item>
                <!-- <el-form-item label="性别" :label-width="formLabelWidth">
                    <el-input v-model="form.gender" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="邮箱" :label-width="formLabelWidth">
                    <el-input v-model="form.email" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="管理区域" :label-width="formLabelWidth">
                    <el-select v-model="form.area_id" placeholder="请选择该管理员的管理区域"
                     @visible-change="findAllAreas" style="width: 280px">
                        <el-option v-for="area in this.allAreas" :key="area"
                         :label=area.area_name :value=area.area_id></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="身份" :label-width="formLabelWidth">
                    <el-select v-model="form.status" placeholder="请选择管理员身份" style="width: 280px">
                        <el-option label="普通管理员" value="0"></el-option>
                        <el-option label="超级管理员" value="1"></el-option>
                    </el-select>
                </el-form-item> -->
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="insertDialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="insertInfo()">确 定</el-button>
            </div>
        </el-dialog>

        <!-- 编辑记录对话框 -->
        <el-dialog title="编辑信息" :visible.sync="modifyDialogFormVisible" width="400px">
            <el-form :model="form">
                <el-form-item label="货物编号" :label-width="formLabelWidth">
                    <el-input v-model="form.goodsId" autocomplete="off" disabled></el-input>
                </el-form-item>
                <el-form-item label="货物名称" :label-width="formLabelWidth">
                    <el-input v-model="form.goodsName" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="单价" :label-width="formLabelWidth">
                    <el-input v-model="form.price" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="重量" :label-width="formLabelWidth">
                    <el-input v-model="form.weight" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="库存量" :label-width="formLabelWidth">
                    <el-input v-model="form.stock" autocomplete="off"></el-input>
                </el-form-item>
                <!-- <el-form-item label="性别" :label-width="formLabelWidth">
                    <el-input v-model="form.gender" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="邮箱" :label-width="formLabelWidth">
                    <el-input v-model="form.email" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="管理区域" :label-width="formLabelWidth">
                    <el-select v-model="form.area_id" placeholder="请选择该管理员的管理区域"
                     @visible-change="findAllAreas" style="width: 280px">
                        <el-option v-for="area in this.allAreas" :key="area"
                         :label=area.area_name :value=area.area_id></el-option>
                    </el-select>
                </el-form-item> -->
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="modifyDialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="modifyInfo()">确 定</el-button>
            </div>
        </el-dialog>

    </div>
</template>

<style>

</style>

<script>
export default {
    name: 'AlarmEvent',
    components: {

    },
    data() {

        return {
            // 搜索框输入的内容
            input: '',
            tableData: [],

            // 添加对话框
            insertDialogFormVisible: false,
            // 编辑对话框
            modifyDialogFormVisible: false,
            modifyId: undefined,

            form: {
                goodsId: '',
                goodsName: '',
                price: '',
                stock: '',
                weight: '',
            },
            formLabelWidth: '80px',

        }
    },
    created() {
        this.findAll()
    },
    mounted() {
        this.findAll()
    },
    methods: {
        areaFormatter(row, column) {
            return row.area_id
        },
        statusFormatter(row, column) {
            // return row.status
            return row.status == 1 ? '超级管理员' : '普通管理员';
        },
        findAll() {
            var token = JSON.parse(localStorage.getItem('token'))
            this.$http.get(
                "/goods?keywords", {
                    headers: {
                        'token': token
                    }
                }
            ).then((response)=>{
                this.tableData = response.data.data
                console.log(response.data.data)
            })
        },
        getInfoById(id) {
            var token = JSON.parse(localStorage.getItem('token'))
            this.$http.get(
                "/goods/" + id, {
                    headers: {
                        'token': token
                    }
                }
            ).then((response)=>{
                if (response.data.code == "1") {
                    this.form = response.data.data
                }
                else {
                    this.form = {}
                }
            })
        },
        getInfoByName() {
            if (this.input === '') {
                this.findAll()
            }
            else {
                var token = JSON.parse(localStorage.getItem('token'))
                this.$http.get(
                    "/goods?keywords=" + this.input, {
                        headers: {
                            'token': token
                        }
                    }
                ).then((response)=>{
                    // this.form = response.data.data
                    // console.log(response.data.data)
                    if (response.data.code == "1") {
                        this.tableData = response.data.data
                    }
                    else {
                        this.tableData = []
                    }
                })
            }
            
        },
        openInsertDialog() {
            this.form = {}
            this.insertDialogFormVisible = true;
        },
        insertInfo() {
            this.insertDialogFormVisible = false
            var token = JSON.parse(localStorage.getItem('token'))
            this.$http.post(
                "/goods/", this.form, {
                    headers: {
                        'token': token
                    }
                }
            ).then((response)=>{
                this.$message({
                    type: 'success',
                    message: '添加成功'
                });
                // 发请求重新获取数据
                this.findAll()
            })
        },
        openModifyDialog(index, row) {
            this.modifyDialogFormVisible = true
            var token = JSON.parse(localStorage.getItem('token'))
            this.modifyId = row.goodsId
            // 回显需要被修改的信息
            this.getInfoById(row.goodsId)
        },
        modifyInfo() {
            var token = JSON.parse(localStorage.getItem('token'))
            this.modifyDialogFormVisible = false
            // 发送请求
            this.$http.put(
                "/goods/", this.form, {
                    headers: {
                        'token': token
                    }
                }
            ).then((response)=>{
                this.$message({
                    type: 'success',
                    message: '修改成功'
                });
                // 发请求重新获取数据
                this.findAll()
            })

        },
        deleteInfo(index, row) {
            this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                var token = JSON.parse(localStorage.getItem('token'))
                // 发送请求
                this.$http.delete(
                    "/goods/" + row.goodsId, {
                    headers: {
                        'token': token
                    }
                }
                ).then((response)=>{
                    this.$message({
                        type: 'success',
                        message: '删除成功!'
                    });
                    // 发请求重新获取数据
                    this.findAll()
                })

            }).catch(() => {
                this.$message({
                type: 'info',
                message: '已取消删除'
                });          
            });
        },

        //添加货物到清单
        addToWaitingList(index, row) {
            this.$confirm('添加货物到清单中, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                var token = JSON.parse(localStorage.getItem('token'))
                // 获取信息
                this.$http.get(
                    "/goods/" + row.goodsId, {
                        headers: {
                            'token': token
                        }
                    }
                ).then((response)=>{
                    if (response.data.code == "1") {
                        var data = response.data.data
                        console.log(data)
                        this.$http.post(
                            "/waitingList/", data, {
                                headers: {
                                    'token': token
                                }
                            }
                        ).then((response)=>{
                            this.$message({
                                type: 'success',
                                message: '添加成功'
                            });
                            // 发请求重新获取数据
                            this.findAll()
                        })
                    }
                })

            }).catch(() => {
                this.$message({
                type: 'info',
                message: '已取消添加'
                });          
            });
            
        }
    }
};
</script>
