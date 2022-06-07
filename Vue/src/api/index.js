import http from "../utils/http.js";

/** 系统请求地址 */
export function show(){
	
	return http.get('/show');
}
export function login(param){
	
	return http.post('/login', param);
}
export function exit(token){
	
	return http.get('/exit', {params: {token: token}});
}
export function getLoginUser(token){
	
	return http.get('/info', {params: {token: token}});
}
export function checkUserPwd(token, oldPwd){
	
	return http.get('/checkPwd', {params: {token: token, oldPwd: oldPwd}});
}
export function updLoginUserInfo(params){
	
	return http.post('/updInfo', params);
}
export function updLoginUserPwd(token, newPwd){
	
	return http.post('/updPwd', {token: token, newPwd: newPwd});
}
