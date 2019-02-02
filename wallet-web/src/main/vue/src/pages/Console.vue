<template>
  <div>
    <el-container>
      <el-aside width="30vw">
        <div>
          <div>
            <!--<el-carousel class="welcome" height="15vw" indicator-position="outside">
              <el-carousel-item v-for="item in 4" :key="item">
                <h3 class="cn-center-me">{{ current }}</h3>
              </el-carousel-item>
            </el-carousel>-->

            <el-card class="box-card bill-card">
              <div slot="header" class="clearfix">
                <span>亲爱的，今天是{{ current }}</span>
              </div>
              <div class="cn-flex">
                <div class="bill-card-item cn-center-me">
                  <p class="bill-card-item-title">今日可用</p>
                  <p class="bill-card-item-money"><i class="fa fa-cny"></i> {{dashboard.leftForToday}}</p>
                </div>
                <div class="bill-card-item cn-center-me">
                  <p class="bill-card-item-title">本月可用</p>
                  <p class="bill-card-item-money"><i class="fa fa-cny"></i> {{ dashboard.leftThisMonth}}</p>
                </div>
              </div>

              <div class="cn-flex">
                <div class="bill-card-item cn-center-me">
                  <p class="bill-card-item-sub-title">本月预算</p>
                  <p class="bill-card-item-sub-money"><i class="fa fa-cny"></i> {{dashboard.budgetThisMonth}}</p>
                </div>

                <div class="bill-card-item cn-center-me">
                  <p class="bill-card-item-sub-title">本月支出</p>
                  <p class="bill-card-item-sub-money"><i class="fa fa-cny"></i> {{ dashboard.usedThisMonth}}</p>
                </div>

                <div class="bill-card-item cn-center-me">
                  <p class="bill-card-item-sub-title">本月收入</p>
                  <p class="bill-card-item-sub-money"><i class="fa fa-cny"></i> {{dashboard.incomeThisMonth}} </p>
                </div>

                <div class="bill-card-item cn-center-me">
                  <p class="bill-card-item-sub-title">累计存款</p>
                  <p class="bill-card-item-sub-money"><i class="fa fa-cny"></i> {{dashboard.deposit}}</p>
                </div>
              </div>
            </el-card>
          </div>

          <div class="quick-note">
            <el-tabs v-model="noteModel" type="card">
              <el-tab-pane label="快速记账" name="first">
                <el-card class="box-card">
                  <el-form ref="form" :model="bill" label-width="80px">
                    <el-form-item label="金额">
                      <el-input type="number" v-model="bill.money"></el-input>
                    </el-form-item>
                    <el-form-item label="备注">
                      <el-input v-model="bill.memo"></el-input>
                    </el-form-item>
                    <el-form-item label="日常">
                      <el-switch v-model="bill.daily"></el-switch>
                    </el-form-item>
                    <el-form-item label="类型">
                      <el-select v-model="bill.billType" placeholder="请选择账单类型">
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
                </el-card>
              </el-tab-pane>
              <el-tab-pane label="快速入账" name="second">
                <el-card class="box-card">
                  <el-form ref="form" :model="income" label-width="80px">
                    <el-form-item label="金额">
                      <el-input type="number" v-model="income.money"></el-input>
                    </el-form-item>
                    <el-form-item label="月份">
                      <el-date-picker
                        v-model="income.incomeMonth"
                        type="month"
                        placeholder="选择月">
                      </el-date-picker>
                    </el-form-item>
                    <el-form-item label="类型">
                      <el-select v-model="income.incomeType" placeholder="请选择账单类型">
                        <el-option label="工资" value="0"></el-option>
                        <el-option label="奖金" value="1"></el-option>
                        <el-option label="利息" value="2"></el-option>
                        <el-option label="其它" value="3"></el-option>
                      </el-select>
                    </el-form-item>
                    <el-form-item label="备注">
                      <el-input v-model="income.memo"></el-input>
                    </el-form-item>
                    <el-form-item>
                      <el-button type="primary" @click="submitIncome">确认</el-button>
                      <el-button @click="clearIncome">取消</el-button>
                    </el-form-item>
                  </el-form>
                </el-card>
              </el-tab-pane>
            </el-tabs>

          </div>
        </div>
      </el-aside>

      <el-main>

        <div>
          <div class="cn-flex">
            <div id="billSummary">

            </div>

            <div id="months">

            </div>
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
          bill: {
            memo: '',
            money:'',
            palType: '',
            billType: '',
            daily:true
          },
          income:{
            money:'',
            incomeMonth:'',
            memo:'',
            incomeType:''
          },
          dashboard:{
            deposit:0,
            leftForToday:0,
            budgetThisMonth:0,
            leftThisMonth:0,
            usedThisMonth:0,
            incomeThisMonth:0,
          },

          noteModel:'first',
        }
      },
      created(){
        this.applyDashboard();
      },
      computed:{
        current() {
          let myDate = new Date();
          let year = myDate.getFullYear();
          let month = myDate.getMonth() + 1;
          let date = myDate.getDate();
          return `${year}年${month}月${date}日`
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
        this.monthsSummary();
      },
      methods: {
        clearBill(){
          this.form = {
            memo: '',
            money:'',
            palType: '',
            billType: '',
            daily:true
          };
        },
        clearIncome(){
          this.income = {
            money:'',
            incomeMonth:'',
            memo:'',
            incomeType:''
          };
        },
        submitBill(){
          let request = {
            money:this.bill.money,
            memo:this.bill.memo,
            billType:this.bill.billType,
            daily:this.bill.daily
          };
          myAxios.put(`/bill`,request)
            .then(res=>{
              if (res.data.code === 200){
                this.$message({
                  message: '添加成功',
                  type: 'success'
                });
                this.applyDashboard();
              }
            })
            .catch(err=>{
              this.$message.error('添加失败');
              console.log(err)
            });
        },
        submitIncome(){
          let request = this.income;
          myAxios.put(`/income`,request)
            .then(res=>{
              if (res.data.code === 200){
                this.$message({
                  message: '添加成功',
                  type: 'success'
                });
                this.applyDashboard();
              }
            })
            .catch(err=>{
              this.$message.error('添加失败');
              console.log(err)
            });
        },
        applyDashboard(){
          myAxios.get(`/budget/dashboard`)
            .then(res=>{
                this.dashboard = res.data.t;
            })
            .catch(err=>{
              this.$message.error('查询预算失败');
              console.log(err)
            })
        },
        monthSummary(){
          //折线图
          myAxios.get('/expense/month/daily')
            .then(res=>{
              let data = res.data.t;

              let myDate = new Date();
              let date = myDate.getDate();

              let graphX = [];
              for(let i = 0;i<date;i++){
                graphX.push(i+1);
              }
              let series = data.expenses.map(item=>{
                return {
                  name: item.type,
                  type:'line',
                  stack: '总量',
                  data: item.money
                };
              });
              let monthSummaryChart = this.$echarts.init(document.getElementById('monthSummary'));
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
                  left: '2%',
                  right: '2%',
                  bottom: '3%',
                  containLabel: true
                },
                xAxis: {
                  type: 'category',
                  boundaryGap: false,
                  data: graphX
                },
                yAxis: {
                  type: 'value'
                },
                series: series
              });
            })
            .catch(err=>{
              this.$message.warning('加载雷达数据失败');
              console.log(err)
            });

        },
        billSummary(){
          myAxios.get('/expense/month/typed')
            .then(res=>{
                let data = res.data.t;
                let graphDefine = data.expenses.map(item=>{
                  return { name: item.type, max: 1000}
                });
                let graphData = data.expenses.map(item=>{
                  return {value:item.money, name:item.type};
                });
              let billSummaryChart = this.$echarts.init(document.getElementById('billSummary'));
              billSummaryChart.setOption({
                title: {
                  text: '支出比例'
                },
                tooltip: {
                  trigger: 'item',
                  formatter: "{b}: {c} ({d}%)"
                },
                legend: {
                  orient: 'vertical',
                  x: 'right',
                  data: graphDefine
                },
                series: [
                  {
                    type:'pie',
                    radius: ['50%', '70%'],
                    avoidLabelOverlap: false,
                    label: {
                      normal: {
                        show: false,
                        position: 'center'
                      },
                      emphasis: {
                        show: true,
                        textStyle: {
                          fontSize: '30',
                          fontWeight: 'bold'
                        }
                      }
                    },
                    labelLine: {
                      normal: {
                        show: false
                      }
                    },
                    data:graphData
                  }
                ]
              });
            })
            .catch(err=>{
                this.$message.warning('加载雷达数据失败');
            });
        },
        monthsSummary(){
          myAxios.get('/expense/year/half')
            .then(res=>{
              let data = res.data.t;
              let graphDefine = data.expenses.map(item=>{
                return item.month;
              });
              let graphData = data.expenses.map(item=>{
                return item.money;
              });
              let monthsSummaryChart = this.$echarts.init(document.getElementById('months'));
              monthsSummaryChart.setOption({
                title: {
                  text: '半年账单'
                },
                xAxis: {
                  type: 'category',
                  data: graphDefine
                },
                yAxis: {
                  type: 'value'
                },
                series: [{
                  data: graphData,
                  type: 'bar'
                }]
              });
            })
            .catch(err=>{
              this.$message.warning('加载历史数据失败');
            });
        }
      }
    }
