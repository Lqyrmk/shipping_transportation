import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'
import VueSession from 'vue-session'
// 引入echarts
import * as echarts from 'echarts'
import horizontalScroll from 'el-table-horizontal-scroll'

// 整合vue
axios.defaults.baseURL = "http://localhost:8088"
Vue.prototype.$http = axios

Vue.prototype.$echarts = echarts
Vue.config.productionTip = false
Vue.use(ElementUI); // 全局注册ElementUI
Vue.use(VueSession);
Vue.use(horizontalScroll)

new Vue({
  render: h => h(App),
  router: router
}).$mount('#app')
