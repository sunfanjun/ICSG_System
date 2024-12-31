<template>
  <div>
    <map-container></map-container>
    <div style="margin-top: 30px">
      <el-container>
        <div style="width: 700px; height: 300px; margin-top: 5px">
          <el-row :gutter="25">
            <el-col :span="11">
              <div>
                <el-card>
                  <el-row :gutter="20">
                    <el-col :span="3">
                      <div>
                        <i class="el-icon-success"  style="color:#0cc20c;font-size:100px"></i>
                      </div>
                    </el-col>
                    <el-col :span="15" :offset="6">
                      <div>
                        <div style="color: #0cc20c;font-size: 20px;font-family: 微软雅黑;font-weight: 700;text-align: center">已处理事件</div>
                        <br>
                        <div style="text-align: center;font-size: 45px; font-weight: 700">{{ solvedCount }}</div>
                      </div>
                    </el-col>
                  </el-row>
                </el-card>
              </div>
            </el-col>
            <el-col :span="11">
              <div>
                <el-card>
                  <el-row :gutter="20">
                    <el-col :span="3">
                      <div>
                        <i class="el-icon-s-order"  style="color:#1778f1;font-size:100px"></i>
                      </div>
                    </el-col>
                    <el-col :span="15" :offset="6">
                      <div>
                        <div style="color: #1778f1;font-size: 20px;font-family: 微软雅黑;font-weight: 700;text-align: center">总告警事件</div>
                        <br>
                        <div style="text-align: center;font-size: 45px; font-weight: 700">{{ event }}</div>
                      </div>
                    </el-col>
                  </el-row>
                </el-card>
              </div>
            </el-col>
          </el-row>
          <br>
          <el-row :gutter="25" style="margin-top: 10px">
            <el-col :span="11">
              <div>
                <el-card>
                  <el-row :gutter="20">
                    <el-col :span="3">
                      <div>
                        <i class="el-icon-warning"  style="color: #cd081b;font-size: 100px"></i>
                      </div>
                    </el-col>
                    <el-col :span="15" :offset="6">
                      <div>
                        <div style="color: #cd081b;font-size: 20px;font-family: 微软雅黑;font-weight: 700;text-align: center">待处理事件</div>
                        <br>
                        <div style="text-align: center;font-size: 45px; font-weight: 700">{{ notSolvedCount }}</div>
                      </div>
                    </el-col>
                  </el-row>
                </el-card>
              </div>
            </el-col>
            <el-col :span="11">
              <div>
                <el-card>
                  <el-row :gutter="20">
                    <el-col :span="3">
                      <div>
                        <i class="el-icon-warning-outline"  style="color: #f8c613;font-size: 100px"></i>
                      </div>
                    </el-col>
                    <el-col :span="15" :offset="6">
                      <div>
                        <div style="color: #f8c613;font-size: 20px;font-family: 微软雅黑;font-weight: 700;text-align: center">待申诉处理</div>
                        <br>
                        <div style="text-align: center;font-size: 45px; font-weight: 700">{{ complaintCount }}</div>
                      </div>
                    </el-col>
                  </el-row>
                </el-card>
              </div>
            </el-col>
          </el-row>
        </div>
        <div id="main" style="width: 720px; height: 380px; margin-left: -10px"></div>
      </el-container>
    </div>
  </div>
</template>

<script>
import MapContainer from "@/components/MapContainer";
import * as echarts from "echarts";
export default {
  name: "ZjuHome",
  components: {MapContainer},
  data() {
    return {
      solvedCount: 0,
      notSolvedCount: 0,
      event:0,
      complaintCount: 0
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      this.request.get("/danger/status/1").then(res => {
        this.solvedCount = res.data;
        this.event+=res.data;
      })
      this.request.get("/danger/status/0").then(res => {
        this.notSolvedCount =  res.data;
        this.event+=res.data;
      })
      this.request.get("/appeal/status/0").then(res => {
        this.complaintCount =  res.data;
      })
    }
  },
  mounted() {
    var option = {
      title: {
        text: '本周告警次数',
        // subtext: '趋势图',
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
          data: [],
          type: 'bar',
          itemStyle: {
            color: '#1778f1'
          }
        },
        // {
        //   name: "瑞幸",
        //   data: [],
        //   type: 'bar'
        // },
        // {
        //   name: "星巴克", //名字不一样，不同图之间显示的颜色就不一样
        //   data: [],
        //   type: 'line'
        // },
        // {
        //   name: "星巴克",
        //   data: [],
        //   type: 'bar'
        // }
      ]
    };
    // var chartDom = document.getElementById('main');
    // var myChart = echarts.init(chartDom);
    // this.request.get("/school_zju/reduceOne").then(res =>{
    //   option.series[0].data[6] = res
    //   option.series[0].data[6] = 5
    // });
    // this.request.get("/school_zju/reduceTwo").then(res => {
    //   option.series[0].data[5] = res
    //   option.series[0].data[5] = 8
    // });
    // this.request.get("/school_zju/reduceThree").then(res => {
    //   option.series[0].data[4] = res
    //   option.series[0].data[4] = 7
    // });
    // this.request.get("/school_zju/reduceFour").then(res => {
    //   option.series[0].data[3] = res
    //   option.series[0].data[3] = 4
    // });
    // this.request.get("/school_zju/reduceFive").then(res => {
    //   option.series[0].data[2] = res
    //   option.series[0].data[2] = 9
    // });
    // this.request.get("/school_zju/reduceSix").then(res => {
    //   option.series[0].data[1] = res
    //   option.series[0].data[1] = 4
    // });
    // this.request.get("/school_zju/reduceSeven").then(res => {
    //   option.series[0].data[0] = res
    //   option.series[0].data[0] = 3
    //       myChart.setOption(option);
    // });
  }
}
</script>

<style scoped>

</style>