<template>
    <div>
        <div>
            <el-carousel :interval="3000" type="card" height="200px">
                <el-carousel-item v-for="(item, index) in imgList" :key="item">
                    <img :src="require('../assets/'+item.url)" class="image" height="200" width="240" style="cursor: pointer;">
                </el-carousel-item>
            </el-carousel>
            <el-card class="box-card" style="width: 450px; height: 270px; float: left; margin-left: 5%; opacity: 0.8;">
                <div slot="header" class="clearfix">
                    <span style="float: center;">待处理事件</span>
                    <el-button style="float: right; padding: 3px 0" type="text" @click="gotoAlarmEvent">查看详情</el-button>
                </div>
                <el-timeline :reverse="reverse">
                    <!-- <el-timeline-item
                    v-show="events!=null"
                    v-for="(event, index) in events"
                    :key="index"
                    :timestamp="event.created_at">
                    {{event.event_name}}
                    </el-timeline-item> -->
                    <el-timeline-item :timestamp="events[2].created_at">
                        {{ events[2].event_name }}
                    </el-timeline-item>
                    <el-timeline-item :timestamp="events[1].created_at">
                        {{ events[1].event_name }}
                    </el-timeline-item>
                    <el-timeline-item :timestamp="events[0].created_at">
                        {{ events[0].event_name }}
                    </el-timeline-item>
                    <!-- <el-timeline-item v-show="events==[]">
                        暂无待处理事件
                    </el-timeline-item> -->
                </el-timeline>
            </el-card>

            <el-card class="box-card" style="width: 450px; height: 270px; float: right; margin-right: 5%; opacity: 0.8;">
                <div slot="header" class="clearfix">
                    <span>系统修改项</span>
                </div>
                <!-- <el-row v-for="o in 3" :key="o" class="text item"> -->
                <!-- &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
                <div>
                    <el-button type="primary" plain @click="openParamsDialog">目标检测配置</el-button>
                    <el-button type="primary" plain>检测区域配置</el-button>
                </div>
                <br />
                <div>
                    <el-button type="primary" plain>系统性能查看</el-button>
                    <el-button type="primary" plain>系统性能查看</el-button>
                </div>
                <!-- </el-row> -->
            </el-card>
        </div>


        <el-dialog title="编辑目标检测参数信息" :visible.sync="paramsDialogFormVisible" width="400px">
            <el-form :model="form">
                <el-form-item label="时间阈值" :label-width="formLabelWidth">
                    <el-input v-model="form.time_threshold" autocomplete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="paramsDialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="changeParams()">确 定</el-button>
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

    .clearfix:before,
    .clearfix:after {
        display: table;
        content: "";
    }
    .clearfix:after {
        clear: both
    }

    .box-card {
        width: 480px;
    }
    .el-carousel__item h3 {
      color: #475669;
      font-size: 14px;
      opacity: 0.75;
      line-height: 200px;
      margin: 0;
    }
    
    .el-carousel__item:nth-child(2n) {
      background-color: #99a9bf;
    }
    
    .el-carousel__item:nth-child(2n+1) {
      background-color: #d3dce6;
    }
    #backgroundImg-div {
        filter:alpha(Opacity=0.5);
        -moz-opacity:1;
        opacity: 1;

    }
</style>

<script>
export default {
    data() {
        return {
            value: new Date(),
            imgList:[
                {id: 1, url: 'cover1.jpg'},
                // {id: 2, url: 'cover2.jpg'},
                // {id: 3, url: 'cover3.jpg'},
                {id: 4, url: 'cover4.jpg'},
                {id: 4, url: 'cover5.jpg'},
            ],
            reverse: true,
            events: [],

            form: {
                time_threshold: '',
            },
            
            // 参数修改对话框
            paramsDialogFormVisible: false,
        };
    },
    // created() {
    //     this.findAllEvent()
    // },
    methods: {
        gotoAlarmEvent() {
            this.$router.push('/alarmEvent')
        },
        // openParamsDialog(){
        //     this.paramsDialogFormVisible = true
        //     this.getParams()
        // },
        // getParams() {
        //     this.$http.get(
        //         "/model/params/",
        //     ).then((response)=>{
        //         this.form.time_threshold = response.data.data
        //     })
        // },
        // changeParams() {
        //     this.$http.put(
        //         "/model/params/" + this.form.time_threshold,
        //     ).then((response)=>{
        //         if (response.data.code == "1") {
        //             this.$message({
        //                 type: 'success',
        //                 message: '修改成功'
        //             });
        //         }
        //         else {
        //             this.$message({
        //                 type: 'error',
        //                 message: '修改失败'
        //             });
        //         }
        //     })
        //     this.paramsDialogFormVisible = false
        // },
        // findAllEvent() {
        //     this.$http.get(
        //         "/alert/findAll",
        //     ).then((response)=>{
        //         this.events = response.data.data
        //         this.events.forEach(function(element) {
        //             element.created_at = element.created_at.replace(/T/g, ' ').replace(/.[\d]{3}Z/, ' ')
        //         });
        //     })
        // },
    }
}
</script>