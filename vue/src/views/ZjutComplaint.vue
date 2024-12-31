<template>
  <el-card>
    <div style="margin: 10px 0">
      <span style="font-size: 13px">申诉日期：</span>
      <el-date-picker
          v-model="time"
          align="right"
          type="date"
          format="yyyy 年 MM 月 dd 日"
          value-format="yyyy-MM-dd"
          placeholder="选择日期"
          :picker-options="pickerOptions1">
      </el-date-picker>
      <span style="font-size: 13px; margin-left:2px">发生区域：</span>
      <el-select v-model="aid" filterable placeholder="请选择" @change="handleSelectionChange">
        <el-option
            v-for="item in areaoption"
            :key="item.id"
            :label="item.name"
            :value="item.id">
        </el-option>
      </el-select>
      <span style="font-size: 13px; margin-left:2px">事件类型：</span>
      <el-input style="width: 180px" placeholder="请输入事件" suffix-icon="el-icon-tickets" class="ml-5" v-model="type"></el-input>
      <span style="font-size: 13px; margin-left:2px">申诉人员：</span>
      <el-input style="width: 180px" placeholder="请输入申诉人员" suffix-icon="el-icon-user" class="ml-6" v-model="pName"></el-input>
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button type="warning" @click="reset">重置</el-button>
    </div>
    <el-tabs type="card">
      <div>
        <el-table :data="tableData" border stripe :header-cell-class-name="headerBg">
          <el-table-column prop="id" label="编号" width="80"></el-table-column>
          <el-table-column prop="time" label="申诉时间" ></el-table-column>
          <el-table-column prop="danger.monitor.area.name" label="发生地点"></el-table-column>
          <el-table-column prop="danger.type" label="事件类型"></el-table-column>
          <el-table-column prop="danger.monitor.id" label="记录的摄像头"></el-table-column>
          <el-table-column prop="danger.securityGuard.name" label="申诉人员"></el-table-column>
          <el-table-column prop="reason" label="申诉理由" width="200px"></el-table-column>
          <el-table-column prop="photo" label="操作" width="200px">
            <template slot-scope="scope">
              <el-button type="primary" @click="preview(scope.row.url)">查看详情</el-button>
              <el-button type="success" @click="handleYes(scope.row.id)">同意 <i class="el-icon-finished"></i></el-button>
            </template>
          </el-table-column>
        </el-table>
        <div style="padding: 10px 0">
          <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="pageNum"
              :page-sizes="[2, 5, 10, 20]"
              :page-size="pageSize"
              layout="total, sizes, prev, pager, next, jumper"
              :total="total">
          </el-pagination>
          <el-dialog :visible.sync="showResource" title="告警事件">
            <template v-if="isImage">
              <img :src="resourceSrc" style="max-width: 100%;" />
            </template>
            <template v-else-if="isVideo">
              <video controls style="max-width: 100%;">
                <source :src="resourceSrc" type="video/mp4" />
              </video>
            </template>
            <template v-else>
              <p>Unsupported resource type.</p>
            </template>
          </el-dialog>
        </div>
      </div>
    </el-tabs>
  </el-card>


</template>

<script>
export default {
  name: "ZjuComplaint",
  data(){
    return {
      showResource: false,
      resourceSrc: "",
      isImage: false,
      isVideo: false,
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 10,
      id: "",
      time: "",
      address: "",
      type: "",
      areaoption: [],
      aid: "",
      mId: "",
      status: 0,
      pName: "",
      reason: "",
      photo: "",
      form: {},
      dialogFormVisible: false,
      multipleSelection: [],
      headerBg: 'headerBg',
      roles: [],
      dialogVisible: false
    }
  },
  created(){
    //请求分页查询数据
    this.load()
    this.area()
  },
  methods: {
    load(){
      const appealPageDto={
        pageNum: this.pageNum,
        pageSize: this.pageSize,
        appealDto: {
          type: this.type,
          time: this.time,
          status: this.status,
          pName: this.pName,
          aId: this.aid,
          mId: this.mId,
        }
      }
      this.request.post("/appeal/find", appealPageDto).then(res =>{
        this.tableData = res.data.list
        this.total = res.data.total
      })
    },
    area(){
      this.request.get("/area").then(res =>{
        this.areaoption=res.data
      })
    },
    reset(){
      this.time = ""
      this.aid = ""
      this.type = ""
      this.pName = ""
      this.load()
    },
    handleSizeChange(pageSize){
      console.log(pageSize)
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum){
      console.log(pageNum)
      this.pageNum = pageNum
      this.load()
    },
    handleYes(id){
      this.request.put("/appeal/"+id).then(res => {
        if (res.code === '200') {
          this.$message.success("申述审核成功！")
          this.load()
        }
        else{
          this.$message.error("申诉失败")
        }
      })
    },
    preview(url) {
      this.showResource = true;
      this.resourceSrc = url;

      // Determine the type of the resource
      const extension = url.split(".").pop().toLowerCase();
      this.isImage = extension === "jpg" || extension === "jpeg" || extension === "png" || extension === "gif";
      this.isVideo = extension === "mp4" || extension === "webm" || extension === "ogg";
    },
  }
}
</script>

<style>
.headerBg {
  background: #eee!important;
}
</style>