import {apiGetMenus} from './api'

let util = {};

util.title = function (title) {
	title = title || '前后端分离开发平台';
	window.document.title = title;
};

util.initMenu = (router, store) => {
	if (store.getters.routes.length > 0) {
		return
	}
	apiGetMenus().then(routes => {
		let fmtRoutes = formatRoutes(routes);
		router.addRoutes(fmtRoutes);
		store.commit('SetRoutes', fmtRoutes);
	})
};