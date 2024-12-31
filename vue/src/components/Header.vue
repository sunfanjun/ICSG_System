<template>
  <div style="line-height: 60px; display: flex">
    <div style="flex: 1;">
      <span :class="collapseBtnClass" style="cursor: pointer; font-size: 18px" @click="collapse"></span>

      <el-breadcrumb separator="/" style="display: inline-block; margin-left: 10px">
        <el-breadcrumb-item :to="'/1'">首页</el-breadcrumb-item>
        <el-breadcrumb-item>{{ currentPathName }}</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <span style="display: inline-block; margin-right: 15px">欢迎您!管理员 {{ user.username }}</span> 
    <el-dropdown style="width: 100px; cursor: pointer">
      <div style="display: inline-block">
        <img src="https://img.zcool.cn/community/01ee3758158d4ba84a0d304fbe062e.png?x-oss-process=image/auto-orient,1/resize,m_lfit,w_1280,limit_1/sharpen,100" alt=""
             style="width: 30px; border-radius: 50%;position: relative; top: 10px; right: 5px">
           <i class="el-icon-arrow-down" style="margin-left: 5px"></i>
      </div>
      <el-dropdown-menu slot="dropdown" style="width: 100px; text-align: center">
        <el-dropdown-item style="font-size: 14px; padding: 5px 0">
          <span style="text-decoration: none" @click="changPassword">修改密码</span>
        </el-dropdown-item>
        <el-dropdown-item style="font-size: 14px; padding: 5px 0">
          <span style="text-decoration: none" @click="perMsg">个人信息</span>
        </el-dropdown-item>
        <el-dropdown-item style="font-size: 14px; padding: 5px 0">
          <span style="text-decoration: none" @click="logout">退出</span>
        </el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<script>
export default {
  name: "Header",
  props: {
    collapseBtnClass: String,
  },
  data() {
    return {
      currentPathName: '',
      user:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
    }
  },
  mounted(){
    //判断token
    this.judge()
  },
  methods: {
    collapse() {
      // this.$parent.$parent.$parent.$parent.collapse() // 通过4个 $parent 找到父组件，从而调用其折叠方法
      this.$emit("asideCollapse")
    },
    judge(){
      console.log(localStorage.getItem("user"))
      if(localStorage.getItem("user")==null){
        this.$router.push("/login")
      }
    },
    logout() {
      this.$router.push("/login")
      localStorage.removeItem("user")
      this.$message.success("退出成功")
    },
    perMsg() {
      if(localStorage.getItem("role") == "管理员")
      {
        this.$router.push("/person")
      } else {
        this.$router.push("/1/person")
      }

    },
    changPassword() {
      this.$router.push("/1/password")
    }
  },
  watch: {
    '$route': function() {
      this.currentPathName = localStorage.getItem("currentPathName")
    }
  }
}
</script>

<style scoped>

</style>