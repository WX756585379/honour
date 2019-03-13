<template>
    <div class="login v2">
        <div class="wrapper">
            <div class="dialog dialog-shadow" style="display: block; margin-top: -362px;">
                <div class="registered">
                    <h4>注册 XMall 账号</h4>
                    <div class="content" style="margin-top: 20px;">
                        <ul class="common-form">
                            <li class="username border-1p">
                                <div style="margin-top: 40px;" class="input">
                                    <input type="text" v-model="registered.userName" placeholder="账号"/>
                                </div>
                            </li>
                            <li>
                                <div class="input">
                                    <input type="password" v-model="registered.userPwd" placeholder="密码"/>
                                </div>
                            </li>
                            <li>
                                <div class="input">
                                    <input type="password" v-model="registered.userPwd2" placeholder="重复密码"/>
                                </div>
                            </li>
                            <li>
                                <div id="captcha"><p id="wait">正在加载验证码...</p></div>
                            </li>
                        </ul>
                        <div style="float: left;margin-bottom: 20px">
                            <el-checkbox style="margin-right: 5px" v-model="agreement"/>
                            <span>我已阅读并同意遵守</span>
                            <a @click="open('法律声明','此仅为个人练习开源模仿项目，仅供学习参考，承担不起任何法律问题')">法律声明</a> 和
                            <a @click="open('隐私条款','本网站将不会严格遵守有关法律法规和本隐私政策所载明的内容收集、使用您的信息')">隐私条款</a>
                        </div>
                        <y-button
                                :classStyle="registered.userPwd&&registered.userPwd2&&registered.userName&&regisTxt==='注册' ? 'main-btn' : 'disabled-btn'"
                                :text="regisTxt"
                                style="margin: 0;width: 100%;height: 48px;font-size: 18px;line-height: 48px"
                                @btnClick="doRegist"/>
                        <ul class="common-form pr">
                            <li style="text-align: center;line-height: 48px;margin-bottom: 0;font-size: 12px;color: #999;">
                                <span>如果您已拥有 XMall 账号，则可在此</span>
                                <a style="margin: 0 5px" @click="toLogin">登陆</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
	/* eslint-disable no-unused-vars */
	import YButton from '../common/YButton'
	import {geetest} from '../../utils/api.js'

	require('../../utils/geetest/gt.js')

	var captcha = null

	export default {
		name: "Register",
		data() {
			return {
				registered: {
					userName: '',
					userPwd: '',
					userPwd2: '',
					errMsg: ''
				},
				regisTxt: '注册',
				agreement: false,
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
			doRegist() {
				this.regisTxt = '注册中...'
				let userName = this.registered.userName
				let userPwd = this.registered.userPwd
				let userPwd2 = this.registered.userPwd2
				if (!userName || !userPwd || !userPwd2) {
					this.message('账号密码不能为空!')
					this.regisTxt = '注册'
					return false
				}
				if (userPwd2 !== userPwd) {
					this.message('两次输入的密码不相同!')
					this.regisTxt = '注册'
					return false
				}
				if (!this.agreement) {
					this.message('您未勾选同意我们的相关注册协议!')
					this.regisTxt = '注册'
					return false
				}
				// var result = captcha.getValidate()
				if (!result) {
					this.message('请完成验证')
					this.regisTxt = '注册'
					return false
				}
			},
			toLogin() {
				this.$router.push({path: '/login'})
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