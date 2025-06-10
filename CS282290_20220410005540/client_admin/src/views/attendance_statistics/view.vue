<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','class_name') || $check_field('add','class_name') || $check_field('set','class_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="班级名称" prop="class_name">
					<el-input id="class_name" v-model="form['class_name']" placeholder="请输入班级名称"
							  v-if="user_group === '管理员' || (form['attendance_statistics_id'] && $check_field('set','class_name')) || (!form['attendance_statistics_id'] && $check_field('add','class_name'))" :disabled="disabledObj['class_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','class_name')">{{form['class_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','attendance_name') || $check_field('add','attendance_name') || $check_field('set','attendance_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="考勤名称" prop="attendance_name">
					<el-input id="attendance_name" v-model="form['attendance_name']" placeholder="请输入考勤名称"
							  v-if="user_group === '管理员' || (form['attendance_statistics_id'] && $check_field('set','attendance_name')) || (!form['attendance_statistics_id'] && $check_field('add','attendance_name'))" :disabled="disabledObj['attendance_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','attendance_name')">{{form['attendance_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','number_of_persons_due') || $check_field('add','number_of_persons_due') || $check_field('set','number_of_persons_due')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="应到人数" prop="number_of_persons_due">
					<el-input-number id="number_of_persons_due" v-model.number="form['number_of_persons_due']"
						v-if="user_group === '管理员' || (form['attendance_statistics_id'] && $check_field('set','number_of_persons_due')) || (!form['attendance_statistics_id'] && $check_field('add','number_of_persons_due'))"></el-input-number>
					<div v-else-if="$check_field('get','number_of_persons_due')">{{form['number_of_persons_due']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','attendance') || $check_field('add','attendance') || $check_field('set','attendance')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="出勤人数" prop="attendance">
					<el-input-number id="attendance" v-model.number="form['attendance']"
						v-if="user_group === '管理员' || (form['attendance_statistics_id'] && $check_field('set','attendance')) || (!form['attendance_statistics_id'] && $check_field('add','attendance'))"></el-input-number>
					<div v-else-if="$check_field('get','attendance')">{{form['attendance']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','number_of_people_asking_for_leave') || $check_field('add','number_of_people_asking_for_leave') || $check_field('set','number_of_people_asking_for_leave')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="请假人数" prop="number_of_people_asking_for_leave">
					<el-input-number id="number_of_people_asking_for_leave" v-model.number="form['number_of_people_asking_for_leave']"
						v-if="user_group === '管理员' || (form['attendance_statistics_id'] && $check_field('set','number_of_people_asking_for_leave')) || (!form['attendance_statistics_id'] && $check_field('add','number_of_people_asking_for_leave'))"></el-input-number>
					<div v-else-if="$check_field('get','number_of_people_asking_for_leave')">{{form['number_of_people_asking_for_leave']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','number_of_absentees') || $check_field('add','number_of_absentees') || $check_field('set','number_of_absentees')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="缺勤人数" prop="number_of_absentees">
					<el-input-number id="number_of_absentees" v-model.number="form['number_of_absentees']"
						v-if="user_group === '管理员' || (form['attendance_statistics_id'] && $check_field('set','number_of_absentees')) || (!form['attendance_statistics_id'] && $check_field('add','number_of_absentees'))"></el-input-number>
					<div v-else-if="$check_field('get','number_of_absentees')">{{form['number_of_absentees']}}</div>
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
				field: "attendance_statistics_id",
				url_add: "~/api/attendance_statistics/add?",
				url_set: "~/api/attendance_statistics/set?",
				url_get_obj: "~/api/attendance_statistics/get_obj?",
				url_upload: "~/api/attendance_statistics/upload?",

				query: {
					"attendance_statistics_id": 0,
				},

				form: {
					"class_name":'', // 班级名称
					"attendance_name":'', // 考勤名称
					"number_of_persons_due":0, // 应到人数
					"attendance":0, // 出勤人数
					"number_of_people_asking_for_leave":0, // 请假人数
					"number_of_absentees":0, // 缺勤人数
					"attendance_statistics_id": 0, // ID

				},
				disabledObj:{
					"class_name_isDisabled": false,
					"attendance_name_isDisabled": false,
				},
			}
		},
		methods: {

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
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
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){
			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/attendance_statistics/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/attendance_statistics/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/attendance_statistics/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/attendance_statistics/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/attendance_statistics/view','get');
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
