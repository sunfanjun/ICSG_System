<template>
  <el-container style="min-height: 100vh">

    <el-aside :width="sideWidth + 'px'" style="box-shadow: 2px 0 6px rgb(0 21 41 / 35%)">
      <ZjutAside :isCollapse="isCollapse" :logoTextShow="logoTextShow" :menus="user" />
    </el-aside>

    <el-container>
      <el-header style="border-bottom: 1px solid #ccc">
        <Header :collapseBtnClass="collapseBtnClass" @asideCollapse="collapse" :user="user" />
      </el-header>

      <el-main>
<!--        表示当前页面的子路由会在 <router-view /> 里面展示-->
        <router-view @refreshUser="getUser"/>
      </el-main>

    </el-container>
  </el-container>
</template>

<script>

import ZjutAside from "@/components/ZjutAside";
import Header from "@/components/Header";

export default {
  name: 'Manage1',
  data() {
    return {
      collapseBtnClass: 'el-icon-s-fold',
      isCollapse: false,
      sideWidth: 230,
      logoTextShow: true,
      user: {}
    }
  },
  components: {
    ZjutAside,
    Header
  },
  created() {
    this.getUser()
  },
  methods: {
    collapse() {  // 点击收缩按钮触发
      this.isCollapse = !this.isCollapse
      if (!this.isCollapse) {  // 展开
        this.sideWidth = 230
        this.collapseBtnClass = 'el-icon-s-fold'
        this.logoTextShow = true
      } else {   // 收缩
        this.sideWidth = 64
        this.collapseBtnClass = 'el-icon-s-unfold'
        this.logoTextShow = false

      }
    },
    // 获取用户的最新数据
    getUser() {
      let username = localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")).username : ""
      if (username) {
        // 从后台获取User数据
        this.request.post("/account/login" + this.user).then(res => {
          this.user = res.data
        })
      }
    }

  }
}
</script>


