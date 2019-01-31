<template>
  <div>
    <el-container>
      <el-aside width="30vw">
        <div>
          <div class="date-panel">
            <p class="cn-center-me guide-text">{{ current }}</p>
            <p class="cn-center-me guide-text">{{ day }}</p>

            <p class="cn-center-me guide-text"> 今日预算：{{ budgetForToday}}</p>
            <p class="cn-center-me guide-text"> 剩余预算：{{leftForToday}}</p>
            <!--<div class="cn-center-me" id="moneyLeftToday"></div>-->
          </div>

          <div class="quick-note">
            <p>快速记账</p>
            <el-form ref="form" :model="form" label-width="80px">
              <el-form-item label="金额">
                <el-input type="number" v-model="form.money"></el-input>
              </el-form-item>
              <el-form-item label="备注">
                <el-input v-model="form.memo"></el-input>
              </el-form-item>
              <!--<el-form-item label="支付">
                <el-radio-group v-model="form.palType">
                  <el-radio label="现金"></el-radio>
                  <el-radio label="Card"></el-radio>
                  <el-radio label="其他"></el-radio>
                </el-radio-group>
              </el-form-item>-->
              <el-form-item label="类型">
                <el-select v-model="form.billType" placeholder="请选择账单类型">
                  <el-option label="饮食" value="0"></el-option>
                  <el-option label="租房" value="1"></el-option>
                  <el-option label="交通" value="2"></el-option>
                  <el-option label="购物" value="3"></el-option>
                  <el-option label="其他" value="4"></el-option>
                </el-select>
              </el-form-item>

              <el-form-item>
                <el-button type="primary" @click="submitBill">确认</el-button>
                <el-button @click="clearBill">取消</el-button>
              </el-form-item>
            </el-form>
          </div>
        </div>
      </el-aside>

      <el-main>

        <div>
          <div id="billSummary">

          </div>

          <div id="monthSummary">

          </div>
        </div>
      </el-main>
    </el-container>
  </div>


</template>

<script>
  import {myAxios} from "@/assets/scripts/commons";

  export default {
      name: "Console",
      data() {
        return {
          form: {
            memo: '',
            money:'',
            palType: '',
            billType: '',
          },
          budgetForToday:50,
          leftForToday:50
        }
      },
      created(){
        this.applyBudget();
        this.leftBudget();
      },
      computed:{
        current() {
          let myDate = new Date();
          let year = myDate.getFullYear();
          let month = myDate.getMonth() + 1;
          let date = myDate.getDate();
          return `今天是${year}年${month}月${date}日`
        },
        day(){
          let myDate = new Date();
          let day = myDate.getDay();
          if(day === 0)
            day = '日'
          return `星期${day}`
        }
      },
      mounted(){
        this.monthSummary();
        this.billSummary();
      },
      methods: {
        clearBill(){
          this.form = {
            memo: '',
            money:'',
            palType: '',
            billType: '',
          };
        },
        submitBill(){
          let request = {
            money:this.form.money,
            memo:this.form.memo,
            billType:this.form.billType
          };

          let userId = 666666;
          myAxios.put(`/bill/${userId}`,request)
            .then(res=>{
              if (res.data.success){
                this.$message({
                  message: '添加成功',
                  type: 'success'
                });
                this.leftBudget();
              }
            })
            .catch(err=>{
              this.$message.error('添加失败');
              console.log(err)
            });
        },
        applyBudget(){
          myAxios.get(`/budget/today/allow`)
            .then(res=>{
                this.budgetForToday = res.data.t;
            })
            .catch(err=>{
              this.$message.error('查询预算失败');
              console.log(err)
            })
        },
        leftBudget(){
          myAxios.get(`/budget/today/left`)
            .then(res=>{
              this.leftForToday = res.data.t;
            })
            .catch(err=>{
              this.$message.error('查询余额失败');
              console.log(err)
            })
        },
        monthSummary(){
          let monthSummaryChart = this.$echarts.init(document.getElementById('monthSummary'));
          // 绘制图表
          monthSummaryChart.setOption({
            title: {
              text: '月度统计'
            },
            tooltip: {
              trigger: 'axis'
            },
            legend: {
              data:['合计','饮食','交通','购物','租房','其它']
            },
            grid: {
              left: '3%',
              right: '4%',
              bottom: '3%',
              containLabel: true
            },
            xAxis: {
              type: 'category',
              boundaryGap: false,
              data: ['周一','周二','周三','周四','周五','周六','周日']
            },
            yAxis: {
              type: 'value'
            },
            series: [
              {
                name:'合计',
                type:'line',
                stack: '总量',
                data:[120, 132, 101, 134, 90, 230, 210]
              },
              {
                name:'饮食',
                type:'line',
                stack: '总量',
                data:[220, 182, 191, 234, 290, 330, 310]
              },
              {
                name:'交通',
                type:'line',
                stack: '总量',
                data:[150, 232, 201, 154, 190, 330, 410]
              },
              {
                name:'购物',
                type:'line',
                stack: '总量',
                data:[320, 332, 301, 334, 390, 330, 320]
              },
              {
                name:'租房',
                type:'line',
                stack: '总量',
                data:[820, 932, 901, 934, 1290, 1330, 1320]
              },
              {
                name:'其它',
                type:'line',
                stack: '总量',
                data:[820, 932, 901, 934, 1290, 1330, 1320]
              }
            ]
          });
        },
        billSummary(){
          let billSummaryChart = this.$echarts.init(document.getElementById('billSummary'));
          // 绘制图表
          billSummaryChart.setOption({
            title: {
              text: '消费类型'
            },
            tooltip: {},
            radar: {
              name: {
                textStyle: {
                  color: '#fff',
                  backgroundColor: '#999',
                  borderRadius: 3,
                  padding: [3, 5]
                }
              },
              indicator: [
                { name: '销售（sales）', max: 6500},
                { name: '管理（Administration）', max: 16000},
                { name: '信息技术（Information Techology）', max: 30000},
                { name: '客服（Customer Support）', max: 38000},
                { name: '研发（Development）', max: 52000},
                { name: '市场（Marketing）', max: 25000},
              ]
            },
            series: [{
              type: 'radar',
              data : [{
                  value : [4300, 10000, 28000, 35000, 50000, 19000],
                }
              ]
            }]
          });
        }
      }
    }
</script>

<style scoped>
  .welcome{
    width: 30%;
    background: bisque;
    height: 100vh;
  }

  .date-panel{
    margin-top: 15vh;

    background: aquamarine;
  }

  .graph{
    flex:1;
    background: aquamarine;
  }

  .guide-text{
    color: #303133;
    font-size: 18px;
  }

  .quick-note{
    margin-top: 10vh;
    margin-left: 20px;
    margin-right: 20px;
  }

  #moneyLeftToday{
    margin-left: 40px;
    margin-right: 40px;
    width: calc(30vh - 80px);
    height: calc(30vh - 80px);
  }
  #billSummary{
    margin-left: 40px;
    margin-right: 40px;
    width: calc(35vw - 80px);
    height: calc(40vh);
  }
  #monthSummary{
    margin-top: 10vh;

    margin-left: 40px;
    margin-right: 40px;
    width: calc(70vw - 80px);
    height: calc(40vh);
  }
</style>
