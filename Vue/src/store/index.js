import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
	state:{
		token: null,
		menus: [],
		user:{},
	},
	getters: {
		
		getToken: state => {
			return state.token
		},
		
		getMenus: state => {
			return state.menus
		},
		getUser: state => {
			return state.user
		},
	},
	mutations: {
		setToken: (state, newToken) =>{
			state.token = newToken;
		},
		clearToken: (state) =>{
			state.token = null;
		},
		setMenus: (state, menus) =>{
			state.menus = menus;
		},
		clearMenus: (state) =>{
			state.menus = [];
		},
		setUser: (state, user) =>{
			state.user = user;
		},
		clearUser: (state) =>{
			state.user = {};
		},
	}
	
})
