import axios from 'axios'
import {getToken} from './token'
import {Message, MessageBox} from 'element-ui'

let baseURL = '';
if (process.env.NODE_ENV === 'development') {
	baseURL = 'http://127.0.0.1:8081'
} else if (process.env === 'production') {
	baseURL = 'https://netease2.bluej.cn/';
}

const service = axios.create({
	baseURL: baseURL, // api的base_url
	timeout: 15000 // 请求超时时间
});

service.interceptors.request.use(config => {
	config.headers['Authorization'] = getToken() || '';
	return config
}, error => {
	return Promise.reject(error)
});

service.interceptors.response.use(response => {
	const res = response.data;
	if (res.code !== 200) {
		Message({message: res.message, type: 'error', duration: 3 * 1000});
		if (res.code === 401 || res.code === 403) {
			MessageBox.confirm('你已被登出，可以取消继续留在该页面，或者重新登录', '确定登出', {
				confirmButtonText: '重新登录',
				cancelButtonText: '取消',
				type: 'warning'
			}).then(() => {
				store.dispatch('FedLogOut').then(() => {
					location.reload()// 为了重新实例化vue-router对象 避免bug
				})
			})
		}
		return Promise.reject('error')
	} else {
		return response.data
	}
}, error => {
	Message({message: error.message, type: 'error', duration: 3 * 1000});
	return Promise.reject(error)
});

export default {
	get(url, params) {
		return new Promise((resolve, reject) => {
			service.get(url, params).then(res => {
				resolve(res)
			}).catch(err => {
				reject(err)
			})
		})
	},
	post(url, params) {
		return new Promise((resolve, reject) => {
			service.post(url, params).then(res => {
				resolve(res);
			}).catch(err => {
				reject(err)
			})
		})
	}
}