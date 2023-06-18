<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<style lang="scss">
  body {
    margin: 0px;
    padding: 0px;
    font-family: Microsoft YaHei, Helvetica Neue, Helvetica, PingFang SC, Hiragino Sans GB,  SimSun, sans-serif;
    // font-size: 14px;
    -webkit-font-smoothing: antialiased;
  }
  
  #app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
    width: 100%;
    height: 100%;
    margin: 0; 
    padding: 0; 
    background-color: #f0f2f5;
    position: absolute;
    top: 0px;
    bottom: 0px;
    left: 0px;
    right: 0px;
  }

  .el-header {
    background-color: #B3C0D1;
    color: #333;
  }
  
  .el-aside {
    color: #333;
  }
</style>

<script>
  export default {
    name: 'App',
    components: {

    },
    data() {
      const item = {
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      };
      return {
        tableData: Array(20).fill(item)
      }
    }
  };
</script>