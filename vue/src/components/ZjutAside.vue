<template>
  <el-menu :default-openeds="['3']" style="min-height: 100%; overflow-x: hidden"
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
    <el-menu-item index="/1/zjutHome">
      <i class="el-icon-house"></i>
      <span slot="title">首页</span>
    </el-menu-item>
    <el-submenu index="2">
      <template slot="title">
        <i class="el-icon-edit-outline"></i>
        <span slot="title">基本信息</span>
      </template>
      <el-menu-item index="/1/zjutClient">
        <i class="el-icon-s-custom"></i>
        <span slot="title">用户管理</span>
      </el-menu-item>
      <el-menu-item index="/1/zjutMonitor">
        <i class="el-icon-s-platform"></i>
        <span slot="title">设备管理</span>
      </el-menu-item>
      <el-menu-item index="/1/zjutArea">
        <i class="el-icon-school"></i>
        <span slot="title">区域管理</span>
      </el-menu-item>
    </el-submenu>
    <el-submenu index="3">
      <template slot="title">
        <i class="el-icon-folder-opened"></i>
        <span slot="title">业务中心</span>
      </template>
      <el-menu-item index="/1/zjutPending">
        <i class="el-icon-folder-add"></i>
        <span slot="title">待处理事件</span>
      </el-menu-item>
      <el-menu-item index="/1/zjutProcessed">
        <i class="el-icon-folder-checked"></i>
        <span slot="title">已处理事件</span>
      </el-menu-item>
      <el-menu-item index="/1/zjutComplaint">
        <i class="el-icon-folder-delete"></i>
        <span slot="title">待申诉处理</span>
      </el-menu-item>
    </el-submenu>
    <el-submenu index="4">
      <template slot="title">
        <i class="el-icon-s-order"></i>
        <span slot="title">历史记录</span>
      </template>
      <el-menu-item index="/1/zjutJishui">
        <i class="el-icon-ice-drink"></i>
        <span slot="title">地面积水</span>
      </el-menu-item>
      <el-menu-item index="/1/zjutSmoke">
        <i class="el-icon-smoking"></i>
        <span slot="title">抽烟</span>
      </el-menu-item>
      <el-menu-item index="/1/zjutFire">
        <i class="el-icon-ice-cream-square"></i>
        <span slot="title">明火</span>
      </el-menu-item>
    </el-submenu>

        <el-submenu index="5">
          <template slot="title">
            <i class="el-icon-menu"></i>
            <span slot="title">系统管理</span>
          </template>
          <el-menu-item index="/1/File">
            <i class="el-icon-document"></i>
            <span slot="title">文件管理</span>
          </el-menu-item>
          <el-menu-item index="#">
            <i class="el-icon-date"></i>
            <span slot="title">日志管理</span>
          </el-menu-item>
          <el-menu-item index="#">
            <i class="el-icon-setting"></i>
            <span slot="title">告警频率设定</span>
          </el-menu-item>
          <el-menu-item index="#">
            <i class="el-icon-edit"></i>
            <span slot="title">划定危险区域</span>
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
  }

}
</script>

<style scoped>

</style>