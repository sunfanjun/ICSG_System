<template>
  <el-card>
    <div style="margin: 10px 0">
      <span style="font-size: 13px">发生日期：</span>
      <el-date-picker
          v-model="fTime"
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
      <span style="font-size: 13px; margin-left:2px">摄像头编号：</span>
      <el-input style="width: 180px" placeholder="请输入摄像头" suffix-icon="el-icon-video-camera" class="ml-5" v-model="mId"></el-input>
    </div>
    <div style="margin: 10px 0">
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button type="warning" @click="reset">重置</el-button>
    </div>
    <el-tabs type="card">
      <div>
        <el-table :data="tableData" border stripe :header-cell-class-name="headerBg">
          <el-table-column prop="id" label="编号" width="80"></el-table-column>
          <el-table-column prop="type" label="类型" width="170"></el-table-column>
          <el-table-column prop="fTime" label="时间" width="170"></el-table-column>
          <el-table-column prop="monitor.area.name" label=发生区域 width="170"></el-table-column>
          <el-table-column prop="monitor.id" label="记录的摄像头"></el-table-column>
          <el-table-column prop="url" label="操作">
            <template slot-scope="scope">
              <el-button type="primary" @click="preview(scope.row)">查看详情</el-button>
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
              :total="total"
          >
          </el-pagination>
          <el-dialog :visible.sync="showResource" title="告警事件">
            <template v-if="isImage">
              <el-carousel :interval="5000" arrow="always" height="400px" style="text-align: center">
                <el-carousel-item v-for="image in images" :key="image.id">
                  <img :src="image.link" style="max-width: 100%;" class="image"/>
                </el-carousel-item>
              </el-carousel>
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
  name: "ZjuPending",
  data(){
    return {
      showResource: false,
      resourceSrc: "",
      isImage: false,
      isVideo: false,
      tableData: [],
      areaoption: [],
      images: [],
      total: 0,
      pageNum: 1,
      pageSize: 10,
      type: "",
      fTime: "",
      aid: "",
      dispose: 0,
      situation: "",
      mId: "",
      url: "",
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
      const dangerPageDto={
        pageNum: this.pageNum,
        pageSize: this.pageSize,
        dangerDto: {
          type: this.type,
          fTime: this.fTime,
          dispose: this.dispose,
          aId: this.aid,
          mId: this.mId,
        }
      }
      this.request.post("/danger/find",dangerPageDto).then(res =>{
        this.tableData = res.data.list
        this.total = res.data.total
      })
    },
    area(){
      this.request.get("/area").then(res =>{
        this.areaoption=res.data
      })
    },
    handleSelectionChange(){
      this.aid = aid
    },
    reset(){
      this.type = ""
      this.fTime = ""
      this.aid = ""
      this.mId = ""
      this.aid = ""
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
    preview(row) {
      this.showResource = true;
      this.images=row.images;
      this.url = row.images[0].link;

      // Determine the type of the resource
      const extension = this.url.split(".").pop().toLowerCase();
      this.isImage = extension === "jpg" || extension === "jpeg" || extension === "png" || extension === "gif";
      this.isVideo = extension === "mp4" || extension === "webm" || extension === "ogg";
    },
  }
}
</script>

<style>
.el-carousel__item{
  width: 80%;
  left: 10%;
}

.headerBg {
  background: #eee!important;
}
</style>