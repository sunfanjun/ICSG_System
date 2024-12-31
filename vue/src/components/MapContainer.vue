<template>
  <div id="container"></div>
</template>

<script>
import AMapLoader from '@amap/amap-jsapi-loader'
window._AMapSecurityConfig = {
  securityJsCode: 'dc861b7b9043a4d3fc0003d18df3e268'
}
export default {
  data() {
    return {
      map: null
    }
  },
  methods: {
    initMap() {
      AMapLoader.load({
        key: '83f1bfa75c23a0fbd784b4ea03c61314', // 申请好的Web端开发者Key，首次调用 load 时必填
        version: '2.0', // 指定要加载的 JSAPI 的版本，缺省时默认为 1.4.15
        plugins: [] // 需要使用的的插件列表，如比例尺'AMap.Scale'等
      })
          .then(AMap => {
            this.map = new AMap.Map('container', {
              //设置地图容器id
              viewMode: '3D', //是否为3D地图模式
              zoom: 16, //初始化地图级别
              center: [120.034531, 30.224041] //初始化地图中心点位置
            })
            let marker1 = new AMap.Marker({
              position: new AMap.LngLat(120.034531, 30.224551),
              title: '图书馆-摄像头1'
            })
            let marker2 = new AMap.Marker({
              position: new AMap.LngLat(120.034531, 30.225551),
              title: '图书馆-摄像头2'
            })
            let marker3 = new AMap.Marker({
              position: new AMap.LngLat(120.036131, 30.224651),
              title: '健行楼-摄像头1'
            })
            let marker4 = new AMap.Marker({
              position: new AMap.LngLat(120.036131, 30.224851),
              title: '健行楼-摄像头2'
            })
            let marker5 = new AMap.Marker({
              position: new AMap.LngLat(120.036731, 30.224851),
              title: '健行楼-摄像头3'
            })
            let marker6 = new AMap.Marker({
              position: new AMap.LngLat(120.036131, 30.226451),
              title: '板球场-摄像头1'
            })
            let marker7 = new AMap.Marker({
              position: new AMap.LngLat(120.036999, 30.226451),
              title: '板球场-摄像头2'
            })
            let marker8 = new AMap.Marker({
              position: new AMap.LngLat(120.036131, 30.225551),
              title: '板球场-摄像头3'
            })
            let marker9 = new AMap.Marker({
              position: new AMap.LngLat(120.038555, 30.226951),
              title: '板球场-摄像头4'
            })
            let marker10 = new AMap.Marker({
              position: new AMap.LngLat(120.038555, 30.225951),
              title: '板球场-摄像头5'
            })
            this.map.add(marker1)
            this.map.add(marker2)
            this.map.add(marker3)
            this.map.add(marker4)
            this.map.add(marker5)
            this.map.add(marker6)
            this.map.add(marker7)
            this.map.add(marker8)
            this.map.add(marker9)
            this.map.add(marker10)

          })
          .catch(e => {
            console.log(e)
          })
    }
  },
  mounted() {
    //DOM初始化完成进行地图初始化
    this.initMap()
  }
}
</script>

<style lang="less">
#container {
  padding: 0px;
  margin: 0px;
  width: 1160px;
  height: 355px;
}
</style>

