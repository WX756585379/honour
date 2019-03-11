import Cookies from 'js-cookie'

export const tokenKey = 'loginToken'

export function getCookie(key) {
	return Cookies.get(key)
}

export function setCookie(key, content) {
	if (!key) return
	if (typeof content !== 'string') {
		content = JSON.stringify(content)
	}
	return Cookies.set(key, content)
}

export function removeCookie(key) {
	return Cookies.remove(key)
}

export function getToken() {
	return Cookies.get(tokenKey)
}

export function setToken(token) {
	return Cookies.set(tokenKey, token)
}

export function removeToken() {
	return Cookies.remove(tokenKey)
}