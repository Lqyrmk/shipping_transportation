<template>
    <div style="weight: 100%; height: 100%; margin: 0; padding: 0;">

        <el-container style="height: 100%; " id="containerNav">
            <!-- rgb(238, 241, 246) -->
            <!-- rgba(255,255,255,.7) -->
            <el-aside width="200rpx" style="background-color: #EDC2C3;" id="asideNav">
                <el-menu 
                :default-openeds="['1', '2']"
                default-active="2"
                class="el-menu-vertical-demo"
                background-color="#EDC2C3"
                active-text-color="#020f1d"

                :collapse="isCollapse">
                    <div class="logo-name">
                        <p id="sysTitle" style="cursor: pointer;" @click="changeIcon">
                            {{ this.systemTitle }}
                            <!-- <el-button type="text" @click="gotoMyInfo">{{ this.systemTitle }}</el-button> -->
                            <el-button type="text" :icon="collapseIcon" @click="changeIcon"></el-button>
                        </p>
                    </div>

                    <el-submenu index="1">
                        <template slot="title">
                            <i class="el-icon-setting"></i>
                            <span slot="title">物流货运管理</span>
                        </template>
                        <el-menu-item v-show="isShipper" @click="gotoGoods" index="1-1">货物管理</el-menu-item>
                        <el-menu-item @click="gotoOrder" index="1-2">物流订单</el-menu-item>
                        <el-menu-item v-show="isShipper" @click="gotoWaiting" index="1-3">待运输货物清单</el-menu-item>
                        <el-menu-item @click="logout" index="1-4">退出登录</el-menu-item>
                    </el-submenu>

                    <!-- <el-submenu index="2">
                        <template slot="title">
                            <i class="el-icon-location"></i>
                            <span slot="title">监控查看</span>
                        </template>
                        <el-menu-item @click="gotoSelectScene" index="2-1">场景选择</el-menu-item>
                        <el-menu-item @click="gotoWelcome" index="2-2">回到主页</el-menu-item>
                    </el-submenu> -->

                </el-menu>

            </el-aside>
            
            <el-container>
                <el-header class="indexHeader"
                style="font-size: 16px; box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04); background-color: #cdcdcd;" >
                    <!-- <el-avatar :size="50" :src="circleUrl"></el-avatar>
                    <span>管理员</span> -->

                    <div style="float: left;">
                        <span style="font-size: 16px; cursor: pointer;" @click="fullScreen">
                            <i class="el-icon-zoom-in"></i>&nbsp;全屏显示
                        </span>
                    </div>

                    <div class="icon" style="float: right;">
                        <img :src="circleUrl" style="cursor: pointer;"/>
                    </div>
                    
                    <div style="float: right;">

                        <el-dropdown trigger="click" @command="handleInfo" style="margin-right: 20px; padding-right: 20px;">
                            <span class="el-dropdown-link" style="font-size: 16px; color: #333333; cursor: pointer;">
                                欢迎您！{{ name }}<i class="el-icon-caret-bottom el-icon--right"></i>
                            </span>
                            <el-dropdown-menu slot="dropdown">
                            <el-dropdown-item command="info">个人信息</el-dropdown-item>
                            <el-dropdown-item command="editPassword">修改密码</el-dropdown-item>
                            <el-dropdown-item command="logout" divided>退出登录</el-dropdown-item>
                            </el-dropdown-menu>
                        </el-dropdown>
                    </div>

                    <user-info v-if="dialogInfoVisible" :title="title" :dialogVisible="dialogInfoVisible" :userId="userId" @successCallback="successCallback"/>
                    <edit-password v-if="dialogPassVisible" :dialogVisible="dialogPassVisible" @editPwdCallback="editPwdCallback"/>

                </el-header>

                <el-main>
                    <router-view></router-view>
                </el-main>
            </el-container>
        </el-container>

        
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

    %cursor {
        cursor: pointer;
    }

    html, body, #app, .el-container, #asideNav, ul.el-menu {
        @extend %h100;
    }

    #sysTitle {
        height: 50px;
        line-height: 50px;
        text-align: center;
        font-size: 16px;
        color: #5e6d82;
    }

    .el-header {
        background-color: #f0f2f5;
        color: #333;
        line-height: 60px;
    }

    .el-menu-vertical-demo:not(.el-menu--collapse) {
        width: 200rpx;
        min-height: 400px;
    }

    .el-menu{
        border-right:none !important;
    }

    #asideNav {
        text-align: left;
        color: #f0b0b0;
        display: flex;
        flex-direction: column;
        border-right: solid 1px #e6e6e6;
        .el-menu-item {
            width: auto !important;
            background-color: #f0b0b0 !important;
            border-bottom: 1px solid #f99f9f;
        }
        .el-menu-item.is-active {
            background-color: #bb6e6e !important;
        }
        .el-menu-item:hover {
            background-color: #b35252 !important;
        }
    }

    .personal {
        display: flex;
        flex-direction: row;
    }
    
    .icon img {
        margin-#{$top}: 10px;
        -webkit-border-radius: 5px;
        -moz-border-radius: 5px;
        border-radius: 5px;
        width: 40px;
        height: 40px;
    }

