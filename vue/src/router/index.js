import Vue from 'vue'
import VueRouter from 'vue-router'
import store from "@/store"

Vue.use(VueRouter)

const routes = [
  {
    path: '/1',
    component: () => import('../views/ZjutManage.vue'),
    redirect: "/1/zjutHome",
    children: [
      { path: 'zjutHome', name: '首页', component: () => import('../views/ZjutHome.vue')},
      { path: 'zjutClient', name: '基本信息 / 用户管理', component: () => import('../views/ZjutClient.vue')},
      { path: 'zjutMonitor', name: '基本信息 / 设备管理', component: () => import('../views/ZjutMonitor.vue')},
      { path: 'zjutArea', name: '基本信息 / 区域管理', component: () => import('../views/ZjutArea.vue')},
      { path: 'person', name: '个人信息', component: () => import('../views/Person.vue')},
      { path: 'password', name: '修改密码', component: () => import('../views/Password.vue')},
      { path: 'zjutPending', name: '业务中心 / 待处理事件', component: () => import('../views/ZjutPending.vue')},
      { path: 'zjutProcessed', name: '业务中心 / 已处理事件', component: () => import('../views/ZjutProcessed.vue')},
      { path: 'zjutComplaint', name: '业务中心 / 待申诉事件', component: () => import('../views/ZjutComplaint.vue')},
      // { path: 'zjutDataAnalysis', name: '业务中心 / 数据分析', component: () => import('../views/ZjutDataAnalysis.vue')},
      // { path: 'zjutWater', name: '历史记录 / 水边等危险区域', component: () => import('../views/ZjutWater.vue')},
      // { path: 'zjutIntrude', name: '历史记录 / 特定区域人员闯入', component: () => import('../views/ZjutIntrude.vue')},
      // { path: 'zjutLeak', name: '历史记录 / 墙面房顶渗漏水', component: () => import('../views/ZjutLeak.vue')},
      // { path: 'zjutWander', name: '历史记录 / 楼顶人员徘徊', component: () => import('../views/ZjutWander.vue')},
      // { path: 'zjutFencing', name: '历史记录 / 身体超出围栏', component: () => import('../views/ZjutFencing.vue')},
      { path: 'zjutJishui', name: '历史记录 / 地面积水', component: () => import('../views/ZjutJishui.vue')},
      { path: 'zjutSmoke', name: '历史记录 / 抽烟', component: () => import('../views/ZjutSmoke.vue')},
      // { path: 'zjutFall', name: '历史记录 / 跌倒', component: () => import('../views/ZjutFall.vue')},
      // { path: 'zjutWave', name: '历史记录 / 挥手', component: () => import('../views/ZjutWave.vue')},
      { path: 'zjutFire', name: '历史记录 / 明火', component: () => import('../views/ZjutFire.vue')},
      // { path: 'zjutSmog', name: '历史记录 / 烟雾', component: () => import('../views/ZjutSmog.vue')},
      { path: 'file', name: '系统管理 / 文件管理', component: () => import('../views/File.vue')},
      // { path: 'log', name: '系统管理 / 日志管理', component: () => import('../views/Log.vue')},
      // { path: 'alarmFrequency', name: '系统管理 / 告警频率设定', component: () => import('../views/AlarmFrequency.vue')},
      // { path: 'biaozhu', name: '系统管理 / 划定危险区域', component: () => import('../views/Biaozhu.vue')},
      // { path: 'reset', name: '系统管理 / 数据集重标注', component: () => import('../views/Reset.vue')},
    ]

  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  // {
  //   path: '/video',
  //   name: 'Video',
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () => import(/* webpackChunkName: "about" */ '../views/Video.vue')
  // },
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/Login.vue')
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import('../views/Register.vue')
  },
  {
    path: '/test1',
    name: 'test1',
    component: () => import('../views/test1.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

// 路由守卫
router.beforeEach((to, from, next) => {
  localStorage.setItem("currentPathName", to.name) //设置当前的路由名称，为了在Header组件中去使用
  store.commit("setPath") //触发store的数据更新
  next() //放行路由
})

export default router
