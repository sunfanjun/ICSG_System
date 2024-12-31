<template>
  <div id="container"></div>
</template>

<script>
import AMapLoader from '@amap/amap-jsapi-loader'
window._AMapSecurityConfig = {
  securityJsCode: 'e6b36a348b5eac7cfcc91599cb6caa2a'
}
export default {
  data() {
    return {
      map: null,
      data1: [
        {}
      ]
    }
  },
  methods: {
    initMap() {
      AMapLoader.load({
        key: '7fb0f793cbe3b386e95a18656bb67d3e', // 申请好的Web端开发者Key，首次调用 load 时必填
        version: '2.0', // 指定要加载的 JSAPI 的版本，缺省时默认为 1.4.15
        plugins: [] // 需要使用的的插件列表，如比例尺'AMap.Scale'等
      })
          .then(AMap => {
            this.map = new AMap.Map('container', {
              //设置地图容器id
              viewMode: '3D', //是否为3D地图模式
              zoom: 10, //初始化地图级别
              center: [120.1707, 30.267771] //初始化地图中心点位置
            })

            let marker1 = new AMap.Marker({
              position: new AMap.LngLat(120.035873, 30.224392),
              icon: new AMap.Icon({  // 设置点标记图标
                size: new AMap.Size(40, 50),  // 图标尺寸
                image: require('../../public/img/yellow.svg')  // 图标地址
              }),
              title: '浙江高校1',
              // fillColor: '#ff0000'
            })
            let marker2 = new AMap.Marker({
              position: new AMap.LngLat(120.196053, 30.271519),
              icon: new AMap.Icon({  // 设置点标记图标
                size: new AMap.Size(40, 50),  // 图标尺寸
                image: require('../../public/img/red.svg')  // 图标地址
              }),
              title: '浙江高校2'
            })
            let marker3 = new AMap.Marker({
              position: new AMap.LngLat(120.120283, 30.4445),
              icon: new AMap.Icon({  // 设置点标记图标
                size: new AMap.Size(40, 50),  // 图标尺寸
                image: require('../../public/img/green.svg')  // 图标地址
              }),
              title: '浙江高校3'
            })
            let marker4 = new AMap.Marker({
              position: new AMap.LngLat(120.145273, 30.224392),
              icon: new AMap.Icon({  // 设置点标记图标
                size: new AMap.Size(40, 50),  // 图标尺寸
                image: require('../../public/img/yellow.svg')  // 图标地址
              }),
              title: '浙江高校4',
              // fillColor: '#ff0000'
            })
            let marker5 = new AMap.Marker({
              position: new AMap.LngLat(120.306053, 30.281519),
              icon: new AMap.Icon({  // 设置点标记图标
                size: new AMap.Size(40, 50),  // 图标尺寸
                image: require('../../public/img/red.svg')  // 图标地址
              }),
              title: '浙江高校5'
            })
            let marker6 = new AMap.Marker({
              position: new AMap.LngLat(120.64283, 30.21445),
              icon: new AMap.Icon({  // 设置点标记图标
                size: new AMap.Size(40, 50),  // 图标尺寸
                image: require('../../public/img/green.svg')  // 图标地址
              }),
              title: '浙江高校6'
            })
            let marker7 = new AMap.Marker({
              position: new AMap.LngLat(120.19873, 30.424392),
              icon: new AMap.Icon({  // 设置点标记图标
                size: new AMap.Size(40, 50),  // 图标尺寸
                image: require('../../public/img/yellow.svg')  // 图标地址
              }),
              title: '浙江高校7',
              // fillColor: '#ff0000'
            })
            let marker8 = new AMap.Marker({
              position: new AMap.LngLat(120.170053, 30.271519),
              icon: new AMap.Icon({  // 设置点标记图标
                size: new AMap.Size(40, 50),  // 图标尺寸
                image: require('../../public/img/red.svg')  // 图标地址
              }),
              title: '浙江高校8'
            })
            let marker9 = new AMap.Marker({
              position: new AMap.LngLat(120.15283, 30.25445),
              icon: new AMap.Icon({  // 设置点标记图标
                size: new AMap.Size(40, 50),  // 图标尺寸
                image: require('../../public/img/green.svg')  // 图标地址
              }),
              title: '浙江高校9'
            })
            let marker10 = new AMap.Marker({
              position: new AMap.LngLat(120.15283, 30.31445),
              icon: new AMap.Icon({  // 设置点标记图标
                size: new AMap.Size(40, 50),  // 图标尺寸
                image: require('../../public/img/green.svg')  // 图标地址
              }),
              title: '浙江高校10'
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
    },
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