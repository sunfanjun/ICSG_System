<template>
  <el-card style="width:600px; padding: 20px">
    <el-form label-width="80px" size="small">
      <div style="text-align: center">
        <el-upload
            class="avatar-uploader"
            action="http://localhost:9090/file/upload"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
        >
          <img v-if="form.avatarUrl" :src="form.avatarUrl" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
      </div>
      <el-form-item label="用户名">
        <el-input v-model="form.username" disabled autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="昵称">
        <el-input v-model="form.nickname" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="邮箱">
        <el-input v-model="form.email" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="电话">
        <el-input v-model="form.phone" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="地址">
        <el-input v-model="form.address" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item>
        <div style="margin-left: 145px">
          <el-button type="primary" @click="save">确 定</el-button>
        </div>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script>
export default {
  name: "Person",
  data() {
    return {
      form: {},
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  },
  created() {
    const username = this.user.username
    if (!username) {
      this.$message.error("无法获取用户信息！")
      return
    }
    // this.request.get("user/username/" + username).then(res => {
    //   this.form = res.data
    // })
  },
  methods: {
    save() {
      // this.request.post("/user", this.form).then(res => {
      //   if (res.code === '200') {
      //     this.$message.success("保存成功")
      //     this.load()
      //
      //     this.$emit('refreshUser')
      //   } else {
      //     this.$message.error("保存失败")
      //   }
      // })
    },
    // save(){
    //   this.request.post("/user", this.form).then(res => {
    //     if (res.code === '200') {
    //       this.$message.success("保存成功")
    //
    //       //更新浏览器存储的用户信息
    //       this.getUser().then(res => {
    //         res.token = JSON.parse(localStorage.getItem("user")).token
    //         localStorage.setItem("user", JSON.stringify(res))
    //       })
    //
    //     }
    //     else{
    //       this.$message.error("保存失败")
    //     }
    //   })
    // },
    handleAvatarSuccess(res) {
      // res就是文件的路径
      this.form.avatarUrl = res
    }
  }

}
</script>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>