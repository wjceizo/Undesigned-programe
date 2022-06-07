<template>
    <div class="login-container">
		<div class="login-bgp">
			
		</div>
        <div class="login-win">
			<div class="login-body">
				<div class="login-title">
					系统登录
				</div>
				<div class="login-form">
					<el-form :model="loginForm" :rules="rules" ref="loginForm">
						<el-form-item prop="userName">
							<el-input type="text" 
                                v-model="loginForm.userName" 
                                placeholder="请输入您的账号"></el-input>
						</el-form-item>
						<el-form-item prop="passWord">
							<el-input type="password" 
                                v-model="loginForm.passWord" 
                                placeholder="请输入您的密码"></el-input>
						</el-form-item>
						<el-form-item>
							<el-button 
                                style="margin-top: 15px; width: 100%;"
                                @click="submitForm('loginForm')" 
                                type="primary">用户登录</el-button>
						</el-form-item>
					</el-form>
				</div>
			</div>
        </div>
    </div>
</template>

<style>
    .login-container {
		background-color: #409EFF;
		position: fixed;
		left: 0;
		top: 0;
		bottom: 0;
		right: 0;
		display: flex;
		
	}
	.login-bgp{
		background: url(https://bing.ioliu.cn/v1/rand?&t=json&w=1920&h=1080) no-repeat;
		
		height: 100%;
		width: 70%;
		background-position: center center;
		background-repeat: no-repeat;
		background-attachment: fixed;
		background-size: cover;
		background-color: #464646;
	}
	.login-win {
		position: relative;
		top: 50%;
		right: 0;
		transform: translate(0%, -50%);
		width: 50%;
		height: 100vh;
		padding: 15px;
		border-radius: 5px;
		background-color: white;
		background-size: cover;
		min-width: 350px;
	}
	.login-body {
		position: absolute;
		right: 25%;
		top: 20%;
		width: 50%;
    }
	.login-title {
		text-align: center;
		font-size: 20px;
		font-weight: bold;
		color: #409EFF;
		margin-bottom: 45px;
	}
</style>

<script>

	import initMenu from "../utils/menu.js";
	import { login } from '../api/index.js'

    export default {
        data(){

            return {
				loginForm: {
					userName: '',
					passWord: ''
				},
				rules: {
					userName: [{
						required: true,
						message: '用户账号必须输入',
						trigger: 'blur'
					}],
					passWord: [{
						required: true,
						message: '用户密码必须输入',
						trigger: 'blur'
					}],
				}
			}
        },
        methods: {
            
            submitForm(formName) {
				this.$refs[formName].validate((valid) => {
					if (valid) {
						
						login(this.loginForm).then(res => {
							
							this.$store.commit('setToken', res.data);
							sessionStorage.setItem("token", res.data);
							initMenu(this.$router, this.$store);
							this.$router.push('/index');
						});
                        
					} else {
						
						return false;
					}
				});
			}
        }
    }
</script>