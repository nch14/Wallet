/**
 * Created by nichenhao on 2017/8/19.
 */
import axios from 'axios'
import router from '../../router'
import route from '../../router'
import store from './storage'

const myAxios = axios.create({
  //baseURL: 'http://v2.manage.chenhaonee.cn',
  baseURL: 'http://localhost:8680',
  timeout: 2000,
});
myAxios.interceptors.response
  .use(res => {
    if (res.data.code == 600 || res.data.code == 601) {
      if (route.name != 'Welcome') {
        store.commit({
          type: 'clearUserInfo'
        });
        router.push({name: 'Welcome'})
      }
    }
    return res;
  }, err => {
    console.log(err)
    return err;
  });
export {myAxios};
