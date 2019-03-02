import {getRequest} from './api'

export const initMenu = (router, store) => {
	if (store.state.routes.length > 0) {
		return
	}
	getRequest('/config/menu').then(res => {
		if (res && res.status === 200) {
			// console.log(res)
			// var fmtRoutes = formatRoutes(res.data);
			// router.addRoutes(fmtRoutes);
			// store.commit('initMenu', fmtRoutes);
			// store.dispatch('connect');
		}
	})
}