</style>

<script>
import UserInfo from "../components/userForm/userInfo"
import EditPassword from "../components/userForm/editPassword"

  export default {
    computed: {
        isShipper() {
            const permission = localStorage.getItem('permission');
            return Boolean(permission && permission === "1");
        }
    },
    components: {EditPassword, UserInfo},
    data () {
      return {
        name: '',
        msgNum: "12",
        systemTitle: "物流货运系统",
        circleUrl: require("../assets/stb.jpg"), // 控制右上角头像
        isCollapse: false, // 控制导航栏展开，false为展开
        collapseIcon: "el-icon-arrow-left",
        switchValue: true,
        
        // 有关信息下拉框
        isfullScreen: true,
        dialogInfoVisible: false,
        dialogPassVisible: false,
        title: "",
        userId: "",
        
        direction: 'rtl',
      }
    },
    created() {
        // this.userInfo = JSON.parse(user)
        // alert(userInfo)
        var token = JSON.parse(localStorage.getItem('token'))
        this.$http.get(
            "/user/info", {
                headers: {
                    'token': token
                }
            }
        ).then((response)=>{
            this.name = response.data.data.username
        })
    },
    methods: {
        changeIcon() {
            if (this.collapseIcon == "el-icon-arrow-left") {
                this.collapseIcon = "el-icon-arrow-right"
                this.systemTitle = ""
                this.isCollapse = true
            }
            else {
                this.collapseIcon = "el-icon-arrow-left"
                this.isCollapse = false
                this.systemTitle = "物流货运系统"
            }
        },
        // gotoMyInfo() {
        //     this.$router.push('/myInfo')
        // },
        gotoGoods() {
            this.$router.push('/goods')
        },
        gotoOrder() {
            this.$router.push('/order')
        },
        gotoWaiting() {
            this.$router.push('/waitingList')
        },
        // gotoSelectScene() {
        //     this.$router.push('/selectScene')
        // },
        // gotoWelcome() {
        //     this.$router.push('/welcome')
        // },
        logout() {
            var token = JSON.parse(localStorage.getItem('token'))
            this.$http.post(
                "/user/logout", {}, {
                    headers: {
                        'token': token
                    }
                }
            ).then((response)=>{
                if (response.data.code == "1") {
                    localStorage.removeItem("token")
                    localStorage.removeItem("permission")
                    this.$router.push('/login')
                    this.$message({
                        type: 'success',
                        message: response.data.msg
                    });
                }

            })
        },
        fullScreen () {
            if (this.isfullScreen) {
                var docElm = document.documentElement
                if (docElm.requestFullscreen) {
                docElm.requestFullscreen()
                } else if (docElm.mozRequestFullScreen) {
                docElm.mozRequestFullScreen()
                } else if (docElm.webkitRequestFullScreen) {
                docElm.webkitRequestFullScreen()
                } else if (elem.msRequestFullscreen) {
                elem.msRequestFullscreen()
                }
                this.isfullScreen = false
            } else {
                if (document.exitFullscreen) {
                document.exitFullscreen()
                } else if (document.mozCancelFullScreen) {
                document.mozCancelFullScreen()
                } else if (document.webkitCancelFullScreen) {
                document.webkitCancelFullScreen()
                } else if (document.msExitFullscreen) {
                document.msExitFullscreen()
                }
                this.isfullScreen = true
            }
        },
        handleMessage (command) {
            if (command === "checkMsg") {
                alert("页面跳转")
            } else if (command === "clearMsg") {
                this.msgNum = "";
                this.$message({
                    type: 'success',
                    message: '全部已读!'
                });
            }
        },
        handleInfo (command) {
            if (command === "info") {
                this.dialogInfoVisible = true
                this.title = "编辑信息"
                this.userId = this.$store.getters.info.uid
            } else if (command === "editPassword") {
                this.dialogPassVisible = true
            } else if (command === "logout") {
                this.$router.push("/login")
                this.$message({
                    type: 'success',
                    message: '已退出登录!'
                });
            }
        },
    }
  }
</script>
