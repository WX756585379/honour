import Vue from 'vue'
import Router from 'vue-router'
import Login from '../components/login/Login'
import Register from '../components/login/Register'

Vue.use(Router)

export default new Router({
	mode: 'history',
	routes: [
		{path: '/', redirect: '/dashboard'},
		{path: '/login', name: 'Login', component: Login},
		{path: '/register', name: 'Register', component: Register},
		{path: '*', redirect: '/404'}
	]
})