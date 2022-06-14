//区分用户权限菜单
import {
	getLoginUser,
} from "../api";

const sysMenus = [{
		id: "1",
		path: '/index',
		name: '系统首页',
		icon: "fa fa-home",
		component: require("../views/index.vue").default
	},
	{
		id: "2",
		path: '/grades',
		name: '班级管理',
		icon: "fa fa-users",
		component: require("../views/grades.vue").default
	},
	{
		id: "3",
		path: '/projects',
		name: '课程管理',
		icon: "fa fa-calendar-o",
		component: require("../views/projects.vue").default
	},
	{
		id: "4",
		path: '/classrooms',
		name: '教室管理',
		icon: "fa fa-building",
		component: require("../views/classrooms.vue").default
	},
	{
		id: "5",
		path: '/users',
		name: '用户管理',
		icon: "fa fa-id-card",
		component: require("../views/users.vue").default
	},
	{
		id: "6",
		path: '/works',
		name: '授课安排',
		icon: "fa fa-calendar",
		component: require("../views/teacherWork.vue").default
	},
	{
		id: "7",
		path: '/plans',
		name: '授课时间',
		icon: "fa fa-bell",
		component: require("../views/projectPlans.vue").default
	},
	{
		id: "8",
		path: '/notices',
		name: '通知管理',
		icon: "fa fa-bullhorn",
		component: require("../views/notices.vue").default
	},
	{
		id: "9",
		path: '/activitys',
		name: '活动管理',
		icon: "fa fa-flag",
		component: require("../views/activitys.vue").default
	},
];

const teacherMenus = [{
		id: "1",
		path: '/index',
		name: '系统首页',
		icon: "fa fa-home",
		component: require("../views/index.vue").default
	},
	{
		id: "2",
		path: '/works',
		name: '授课安排',
		icon: "fa fa-calendar",
		component: require("../views/teacherWork.vue").default
	},
	{
		id: "3",
		path: '/plans',
		name: '授课时间',
		icon: "fa fa-bell",
		component: require("../views/projectPlans.vue").default
	},
	{
		id: "4",
		path: '/record',
		name: '签到记录',
		icon: "el-icon-s-data",
		component: require("../views/qiandaorecord.vue").default
	},
];

const studentMenus = [{
		id: "1",
		path: '/index',
		name: '系统首页',
		icon: "fa fa-home",
		component: require("../views/index.vue").default
	},
	{
		id: "2",
		path: '/works',
		name: '授课安排',
		icon: "fa fa-calendar",
		component: require("../views/teacherWork.vue").default
	},
	{
		id: "3",
		path: '/plans',
		name: '授课时间',
		icon: "fa fa-bell",
		component: require("../views/projectPlans.vue").default
	},
];

export default function initMenu(router, store) {

	let token = null;
	if (store.state.token) {

		token = store.state.token;
	} else {

		token = sessionStorage.getItem("token");
		store.state.token = sessionStorage.getItem("token");
	}

	getLoginUser(token).then(resp => {
		console.log(resp)
		store.commit("setUser", resp.data)
		if (resp.data.type == 0) {

			sysMenus.forEach(item => {

				router.addRoute("home", item);
			});
			store.commit("setMenus", sysMenus);
		} else if (resp.data.type == 1) {

			teacherMenus.forEach(item => {

				router.addRoute("home", item);
			});
			store.commit("setMenus", teacherMenus);
		} else if (resp.data.type == 2) {

			studentMenus.forEach(item => {

				router.addRoute("home", item);
			});
			store.commit("setMenus", studentMenus);
		}
	});

}
