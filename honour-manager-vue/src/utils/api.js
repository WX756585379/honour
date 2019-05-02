import http from "./http"

// 获取菜单
export const apiGetMenus = params => http.post('/menu/getMenus', params);