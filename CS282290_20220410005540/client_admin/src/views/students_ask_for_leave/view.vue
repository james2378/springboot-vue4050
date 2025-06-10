<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','student_number') || $check_field('add','student_number') || $check_field('set','student_number')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="学号" prop="student_number">
						<div v-if="user_group !== '管理员'">
							{{ get_user_session_student_number(form['student_number']) }}
							<!--<el-input id="business_name" v-model="form['student_number']" placeholder="请输入学号"-->
							<!--v-if="user_group === '管理员' || (form['students_ask_for_leave_id'] && $check_field('set','student_number')) || (!form['students_ask_for_leave_id'] && $check_field('add','student_number'))" :disabled="disabledObj['student_number_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','student_number')">{{form['student_number']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['students_ask_for_leave_id'] && $check_field('set','student_number')) || (!form['students_ask_for_leave_id'] && $check_field('add','student_number'))" id="student_number" v-model="form['student_number']" :disabled="disabledObj['student_number_isDisabled']">
								<el-option v-for="o in list_user_student_number" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','student_number')" id="student_number" v-model="form['student_number']" :disabled="true">
								<el-option v-for="o in list_user_student_number" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="student_number" v-model="form['student_number']" :disabled="disabledObj['student_number_isDisabled']">
							<el-option v-for="o in list_user_student_number" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','student_name') || $check_field('add','student_name') || $check_field('set','student_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="学生姓名" prop="student_name">
					<el-input id="student_name" v-model="form['student_name']" placeholder="请输入学生姓名"
							  v-if="user_group === '管理员' || (form['students_ask_for_leave_id'] && $check_field('set','student_name')) || (!form['students_ask_for_leave_id'] && $check_field('add','student_name'))" :disabled="disabledObj['student_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','student_name')">{{form['student_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','class_name') || $check_field('add','class_name') || $check_field('set','class_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="班级名称" prop="class_name">
					<el-input id="class_name" v-model="form['class_name']" placeholder="请输入班级名称"
							  v-if="user_group === '管理员' || (form['students_ask_for_leave_id'] && $check_field('set','class_name')) || (!form['students_ask_for_leave_id'] && $check_field('add','class_name'))" :disabled="disabledObj['class_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','class_name')">{{form['class_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','tutor_no') || $check_field('add','tutor_no') || $check_field('set','tutor_no')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="辅导员工号" prop="tutor_no">
						<el-select v-if="user_group === '管理员' || (form['students_ask_for_leave_id'] && $check_field('set','tutor_no')) || (!form['students_ask_for_leave_id'] && $check_field('add','tutor_no'))" id="tutor_no" v-model="form['tutor_no']" :disabled="disabledObj['tutor_no_isDisabled']">
							<el-option v-for="o in list_user_tutor_no" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
						<el-select v-else-if="$check_field('get','tutor_no')" id="tutor_no" v-model="form['tutor_no']" :disabled="true">
							<el-option v-for="o in list_user_tutor_no" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','leave_type') || $check_field('add','leave_type') || $check_field('set','leave_type')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="请假类型" prop="leave_type">
					<el-select id="leave_type" v-model="form['leave_type']"
						v-if="user_group === '管理员' || (form['students_ask_for_leave_id'] && $check_field('set','leave_type')) || (!form['students_ask_for_leave_id'] && $check_field('add','leave_type'))">
						<el-option v-for="o in list_leave_type" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','leave_type')">{{form['leave_type']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','leave_start_time') || $check_field('add','leave_start_time') || $check_field('set','leave_start_time')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="请假开始时间" prop="leave_start_time">
					<el-date-picker :disabled="disabledObj['leave_start_time_isDisabled']" v-if="user_group === '管理员' || (form['students_ask_for_leave_id'] && $check_field('set','leave_start_time')) || (!form['students_ask_for_leave_id'] && $check_field('add','leave_start_time'))" id="leave_start_time"
						v-model="form['leave_start_time']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','leave_start_time')">{{form['leave_start_time']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','leave_end_time') || $check_field('add','leave_end_time') || $check_field('set','leave_end_time')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="请假结束时间" prop="leave_end_time">
					<el-date-picker :disabled="disabledObj['leave_end_time_isDisabled']" v-if="user_group === '管理员' || (form['students_ask_for_leave_id'] && $check_field('set','leave_end_time')) || (!form['students_ask_for_leave_id'] && $check_field('add','leave_end_time'))" id="leave_end_time"
						v-model="form['leave_end_time']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','leave_end_time')">{{form['leave_end_time']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','counselor_approval') || $check_field('add','counselor_approval') || $check_field('set','counselor_approval')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="辅导员审批" prop="counselor_approval">
					<el-select id="counselor_approval" v-model="form['counselor_approval']"
						v-if="user_group === '管理员' || (form['students_ask_for_leave_id'] && $check_field('set','counselor_approval')) || (!form['students_ask_for_leave_id'] && $check_field('add','counselor_approval'))">
						<el-option v-for="o in list_counselor_approval" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','counselor_approval')">{{form['counselor_approval']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','approved_by_leaders_of_the_institute') || $check_field('add','approved_by_leaders_of_the_institute') || $check_field('set','approved_by_leaders_of_the_institute')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="院领导审批" prop="approved_by_leaders_of_the_institute">
					<el-select id="approved_by_leaders_of_the_institute" v-model="form['approved_by_leaders_of_the_institute']"
						v-if="user_group === '管理员' || (form['students_ask_for_leave_id'] && $check_field('set','approved_by_leaders_of_the_institute')) || (!form['students_ask_for_leave_id'] && $check_field('add','approved_by_leaders_of_the_institute'))">
						<el-option v-for="o in list_approved_by_leaders_of_the_institute" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','approved_by_leaders_of_the_institute')">{{form['approved_by_leaders_of_the_institute']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','reason_for_leave') || $check_field('add','reason_for_leave') || $check_field('set','reason_for_leave')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="请假原因" prop="reason_for_leave">
					<el-input type="textarea" id="reason_for_leave" v-model="form['reason_for_leave']" placeholder="请输入请假原因"
						v-if="user_group === '管理员' || (form['students_ask_for_leave_id'] && $check_field('set','reason_for_leave')) || (!form['students_ask_for_leave_id'] && $check_field('add','reason_for_leave'))" :disabled="disabledObj['reason_for_leave_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','reason_for_leave')">{{form['reason_for_leave']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','counselor_reply') || $check_field('add','counselor_reply') || $check_field('set','counselor_reply')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="辅导员回复" prop="counselor_reply">
					<el-input type="textarea" id="counselor_reply" v-model="form['counselor_reply']" placeholder="请输入辅导员回复"
						v-if="user_group === '管理员' || (form['students_ask_for_leave_id'] && $check_field('set','counselor_reply')) || (!form['students_ask_for_leave_id'] && $check_field('add','counselor_reply'))" :disabled="disabledObj['counselor_reply_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','counselor_reply')">{{form['counselor_reply']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','reply_from_leaders_of_the_institute') || $check_field('add','reply_from_leaders_of_the_institute') || $check_field('set','reply_from_leaders_of_the_institute')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="院领导回复" prop="reply_from_leaders_of_the_institute">
					<el-input type="textarea" id="reply_from_leaders_of_the_institute" v-model="form['reply_from_leaders_of_the_institute']" placeholder="请输入院领导回复"
						v-if="user_group === '管理员' || (form['students_ask_for_leave_id'] && $check_field('set','reply_from_leaders_of_the_institute')) || (!form['students_ask_for_leave_id'] && $check_field('add','reply_from_leaders_of_the_institute'))" :disabled="disabledObj['reply_from_leaders_of_the_institute_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','reply_from_leaders_of_the_institute')">{{form['reply_from_leaders_of_the_institute']}}</div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_btn_warp">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "students_ask_for_leave_id",
				url_add: "~/api/students_ask_for_leave/add?",
				url_set: "~/api/students_ask_for_leave/set?",
				url_get_obj: "~/api/students_ask_for_leave/get_obj?",
				url_upload: "~/api/students_ask_for_leave/upload?",

				query: {
					"students_ask_for_leave_id": 0,
				},

				form: {
					"student_number": 0, // 学号
					"student_name":'', // 学生姓名
					"class_name":'', // 班级名称
					"tutor_no": 0, // 辅导员工号
					"leave_type":'', // 请假类型
					"leave_start_time":'', // 请假开始时间
					"leave_end_time":'', // 请假结束时间
					"counselor_approval":'', // 辅导员审批
					"approved_by_leaders_of_the_institute":'', // 院领导审批
					"reason_for_leave":'', // 请假原因
					"counselor_reply":'', // 辅导员回复
					"reply_from_leaders_of_the_institute":'', // 院领导回复
					"students_ask_for_leave_id": 0, // ID

				},
				disabledObj:{
					"student_number_isDisabled": false,
					"student_name_isDisabled": false,
					"class_name_isDisabled": false,
					"tutor_no_isDisabled": false,
					"leave_type_isDisabled": false,
					"leave_start_time_isDisabled": false,
					"leave_end_time_isDisabled": false,
					"counselor_approval_isDisabled": false,
					"approved_by_leaders_of_the_institute_isDisabled": false,
					"reason_for_leave_isDisabled": false,
					"counselor_reply_isDisabled": false,
					"reply_from_leaders_of_the_institute_isDisabled": false,
				},
				// 用户列表
				list_user_student_number: [],
				// 用户组
				group_user_student_number: "",
				// 用户列表
				list_user_tutor_no: [],
				//请假类型选项列表
				list_leave_type: ['事假','病假','其他'],
				//辅导员审批选项列表
				list_counselor_approval: ['未通过','已通过'],
				//院领导审批选项列表
				list_approved_by_leaders_of_the_institute: ['未通过','已通过'],
			}
		},
		methods: {
			/**
			 * 获取学生用户列表
			 */
			async get_list_user_student_number() {
                // if(this.user_group !== "管理员" && this.form["student_number"] === 0) {
                //     this.form["student_number"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=学生");
                if(json.result && json.result.list){
                    this.list_user_student_number = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
			/**
			 * 获取学生用户组
			 */
			async get_group_user_student_number() {
				this.form["student_number"] = this.user.user_id;
				var json = await this.$get("~/api/user_group/get_obj?name=学生");
				if(json.result && json.result.obj){
					this.group_user_student_number = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_student_number(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_student_number.source_table+"/get_obj?"
				this.$get(url, user_id, function(res) {
					if (res.result && res.result.obj) {
						var arr = []
						for (let key in res.result.obj) {
							arr.push(key)
						}
						var arrForm = []
						for (let key in _this.form) {
							arrForm.push(key)
						}
						for (var i=0;i<arr.length;i++){
							for (var j=0;j<arrForm.length;j++){
								if (arr[i]===arrForm[j]){
									if (arr[i]!=="student_number") {
										_this.form[arrForm[j]] = res.result.obj[arr[i]]
										_this.disabledObj[arrForm[j] + '_isDisabled'] = true
										break;
									}else {
										_this.disabledObj[arrForm[j] + '_isDisabled'] = true
									}
								}
							}
						}
					}
				});
			},
			get_user_student_number(id){
				var obj = this.list_user_student_number.getObj({"user_id":id});
				var ret = "";
				if(obj){
					if(obj.nickname){
						ret = obj.nickname;}
					else{
						ret = obj.username;
					}
				}
				return ret;
			},
			/**
			 * 获取辅导员用户列表
			 */
			async get_list_user_tutor_no() {
                // if(this.user_group !== "管理员" && this.form["tutor_no"] === 0) {
                //     this.form["tutor_no"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=辅导员");
                if(json.result && json.result.list){
                    this.list_user_tutor_no = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
			get_user_tutor_no(id){
				var obj = this.list_user_tutor_no.getObj({"user_id":id});
				var ret = "";
				if(obj){
					if(obj.nickname){
						ret = obj.nickname;}
					else{
						ret = obj.username;
					}
				}
				return ret;
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
				/**
				* 请求列表前
				* @param {Object} param
				*/
				var user_group = this.user.user_group;
				if (user_group !== "管理员") {
					switch (user_group) {
						case "学号":
							if(param["student_number"] > 0){
								param["student_number"] = this.user.user_id;
							}
							break;
						case "辅导员工号":
							if(param["tutor_no"] > 0){
								param["tutor_no"] = this.user.user_id;
							}
							break;
					}
				}
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							// if(dbKey === "charging_standard"){
							// 	this.form['charging_rules'] = form[dbKey];
							// 	this.disabledObj['charging_rules_isDisabled'] = true;
							// };
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
        if (this.form["leave_start_time"].indexOf("-")===-1){
          this.form["leave_start_time"] = this.$toTime(parseInt(this.form["leave_start_time"]),"yyyy-MM-dd")
        }
        if (this.form["leave_end_time"].indexOf("-")===-1){
          this.form["leave_end_time"] = this.$toTime(parseInt(this.form["leave_end_time"]),"yyyy-MM-dd")
        }
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){
				if(parseInt(this.form["leave_start_time"]) > 9999){
					this.form["leave_start_time"] = this.$toTime(parseInt(this.form["leave_start_time"]),"yyyy-MM-dd")
				}
				if(parseInt(this.form["leave_end_time"]) > 9999){
					this.form["leave_end_time"] = this.$toTime(parseInt(this.form["leave_end_time"]),"yyyy-MM-dd")
				}
			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/students_ask_for_leave/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/students_ask_for_leave/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/students_ask_for_leave/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/students_ask_for_leave/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/students_ask_for_leave/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
			this.get_list_user_student_number();
			this.get_group_user_student_number();
			this.get_list_user_tutor_no();
		},
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}
</style>
