<template>
  <div class="cn-flex login-page">

    <div>
      <div class="cn-flex">
        <div class="action-title cn-center-me" @click="doSwitch" v-bind:class="{actionActive:switcher}">
          登录
        </div>

        <div class="action-title cn-center-me" @click="doSwitch" v-bind:class="{actionActive:!switcher}">
          注册
        </div>
      </div>

      <div v-if="switcher">
        <el-card class="box-card action-card">
          <el-form ref="form" :model="login">
            <el-form-item>
              <el-input v-model="login.username" placeholder="请输入用户名"></el-input>
            </el-form-item>
            <el-form-item>
              <el-input v-model="login.password" type="password" placeholder="请输入密码"></el-input>
            </el-form-item>
            <el-form-item class="cn-center-me">
              <el-button type="primary" @click="doLogin">登录</el-button>
              <el-button @click="clearLogin">取消</el-button>
            </el-form-item>
          </el-form>
        </el-card>
      </div>

      <div v-else>
        <el-card class="box-card action-card">
          <el-form ref="form" :model="join">
            <el-form-item>
              <el-input v-model="join.username" placeholder="请输入用户名"></el-input>
            </el-form-item>
            <el-form-item>
              <el-input v-model="join.nickname" placeholder="请输入昵称"></el-input>
            </el-form-item>
            <el-form-item>
              <el-input v-model="join.password" type="password" placeholder="请输入密码"></el-input>
            </el-form-item>
            <el-form-item class="cn-center-me">
              <el-button type="primary" @click="doJoin">注册</el-button>
              <el-button @click="clearJoin">取消</el-button>
            </el-form-item>
          </el-form>
        </el-card>
      </div>
    </div>

  </div>
</template>

<script>
  import {myAxios} from "@/assets/scripts/commons";

  export default {
    name: "Welcome",
    data() {
      return {
        switcher: true,
        join: {
          nickname: '',
          username: '',
          password: ''
        },
        login: {
          username: '',
          password: ''
        }
      }
    },
    methods: {
      doSwitch() {
        this.switcher = !this.switcher;
      },
      doLogin() {
        let request = {
          username:this.login.username,
          password:this.login.password
        };
        myAxios.post('/account/login', request)
          .then(res => {
            console.log(res)
            if (res.data.code == 200) {
              let data = res.data.t;

              console.log(data)
              this.$store.commit({
                type: 'setUserInfo',
                nickname: data.nickname
              });
              this.$store.commit({
                type: 'setToken',
                token: data.token
              });

              myAxios.defaults.headers.OAuth = data.token;

              this.$router.push({ name: 'Console' })
            }
          })
          .catch(err => {
            this.$message.error("登录失败");
            console.log(err);
          })
      },
      doJoin() {
        let request = {
          username:this.join.username,
          password:this.join.password,
          nickname:this.join.nickname
        };
        myAxios.put('/account/join', request)
          .then(res => {
            if (res.data.success) {
              this.$message.success("注册成功");
              this.doSwitch();
            }
          })
          .catch(err => {
            this.$message.error("注册失败");
            console.log(err);
          })
      },
      clearLogin() {

      },
      clearJoin() {

      }
    }
  }
</script>

<style scoped>
  .login-page {
    justify-content: center; /* 水平居中 */
    align-items: center; /* 垂直居中 */
    height: calc(100vh - 2px);
  }

  .action-title {
    flex: 1;
    padding: 30px 20px;
    color: #969696;
  }

  .action-card {
    width: 30vw;
  }

  .actionActive {
    font-weight: 700;
    color: #ea6f5a;
    border-bottom: 2px solid #ea6f5a;
  }
</style>
