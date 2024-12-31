<template>
  <div class="about">
    <h1>This is an about page</h1>
    <video id="myVideo" src="http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4" muted="true" controls></video>
  </div>
</template>

<script>
import GoEasy from "goeasy";
let goEasy = new GoEasy({
  appkey: 'BC-fb8c1fc557fc4effae4d0fbcbd0ad968'
});
var pubsub = goEasy.pubsub;
pubsub.subscribe({
  channel: "my_channel1",//替换为您自己的channel
  onMessage: function (message) {
    alert(message.content)
    console.log("Channel:" + message.channel + " content:" + message.content);
  },
  onSuccess: function () {
    console.log("Channel订阅成功。");
  },
  onFailed: function (error) {
    console.log("Channel订阅失败, 错误编码：" + error.code + " 错误信息：" + error.content)
  }
});

export default {

}

</script>