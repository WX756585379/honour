import Vue from 'vue'
import Router from 'vue-router'
import Login from '../components/login'

Vue.use(Router)

export default new Router({
	mode: 'history',
	routes: [
		{path: '/', redirect: '/dashboard'},
		{path: '/login', name: 'Login', component: Login},
		{path: '*', redirect: '/404'}
	]
})