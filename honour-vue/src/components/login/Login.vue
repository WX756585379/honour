<template>
    <div class="login v2">
        <div class="wrapper">
            <div class="dialog dialog-shadow" style="display: block; margin-top: -362px;">
                <div class="title"><h4>使用 XMall 账号 登录官网</h4></div>
                <div class="content">
                    <ul class="common-form">
                        <li class="username border-1p">
                            <div class="input">
                                <input type="text" v-model="ruleForm.userName" placeholder="账号">
                            </div>
                        </li>
                        <li>
                            <div class="input">
                                <input type="password" v-model="ruleForm.userPwd" @keyup.enter="login" placeholder="密码">
                            </div>
                        </li>
                        <li>
                            <div id="captcha"><p id="wait">正在加载验证码...</p></div>
                        </li>
                        <li style="text-align: right" class="pr">
                            <el-checkbox class="auto-login" v-model="autoLogin">记住密码</el-checkbox>
                            <a class="register" @click="toRegister">注册 XMall 账号</a>
                            <a style="padding: 1px 0 0 10px" @click="open('找回密码','请联系作者邮箱找回密码或使用测试账号登录')">忘记密码 ?</a>
                        </li>
                    </ul>
                    <!--登陆-->
                    <div style="margin-top: 25px">
                        <y-button :text="loginTxt"
                                  :classStyle="ruleForm.userPwd && ruleForm.userName && loginTxt === '登录' ? 'main-btn' : 'disabled-btn'"
                                  @btnClick="login"
                                  style="margin: 0;width: 100%;height: 48px;font-size: 18px;line-height: 48px"/>
                    </div>
                    <!--返回-->
                    <div>
                        <y-button text="返回" @btnClick="login_back" class="y-button"/>
                    </div>
                    <div class="border"></div>
                    <div class="footer">
                        <div class="other">其它账号登录：</div>
                        <a>
                            <img @click="open('待开发','此功能开发中...')"
                                 style="height: 15px; margin-top: 22px;"
                                 src="../../assets/images/other-login.png"/>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
	import YButton from '../common/YButton'
	import {geetest, apiLogin} from '../../utils/api.js'
	import {setCookie, removeCookie, setToken} from "../../utils/token";

	require('../../utils/geetest/gt.js')

	var captcha = null

	export default {
		name: "Login",
		data() {
			return {
				ruleForm: {
					userName: '',
					userPwd: '',
					errMsg: ''
				},
				autoLogin: false,
				loginTxt: '登录',
				statusKey: ''
			}
		},
		created() {
			this.init_geetest()
		},
		methods: {
			open(t, m) {
				this.$notify.info({title: t, message: m})
			},
			toRegister() {
				this.$router.push({path: '/register'})
			},
			login() {
				if (!this.ruleForm.userName || !this.ruleForm.userPwd) {
					this.message('账号或者密码不能为空!')
					return false
				}
				var result = captcha.getValidate()
				if (!result) {
					this.message('请完成验证')
					this.loginTxt = '登录'
					return false
				}
				this.loginTxt = '登录中...'
				this.rememberPass()
				var params = {
					userName: this.ruleForm.userName,
					userPwd: this.ruleForm.userPwd,
					challenge: result.geetest_challenge,
					validate: result.geetest_validate,
					seccode: result.geetest_seccode,
					statusKey: this.statusKey
				}
				apiLogin(params).then(res => {
					setToken(res.token)
				})
			},
			rememberPass() {
				if (this.autoLogin === true) {
					setCookie('remember', 'true')
					setCookie('rusername', this.ruleForm.userName)
					setCookie('rpassword', this.ruleForm.userPwd)
				} else {
					setCookie('remember', 'false')
					removeCookie('rusername')
					removeCookie('rpassword')
				}
			},
			login_back() {
				this.$router.go(-1)
			},
			submitForm(formName) {
				this.$refs[formName].validate((valid) => {
					if (valid) {
						localStorage.setItem('ms_username', this.ruleForm.username);
						this.$router.push('/');
					} else {
						return false;
					}
				});
			},
			init_geetest() {
				geetest().then(res => {
					this.statusKey = res.statusKey
					window.initGeetest({
						gt: res.gt,
						challenge: res.challenge,
						new_captcha: res.new_captcha,
						offline: !res.success,
						product: 'popup',
						width: '100%'
					}, function (captchaObj) {
						captcha = captchaObj
						captchaObj.appendTo('#captcha')
						captchaObj.onReady(function () {
							document.getElementById('wait').style.display = 'none'
						})
					})
				})
			}
		},
		components: {
			YButton
		}
	}
</script>

<style lang="scss" rel="stylesheet/scss" scoped>
    @import "./login.scss";
</style>