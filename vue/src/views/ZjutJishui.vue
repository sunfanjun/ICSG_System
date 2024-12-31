<template>
  <el-card>
    <el-tabs type="card">
      <el-tab-pane label="告警记录">
        <div style="margin: 10px 0">
          <span style="font-size: 13px">发生日期：</span>
          <el-date-picker style="width: 160px"
              v-model="fTime"
              align="right"
              type="date"
              format="yyyy 年 MM 月 dd 日"
              value-format="yyyy-MM-dd"
              placeholder="选择日期"
              :picker-options="pickerOptions1">
          </el-date-picker>
          <span style="font-size: 13px; margin-left:2px; width: 160px">发生区域：</span>
          <el-select v-model="aid" filterable placeholder="请选择" @change="handleSelectionChange">
            <el-option
                v-for="item in areaoption"
                :key="item.id"
                :label="item.name"
                :value="item.id">
            </el-option>
          </el-select>
          <span style="font-size: 13px; margin-left:2px">摄像头编号：</span>
          <el-input style="width: 180px" placeholder="请输入摄像头" suffix-icon="el-icon-video-camera" class="ml-5" v-model="mId"></el-input>
          <span style="font-size: 13px; margin-left:2px">处理人姓名：</span>
          <el-input style="width: 160px" placeholder="请输入处理人" suffix-icon="el-icon-video-camera" class="ml-5" v-model="pName"></el-input>
          <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
          <el-button type="warning" @click="reset">重置</el-button>
        </div>
        <div style="margin-top: 30px">
          <el-table :data="tableData" border stripe :header-cell-class-name="headerBg" @selection-change="handleSelectionChange">
            <el-table-column prop="id" label="编号" width="80"></el-table-column>
            <el-table-column prop="ftime" label="时间" ></el-table-column>
            <el-table-column prop="monitor.area.name" label="地点"></el-table-column>
            <el-table-column prop="monitor.id" label="记录的摄像头"></el-table-column>
            <el-table-column prop="securityGuard.name" label="处理人员"></el-table-column>
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
                :total="total">
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
      </el-tab-pane>
      <el-tab-pane label="数据分析" style="height: 700px">
        <el-row :gutter="20">
          <el-col :span="2">
            <div id="paint1" style="width: 550px; height: 500px"></div>
          </el-col>
          <el-col :span="9">
            <div id="paint3" style="width: 1000px; height: 550px"></div>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="11">
            <div id="paint4" style="width: 500px; height: 500px"></div>
          </el-col>
          <el-col :span="6">
            <div id="paint2" style="width: 650px; height: 400px"></div>
          </el-col>
        </el-row>
      </el-tab-pane>
    </el-tabs>
  </el-card>


</template>

<script>
import * as echarts from "echarts";

