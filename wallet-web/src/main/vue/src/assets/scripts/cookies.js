/**
 * 添加cookie
 * @param objName
 * @param objValue
 * @param objSeconds
 */
export function addCookie(objName, objValue, objSeconds) {
  let str = objName + "=" + escape(objValue);
  //为0时不设定过期时间，浏览器关闭时cookie自动消失
  if (objSeconds > 0) {
    let date = new Date();
    let ms = objSeconds * 1000;
    date.setTime(date.getTime() + ms);
    str += "; expires=" + date.toGMTString();
  }
  document.cookie = str;
}

/**
 * 获取指定名称的cookie的值
 * @param objName
 * @returns {string}
 */
export function getCookie(objName) {
  let arrStr = document.cookie.split("; ");
  for (let i = 0; i < arrStr.length; i++) {
    let temp = arrStr[i].split("=");
    if (temp[0] === objName)
      return unescape(temp[1]);
  }
}

/**
 * 为了删除指定名称的cookie，可以将其过期时间设定为一个过去的时间
 * @param name
 */
export function delCookie(name) {
  let date = new Date();
  date.setTime(date.getTime() - 10000);
  document.cookie = name + "=a; expires=" + date.toGMTString();
}

/**
 * 读取所有保存的cookie字符串
 */
function allCookie() {
  return document.cookie;
}
