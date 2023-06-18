<template>
    <div>
        <div style="float: left;">
            <h3>清单货物总价格：{{ this.waitingList.totalPrice }} &nbsp; 总重量：{{ this.waitingList.totalWeight }}</h3>
            <!-- <el-input
                placeholder="请输入区域名"
                v-model="input"
                clearable
                style="width: 300px; text-align: center;">
            </el-input>
            <el-button icon="el-icon-search" circle style="margin-left: 10px;"
             @click="getInfoByName"></el-button>        -->
        </div>
        <div style="float: right;">
            <el-button type="primary" round @click="openInsertDialog">根据清单货物生成运输订单</el-button>
        </div>

        <br />
        <br />
        <br />
        
        <el-table :data="tableData" v-horizontal-scroll="'always'"
                  style="width: 100%"
                  :default-sort = "{prop: 'goodsId', order: 'descending'}">
            <el-table-column prop="goodsId" label="货物编号" width="180" sortable header-align="center" align="center">
            </el-table-column>
            <el-table-column prop="goodsName" label="货物名称" sortable width="200" header-align="center" align="center">
            </el-table-column>
            <el-table-column prop="price" label="单价" width="100" sortable header-align="center" align="center">
            </el-table-column>
            <el-table-column prop="weight" label="重量" width="120" sortable header-align="center" align="center">
            </el-table-column>
            <el-table-column prop="num" label="运输数量" width="100" sortable header-align="center" align="center">
            </el-table-column>
            <el-table-column prop="operations" label="操作" header-align="center" align="center">
                <template slot-scope="scope">
                    <el-button type="success" size="small" @click="openModifyDialog(scope.$index, scope.row)">修改</el-button>
                    <el-button type="danger" size="small" @click="deleteInfo(scope.$index, scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination
            background
            layout="prev, pager, next"
            :total="1000">
        </el-pagination>

        <!-- 添加记录对话框 -->
        <el-dialog title="创建运输订单" :visible.sync="insertDialogFormVisible" width="400px">
            <el-form :model="order">
                <el-form-item label="托运人" :label-width="formLabelWidth">
                    <el-input v-model="order.shipperName" placeholder="请填写托运人" autocomplete="off" disabled></el-input>
                </el-form-item>
                <el-form-item label="承运商" :label-width="formLabelWidth">
                    <el-select v-model="order.carrierId" placeholder="请选择承运商"
                     @visible-change="findAllCarrier" style="width: 280px">
                        <el-option v-for="carrier in this.allCarrier" :key="carrier"
                         :label=carrier.carrierName :value=carrier.carrierId></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="收货人" :label-width="formLabelWidth">
                    <el-input v-model="order.consignee" placeholder="请填写收货人" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="收货人电话" :label-width="formLabelWidth">
                    <el-input v-model="order.consigneePhone" placeholder="请填写收货人电话" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="发货地" :label-width="formLabelWidth">
                    <el-input v-model="order.shipmentPlace" placeholder="请填写发货地" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="目的地" :label-width="formLabelWidth">
                    <el-input v-model="order.destination" placeholder="请填写目的地" autocomplete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="insertDialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="generateOrder()">确 定</el-button>
            </div>
        </el-dialog>

        <!-- 编辑记录对话框 -->
        <el-dialog title="编辑运输清单项" :visible.sync="modifyDialogFormVisible" width="500px">
            <el-form :model="form">
                <el-form-item label="货物编号" :label-width="formLabelWidth">
                    <el-input v-model="form.goodsId" autocomplete="off" disabled></el-input>
                </el-form-item>
                <el-form-item label="货物名称" :label-width="formLabelWidth">
                    <el-input v-model="form.goodsName" autocomplete="off" disabled></el-input>
                </el-form-item>
                <el-form-item label="单价" :label-width="formLabelWidth">
                    <el-input v-model="form.price" autocomplete="off" disabled></el-input>
                </el-form-item>
                <el-form-item label="重量" :label-width="formLabelWidth">
                    <el-input v-model="form.weight" autocomplete="off" disabled></el-input>
                </el-form-item>
                <el-form-item label="运输数量" :label-width="formLabelWidth">
                    <el-input v-model="form.num" autocomplete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="modifyDialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="modifyInfo()">确定</el-button>
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
        allCarrier: [],

        // 添加对话框
        insertDialogFormVisible: false,
        // 编辑对话框
        modifyDialogFormVisible: false,
        modifyId: undefined,

        order: {
            orderId: '',
            orderName: '',
            shipperId: '',
            shipperName: '',
            carrierId: '',
            carrier: {
                carrierId: "",
                carrierName: "",
                companyName: ""
            },
            consignee: '',
            consigneePhone: '',
            destination: '',
            shipmentPlace: '',
            goodsList: [],
            totalWeight: '',
            totalPrice: '',
            state: '',
        },

        form: {
            waitingId: '',
            goodsName: '',
            price: '',
            weight: '',
            num: '',
        },

        waitingList : {
            id: "",
            userId: "",
            totalPrice: 0,
            totalWeight: 0
        },

        formLabelWidth: '100px',

      }
    },
    created() {
        this.getAllWaitingItem()
        this.getWaitingList()
    },
    methods: {
        // 数据接口
        getAllWaitingItem() {
            var token = JSON.parse(localStorage.getItem('token'))
            this.$http.get(
                "/waitingItem", {
                    headers: {
                        'token': token
                    }
                }
            ).then((response)=>{
                this.tableData = response.data.data
                console.log(response)
            })
        },
        getWaitingList() {
            var token = JSON.parse(localStorage.getItem('token'))
            this.$http.get(
                "/waitingList", {
                    headers: {
                        'token': token
                    }
                }
            ).then((response)=>{
                this.waitingList = response.data.data
                console.log(response)
            })
        },
        findAllCarrier() {
            var token = JSON.parse(localStorage.getItem('token'))
            this.$http.get(
                "/carrier", {
                    headers: {
                        'token': token
                    }
                }
            ).then((response)=>{
                this.allCarrier = response.data.data
            })
        },
        findCurrentShipperName() {
            var token = JSON.parse(localStorage.getItem('token'))
            this.$http.get(
                "/shipper/details", {
                    headers: {
                        'token': token
                    }
                }
            ).then((response)=>{
                this.order.shipperId = response.data.data.shipperId
                this.order.shipperName = response.data.data.shipperName
                console.log(this.currentShipper)
            })
        },
        getInfoById(id) {
            var token = JSON.parse(localStorage.getItem('token'))
            this.$http.get(
                "/waitingItem/" + id, {
                    headers: {
                        'token': token
                    }
                }
            ).then((response)=>{
                // this.form = response.data.data
                // console.log(response.data.data)
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
                this.getAllWaitingItem()
            }
            else {
                var token = JSON.parse(localStorage.getItem('token'))
                this.$http.get(
                    "/waitingItem?name=" + this.input, {
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
            this.insertDialogFormVisible = true;
            this.findCurrentShipperName()
        },
        generateOrder() {
            var token = JSON.parse(localStorage.getItem('token'))
            this.insertDialogFormVisible = false;
            console.log(this.order)
            this.$http.post(
                "/order",
                this.order, {
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
                this.getAllWaitingItem()
                this.getWaitingList()
            })
        },
        openModifyDialog(index, row) {
            this.modifyDialogFormVisible = true
            // 回显需要被修改的信息
            this.getInfoById(row.waitingId)
        },
        modifyInfo() {
            var token = JSON.parse(localStorage.getItem('token'))
            this.modifyDialogFormVisible = false
            // 发送请求
            this.$http.put(
                "/waitingList",
                this.form, {
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
                this.getAllWaitingItem()
                this.getWaitingList()
            })

        },
        deleteInfo(index, row) {
            console.log(row.waitingId)
            this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                var token = JSON.parse(localStorage.getItem('token'))
                // 发送请求
                this.$http.delete(
                    "/waitingList/" + row.waitingId, {
                        headers: {
                            'token': token
                        }
                    }
                ).then((response)=>{
                    if (response.data.code == "1") {
                        this.$message({
                        type: 'success',
                        message: '删除成功!'
                    });
                    }
                    else {
                        this.$message({
                        type: 'error',
                        message: '删除失败!'
                    });
                    }

                    // 发请求重新获取数据
                    this.getAllWaitingItem()
                    this.getWaitingList()
                })

            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });          
            });
        }
    }
  };
</script>
