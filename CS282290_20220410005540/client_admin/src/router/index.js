import Vue from 'vue';
import VueRouter from 'vue-router';
import index from '../views/index.vue';
import login from '../views/login.vue';
import forgot from '../views/forgot.vue';
Vue.use(VueRouter)

const routes = [
	// 主页
	{
		path: '/',
		name: 'index',
		component: index,
		meta: {
			index: 0,
			title: '首页'
		}
	},

	// 登录
	{
		path: '/login',
		name: 'login',
		component: login,
		meta: {
			index: 0,
			title: '登录'
		}
	},

	// 忘记密码
	{
		path: '/forgot',
		name: "forgot",
		component: forgot,
		meta: {
			index: 0,
			title: '忘记密码'
		}
	},

	// 修改密码
	{
		path: '/user/password',
		name: "password",
		component: () => import("../views/user/password.vue"),
		meta: {
			index: 0,
			title: '修改密码'
		}
	},

	// 视频播放页
	{
		path: "/media/video",
		name: "video",
		component: () => import('../views/media/video.vue'),
		meta: {
			index: 0,
			title: "视频"
		}
	},

	// 音频播放页
	{
		path: "/media/audio",
		name: "audio",
		component: () => import('../views/media/audio.vue'),
		meta: {
			index: 0,
			title: "音频"
		}
	},
	// 院领导路由
	{
		path: '/hospital_leaders/table',
		name: 'hospital_leaders_table',
		component: () => import('../views/hospital_leaders/table.vue')
	},
	{
		path: '/hospital_leaders/view',
		name: 'hospital_leaders_view',
		component: () => import('../views/hospital_leaders/view.vue')
	},
	// 辅导员路由
	{
		path: '/instructor/table',
		name: 'instructor_table',
		component: () => import('../views/instructor/table.vue')
	},
	{
		path: '/instructor/view',
		name: 'instructor_view',
		component: () => import('../views/instructor/view.vue')
	},
	// 教师路由
	{
		path: '/teacher/table',
		name: 'teacher_table',
		component: () => import('../views/teacher/table.vue')
	},
	{
		path: '/teacher/view',
		name: 'teacher_view',
		component: () => import('../views/teacher/view.vue')
	},
	// 学生路由
	{
		path: '/student/table',
		name: 'student_table',
		component: () => import('../views/student/table.vue')
	},
	{
		path: '/student/view',
		name: 'student_view',
		component: () => import('../views/student/view.vue')
	},
	// 班级管理路由
	{
		path: '/class_management/table',
		name: 'class_management_table',
		component: () => import('../views/class_management/table.vue')
	},
	{
		path: '/class_management/view',
		name: 'class_management_view',
		component: () => import('../views/class_management/view.vue')
	},
	// 在线考勤路由
	{
		path: '/online_attendance/table',
		name: 'online_attendance_table',
		component: () => import('../views/online_attendance/table.vue')
	},
	{
		path: '/online_attendance/view',
		name: 'online_attendance_view',
		component: () => import('../views/online_attendance/view.vue')
	},
	// 考勤签到路由
	{
		path: '/attendance_check_in/table',
		name: 'attendance_check_in_table',
		component: () => import('../views/attendance_check_in/table.vue')
	},
	{
		path: '/attendance_check_in/view',
		name: 'attendance_check_in_view',
		component: () => import('../views/attendance_check_in/view.vue')
	},
	// 学生请假路由
	{
		path: '/students_ask_for_leave/table',
		name: 'students_ask_for_leave_table',
		component: () => import('../views/students_ask_for_leave/table.vue')
	},
	{
		path: '/students_ask_for_leave/view',
		name: 'students_ask_for_leave_view',
		component: () => import('../views/students_ask_for_leave/view.vue')
	},
	// 考勤统计路由
	{
		path: '/attendance_statistics/table',
		name: 'attendance_statistics_table',
		component: () => import('../views/attendance_statistics/table.vue')
	},
	{
		path: '/attendance_statistics/view',
		name: 'attendance_statistics_view',
		component: () => import('../views/attendance_statistics/view.vue')
	},
	// 通知公告路由
	{
		path: '/notice_announcement/table',
		name: 'notice_announcement_table',
		component: () => import('../views/notice_announcement/table.vue')
	},
	{
		path: '/notice_announcement/view',
		name: 'notice_announcement_view',
		component: () => import('../views/notice_announcement/view.vue')
	},

	// 用户路由
	{
		path: '/user/table',
		name: 'user_table',
		component: () => import('../views/user/table.vue'),
		meta: {
			index: 0,
			title: '用户列表'
		}
	},
	{
		path: '/user/view',
		name: 'user_view',
		component: () => import('../views/user/view.vue'),
		meta: {
			index: 0,
			title: '用户详情'
		}
	},
	{
		path: '/user/info',
		name: 'user_info',
		component: () => import('../views/user/info.vue'),
		meta: {
			index: 0,
			title: '个人信息'
		}
	},
	// 用户组路由
	{
		path: '/user_group/table',
		name: 'user_group_table',
		component: () => import('../views/user_group/table.vue'),
		meta: {
			index: 0,
			title: '用户组列表'
		}
	},
	{
		path: '/user_group/view',
		name: 'user_group_view',
		component: () => import('../views/user_group/view.vue'),
		meta: {
			index: 0,
			title: '用户组详情'
		}
	}
]

const router = new VueRouter({
	mode: 'history',
	base: process.env.BASE_URL,
	routes
})

router.beforeEach((to, from, next) => {
	let token = to.query.token;
	if (token){
		$.db.set("token",token,120);
	}
	next();
})

router.afterEach((to, from, next) => {
	let title = "高校学生考勤系统-admin";
	document.title = title;
})

export default router
