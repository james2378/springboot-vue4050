<template>
	<article class="sign_in">
		<div class="warp">
			<el-row>
				<el-col>
					<el-form :model="form" :rules="rules" ref="form" label-width="60px" class="form">
						<h1>高校学生考勤系统</h1>
						
						<el-form-item label="账号" prop="account">
							<el-input type="text" v-model="form.account" placeholder="用户名 / 手机 / 邮箱">
							</el-input>
						</el-form-item>
						
						<el-form-item label="密码" prop="password">
							<el-input type="password" v-model="form.password" placeholder="5 到 16 个字符"
								autocomplete="off">
							</el-input>
						</el-form-item>
						
						<!-- 极验开始 -->
						<div class="geetest_box">
							<div class="geetest_box_title">验证</div>
							<div class="geetest_box_content">
								<SlidePicture @slidePictureState="slidePictureState"></SlidePicture>
							</div>
						</div>
						<!-- 极验结束 -->
						<div class="btns">
							<el-button type="primary" @click="sign_in()">登录</el-button>
						</div>
						<div class="btns">
							<router-link to="./forgot" >忘记密码?</router-link>
						</div>
					</el-form>
				</el-col>
			</el-row>
		</div>
	</article>
</template>

<script>
		import SlidePicture from "@/components/common/slidePicture";	
	export default {
		data: function() {
			return {
				// 极验状态开始
				isGeeFinish: false,
				// 极验状态结束
				oauth: {
					signIn: false
				},
				web: this.$store.state.web,
				form: {
					account: "",
					password: ""
				},
				remember_me: 1,
				rules: {
					account: [{
							required: true,
							message: '请输入账号',
							trigger: 'blur'
						},
						{
							min: 5,
							max: 16,
							message: '长度在 5 到 16 个字符',
							trigger: 'blur'
						}
					],
					password: [{
							required: true,
							message: '请输入密码',
							trigger: 'blur'
						},
						{
							min: 5,
							max: 16,
							message: '长度在 5 到 16 个字符',
							trigger: 'blur'
						}
					]
				}
			}
		},
		methods: {
					// 极验滑块事件开始
			slidePictureState(state) {
						if (state === true) {
							// 成功
							this.$message.success("哇塞~检验成功，速度战胜99%的用户，请进行登录");
							this.isGeeFinish = true;
						} else {
							this.isGeeFinish = false;
						}
					},
			// 极验滑块事件开始
			sign_in() {
				var f = this.form;
				var form = {
					password: f.password
				};
				var account = f.account + '';
				
				// 账号类型判断
				if (account.indexOf('@') !== -1) {
					form.email = account;
				} else if (/1[0-9]{10}/.test(account)) {
					form.phone = account;
				} else {
					form.username = account;
				}
				
				
				if(!this.form.account){
					return this.$message.warning("请输入正确账号")
				}else if(!this.form.password){
					return this.$message.warning("请输入正确密码")
				}
					// 极验开始
					else if(!this.isGeeFinish){
					return this.$message.warning("请进行拖拽检验")
				};
					// 极验结束
			
				
				var _this = this;
				
				// 查询用户
				this.$post('~/api/user/login?', form, (res) => {
					if (res.result && res.result.obj) {
			            var obj = res.result.obj;
						
						// 缓存用户
						_this.$store.commit('user_set', obj);
						
						// 存储用户
						if (_this.remember_me) {
							$.db.set('account', account);
						}
						
						// 获取权限
						_this.$get_auth(obj.user_group, ()=>{
							var url = _this.$redirect();
							_this.$router.push(url || '/');
						})
						
						// 存储登录回来的用户信息
						$.db.set('user_group', JSON.stringify(res.result.obj) );
						
						
					} else if (res.error) {
						console.log(res.error);
						_this.$toast(res.error.message, 'error');
					}
				});
			}
		},
		created() {

		},
		components:{
						SlidePicture
					}	
	}
</script>

<style>
	.sign_in {
		box-sizing: border-box;
		height: 100vh;
		padding: .5rem;
		width: 100%;
		background: url(/img/bg.jpg) no-repeat;
		background-size: 100% 100%;
		color: #fff;
	}

	.sign_in h1,
	.sign_in .btns {
		text-align: center;
	}

	.sign_in .btns .el-button {
		width: 40%;
	}

	.sign_in .el-form-item__label {
		color: #fff;
	}

	.sign_in .form {
		box-sizing: border-box;
		padding: 1rem 3rem;
		margin: auto;
		margin-top: 17rem;
		width: 100%;
		max-width: 32rem!important;
		box-shadow: 0 0 0 1px hsla(240, 0%, 100%, .3) inset,
			0 .5em 1em rgba(50, 50, 50, 0.6);
		text-shadow: 0 1px 1px hsla(240, 0%, 100%, .5);
		overflow: hidden;
		/* background-color: rgba(255, 255, 255, .5); */
		border-radius: 0.5rem;
	}

	.sign_in .form:after {
		content: '';
		position: absolute;
		top: 0;
		left: 0;
		right: 0;
		bottom: 0;
		background-color: rgba(255, 255, 255, 0.8);
		z-index: -1;

		background-image: url(/img/bg.jpg);
		background-position: center top;
		background-size: cover;
		background-attachment: fixed;
		-webkit-filter: blur(20px);
		-moz-filter: blur(20px);
		-ms-filter: blur(20px);
		-o-filter: blur(20px);
		filter: blur(20px);
	}
	
		
	/*************************************** */
	.sign_in .form .el-input{
		width: 100%;
	}
	
	/***************极验************************ */
	.geetest_box{
		display: flex;
		margin-bottom: 30px;
		width: 420px;
	}
	.geetest_box_title{
    width: 48px;
    font-size: 14px;
    text-align: right;
    margin-right: 14px;
	}
	.geetest_box_title::before{
        content: '*';
    color: #F56C6C;
    margin-right: 4px;
	}
	.geetest_box_content{
	}

</style>
