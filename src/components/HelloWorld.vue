<template>
    <div>
      <video class="demo-video" ref="player"></video>
    </div>
</template>
<script>
  import flvjs from "flv.js";
  export default {
    props: {
      rtsp: String,
      id: String,
      canPlay: Boolean
    },
    watch:{
      canPlay(){
        if(this.canPlay){
          console.log('OK');
          this.player.play()
        }
      }
    },

    
    data() {
      return {
        player: null,
      };
    },
    mounted() {
      if (flvjs.isSupported()) {
        let video = this.$refs.player;
        if (video) {
          this.player = flvjs.createPlayer({
            type: "flv",
            isLive: true,
            url: `ws://localhost:8888/rtsp/${this.id}/?url=${this.rtsp}`,
            hasVideo: true,
          });
          this.player.attachMediaElement(video);
          try {
            // 【重要事件监听】http请求建立好后，该事件会一直监听flvjs实例
            this.player.on(flvjs.Events.STATISTICS_INFO, (res) => {
              console.log("请求数据信息");
            });
  
            this.player.load();
            // this.player.play();
          } catch (error) {
            console.log(error);
          }
        }
      }
    },
    beforeDestroy() {
      this.player.destory();
    },
  };
  </script>
  <style>
  .demo-video {
    max-width: 480px;
    max-height: 360px;
  }
  </style>
  