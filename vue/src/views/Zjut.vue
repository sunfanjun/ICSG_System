<template>
  <el-card>

    <el-tabs type="card">
      <el-tab-pane label="告警记录">
        <div style="margin: 10px 0">
          <span style="font-size: 13px">发生时间：</span>
          <el-input style="width: 180px" placeholder="请输入时间" suffix-icon="el-icon-search" v-model="time"></el-input>
          <span style="font-size: 13px; margin-left: 10px">发生地点：</span>
          <el-input style="width: 180px" placeholder="请输入地点" suffix-icon="el-icon-location-outline" v-model="address"></el-input>
          <span style="font-size: 13px; margin-left: 10px">事件：</span>
          <el-input style="width: 180px" placeholder="请输入事件" suffix-icon="el-icon-search" v-model="situation"></el-input>
          <span style="font-size: 13px; margin-left: 10px">摄像头：</span>
          <el-input style="width: 180px" placeholder="请输入摄像头" suffix-icon="el-icon-video-camera" v-model="camera"></el-input>
          <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
          <el-button type="warning" @click="reset">重置</el-button>
        </div>
        <div style="margin-top: 30px">
          <el-table :data="tableData" border stripe :header-cell-class-name="headerBg" @selection-change="handleSelectionChange">
            <el-table-column prop="id" label="编号" width="80"></el-table-column>
            <el-table-column prop="time" label="时间" width="170"></el-table-column>
            <el-table-column prop="address" label="地点"></el-table-column>
            <el-table-column prop="situation" label="事件"></el-table-column>
            <el-table-column prop="camera" label="记录的摄像头" width="120"></el-table-column>
            <el-table-column prop="url" label="操作">
              <template slot-scope="scope">
                <el-button type="primary" @click="preview(scope.row.url)">查看详情</el-button>
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
          </div>
        </div>
      </el-tab-pane>
      <el-tab-pane label="待处理事件">
        <div style="margin: 10px 0">
          <span style="font-size: 13px">发生时间：</span>
          <el-input style="width: 180px" placeholder="请输入时间" suffix-icon="el-icon-search" v-model="time"></el-input>
          <span style="font-size: 13px; margin-left: 10px">发生地点：</span>
          <el-input style="width: 180px" placeholder="请输入地点" suffix-icon="el-icon-location-outline" class="ml-5" v-model="address"></el-input>
          <span style="font-size: 13px; margin-left: 10px">事件：</span>
          <el-input style="width: 180px" placeholder="请输入事件" suffix-icon="el-icon-search" v-model="situation"></el-input>
          <span style="font-size: 13px; margin-left: 10px">摄像头：</span>
          <el-input style="width: 180px" placeholder="请输入摄像头" suffix-icon="el-icon-video-camera" class="ml-5" v-model="camera"></el-input>
          <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
          <el-button type="warning" @click="reset">重置</el-button>
        </div>
        <div style="margin-top: 30px">
          <el-table :data="tableData1" border stripe :header-cell-class-name="headerBg" @selection-change="handleSelectionChange">
            <el-table-column prop="id" label="编号" width="80"></el-table-column>
            <el-table-column prop="time" label="时间" width="170"></el-table-column>
            <el-table-column prop="address" label="地点"></el-table-column>
            <el-table-column prop="situation" label="事件"></el-table-column>
            <el-table-column prop="camera" label="记录的摄像头" width="120"></el-table-column>
            <el-table-column prop="url" label="操作">
              <template slot-scope="scope">
                <el-button type="primary" @click="preview(scope.row.url)">查看详情</el-button>
              </template>
            </el-table-column>
          </el-table>
          <div style="padding: 10px 0">
            <el-pagination
                @size-change="handleSizeChange1"
                @current-change="handleCurrentChange1"
                :current-page="pageNum1"
                :page-sizes="[2, 5, 10, 20]"
                :page-size="pageSize1"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total1">
            </el-pagination>
          </div>
        </div>
      </el-tab-pane>
      <el-tab-pane label="已处理事件">
        <div style="margin: 10px 0">
          <span style="font-size: 13px">发生时间：</span>
          <el-input style="width: 180px" placeholder="请输入时间" suffix-icon="el-icon-search" v-model="time"></el-input>
          <span style="font-size: 13px; margin-left: 10px">发生地点：</span>
          <el-input style="width: 180px" placeholder="请输入地点" suffix-icon="el-icon-location-outline" class="ml-5" v-model="address"></el-input>
          <span style="font-size: 13px; margin-left: 10px">事件：</span>
          <el-input style="width: 180px" placeholder="请输入事件" suffix-icon="el-icon-search" v-model="situation"></el-input>
          <span style="font-size: 13px; margin-left: 10px">处理人员：</span>
          <el-input style="width: 180px" placeholder="请输入姓名" suffix-icon="el-icon-video-camera" class="ml-5" v-model="person"></el-input>
          <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
          <el-button type="warning" @click="reset">重置</el-button>
        </div>
        <div style="margin-top: 30px">
          <el-table :data="tableData2" border stripe :header-cell-class-name="headerBg" @selection-change="handleSelectionChange">
            <el-table-column prop="id" label="编号" width="80"></el-table-column>
            <el-table-column prop="time" label="时间" width="170"></el-table-column>
            <el-table-column prop="address" label="地点"></el-table-column>
            <el-table-column prop="situation" label="事件"></el-table-column>
            <el-table-column prop="camera" label="记录的摄像头" width="120"></el-table-column>
            <el-table-column prop="person" label="处理人员"></el-table-column>
            <el-table-column prop="description" label="备注"></el-table-column>
            <el-table-column prop="url" label="操作">
              <template slot-scope="scope">
                <el-button type="primary" @click="preview(scope.row.url)">查看详情</el-button>
              </template>
            </el-table-column>
          </el-table>
          <div style="padding: 10px 0">
            <el-pagination
                @size-change="handleSizeChange2"
                @current-change="handleCurrentChange2"
                :current-page="pageNum2"
                :page-sizes="[2, 5, 10, 20]"
                :page-size="pageSize2"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total2">
            </el-pagination>
          </div>
        </div>
      </el-tab-pane>
      <el-tab-pane label="待申诉处理">
        <div style="margin: 10px 0">
          <span style="font-size: 13px">发生时间：</span>
          <el-input style="width: 180px" placeholder="请输入时间" suffix-icon="el-icon-search" v-model="time"></el-input>
          <span style="font-size: 13px; margin-left: 10px">发生地点：</span>
          <el-input style="width: 180px" placeholder="请输入地点" suffix-icon="el-icon-location-outline" class="ml-5" v-model="address"></el-input>
          <span style="font-size: 13px; margin-left: 10px">事件：</span>
          <el-input style="width: 180px" placeholder="请输入事件" suffix-icon="el-icon-search" v-model="situation"></el-input>
          <span style="font-size: 13px; margin-left: 10px">申诉人员：</span>
          <el-input style="width: 180px" placeholder="请输入姓名" suffix-icon="el-icon-video-camera" class="ml-5" v-model="person"></el-input>
          <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
          <el-button type="warning" @click="reset">重置</el-button>
        </div>
        <div style="margin-top: 30px">
          <el-table :data="tableData3" border stripe :header-cell-class-name="headerBg" @selection-change="handleSelectionChange">
            <el-table-column prop="id" label="编号" width="80"></el-table-column>
            <el-table-column prop="time" label="时间" width="170"></el-table-column>
            <el-table-column prop="address" label="地点"></el-table-column>
            <el-table-column prop="situation" label="事件"></el-table-column>
            <el-table-column prop="camera" label="记录的摄像头" width="120"></el-table-column>
            <el-table-column prop="personSolve" label="处理人员"></el-table-column>
            <el-table-column prop="person" label="申诉人员"></el-table-column>
            <el-table-column prop="description" label="申诉理由"></el-table-column>
            <el-table-column prop="url" label="操作" width="200px">
              <template slot-scope="scope">
                <el-button type="primary" @click="preview(scope.row.url)">查看详情</el-button>
                <el-button type="success" @click="handleYes(scope.row.id)">同意 <i class="el-icon-finished"></i></el-button>
              </template>
            </el-table-column>
          </el-table>
          <div style="padding: 10px 0">
            <el-pagination
                @size-change="handleSizeChange3"
                @current-change="handleCurrentChange3"
                :current-page="pageNum3"
                :page-sizes="[2, 5, 10, 20]"
                :page-size="pageSize3"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total3">
            </el-pagination>
          </div>
        </div>
      </el-tab-pane>
      <el-tab-pane label="数据分析" style="height: 1000px">
        <el-row :gutter="20">
          <el-col :span="2">
            <div id="paint4" style="width: 1000px; height: 500px"></div>
          </el-col>
        </el-row>
        <el-row :gutter="20" style="margin-top: 30px">
          <el-col :span="2">
            <div id="paint1" style="width: 550px; height: 500px"></div>
          </el-col>
          <el-col :span="9">
            <div id="paint3" style="width: 1000px; height: 550px"></div>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="11">
            <div id="paint5" style="width: 500px; height: 500px"></div>
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
  name: "Zjut",
  data(){
    return {
      tableData: [],
      tableData1: [],
      tableData2: [],
      tableData3: [],
      total: 0,
      total1: 0,
      total2: 0,
      total3: 0,
      pageNum: 1,
      pageSize: 10,
      pageNum1: 1,
      pageSize1: 10,
      pageNum2: 1,
      pageSize2: 10,
      pageNum3: 1,
      pageSize3: 10,
      time: "",
      address: "",
      camera: "",
      situation: "",
      person: "",
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
  },
  methods: {
    load(){
      this.request.get("/school_zjut/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          time: this.time,
          address: this.address,
          situation: this.situation,
          person: this.person,
        }
      }).then(res =>{
        this.tableData = res.data.records
        this.total = res.data.total
      })
      this.request.get("/school_zjut/page1", {
        params: {
          pageNum: this.pageNum1,
          pageSize: this.pageSize1,
          time: this.time,
          address: this.address,
          situation: this.situation,
          person: this.person,
        }
      }).then(res =>{
        this.tableData1 = res.data.records
        this.total1 = res.data.total
      })
      this.request.get("/zjut_processed/page", {
        params: {
          pageNum: this.pageNum2,
          pageSize: this.pageSize2,
          time: this.time,
          address: this.address,
          situation: this.situation,
          person: this.person,
        }
      }).then(res =>{
        this.tableData2 = res.data.records
        this.total2 = res.data.total
      })
      this.request.get("/zjut_complaint/page", {
        params: {
          pageNum: this.pageNum3,
          pageSize: this.pageSize3,
          time: this.time,
          address: this.address,
          situation: this.situation,
          person: this.person,
        }
      }).then(res =>{
        this.tableData3 = res.data.records
        this.total3 = res.data.total
      })
    },
    reset(){
      this.time = ""
      this.address = ""
      this.situation = ""
      this.person = ""
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
    handleSizeChange1(pageSize1){
      console.log(pageSize1)
      this.pageSize1 = pageSize1
      this.load()
    },
    handleCurrentChange1(pageNum1){
      console.log(pageNum1)
      this.pageNum1 = pageNum1
      this.load()
    },
    handleSizeChange2(pageSize2){
      console.log(pageSize2)
      this.pageSize2 = pageSize2
      this.load()
    },
    handleCurrentChange2(pageNum2){
      console.log(pageNum2)
      this.pageNum2 = pageNum2
      this.load()
    },
    handleSizeChange3(pageSize3){
      console.log(pageSize3)
      this.pageSize3 = pageSize3
      this.load()
    },
    handleCurrentChange3(pageNum3){
      console.log(pageNum3)
      this.pageNum3 = pageNum3
      this.load()
    },
    handleYes(id){
      this.request.post("/zjut_complaint/" + id).then(res => {
        if (res.code === '200') {
          this.$message.success("同意申诉")
          this.load()
        }
        else{
          this.$message.error("申诉失败")
        }
      })
    },
    preview(url) {
      window.open('https://file.keking.cn/onlinePreview?url=' + encodeURIComponent(window.btoa((url))))
    },
  },

  mounted() { // 页面元素渲染之后再触发
    // 本周告警统计
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

    // 按月告警统计
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

    // 各地告警统计
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

    // 各事件发生统计
    var option = {
      title: {
        text: '各事件发生统计',
        left: 'center'
      },
      dataset: {
        source: [
          ['score', '告警次数', 'situation'],
          [99, 30666, '烟雾'],
          [60, 30196, '明火'],
          [32.7, 20112, '挥手'],
          [10.6, 10185, '摔倒'],
          [19.6, 91852, '抽烟'],
          [68.1, 79146, '地面积水'],
          [89.7, 20145, '身体超出围栏'],
          [50.1, 12755, '楼顶人员徘徊'],
          [74.4, 41032, '墙面房顶渗漏水'],
          [57.1, 78254, '特定区域人员闯入'],
          [89.3, 58212, '在水边等危险区域'],
        ]
      },
      grid: { containLabel: true },
      xAxis: { name: '告警次数' },
      yAxis: { type: 'category' },
      visualMap: {
        orient: 'horizontal',
        left: 'center',
        min: 10,
        max: 100,
        // text: ['High Score', 'Low Score'],
        // Map the score column to color
        dimension: 0,
        inRange: {
          color: ['#65B581', '#FFCE34', '#FD665F']
        }
      },
      series: [
        {
          type: 'bar',
          encode: {
            // Map the "amount" column to X axis.
            x: '告警次数',
            // Map the "product" column to Y axis
            y: 'situation'
          }
        }
      ]
    };
    var chartDom4 = document.getElementById('paint4');
    var myChart = echarts.init(chartDom4);
    myChart.setOption(option);

    // 按季度告警统计
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
          name: '按季度告警统计',
          type: 'pie',
          radius: '50%',
          data: [
            { value: 1048, name: '第一季度' },
            { value: 735, name: '第二季度' },
            { value: 580, name: '第三季度' },
            { value: 484, name: '第四季度' },
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
    var chartDom5 = document.getElementById('paint5');
    var myChart = echarts.init(chartDom5);
    myChart.setOption(option);

  }
}
</script>

<style>
.headerBg {
  background: #eee!important;
}
</style>