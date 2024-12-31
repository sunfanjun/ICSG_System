<template>
  <div>
    <button @click="startProgress">开始</button>
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
  data() {
    return {
      dialogVisible: false,
      showProgress: false,
      progress: 0,
      intervalId: null
    };
  },
  computed: {
    progressText() {
      return `(${Math.floor((this.progressCount / this.totalCount) * 100)}%)`;
    }
  },
  methods: {
    updateProgress() {
      this.progress++;
      if (this.progress === 100) {
        clearInterval(this.intervalId);
      }
    },
    startProgress() {
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
};
</script>
<style scoped>
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
