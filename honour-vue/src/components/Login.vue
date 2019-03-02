<template>
    <div class="login-wrap">
        <div class="ms-login">
            <div class="ms-title">后台管理系统</div>
            <el-form :model="loginForm" :rules="rules" ref="loginForm" label-width="0px" class="ms-content">
                <el-form-item prop="username">
                    <el-input v-model="loginForm.username" placeholder="username">
                        <el-button slot="prepend" icon="el-icon-lx-people"></el-button>
                    </el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input type="password" placeholder="password" v-model="loginForm.password"
                              @keyup.enter.native="submitForm('loginForm')">
                        <el-button slot="prepend" icon="el-icon-lx-lock"></el-button>
                    </el-input>
                </el-form-item>
                <div class="login-btn">
                    <el-button :loading="loading" type="primary" @click="submitForm('loginForm')">登录</el-button>
                </div>
                <p class="login-tips">Tips : 用户名和密码随便填。</p>
            </el-form>
        </div>
    </div>
</template>

<script>
	export default {
		name: "Login",
		data() {
			const validateUsername = (rule, value, callback) => {
				if (this.isNullOrBlank(value)) {
					callback(new Error('请输入正确的用户名'))
				} else {
					callback()
				}
			}
			const validatePass = (rule, value, callback) => {
				if (value.length < 6) {
					callback(new Error('密码不能小于6位'))
				} else {
					callback()
				}
			}
			return {
				loginForm: {
					username: 'admin',
					password: '123456'
				},
				loginRules: {
					username: [{required: true, trigger: 'blur', validator: validateUsername}],
					password: [{required: true, trigger: 'blur', validator: validatePass}]
				},
				loading: false
			}
		},
		methods: {}
	}
</script>

<style scoped>
    .login-wrap {
        position: relative;
        width: 100%;
        height: 100%;
        background-image: url(../assets/images/login-bg.jpg);
        background-size: 100%;
    }

    .ms-login {
        position: absolute;
        left: 50%;
        top: 50%;
        width: 350px;
        margin: -190px 0 0 -175px;
        border-radius: 5px;
        background: rgba(255, 255, 255, 0.3);
        overflow: hidden;
    }

    .ms-title {
        width: 100%;
        line-height: 50px;
        text-align: center;
        font-size: 20px;
        color: #fff;
        border-bottom: 1px solid #ddd;
    }
</style>