export default {
  name: "Jishui",
  data(){
    return {
      showResource: false,
      resourceSrc: "",
      isImage: false,
      isVideo: false,
      tableData: [],
      areaoption: [],
      imageList: [],
      total: 0,
      pageNum: 1,
      pageSize: 10,
      pName: "",
      fTime: "",
      aid: "",
      dispose: 1,
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
          type: "积水",
          fTime: this.fTime,
          dispose: "1",
          aId: this.aid,
          mId: this.mId,
          pName: this.pName
        }
      }
      this.request.post("/danger/find",dangerPageDto).then(res =>{
        console.log(res.data.list[0].images[0].link)
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
      this.areaName = ""
      this.mId = ""
      this.aid = ""
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
    preview(row) {
      this.showResource = true;
      this.images=row.images;
      this.url = row.images[0].link;

      // Determine the type of the resource
      const extension = this.url.split(".").pop().toLowerCase();
      this.isImage = extension === "jpg" || extension === "jpeg" || extension === "png" || extension === "gif";
      this.isVideo = extension === "mp4" || extension === "webm" || extension === "ogg";
    },
  },

  mounted() { // 页面元素渲染之后再触发
    // paint1
    var option = {
      title: {
        text: '本周告警统计',
        left: 'center'
      },
      tooltip: {
        trigger: 'item'
      },
      legend: {
        orient: 'vertical',
        left: 'left'
      },
      xAxis: {
        type: 'category',
        data: ["周一", "周二", "周三", "周四", "周五", "周六", "周日"]
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          name: "本周告警次数", //名字不一样，不同图之间显示的颜色就不一样
          data: [
            {
              value: 5,
              itemStyle: {
                color: '#e34225'
              }
            },
            {
              value: 8,
              itemStyle: {
                color: '#e38e25'
              }
            },
            {
              value: 13,
              itemStyle: {
                color: '#159015'
              }
            },
            {
              value: 7,
              itemStyle: {
                color: '#258ae3'
              }
            },
            {
              value: 9,
              itemStyle: {
                color: '#2a08a5'
              }
            },
            {
              value: 12,
              itemStyle: {
                color: '#8b6315'
              }
            },
            {
              value: 8,
              itemStyle: {
                color: '#25e3b7'
              }
            },
          ],
          type: 'bar',
          itemStyle: {
            color: '#1071bd'
          }
        },
      ]
    };
    var chartDom1 = document.getElementById('paint1');
    var myChart = echarts.init(chartDom1);
    myChart.setOption(option);

    // paint2
    var option = {
      title: {
        text: '按月告警统计',
        left: 'center',
        textStyle: {
          textAlign: 'center'
        }
      },
      tooltip: {
        trigger: 'item'
      },
      legend: {
        orient: 'vertical',
        left: 'left'
      },
      xAxis: {
        type: 'category',
        data: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"]
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          name: "按月告警统计", //名字不一样，不同图之间显示的颜色就不一样
          data: [5,8,13,7,9,12,8,5,17,9,13,16],
          type: 'bar',
          itemStyle: {
            color: '#2bd28c'
          }
        },
        {
          data: [5,8,13,7,9,12,8,5,17,9,13,16],
          type: 'line',
          itemStyle: {
            color: '#eeb628'
          }
        }
      ]
    };
    var chartDom2 = document.getElementById('paint2');
    var myChart = echarts.init(chartDom2);
    myChart.setOption(option);

    // paint3
    var option = {
      title: {
        text: '各地告警统计',
        left: 'center'
      },
      tooltip: {
        trigger: 'item',
        formatter: '{a} <br/>{b} : {c} ({d}%)'
      },
      legend: {
        left: 'center',
        top: 'bottom',
        data: [
          'rose1',
          'rose2',
          'rose3',
          'rose4',
          'rose5',
          'rose6',
          'rose7',
          'rose8'
        ]
      },
      series: [
        {
          name: '水边等危险区域',
          type: 'pie',
          radius: [30, 160],
          center: ['75%', '50%'],
          roseType: 'area',
          itemStyle: {
            borderRadius: 5
          },
          data: [
            { value: 30, name: '地区1' },
            { value: 28, name: '地区2' },
            { value: 26, name: '地区3' },
            { value: 24, name: '地区4' },
            { value: 22, name: '地区5' },
            { value: 20, name: '地区6' },
            { value: 18, name: '地区7' },
            { value: 16, name: '地区8' }
          ]
        }
      ]
    };
    var chartDom3 = document.getElementById('paint3');
    var myChart = echarts.init(chartDom3);
    myChart.setOption(option);

    // paint4
    var option = {
      title: {
        text: '按季度告警统计',
        left: 'center'
      },
      tooltip: {
        trigger: 'item'
      },
      legend: {
        orient: 'vertical',
        left: 'left'
      },
      series: [
        {
          name: '水边等危险区域',
          type: 'pie',
          radius: '50%',
          data: [
            { value: 1048, name: '第一季度' },
            { value: 735, name: '第二季度' },
            { value: 580, name: '第三季度' },
            { value: 484, name: '第四季度' }
          ],
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }
      ]
    };
    var chartDom4 = document.getElementById('paint4');
    var myChart = echarts.init(chartDom4);
    myChart.setOption(option);

  }
}
</script>

<style>
.headerBg {
  background: #eee!important;
}
</style>