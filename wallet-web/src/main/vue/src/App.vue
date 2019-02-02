<template>
  <div id="app">
    <el-header v-if="showNav" class="head">
      <div class="cn-flex" style="justify-content:space-between">
        <div class="cn-flex">
          <div class="logo">瑾 Pal</div>
          <div class="head-item" v-bind:class="{headItemActive:selected === 0}" @click="onClickMenu(0)">我的首页</div>
          <div class="head-item" v-bind:class="{headItemActive:selected === 1}" @click="onClickMenu(1)">我的计划</div>
          <div class="head-item" v-bind:class="{headItemActive:selected === 2}" @click="onClickMenu(2)">我的账单</div>
          <div class="head-item" v-bind:class="{headItemActive:selected === 3}" @click="onClickMenu(3)">我的账户</div>
        </div>
        <div>
          <el-dropdown class="user-info"  @command="handleCommand">
            <p>{{nickname}}<i class="el-icon-arrow-down el-icon--right"></i></p>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>发起工单</el-dropdown-item>
              <el-dropdown-item divided command="leave">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </div>
    </el-header>
    <router-view/>
  </div>
</template>

<script>

  import {getCookie} from './assets/scripts/cookies'

  export default {
    name: 'App',
    data() {
      return {
        selected: 0,
      }
    },
    created() {
      console.log('init')

      let oauth = getCookie("OAuth");
      if (oauth) {
        this.$store.commit({
          type: 'setToken',
          token: oauth
        });
      }

      let nickname = getCookie("nickname");
      if (nickname) {
        this.$store.commit({
          type: 'setUserInfo',
          nickname: nickname
        });
      }

      if (this.$route.name != 'Welcome' && (!oauth)) {
        this.$router.push({name: 'Welcome'});
        console.log('jump')
      } else {
        this.$http.defaults.headers.OAuth = oauth;
        console.log('wait')
      }
    },
    computed: {
      showNav() {
        return !this.$route.meta.hideHead;
      },
      nickname() {
        return this.$store.getters.getNickname;
      },
      token() {
        return this.$store.getters.getToken;
      }
    },
    methods: {
      onClickMenu(index) {
        this.selected = index;
      },
      handleCommand(command){
        switch (command) {
          case 'leave':
            this.onLeave();
            break;
        }
      },
      onLeave() {
        this.$router.push({name: 'Welcome'});
        this.$store.commit({
          type: 'clearUserInfo'
        })
      }
    }
  }
</script>

<style>
  #app {
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
    color: #2c3e50;
  }

  .head {
    background-color: rgb(84, 92, 100);
  }

  .head-item {
    padding: 5px 20px;
    display: flex;
    height: 50px;
    align-items: center; /*垂直居中*/
    justify-content: center; /*水平居中*/
    color: white;
    background-color: rgb(84, 92, 100);
    font-weight: 400;
  }

  .user-info {
    padding: 5px 20px;
    display: flex;
    height: 50px;
    align-items: center; /*垂直居中*/
    justify-content: center; /*水平居中*/
    color: white;
    background-color: rgb(84, 92, 100);
    font-weight: 400;
  }

  .head-item:hover {
    background-color: rgb(67, 74, 80)
  }

  .headItemActive {
    border-bottom: 2px solid rgb(255, 208, 75);
    color: rgb(255, 208, 75);
  }

  .logo {
    padding: 5px 20px;
    display: flex;
    height: 50px;
    align-items: center; /*垂直居中*/
    justify-content: center; /*水平居中*/
    color: white;
    background-color: rgb(84, 92, 100);
    font-weight: 400;
  }

  .el-dropdown {
    vertical-align: top;
  }

  .el-dropdown + .el-dropdown {
    margin-left: 15px;
  }

  .el-icon-arrow-down {
    font-size: 12px;
  }

</style>
