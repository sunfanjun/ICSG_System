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
    <el-menu-item index="/1/zjuHome">
      <template slot="title">
        <i class="el-icon-house"></i>
        <span slot="title">首页</span>
      </template>
    </el-menu-item>
    <el-submenu index="2">
      <template slot="title">
        <i class="el-icon-edit-outline"></i>
        <span slot="title">基本信息</span>
      </template>
      <el-menu-item index="/1/zjuClient">
        <i class="el-icon-s-custom"></i>
        <span slot="title">用户管理</span>
      </el-menu-item>
    </el-submenu>
    <el-submenu index="3">
      <template slot="title">
        <i class="el-icon-folder-opened"></i>
        <span slot="title">业务中心</span>
      </template>
      <el-menu-item index="/1/zjuPending">
        <i class="el-icon-folder-add"></i>
        <span slot="title">待处理事件</span>
      </el-menu-item>
      <el-menu-item index="/1/zjuProcessed">
        <i class="el-icon-folder-checked"></i>
        <span slot="title">已处理事件</span>
      </el-menu-item>
      <el-menu-item index="/1/zjuComplaint">
        <i class="el-icon-folder-delete"></i>
        <span slot="title">待申诉处理</span>
      </el-menu-item>
      <el-menu-item index="/1/zjuDataAnalysis">
        <i class="el-icon-picture-outline-round"></i>
        <span slot="title">数据分析</span>
      </el-menu-item>
    </el-submenu>
    <el-submenu index="4">
      <template slot="title">
        <i class="el-icon-s-order"></i>
        <span slot="title">历史记录</span>
      </template>
      <el-menu-item index="/1/zjuWater">
        <i class="el-icon-cherry"></i>
        <span slot="title">水边等危险区域</span>
      </el-menu-item>
      <el-menu-item index="/1/zjuIntrude">
        <i class="el-icon-pear"></i>
        <span slot="title">特定区域人员闯入</span>
      </el-menu-item>
      <el-menu-item index="/1/zjuLeak">
        <i class="el-icon-orange"></i>
        <span slot="title">墙面房顶渗漏水</span>
      </el-menu-item>
      <el-menu-item index="/1/zjuWander">
        <i class="el-icon-coffee"></i>
        <span slot="title">楼顶人员徘徊</span>
      </el-menu-item>
      <el-menu-item index="/1/zjuFencing">
        <i class="el-icon-coffee"></i>
        <span slot="title">身体超出围栏</span>
      </el-menu-item>
      <el-menu-item index="/1/zjuJishui">
        <i class="el-icon-ice-drink"></i>
        <span slot="title">地面积水</span>
      </el-menu-item>
      <el-menu-item index="/1/zjuSmoke">
        <i class="el-icon-milk-tea"></i>
        <span slot="title">抽烟</span>
      </el-menu-item>
      <el-menu-item index="/1/zjuFall">
        <i class="el-icon-potato-strips"></i>
        <span slot="title">跌倒</span>
      </el-menu-item>
      <el-menu-item index="/1/zjuWave">
        <i class="el-icon-lollipop"></i>
        <span slot="title">挥手</span>
      </el-menu-item>
      <el-menu-item index="/1/zjuFire">
        <i class="el-icon-ice-cream-square"></i>
        <span slot="title">明火</span>
      </el-menu-item>
      <el-menu-item index="/1/zjuSmog">
        <i class="el-icon-ice-cream-round"></i>
        <span slot="title">烟雾</span>
      </el-menu-item>
    </el-submenu>

        <el-submenu index="5">
          <template slot="title">
            <i class="el-icon-menu"></i>
            <span slot="title">系统管理</span>
          </template>
          <el-menu-item index="/1/file">
            <i class="el-icon-document"></i>
            <span slot="title">文件管理</span>
          </el-menu-item>
          <el-menu-item index="/1/log">
            <i class="el-icon-date"></i>
            <span slot="title">日志管理</span>
          </el-menu-item>
          <el-menu-item index="/1/alarmFrequency">
            <i class="el-icon-setting"></i>
            <span slot="title">告警频率设定</span>
          </el-menu-item>
          <el-menu-item index="/1/biaozhu">
            <i class="el-icon-edit"></i>
            <span slot="title">划定危险区域</span>
          </el-menu-item>
          <el-menu-item index="/1/reset">
            <i class="el-icon-s-tools"></i>
            <span slot="title">数据集重标注</span>
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