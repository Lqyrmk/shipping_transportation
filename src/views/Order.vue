<template>
    <div>
        <div style="float: left;">
            <el-input
                placeholder="请输入订单信息关键词"
                v-model="input"
                clearable
                style="width: 300px; text-align: center;">
            </el-input>
            <el-button icon="el-icon-search" circle style="margin-left: 10px;"
            @click="getInfoByKey"></el-button>       
        </div>
        <div style="float: right;">
            <el-button type="primary" round @click="openInsertDialog">创建运输订单</el-button>
        </div>

        <br />
        <br />
        <br />
        
        <el-table :data="tableData" v-horizontal-scroll="'always'"
                    style="width: 100%"
                    :default-sort = "{prop: 'orderId', order: 'descending'}">
            <el-table-column prop="orderId" label="订单编号" width="150" sortable header-align="center" align="center">
            </el-table-column>
            <el-table-column prop="shipper.shipperName" label="托运人" width="100" sortable header-align="center" align="center">
            </el-table-column>
            <el-table-column prop="carrier.carrierName" label="承运人" width="100" sortable header-align="center" align="center">
            </el-table-column>
            <el-table-column prop="consignee" label="收货人" width="100" sortable header-align="center" align="center">
            </el-table-column>
            <el-table-column prop="shipmentPlace" label="发货地" width="90" sortable header-align="center" align="center">
            </el-table-column>
            <el-table-column prop="destination" label="目的地" width="90" sortable header-align="center" align="center">
            </el-table-column>
            <el-table-column prop="totalPrice" label="运输总价" width="100" sortable header-align="center" align="center">
            </el-table-column>
            <el-table-column prop="totalWeight" label="运输重量" width="100" sortable header-align="center" align="center">
            </el-table-column>
            <el-table-column prop="operations" label="操作" header-align="center" align="center">
                <template slot-scope="scope">
                    <el-button type="primary" size="small" @click="openInfoDialog(scope.$index, scope.row)">详情</el-button>
                    <el-button v-show="!isShipper" type="success" size="small" @click="openModifyDialog(scope.$index, scope.row)">编辑</el-button>
                    <el-button type="danger" size="small" @click="deleteInfo(scope.$index, scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination
            background
            layout="prev, pager, next"
            :total="1000">
        </el-pagination>

        <!-- 详细信息对话框 -->
        <el-dialog title="订单详情：" :visible.sync="infoDialogVisible" width="30%" center>
            <el-form :model="form">
                <el-form-item label="收货人" :label-width="formLabelWidth">
                    <el-input v-model="form.order.consignee" autocomplete="off" disabled></el-input>
                </el-form-item>
                <el-form-item label="收货人电话" :label-width="formLabelWidth">
                    <el-input v-model="form.order.consigneePhone" autocomplete="off" disabled></el-input>
                </el-form-item>
                <el-form-item label="托运人id" :label-width="formLabelWidth">
                    <el-input v-model="form.order.shipperId" autocomplete="off" disabled></el-input>
                </el-form-item>
                <el-form-item label="托运人姓名" :label-width="formLabelWidth">
                    <el-input v-model="form.order.shipper.shipperName" autocomplete="off" disabled></el-input>
                </el-form-item>
                <el-form-item label="承运人id" :label-width="formLabelWidth">
                    <el-input v-model="form.order.carrierId" autocomplete="off" disabled></el-input>
                </el-form-item>
                <el-form-item label="承运人姓名" :label-width="formLabelWidth">
                    <el-input v-model="form.order.carrier.carrierName" autocomplete="off" disabled></el-input>
                </el-form-item>
                <el-form-item label="发货地" :label-width="formLabelWidth">
                    <el-input v-model="form.order.shipmentPlace" autocomplete="off" disabled></el-input>
                </el-form-item>
                <el-form-item label="目的地" :label-width="formLabelWidth">
                    <el-input v-model="form.order.destination" autocomplete="off" disabled></el-input>
                </el-form-item>
                <el-form-item label="运输货物" :label-width="formLabelWidth">
                    <el-input v-model="form.orderDetails" autocomplete="off" disabled></el-input>
                </el-form-item>
                <el-form-item label="运输总价" :label-width="formLabelWidth">
                    <el-input v-model="form.order.totalPrice" autocomplete="off" disabled></el-input>
                </el-form-item>
                <el-form-item label="运输重量" :label-width="formLabelWidth">
                    <el-input v-model="form.order.totalWeight" autocomplete="off" disabled></el-input>
                </el-form-item>
                <el-form-item label="订单状态" :label-width="formLabelWidth">
                    <el-input v-model="form.order.state" autocomplete="off" disabled></el-input>
                </el-form-item>
            </el-form>
        </el-dialog>


        <!-- 编辑记录对话框 -->
        <el-dialog title="编辑运输订单" :visible.sync="modifyDialogFormVisible" width="400px">
            <el-form :model="form">
                <el-form-item label="收货人" :label-width="formLabelWidth">
                    <el-input v-model="form.order.consignee" autocomplete="off" disabled></el-input>
                </el-form-item>
                <el-form-item label="收货人电话" :label-width="formLabelWidth">
                    <el-input v-model="form.order.consigneePhone" autocomplete="off" disabled></el-input>
                </el-form-item>
                <el-form-item label="托运人id" :label-width="formLabelWidth">
                    <el-input v-model="form.order.shipperId" autocomplete="off" disabled></el-input>
                </el-form-item>
                <el-form-item label="托运人姓名" :label-width="formLabelWidth">
                    <el-input v-model="form.order.shipper.shipperName" autocomplete="off" disabled></el-input>
                </el-form-item>
                <el-form-item label="承运人id" :label-width="formLabelWidth">
                    <el-input v-model="form.order.carrierId" autocomplete="off" disabled></el-input>
                </el-form-item>
                <el-form-item label="承运人姓名" :label-width="formLabelWidth">
                <el-input v-model="form.order.carrier.carrierName" autocomplete="off" disabled></el-input>
                </el-form-item>
                <el-form-item label="发货地" :label-width="formLabelWidth">
                    <el-input v-model="form.order.shipmentPlace" autocomplete="off" disabled></el-input>
                </el-form-item>
                <el-form-item label="目的地" :label-width="formLabelWidth">
                    <el-input v-model="form.order.destination" autocomplete="off" disabled></el-input>
                </el-form-item>
                <el-form-item label="运输货物" :label-width="formLabelWidth">
                    <el-input v-model="form.orderDetails" autocomplete="off" disabled></el-input>
                </el-form-item>
                <el-form-item label="运输重量" :label-width="formLabelWidth">
                    <el-input v-model="form.order.totalWeight" autocomplete="off" disabled></el-input>
                </el-form-item>
                <el-form-item label="运输总价" :label-width="formLabelWidth">
                    <el-input v-model="form.order.totalPrice" autocomplete="off" disabled></el-input>
                </el-form-item>
                <el-form-item label="订单状态" :label-width="formLabelWidth">
                    <el-input v-model="form.order.state" autocomplete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="modifyDialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="modifyInfo()">确 定</el-button>
            </div>
        </el-dialog>

    </div>
