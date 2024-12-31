<template>
  <div>
    <el-card>
      <span style="font-size: 13px; margin-right: 20px; margin-left: 300px">发生时间：</span>
      <el-date-picker
          v-model="value2"
          type="daterange"
          align="right"
          unlink-panels
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :picker-options="pickerOptions">
      </el-date-picker>
    </el-card>
    <el-table
        :data="tableData"
        :span-method="objectSpanMethod"
        border
        style="width: 100%; margin-top: 20px">
      <el-table-column
          prop="id"
          label="学校名称"
          width="180">
      </el-table-column>
      <el-table-column
          prop="name"
          label="事件">
      </el-table-column>
      <el-table-column
          prop="amount1"
          label="次数">
      </el-table-column>
      <el-table-column
          prop="reason"
          label="主要原由">
      </el-table-column>
      <el-table-column prop="id" label="操作">
        <template slot-scope="scope">
          <el-button type="primary" @click="showPaint(scope.row)" size="medium" style="display: block; margin: 0 auto;">查看详情</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog :visible.sync="dialogVisible1" :title="this.title" destroy-on-close :append-to-body="true" @open="open">
      <div id="dialogChart" style="width: 100%; height: 500px"></div>
    </el-dialog>
  </div>
</template>


<script>
import * as echarts from "echarts"; //只有这样的引入方式才是正确的
export default {
  name: "schoolTable",
  data() {
    return {
      dialogVisible1: false,
      title: '',
      chart: null,
      pickerOptions: {
        shortcuts: [{
          text: '最近一周',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近一个月',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近三个月',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '本年度',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
            picker.$emit('pick', [start, end]);
          }
        }]
      },
      value1: '',
      value2: '',

      tableData: [{
        id: '高校一号',
        name: '徘徊',
        amount1: '3',
        reason: '自杀倾向',
      }, {
        id: '高校一号',
        name: '摔倒',
        amount1: '13',
        reason: '电瓶车带人',
      }, {
        id: '高校一号',
        name: '起火',
        amount1: '4',
        reason: '电动车充电',
      }, {
        id: '高校一号',
        name: '挥手',
        amount1: '6',
        reason: '需要急救',
      }, {
        id: '高校一号',
        name: '打架',
        amount1: '7',
        reason: '打架斗殴',
      }, {
        id: '高校一号',
        name: '特定区域人员闯入',
        amount1: '8',
        reason: '陌生人进入行窃',
      }, {
        id: '高校二号',
        name: '徘徊',
        amount1: '8',
        reason: '男女生谈恋爱',
      }, {
        id: '高校二号',
        name: '摔倒',
        amount1: '165',
        reason: '电动车骑太快',
      }, {
        id: '高校二号',
        name: '起火',
        amount1: '324',
        reason: '使用违规电器',
      }, {
        id: '高校二号',
        name: '挥手',
        amount1: '621',
        reason: '求救',
      }, {
        id: '高校二号',
        name: '打架',
        amount1: '539',
        reason: '矛盾冲突',
      }, {
        id: '高校二号',
        name: '特定区域人员闯入',
        amount1: '165',
        reason: '学生误入',
      }, {
        id: '高校三号',
        name: '徘徊',
        amount1: '165',
        reason: '心情压抑',
      }, {
        id: '高校三号',
        name: '摔倒',
        amount1: '165',
        reason: '车辆碰撞',
      }, {
        id: '高校三号',
        name: '起火',
        amount1: '324',
        reason: '充电桩老化',
      }, {
        id: '高校三号',
        name: '挥手',
        amount1: '621',
        reason: '紧急情况',
      }, {
        id: '高校三号',
        name: '打架',
        amount1: '539',
        reason: '学生冲突',
      }, {
        id: '高校三号',
        name: '特定区域人员闯入',
        amount1: '165',
        reason: '陌生人误入',
      }]
    }
  },
  methods: {
    open() {
      this.$nextTick(() => {
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
            data: ['徘徊', '摔倒', '起火', '挥手', '打架', '人员闯入']
          },
          yAxis: {
            type: 'value'
          },
          series: [
            {
              data: [120, 200, 150, 80, 70, 110, 130],
              type: 'bar'
            }
          ]
        };
        var chartDom1 = document.getElementById('dialogChart');
        this.chart = echarts.init(chartDom1);
        this.chart.setOption(option);
      })
    },
    showPaint(row) {
      this.dialogVisible1 = true
      this.title = row.id

    },
    objectSpanMethod({row, column, rowIndex, columnIndex}) {
      if (columnIndex === 0 || columnIndex === 4) {
        if (rowIndex % 6 === 0) {
          return {
            rowspan: 6,
            colspan: 1
          };
        } else {
          return {
            rowspan: 0,
            colspan: 0
          };
        }
      }
    }
  }
}
</script>

<style scoped>

</style>