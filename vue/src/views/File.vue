<!--<template>-->
<!--  <div>-->
<!--    <div style="margin: 10px 0">-->
<!--      <el-input style="width: 200px" placeholder="请输入名称" suffix-icon="el-icon-search" v-model="name"></el-input>-->
<!--      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>-->
<!--      <el-button type="warning" @click="reset">重置</el-button>-->
<!--    </div>-->
<!--    <div style="margin: 10px 0">-->
<!--      <el-upload action="http://localhost:9090/file/upload" :show-file-list="false" :on-success="handleFileUploadSuccess" style="display: inline-block">-->
<!--        <el-button type="primary" class="ml-5">上传文件 <i class="el-icon-top"></i></el-button>-->
<!--      </el-upload>-->
<!--      <el-popconfirm-->
<!--          class="ml-5"-->
<!--          confirm-button-text='确定'-->
<!--          cancel-button-text='我再想想'-->
<!--          icon="el-icon-info"-->
<!--          icon-color="red"-->
<!--          title="您确定批量删除这些数据吗？"-->
<!--          @confirm="delBatch"-->
<!--      >-->
<!--        <el-button type="danger" slot="reference">批量删除 <i class="el-icon-remove-outline"></i></el-button>-->
<!--      </el-popconfirm>-->

<!--    </div>-->
<!--    <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'"  @selection-change="handleSelectionChange">-->
<!--      <el-table-column type="selection" width="55"></el-table-column>-->
<!--      <el-table-column prop="id" label="ID" width="80"></el-table-column>-->
<!--      <el-table-column prop="name" label="文件名称"></el-table-column>-->
<!--      <el-table-column prop="type" label="文件类型"></el-table-column>-->
<!--      <el-table-column prop="size" label="文件大小(kb)"></el-table-column>-->
<!--      <el-table-column label="预览">-->
<!--        <template slot-scope="scope">-->
<!--          <el-button type="primary" @click="preview(scope.row.url)">预览</el-button>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column label="下载">-->
<!--        <template slot-scope="scope">-->
<!--          <el-button type="primary" @click="download(scope.row.url)">下载</el-button>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column label="启用">-->
<!--        <template slot-scope="scope">-->
<!--          <el-switch v-model="scope.row.enable" active-color="#13ce66" inactive-color="#ccc" @change="changeEnable(scope.row)"></el-switch>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column label="操作"  width="200" align="center">-->
<!--        <template slot-scope="scope">-->
<!--          <el-popconfirm-->
<!--              class="ml-5"-->
<!--              confirm-button-text='确定'-->
<!--              cancel-button-text='我再想想'-->
<!--              icon="el-icon-info"-->
<!--              icon-color="red"-->
<!--              title="您确定删除吗？"-->
<!--              @confirm="del(scope.row.id)"-->
<!--          >-->
<!--            <el-button type="danger" slot="reference">删除 <i class="el-icon-remove-outline"></i></el-button>-->
<!--          </el-popconfirm>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--    </el-table>-->

<!--    <div style="padding: 10px 0">-->
<!--      <el-pagination-->
<!--          @size-change="handleSizeChange"-->
<!--          @current-change="handleCurrentChange"-->
<!--          :current-page="pageNum"-->
<!--          :page-sizes="[2, 5, 10, 20]"-->
<!--          :page-size="pageSize"-->
<!--          layout="total, sizes, prev, pager, next, jumper"-->
<!--          :total="total">-->
<!--      </el-pagination>-->
<!--    </div>-->

