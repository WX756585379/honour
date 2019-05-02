import Vue from 'vue'
import App from './App.vue'
import 'normalize.css/normalize.css' // A modern alternative to CSS resets
import ElementUI from 'element-ui'
import {Message} from 'element-ui'
import locale from 'element-ui/lib/locale/lang/zh-CN' // lang i18n
import 'element-ui/lib/theme-chalk/index.css'
import NProgress from 'nprogress' // Progress 进度条
import 'nprogress/nprogress.css'// Progress 进度条样式
import router from './router'
import store from './store'
import {getToken} from './utils/token'
import 'font-awesome/css/font-awesome.min.css'
import './styles/index.scss' // global css

Vue.use(ElementUI, {locale});

Vue.config.productionTip = false;

const whiteList = ['/login'];
router.beforeEach((to, from, next) => {
	NProgress.start();
	if (getToken()) {
		if (to.path === '/login') {
			next({path: '/'})
		} else {
			if (store.getters.routes.length === 0) {

			} else {
				next()
			}
		}
	} else {
		if (whiteList.indexOf(to.path) !== -1) {
			next()
		} else {
			next('/login')
		}
	}
});

router.afterEach(to => {
	window.document.title = to.meta.title || '前后端分离开发平台';
	NProgress.done();
	window.scrollTo(0, 0);
});

new Vue({
	store,
	router,
	render: h => h(App),
}).$mount('#app');
