import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import './assets/global.css'
import request from "@/utils/request";
import GoEasy from "goeasy";


Vue.config.productionTip = false

Vue.use(ElementUI, {size: "mini"});

// 全局注册了axios
Vue.prototype.request=request

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')

let goEasy = GoEasy.getInstance({
  host:'hangzhou.goeasy.io', //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
  appkey: "BC-fb8c1fc557fc4effae4d0fbcbd0ad968", //替换为您的应用appkey
  modules: ['pubsub']
});

//建立连接
goEasy.connect({
  onSuccess: function () {  //连接成功
    console.log("GoEasy connect successfully.") //连接成功
  },
  onFailed: function (error) { //连接失败
    console.log("Failed to connect GoEasy, code:"+error.code+ ",error:"+error.content);
  },
  onProgress:function(attempts) { //连接或自动重连中
    console.log("GoEasy is connecting", attempts);
  }
});
