import {getToken} from "../../utils/token";

const user = {
	state: {
		routes: [],
		userInfo: {},
		token: getToken()
	},
	mutations: {
		SET_TOKEN: (state, token) => {
			state.token = token
		},
		SET_USER_INFO: (state, info) => {
			state.userInfo = info
		}
	},
	actions: {}
}

export default user