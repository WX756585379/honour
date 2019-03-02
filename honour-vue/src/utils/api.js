import {get, post} from "./http.js";

// 登录
export const apiLoginCellphone = params => get('/login/cellphone', params)

// 退出登录
export const apiLoginOut = params => post('/logout', params)