</script>

<style scoped>
  .welcome{
    margin-left: 10px;
    margin-right: 10px;
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
    margin-top: 2vh;
    margin-left: 20px;
    margin-right: 20px;
  }

  #moneyLeftToday{
    margin-left: 40px;
    margin-right: 40px;
    width: calc(30vh - 30px);
    height: calc(30vh - 80px);
  }
  #billSummary{
    margin-left: 10px;
    margin-right: 10px;
    width: calc(35vw - 30px);
    height: calc(47.5vh - 50px);
  }
  #monthSummary{
    margin-top: 5vh;

    margin-left: 10px;
    margin-right: 10px;
    width: calc(70vw - 60px);
    height: calc(47.5vh - 50px);
  }

  #months{
    margin-left: 10px;
    margin-right: 10px;
    width: calc(35vw - 30px);
    height: calc(47.5vh - 50px);
  }

  .bill-card{
    margin-top: 20px;

    margin-left: 20px;
    margin-right: 20px;
  }

  .bill-card-item{
    flex: 1;
  }

  .bill-card-item-title{
    color: #606266;
    font-size: 16px;
  }

  .bill-card-item-money{
    font-size: 24px;
    color: #303133;
  }

  .bill-card-item-sub-title{
    color: #909399;
    font-size: 12px;
  }

  .bill-card-item-sub-money{
    font-size: 16px;
    color: #303133;
  }

  .el-carousel__item h3 {
    color: #475669;
    font-size: 18px;
    opacity: 0.75;
    height: 15vw;
    margin: 0;
  }

  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }
</style>
