import axios from 'axios'
import {Message} from 'element-ui'

if (process.env.NODE_ENV === 'development') {
	axios.defaults.baseURL = 'http://localhost:8087'
} else if (process.env === 'production') {
	axios.defaults.baseURL = 'https://netease2.bluej.cn';
}

axios.defaults.timeout = 10000
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8'

axios.interceptors.request.use(config => {
	config.headers['token'] = this.$store.state.token
	return config
}, err => {
	Message.error({message: '请求错误!'})
	return Promise.reject(err)
})

axios.interceptors.response.use(response => {
	return response
}, error => {
	return Promise.reject(error)
})

export function get(url, params = {}) {
	return new Promise((resolve, reject) => {
		axios.get(url, {
			params: params
		}).then(res => {
			resolve(res.data)
		}).catch(err => {
			reject(err.data)
		})
	})
}

export function post(url, params = {}) {
	return new Promise((resolve, reject) => {
		axios.post(url, params).then(res => {
			resolve(res.data);
		}).catch(err => {
			reject(err.data)
		})
	})
}

export const getRequest = (url) => {
	return axios({
		method: 'get',
		url: url
	})
}

