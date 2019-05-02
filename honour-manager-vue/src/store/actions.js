import {removeToken} from '../utils/token'

export const FedLogOut = function ({commit}) {
	commit('SET_TOKEN', '');
	removeToken()
};

export const SetRoutes = function ({commit}, routes) {
	commit('SET_ROUTES', routes)
};
