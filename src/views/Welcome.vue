<template>
    <div style="weight: 100%; height: 100%; margin: 0; padding: 0; background-size: 100% 100%;" 
             :style="{backgroundImage:'url('+this.bgImg+')'}">
        <el-drawer
        title="个人资料"
        :visible.sync="drawer"
        :direction="rtl"
        :before-close="drawerClose">
            <!-- <span>我来啦!</span> -->
            
        </el-drawer>
        <el-container>
            <el-header id="welcomeHeader"
             style="font-size: 12px; padding-left: 0%; padding-right: 0%;
              box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04); background-color: #cdcdcd;">
                <el-menu
                    :default-active="activeIndex2"
                    class="el-menu-demo"
                    mode="horizontal"
                    @select="handleSelect"
                    background-color="black"
                    text-color="#fff"
                    active-text-color="#56a9ff"
                    style="float;">
                    <el-menu-item index="1" @click="gotoMain">
                        <i class="el-icon-s-home"></i>
                        首页
                    </el-menu-item>
                    <el-submenu index="2">
                        <template slot="title">
                            <i class="el-icon-setting"></i>
                            监控管理
                        </template>
                        <el-menu-item index="2-1" @click="gotoSelectScene">
                            <i class="el-icon-video-camera-solid"></i>
                            监控实况
                        </el-menu-item>
                        <el-menu-item index="2-2" @click="gotoStatistics">
                            <i class="el-icon-s-data"></i>
                            数据统计
                        </el-menu-item>
                    </el-submenu>
                    <!-- <el-menu-item index="3" :disabled="true">
                        <i class="el-icon-menu"></i>
                        后台管理
                    </el-menu-item> -->
                    <!-- <el-menu-item index="4" v-show="this.userInfo.status == 1" @click="gotoIndex"> -->
                    <el-menu-item index="4" v-show="this.userInfo.status == 1" @click="gotoIndex">
                        <i class="el-icon-menu"></i>
                        后台管理
                    </el-menu-item>

                    <!-- <el-menu-item style="float: right;" @click="checkInfo" index="5">
                        <i class="el-icon-user-solid"></i>
                        普通管理员
                    </el-menu-item> -->

                    <el-dropdown trigger="click" @command="handleMyInfo" style="float: right; margin-right: 10px; padding-right: 10px;">
                        <span class="el-dropdown-link" style="font-size: 14px; color: #ffffff; cursor: pointer;">
                            <i class="el-icon-user-solid"></i>
                                普通管理员
                            <i class="el-icon-caret-bottom el-icon--right"></i>
                        </span>
                        <el-dropdown-menu slot="dropdown">
                            <el-dropdown-item class="clearfix" command="checkInfo">
                                <i class="el-icon-postcard"></i>
                                个人资料
                            </el-dropdown-item>
                            <el-dropdown-item class="clearfix" command="logout">
                                <i class="el-icon-switch-button"></i>
                                退出登录
                            </el-dropdown-item>
                        </el-dropdown-menu>
                    </el-dropdown>
                    
                    <el-dropdown trigger="click" @command="handleMessage" style="float: right; margin-right: 10px; padding-right: 10px;">
                        <span class="el-dropdown-link" style="font-size: 14px; color: #ffffff; cursor: pointer;">
                            <i class="el-icon-bell"></i>
                                报警通知
                            <i class="el-icon-caret-bottom el-icon--right"></i>
                            <!-- <el-badge class="mark" :value="msgNum" /> -->
                        </span>
                        <el-dropdown-menu slot="dropdown">
                            <el-dropdown-item class="clearfix" command="checkMsg">
                                查看通知详情
                            <el-badge class="mark" :value="msgNum" />
                            </el-dropdown-item>
                            <el-dropdown-item class="clearfix" command="clearMsg">
                                全部标为已读
                            </el-dropdown-item>
                        </el-dropdown-menu>
                    </el-dropdown>

                    <span style="float: right; margin-right: 10px; padding-right: 10px;">
                        <el-switch
                            v-model="switchValue"
                            active-text="夜晚"
                            inactive-text="白天"
                            active-color="#13ce66"
                            inactive-color="#409eff"
                            @change="switchColor">
                        </el-switch>
                    </span>

                </el-menu>
            </el-header>

            <el-main>
                <router-view></router-view>
            </el-main>
        </el-container>
        
        
        <!-- <el-container>
            <el-main>
            </el-main>
        </el-container> -->
    </div>
</template>

<style lang="scss">
    $top: top;
    $bottom: bottom;
    $left: left;
    $right: right;
    %w100 {
        width: 100%;
    }

    %h100 {
        height: 100%;
    }
    
    // %cursor {
    //     cursor: pointer;
    // }
    // html, body, #app, .el-container, #asideNav, ul.el-menu {
    //     @extend %h100;
    // }

    #welcomeHeader {
        .el-menu-item:hover {
            background-color: #000000 !important;
        }
    }


</style>

<script>
export default {
    data() {
        return {
            userInfo: null,
            bgImg: require('../assets/background1.png'),
            switchValue: true,
            msgNum: "12",
            activeIndex: '1',
            activeIndex2: '1',
            drawer: false,
        };
    },
    created() {
    },
    methods: {
        handleSelect(key, keyPath) {
            console.log(key, keyPath);
        },
        gotoMain() {
            this.$router.push('/main')
        },
        gotoIndex() {
            this.$router.push('/index')
            const h = this.$createElement;
            this.$notify({
            title: '安全验证',
            message: h('i', { style: 'color: teal'}, '权限认证通过!\n欢迎您进入系统管理端')
            });
        },
        gotoSelectScene() {
            this.$router.push('/selectScene')
        },
        gotoStatistics() {
            this.$router.push('/statistics')
        },
        handleMessage (command) {
            this.msgNum = "";
            if (command === "checkMsg") {
                this.$message({
                    type: 'info',
                    message: '查看报警消息'
                });
                this.$router.push('/alarmEvent')
            } else if (command === "clearMsg") {
                this.$message({
                    type: 'success',
                    message: '全部已读!'
                });
            }
        },
        switchColor() {
            if (this.switchValue == true) {
                this.bgImg = require('../assets/background1.png')
            }
            else {
                this.bgImg = require('../assets/background2.png')
            }
        },
        handleMyInfo (command) {
            if (command === "checkInfo") {
                this.drawer = true
            } else if (command === "logout") {
                this.$http.get(
                    "/user/logout", 
                ).then((response)=>{
                    if (response.data.code == "1") {
                        localStorage.removeItem("userInfo")
                        this.$router.push('/login')
                        this.$message({
                            type: 'success',
                            message: response.data.msg
                        });
                    }

                })

            }
        },
    }
}
</script>