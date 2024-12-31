<template>
  <div class="wrapper">
    <div style="margin: 200px auto; background-color: #fff; width: 420px; height: 350px; padding: 20px; border-radius: 10px">
      <div style="margin: 20px 0; text-align: center; font-size: 24px"><b>登录</b></div>
      <el-form :model="user" :rules="rules" ref="userForm">
        <!-- <el-form-item label="身份：" prop="role" :popper-append-to-body="false">
          <el-select placeholder="请选择身份"  v-model="user.role">
            <el-option label="管理员" value="1" ></el-option>
            <el-option label="保安" value="0"></el-option>
          </el-select>
        </el-form-item> -->
        <el-form-item prop="username" label="账号：">
          <el-input placeholder="请输入账号" size="medium" style="margin: 5px 0" prefix-icon="el-icon-user" v-model="user.username"></el-input>
        </el-form-item>
        <el-form-item prop="password" label="密码：">
          <el-input placeholder="请输入密码" size="medium" style="margin: 5px 0" prefix-icon="el-icon-lock" show-password v-model="user.password"></el-input>
        </el-form-item>
        <el-form-item style="margin: 5px 0; text-align: right">
          <el-button type="primary" size="small"  autocomplete="off" @click='login'>登录</el-button>
          <el-button type="warning" size="small"  autocomplete="off" @click="$router.push('/register')">注册</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      user: {}, //上面的model是一个双向绑定的功能，将值直接给到rules进行判断
      rules: {
        // role:[
        //   {required:true,message: '请选择身份', trigger: 'blur'}
        // ],
        username: [ //required表示必填
          { required: true, message: '请输入用户名', trigger: 'blur' },// blur表示鼠标失交的时候会触发校验
          { min: 6, max: 12, message: '长度在 6 到 12 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, max: 20, message: '长度在  到 20 个字符', trigger: 'blur' }
        ],
      },
    }
  },
  methods: {
    login() {
      this.$refs['userForm'].validate((valid) => {
        if (valid) {  // 表单校验合法
          this.request.post("/account/login", this.user).then(res => {
            if(res.code === '200') {
              localStorage.setItem("user", JSON.stringify(this.user))  // 存储用户信息到浏览器
              localStorage.setItem("token",JSON.stringify(res.data))
              console.log(res.data)
              this.$router.push('/1')
              this.$message.success("登录成功")
            } else {
              this.$message.error(res.message)
            }
          })
        }
      });
    },
    
  }
}
</script>

<style>
.wrapper {
  height: 100vh;
  background-image: linear-gradient(to bottom right, #FC466B , #3F5EFB);
  overflow: hidden;
}
.input-with-select {
  background-color: #fff;
}
</style>
