<template>
  <div>
    <span>111</span>

  </div>
</template>

<script>
import Video from "@/views/Video";
export default {
  components: {Video}
}
</script>
<script type="text/javascript">
var goEasy = new GoEasy({
  appkey: 'BC-fb8c1fc557fc4effae4d0fbcbd0ad968'
});

goEasy.subscribe({
  channel:'my_channel1',
  onMessage: function(message){
    alert('收到：'+message.content);
  }
});
export default {
  name: "test"
}
</script>

<style scoped>

</style>