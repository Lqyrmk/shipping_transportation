import VueRouter from "vue-router"
import Vue from "vue"
import Order from "../views/Order.vue"
import App from "../App.vue"
import Index from "../views/Index.vue"
import Goods from '../views/Goods.vue'
import WaitingList from '../views/WaitingList.vue'
import Login from '../views/Login.vue'
import Welcome from '../views/Welcome.vue'
import Main from '../views/Main.vue'

Vue.use(VueRouter)

const router = new VueRouter({
    routes: [
        {
            path: "/",
            redirect: '/login', // 还没有加拦截器，加了应该用/index
        },
        {
            path: "/login",
            name: "登录界面",
            component: Login,
        },
        {
            path: "/welcome",
            name: "前台",
            component: Welcome,
            redirect: '/main',
            children: [
                {
                    path: "/main",
                    name: "首页",
                    component: Main,
                },
            ]
        },
        {
            path: "/index",
            name: "后台",
            component: Index,
            redirect: '/goods',
            children: [
                {
                    path: "/goods",
                    name: "货物列表",
                    component: Goods
                },
                {
                    path: "/order",
                    name: "订单列表",
                    component: Order
                },
                {
                    path: "/waitingList",
                    name: "待运输货物列表",
                    component: WaitingList
                },
            ]
        },

    ]
})



export default router
