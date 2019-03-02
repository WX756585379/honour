import Vue from 'vue'
import App from './App.vue'
import router from './router';
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import 'normalize.css/normalize.css'
import 'font-awesome/css/font-awesome.min.css'
import {isNullOrBlank} from './utils/validate.js'
import {initMenu} from './utils/utils'

import store from './vuex'

Vue.use(ElementUI)

Vue.prototype.$store = store
Vue.prototype.isNullOrBlank = isNullOrBlank

Vue.config.productionTip = false

// 使用钩子函数对路由进行权限跳转
router.beforeEach((to, from, next) => {
	if (to.name === 'Login' || to.path === '/login') {
		next()
		return
	}
	var token = store.state.token
	var userInfo = store.state.userInfo
	if (isNullOrBlank(token) || isNullOrBlank(userInfo) || isNullOrBlank(userInfo.role)) {
		next('/login')
		return
	}
	if (userInfo.role !== 'admin') {
		next('/403')
		return
	}
	initMenu(router, store)
	next('/login')
})

new Vue({
	store,
	router,
	render: h => h(App),
}).$mount('#app')
