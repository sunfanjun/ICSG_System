<template>
  <el-menu :default-openeds="['1', '3']" style="min-height: 100%; overflow-x: hidden"
           background-color="rgb(48, 65, 86)"
           text-color="#fff"
           active-text-color="#ffd04b"
           :collapse-transition="false"
           :collapse="isCollapse"
           router
  >
    <div style="height: 60px; line-height: 60px; text-align: center">
      <img src="../assets/logo.png" alt="" style="width: 20px; position: relative; top: 5px; right: 5px">
      <b style="color: white" v-show="logoTextShow">后台管理系统</b>
    </div>
    <el-menu-item index="/home">
      <template slot="title">
        <i class="el-icon-house"></i>
        <span slot="title">首页</span>
      </template>
    </el-menu-item>
    <el-submenu index="3">
      <template slot="title">
        <i class="el-icon-folder-opened"></i>
        <span slot="title">业务中心</span>
      </template>
      <el-menu-item index="/event">
        <i class="el-icon-folder-add"></i>
        <span slot="title">按事件统计</span>
      </el-menu-item>
      <el-menu-item index="/schoolTable">
        <i class="el-icon-folder-add"></i>
        <span slot="title">按学校统计</span>
      </el-menu-item>
      <el-menu-item index="/dataAnalysis">
        <i class="el-icon-folder-add"></i>
        <span slot="title">数据总统计</span>
      </el-menu-item>
    </el-submenu>
    <el-submenu index="3-1">
      <template slot="title">
        <i class="el-icon-office-building"></i>
        <span slot="title">学校管理</span>
      </template>
      <el-menu-item index="/zju">
        <i class="el-icon-school"></i>
        <span slot="title">高校一号</span>
      </el-menu-item>
      <el-menu-item index="/zjut">
        <i class="el-icon-school"></i>
        <span slot="title">高校二号</span>
      </el-menu-item>
      <el-menu-item index="/zstu">
        <i class="el-icon-school"></i>
        <span slot="title">高校三号</span>
      </el-menu-item>
<!--      <el-menu-item index="/zstu">-->
<!--        <i class="el-icon-school"></i>-->
<!--        <span slot="title">高校四号</span>-->
<!--      </el-menu-item>-->
<!--      <el-menu-item index="/zstu">-->
<!--        <i class="el-icon-school"></i>-->
<!--        <span slot="title">高校五号</span>-->
<!--      </el-menu-item>-->
<!--      <el-menu-item index="/zstu">-->
<!--        <i class="el-icon-school"></i>-->
<!--        <span slot="title">高校六号</span>-->
<!--      </el-menu-item>-->
<!--      <el-menu-item index="/zstu">-->
<!--        <i class="el-icon-school"></i>-->
<!--        <span slot="title">高校七号</span>-->
<!--      </el-menu-item>-->
<!--      <el-menu-item index="/zstu">-->
<!--        <i class="el-icon-school"></i>-->
<!--        <span slot="title">高校八号</span>-->
<!--      </el-menu-item>-->
<!--      <el-menu-item index="/zstu">-->
<!--        <i class="el-icon-school"></i>-->
<!--        <span slot="title">高校九号</span>-->
<!--      </el-menu-item>-->
<!--      <el-menu-item index="/zstu">-->
<!--        <i class="el-icon-school"></i>-->
<!--        <span slot="title">高校十号</span>-->
<!--      </el-menu-item>-->
    </el-submenu>
    <el-submenu index="5">
          <template slot="title">
            <i class="el-icon-menu"></i>
            <span slot="title">系统管理</span>
          </template>
          <el-menu-item index="/user">
            <i class="el-icon-s-custom"></i>
            <span slot="title">组织管理</span>
          </el-menu-item>
          <el-menu-item index="/client">
            <i class="el-icon-s-custom"></i>
            <span slot="title">用户管理</span>
          </el-menu-item>
          <el-menu-item index="/file">
            <i class="el-icon-document"></i>
            <span slot="title">文件管理</span>
          </el-menu-item>
          <el-menu-item index="/log">
            <i class="el-icon-date"></i>
            <span slot="title">日志管理</span>
          </el-menu-item>
        </el-submenu>
  </el-menu>
</template>

<script>
export default {
  name: "Aside",
  props: {
    isCollapse: Boolean,
    logoTextShow: Boolean
  },
  methods: {
    handleSelect() {

    }
  }

}
</script>

<style scoped>

</style>