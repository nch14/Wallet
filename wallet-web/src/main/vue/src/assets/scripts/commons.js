/**
 * Created by nichenhao on 2017/8/19.
 */
import axios from 'axios'

const myAxios = axios.create({
  //baseURL: 'http://v2.manage.chenhaonee.cn',
  baseURL: 'http://localhost:8680',
  timeout: 2000,
});
myAxios.defaults.headers.common['Content-Type'] = 'application/x-www-form-urlencoded';

export {myAxios};