</template>

<style>
  .text {
    font-size: 14px;
  }

  .item {
    margin-bottom: 18px;
  }
</style>

<script>
import { all } from 'axios';
export default {
    computed: {
        isShipper() {
            const permission = localStorage.getItem('permission');
            return Boolean(permission && permission === "1");
        }
    },
    data() {

        return {
            // 搜索框输入的内容
            input: '',
            tableData: [],
            allCarrier: [],
            currentShipper: {},

            // 详细信息对话框
            infoDialogVisible: false,
            infoId: undefined,
            cameraModelList: [],
            // 添加对话框
            insertDialogFormVisible: false,
            // 编辑对话框
            modifyDialogFormVisible: false,
            modifyId: undefined,

            form: {
                orderDetails: "",
                order: {
                    orderId: '',
                    orderName: '',
                    shipperId: '',
                    shipper: {
                        shipperId: "",
                        shipperName: "",
                        gender: "",
                        email: "",
                        phone: "",
                        orders: null
                    },
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
                }
            },
            formLabelWidth: '90px',
            
            // 模型多选框
            // 场景列表
            allScenes: [],
            showCheckboxGroup: false,
            modelCheckboxGroup: [1, 2],
            // 模型列表
            allModels: [],
            // 区域列表
            allAreas: [],
        }
    },
    created() {
        this.findAll()
        // this.findAllAreas()
    },
    methods: {
        // 数据接口
        findAll() {
            var token = JSON.parse(localStorage.getItem('token'))
            this.$http.get(
                "/order" + "?keywords=", {
                    headers: {
                        'token': token
                    }
                }
            ).then((response)=>{
                this.tableData = response.data.data
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
                this.currentShipper = response.data.data
                console.log(this.currentShipper)
            })
        },
        // findAllAreas() {
        //     this.$http.get(
        //         "/area/findAll",
        //     ).then((response)=>{
        //         this.allAreas = response.data.data
        //     })
        // },
        getInfoById(id) {
            var token = JSON.parse(localStorage.getItem('token'))
            this.$http.get("/order/" + id, {
                    headers: {
                        'token': token
                    }
                }
            ).then((response)=>{
                if (response.data.code == "1") {
                    this.form = response.data.data
                    console.log(this.form)
                }
                else {
                    this.form = {}
                }
            })
        },
        getInfoByKey() {
            if (this.input === '') {
                this.findAll()
            }
            else {
                var token = JSON.parse(localStorage.getItem('token'))
                this.$http.get(
                    "/order?keywords=" + this.input, {
                        headers: {
                            'token': token
                        }
                    }
                ).then((response)=>{
                    if (response.data.code == "1") {
                        console.log(response.data.data)
                        this.tableData = response.data.data
                    }
                    else {
                        this.tableData = []
                    }
                })
            }
            
        },
        openInfoDialog(index, row) {
            this.infoDialogVisible = true
            // 回显需要被修改的信息
            this.getInfoById(row.orderId)
        },
        openInsertDialog(index, row) {
            this.$router.push({path: "/waitingList"})
        },
        openModifyDialog(index, row) {
            this.modifyDialogFormVisible = true
            // 回显需要被修改的信息
            this.getInfoById(row.orderId)
        },
        modifyInfo() {
            var token = JSON.parse(localStorage.getItem('token'))
            this.modifyDialogFormVisible = false
            // 发送请求
            this.$http.put(
                "/order/", this.form.order, {
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
                    "/order/" + row.orderId, {
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
        }
    }
};
</script>
