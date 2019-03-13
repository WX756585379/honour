import {post, get} from "./http.js";

// 登录
export const apiLogin = params => post('/honour/auth/login', params)

// 退出登录
export const apiLoginOut = params => post('/logout', params)

// 获取验证码
export const geetest = params => get('/honour/common/geeInit?t=' + (new Date()).getTime(), params)