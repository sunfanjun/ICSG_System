<template>
  <div>
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="未标注" name="first">
        <el-row :gutter="20">
          <el-col :span="16">
            <el-card>
              <el-row>
                <el-col :span="2">
                  <br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
                  <div class="triangle1" @click="changeButtonPre()"></div>
                </el-col>
                <el-col :span="20">
                  <div class="imgBox">
                    <img id="pic" :src="require('../../public/img/4.jpg')" alt="">
                  </div>
                </el-col>
                <el-col :span="2">
                  <br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
                  <div class="triangle" @click="changeButton()"></div>
                </el-col>
              </el-row>
            </el-card>
          </el-col>
          <el-col :span="8">
            <el-card>
              <div style="margin-bottom: 10px">
                <h2>当前图片标注</h2>
              </div>
              <hr>
              <div class="abc">
                <p>抽烟</p>
                <p>Smoke</p>
              </div>
              <div class="abc">
                <p>挥手</p>
                <p>Wave</p>
              </div>
              <div class="abc">
                <p>身体翻越围栏</p>
                <p>Body over the fence</p>
              </div>
              <div class="abc">
                <p>在水边等危险区域</p>
                <p>Hazardous areas</p>
              </div>
              <div class="abc">
                <p>打架</p>
                <p>Fight</p>
              </div>
              <div class="abc">
                <p>楼顶人员徘徊</p>
                <p>Wandering on the roof</p>
              </div>
              <div class="abc">
                <p>起火</p>
                <p>Fire</p>
              </div>
              <div style="margin-top: 20px">
                <button class="abd">重新标注</button>
              </div>
              <div style="margin-top: 20px;">
                <button class="abe" @click="train">一键训练</button>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </el-tab-pane>
      <el-tab-pane label="已标注" name="second">
        <el-table :data="tableData" border stripe :header-cell-class-name="headerBg" @selection-change="handleSelectionChange">
          <el-table-column prop="id" label="图片编号" width="80"></el-table-column>
          <el-table-column prop="time" label="时间" width="170"></el-table-column>
          <el-table-column prop="address" label="地点"></el-table-column>
          <el-table-column prop="situation" label="标注信息"></el-table-column>
        </el-table>
      </el-tab-pane>
    </el-tabs>
    <el-dialog title="模型训练配置" :visible.sync="dialogVisible" width="30%">
      <div v-if="showProgress" class="progress-container">
        <div class="progress-bar" :style="{ width: progress + '%' }"></div>
        <div class="progress-text">{{ progress }}%</div>
      </div>
      <span slot="footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "Reset",
  data() {
    return {
      tableData: [
        {
          id: 1,
          time: 2023-3-3,
          address: "图书馆2号门",
          situation: "抽烟"
        },
        {
          id: 2,
          time: 2023-3-3,
          address: "图书馆1号门",
          situation: "打架"
        },
        {
          id: 3,
          time: 2023-3-3,
          address: "教学楼2号",
          situation: "摔倒"
        },
        {
          id: 4,
          time: 2023-3-3,
          address: "教学楼3号",
          situation: "起火"
        },
        {
          id: 5,
          time: 2023-3-3,
          address: "教学楼4号",
          situation: "楼顶人员徘徊"
        },
        {
          id: 6,
          time: 2023-3-3,
          address: "教学楼4号",
          situation: "抽烟"
        },
        {
          id: 7,
          time: 2023-3-3,
          address: "图书馆1号门",
          situation: "摔倒"
        },
        {
          id: 8,
          time: 2023-3-3,
          address: "教学楼2号",
          situation: "楼顶人员徘徊"
        },
      ],

      activeName: 'first',
      arr: ['4.jpg','5.jpg','6.jpg','7.jpg','8.jpg'],
      index: 0,

      dialogVisible: false,
      showProgress: false,
      progress: 0,
      intervalId: null
    }
  },
  computed: {
    progressText() {
      return `(${Math.floor((this.progressCount / this.totalCount) * 100)}%)`;
    }
  },
  methods: {
    changeButton(){
      // 让数组下标自增
      this.index++
      // 显示下标最大值
      if(this.index === this.arr.length) this.index = 0
      // 根据随机下标获取到数组中图片的路径
      var imgPath = this.arr[this.index]
      console.log(imgPath)

      var msg=document.getElementById("pic");
      //切换到另一个图片
      // !!! require不支持传入变量，要么直接传入字符串，要么字符串加上一个变量（本质还是给require一个字符串，因为通过+，可以先把变量转为字符串）
      msg.src=require('../../public/img/'+ imgPath);
    },
    changeButtonPre(){
      // 让数组下标自增
      this.index--
      // 显示下标最大值
      if(this.index === -1) this.index = this.arr.length - 1
      // 根据随机下标获取到数组中图片的路径
      var imgPath = this.arr[this.index]
      console.log(imgPath)

      var msg=document.getElementById("pic");
      //切换到另一个图片
      // !!! require不支持传入变量，要么直接传入字符串，要么字符串加上一个变量（本质还是给require一个字符串，因为通过+，可以先把变量转为字符串）
      msg.src=require('../../public/img/'+ imgPath);
    },
    updateProgress() {
      this.progress++;
      if (this.progress === 100) {
        clearInterval(this.intervalId);
      }
    },
    train() {
      // 显示进度条
      this.dialogVisible = true;
      this.showProgress = true;

      // 模拟进度
      this.progress = 0;
      this.intervalId = setInterval(() => {
        this.updateProgress();
      }, 100);
    }
  }
}
</script>
<style scoped>
.imgBox,.imgBox img{
  width: 600px;
  height: 600px;
}
.imgBox{
  border:3px solid #ccc;
}
.triangle {
  width: 0;
  height: 0;
  border-left: 50px solid #a5abab;
  border-top: 50px solid transparent;
  border-bottom: 50px solid transparent;
}
.triangle1 {
  width: 0;
  height: 0;
  border-left: 50px solid rgb(166, 172, 172);
  border-top: 50px solid transparent;
  border-bottom: 50px solid transparent;
  transform: rotate(180deg);
}
.abc {
  padding: 10px;
  cursor: pointer;
}
.abc:hover {
  background-color: #eff9fe;
}
.abd {
  display: block;
  width: 335px;
  height: 45px;
  margin: 0 auto;
  font-size: 18px;
  font-weight: bold;
  background-color: #409EFF;
  color: #ebeef1;
  border: none;
  cursor: pointer;
}
.abe {
  display: block;
  width: 335px;
  height: 45px;
  margin: 0 auto;
  font-size: 18px;
  font-weight: bold;
  background-color: #0177ff;
  color: #ebeef1;
  border: none;
  cursor: pointer;
}

.progress-container {
  width: 400px;
  height: 30px;
  border: 1px solid #ccc;
  position: relative;
}

.progress-bar {
  height: 100%;
  background-color: green;
  position: absolute;
  left: 0;
  top: 0;
  width: 0%;
  transition: width 1s linear;
}

.progress-text {
  position: absolute;
  right: 0;
  top: 0;
  line-height: 20px;
  padding: 0 10px;
}
</style>