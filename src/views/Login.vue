<template>
    <div class="box">
      <div ref="vantaRef" style="width: 100%; height: 100vh"></div>
      <div class="banner">

        <div class="loginCon">
            <p class="title">物流货运系统</p>
            <p class="title">shipping and transportation system </p>
            <el-card shadow="always" class="login-module" v-if="smdl">
                <div slot="header" class="clearfix formTitlt">
                    <span>{{ topText }}</span>
                </div>
                <el-form label-position="left" :model="loginForm" status-icon label-width="50px" 
                    class="demo-ruleForm">
                    <div style="float: top;">
                        <el-form-item label="账号">
                            <el-input style="float: left;" type="text" v-model="loginForm.username" auto-complete="off" 
                                        placeholder="请输入账号">
                            </el-input>
                        </el-form-item>
                        <el-form-item label="密码">
                            <el-input style="float: left;" type="password" v-model="loginForm.password" auto-complete="off"
                                        placeholder="请输入密码">
                            </el-input>
                        </el-form-item>
                    </div>
                    <div style="float: bottom;">
                        <el-button style="width:100%; margin-bottom:15px;" class="subBtn" type="primary"
                            :loading="loading" @click="submitForm">{{loginAndRegisterText}}</el-button>
                    </div>
                    <el-button style="float: right;" type="text" @click="changeIdentity">
                        {{ bottomBtnText }}
                    </el-button>
                    <el-button style="float: left;" type="text" @click="toRegister">
                        托运人注册
                    </el-button>
                </el-form>
          </el-card>
        </div>
      </div>
    </div>
  </template>
  
<script>
import * as THREE from "three";
import BIRDS from "vanta/src/vanta.birds";
  
export default {
    computed: {
        isShipper() {
            if (this.isRoot == '1') {
                return '好';
            }
        }
    },
    data() {
        return {
            smdl: true,
            loginForm: {
            username: "admin",
            password: "123"
            },
            loading: false,
            identity: '1',
            bottomBtnText: '承运商登录',
            topText: '托运人登录',
            loginAndRegisterText: '登录'
        };
    },
    methods: {
        changeIdentity() {
            if (this.identity !== '2') {
                this.identity = '2'
                this.topText = '承运商登录'
                this.bottomBtnText = '托运人登录'
                this.loginAndRegisterText = '登录'
            }
            else if (this.identity !== '1') {
                this.identity = '1'
                this.topText = '托运人登录'
                this.bottomBtnText = '承运商登录'
                this.loginAndRegisterText = '登录'
            }
        },
        toRegister() {
            this.identity = '0'
            this.topText = '注册'
            this.loginAndRegisterText = '注册'
        },
        submitForm () {
            this.loading = true;
            if (this.identity === '0') {
                // 注册
                this.$http.post(
                    "/user/register", 
                    {"username": this.loginForm.username, "password": this.loginForm.password}
                ).then((response)=>{
                    if (response.data.code == "1") {
                        this.$message({
                            type: 'success',
                            message: response.data.msg
                        });
                        this.loading = false;
                        this.$router.go(0)
                        // this.$router.push("/login")
                    }
                    else {
                        this.$message({
                            type: 'error',
                            message: response.data.msg
                        });
                        this.loading = false;
                    }

                })
            }
            else {
                // 登录
                this.$http.post(
                    "/user/login", 
                    {"username": this.loginForm.username, "password": this.loginForm.password}
                ).then((response)=>{
                    if (response.data.code == "1") {
                        this.$message({
                            type: 'success',
                            message: response.data.msg
                        });
                        this.loading = false;
                        localStorage.setItem("token", JSON.stringify(response.data.data.token))
                        localStorage.setItem("permission", JSON.stringify(response.data.data.permission))
                        this.$router.push("/index")
                    }
                    else {
                        this.$message({
                            type: 'error',
                            message: '用户名或密码错误'
                        });
                        this.loading = false;
                    }

                })
            }
            
        }
    },
    mounted() {
        sessionStorage.removeItem("addTab")
        this.vantaEffect = BIRDS({
            el: this.$refs.vantaRef,
            THREE: THREE,
        });
        // 修改颜色时 cells 需要全大写字母 可生效
        VANTA.BIRDS({
            el: this.$refs.vantaRef,
            mouseControls: true,
            touchControls: true,
            gyroControls: false,
            minHeight: 200.0,
            minWidth: 200.0,
            scale: 1.0,
            color1: 14381274,
            color2: 16443110,
        });
    },
    beforeDestroy() {
        if (this.vantaEffect) {
            this.vantaEffect.destroy();
        }
    },
  };
  </script>
  
  <style lang="less" scoped>
.box {
    position: relative;
    .banner {
        z-index: 999;
        position: absolute;
        top: 30%;
        left: 10%;
        color: #fff;
    }
}
h1 {
    font-size: 66px;
}
p {
    margin-top: 60px;
    font-size: 18px;
}

.loginCon {
    width: 990px;
    margin: auto;
    position: relative;
    height: 388px;
    opacity: 0.9;

    .el-card__header {
        border-bottom: 0px;
    }
    .title{
        font-size: 36px;
        font-weight: 600;
        color: #ffffff;
        width: 500px;
        float: left;
        margin-top: 0px;
        &:first-child{
            font-size: 50px;
            margin-top: 50px;
            margin-bottom: 30px;
        }
    }
    .login-module {
        width: 380px;
        height: 325px;
        margin-top: 0%;
        margin-left: 60%;
        border: none;
        position: absolute;
        // left: 0;
        // top: 0;
        // right: 0;
        // bottom: 0;

        .formTitlt {
            font-size: 18px;
            font-weight: 400;
        }

        .smalltxt {
            text-align: right;

            // .a {
            //     text-decoration: none;
            //     color: #999999;
            //     font-size: 12px;
            //     margin-left: 8px;
            // }
        }
    }

    .el-form-item__content {
        margin-left: 0px !important;

        .subBtn {
            width: 100%;
        }
    }
}

    // .el-input__inner, .el-button, .el-card, .el-message {
    //     border-radius: 0px !important;
    // }


</style>
  
  