<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--export default {-->
<!--  name: "File",-->
<!--  data() {-->
<!--    return {-->
<!--      tableData: [],-->
<!--      name: '',-->
<!--      multipleSelection: [],-->
<!--      pageNum: 1,-->
<!--      pageSize: 10,-->
<!--      total: 0-->
<!--    }-->
<!--  },-->
<!--  created() {-->
<!--    this.load()-->
<!--  },-->
<!--  methods: {-->
<!--    load() {-->
<!--      this.request.get("/file/page", {-->
<!--        params: {-->
<!--          pageNum: this.pageNum,-->
<!--          pageSize: this.pageSize,-->
<!--          name: this.name,-->
<!--        }-->
<!--      }).then(res => {-->

<!--        this.tableData = res.data.records-->
<!--        this.total = res.data.total-->

<!--      })-->
<!--    },-->
<!--    changeEnable(row) {-->
<!--      this.request.post("/file/update", row).then(res => {-->
<!--        if (res.code === '200') {-->
<!--          this.$message.success("操作成功")-->
<!--        }-->
<!--      })-->
<!--    },-->
<!--    del(id) {-->
<!--      this.request.delete("/file/" + id).then(res => {-->
<!--        if (res.code === '200') {-->
<!--          this.$message.success("删除成功")-->
<!--          this.load()-->
<!--        } else {-->
<!--          this.$message.error("删除失败")-->
<!--        }-->
<!--      })-->
<!--    },-->
<!--    handleSelectionChange(val) {-->
<!--      console.log(val)-->
<!--      this.multipleSelection = val-->
<!--    },-->
<!--    delBatch() {-->
<!--      let ids = this.multipleSelection.map(v => v.id)  // [{}, {}, {}] => [1,2,3]-->
<!--      this.request.post("/file/del/batch", ids).then(res => {-->
<!--        if (res.code === '200') {-->
<!--          this.$message.success("批量删除成功")-->
<!--          this.load()-->
<!--        } else {-->
<!--          this.$message.error("批量删除失败")-->
<!--        }-->
<!--      })-->
<!--    },-->
<!--    reset() {-->
<!--      this.name = ""-->
<!--      this.load()-->
<!--    },-->
<!--    handleSizeChange(pageSize) {-->
<!--      console.log(pageSize)-->
<!--      this.pageSize = pageSize-->
<!--      this.load()-->
<!--    },-->
<!--    handleCurrentChange(pageNum) {-->
<!--      console.log(pageNum)-->
<!--      this.pageNum = pageNum-->
<!--      this.load()-->
<!--    },-->
<!--    handleFileUploadSuccess(res) {-->
<!--      console.log(res)-->
<!--      this.load()-->
<!--    },-->
<!--    download(url) {-->
<!--      window.open(url)-->
<!--    },-->
<!--    preview(url) {-->
<!--      window.open('https://file.keking.cn/onlinePreview?url=' + encodeURIComponent(window.btoa((url))))-->
<!--    },-->
<!--  }-->
<!--}-->
<!--</script>-->

<!--<style scoped>-->

<!--</style>-->

<!--<template>-->
<!--  <div>-->
<!--    &lt;!&ndash; 上传图片按钮 &ndash;&gt;-->
<!--    <el-upload-->
<!--        class="upload-demo"-->
<!--        action="/your-upload-api"-->
<!--        :before-upload="beforeUpload"-->
<!--        :on-success="handleSuccess"-->
<!--        :on-remove="handleRemove"-->
<!--    >-->
<!--      <el-button size="small" type="primary">点击上传</el-button>-->
<!--      <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>-->
<!--    </el-upload>-->

<!--    &lt;!&ndash; 图片预览 &ndash;&gt;-->
<!--    <el-image-->
<!--        v-if="imageUrl"-->
<!--        :src="imageUrl"-->
<!--        style="width: 100px; height: 100px; margin-top: 10px"-->
<!--        fit="cover"-->
<!--    ></el-image>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--export default {-->
<!--  data() {-->
<!--    return {-->
<!--      imageUrl: '', // 存放上传成功后的图片地址-->
<!--    };-->
<!--  },-->
<!--  methods: {-->
<!--    // 上传前的钩子函数-->
<!--    beforeUpload(file) {-->
<!--      const isJpgOrPng = file.type === 'image/jpeg' || file.type === 'image/png';-->
<!--      if (!isJpgOrPng) {-->
<!--        this.$message.error('只能上传jpg/png文件');-->
<!--        return false;-->
<!--      }-->
<!--      const isLt500K = file.size / 1024 < 500;-->
<!--      if (!isLt500K) {-->
<!--        this.$message.error('图片大小不能超过500KB');-->
<!--        return false;-->
<!--      }-->
<!--      return true;-->
<!--    },-->
<!--    // 上传成功的钩子函数-->
<!--    handleSuccess(response, file) {-->
<!--      this.imageUrl = URL.createObjectURL(file.raw);-->
<!--      this.$message.success('上传成功');-->
<!--    },-->
<!--    // 移除图片的钩子函数-->
<!--    handleRemove(file, fileList) {-->
<!--      this.imageUrl = '';-->
<!--      this.$message.success('删除成功');-->
<!--    },-->
<!--  },-->
<!--};-->
<!--</script>-->

<!--<style scoped>-->
<!--.upload-demo {-->
<!--  display: inline-block;-->
<!--  margin-right: 12px;-->
<!--}-->
<!--</style>-->

<template>
  <div>
<el-upload
    class="avatar-uploader"
    action="http://10.81.110.252:9090/detect/best"
    :show-file-list="true"
    :on-success="handleAvatarSuccess"
    :before-upload="beforeAvatarUpload">
<img v-if="imageUrl" :src="imageUrl" class="avatar">
<i v-else class="el-icon-plus avatar-uploader-icon"></i>
</el-upload>
  <div>
    <el-input v-model="type" style="width:150px;" readonly="true"></el-input>
  </div>
  </div>
</template>



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

<script>
export default {
  data() {
    return {
      imageUrl: '',
      type: ''
    };
  },
  methods: {
    handleAvatarSuccess(res, file) {
      console.log(res)
      this.type = res.data
      console.log(this.type)
      this.imageUrl = URL.createObjectURL(file.raw);
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg'|| file.type === 'image/png';
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }
      return isJPG && isLt2M;
    }
  }
}
</script>