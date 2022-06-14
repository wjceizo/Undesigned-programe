import http from "../utils/http.js";

/** 系统请求地址 */
export function show(){
	
	return http.get('/show');
}
export function ischeck(stuId){
	
	return http.get('/check/ischeck',{params: {stuId: stuId}});
}
export function outtime(stuId){
	
	return http.get('/check/outtime',{params: {stuId: stuId}});
}
export function qiandao(stuId){
	
	return http.get('/check/qiandao',{params: {stuId: stuId}});
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

/** 系统通知 */
export function getPageNotices(pageIndex, pageSize, title){

	return http.get('/notices/page', 
        {params: {pageIndex: pageIndex, pageSize: pageSize, title: title}});
}
export function addNotice(params){
	
	return http.post('/notices/add', params);
}
export function updNotice(params){
	
	return http.post('/notices/upd', params);
}
export function delNotice(id){
	
	return http.post('/notices/del', {id: id});
}

/** 校园活动 */
export function getPageActivitys(pageIndex, pageSize, title, loc){

	return http.get('/activitys/page', 
        {params: {pageIndex: pageIndex, pageSize: pageSize, title: title, loc: loc}});
}
export function addActivitys(params){
	
	return http.post('/activitys/add', params);
}
export function updActivitys(params){
	
	return http.post('/activitys/upd', params);
}
export function delActivitys(id){
	
	return http.post('/activitys/del', {id: id});
}

/** 课程信息 */
export function getAllProjects(){

	return http.get('/projects/all');
}
export function getAllowProjects(gradeId){

	return http.get('/projects/allow', {params: {gradeId: gradeId}});
}
export function getRecord(pageIndex, pageSize, token, projectId, teacherId, gradeId){

	return http.get('/check/getrecord', 
        {params: {pageIndex: pageIndex, pageSize: pageSize, token: token,
					projectId: projectId, teacherId: teacherId, gradeId: gradeId}});
}
export function getPageProjects(pageIndex, pageSize, name){

	return http.get('/projects/page', 
        {params: {pageIndex: pageIndex, pageSize: pageSize, name: name}});
}
export function addCheck(id,gradeId){

	return http.get('/check/addcheck', {params: {
		id:id
		}});
}
export function addProjects(params){
	
	return http.post('/projects/add', params);
}
export function updProjects(params){
	
	return http.post('/projects/upd', params);
}
export function delProjects(id){
	
	return http.post('/projects/del', {id: id});
}

/** 班级信息 */
export function getAllGrades(){

	return http.get('/grades/all');
}
export function getPageGrades(pageIndex, pageSize, name){

	return http.get('/grades/page', 
        {params: {pageIndex: pageIndex, pageSize: pageSize, name: name}});
}
export function addGrades(params){
	
	return http.post('/grades/add', params);
}
export function updGrades(params){
	
	return http.post('/grades/upd', params);
}
export function delGrades(id){
	
	return http.post('/grades/del', {id: id});
}

/** 教室信息 */
export function getAllClassrooms(){

	return http.get('/classrooms/all');
}
export function getPageClassrooms(pageIndex, pageSize, name, loc){

	return http.get('/classrooms/page', 
        {params: {pageIndex: pageIndex, pageSize: pageSize, name: name, loc: loc}});
}
export function addClassrooms(params){
	
	return http.post('/classrooms/add', params);
}
export function updClassrooms(params){
	
	return http.post('/classrooms/upd', params);
}
export function delClassrooms(id){
	
	return http.post('/classrooms/del', {id: id});
}

/** 系统用户信息 */
export function getPageUsers(pageIndex, pageSize, userName, name){

	return http.get('/users/page', 
        {params: {pageIndex: pageIndex, pageSize: pageSize, userName: userName, name: name}});
}
export function addUsers(params){
	
	return http.post('/users/add', params);
}
export function updUsers(params){
	
	return http.post('/users/upd', params);
}
export function delUsers(id){
	
	return http.post('/users/del', {id: id});
}

/** 学生信息 */
export function getStudentInfo(id){

	return http.get('/students/info', 
        {params: {id: id}});
}
export function addStudents(params){
	
	return http.post('/students/add', params);
}
export function updStudents(params){
	
	return http.post('/students/upd', params);
}

/** 教师信息 */
export function addTeachers(params){
	
	return http.post('/teachers/add', params);
}
export function updTeachers(params){
	
	return http.post('/teachers/upd', params);
}

/** 授课安排 */
export function getPageWorks(pageIndex, pageSize, token, projectId, teacherId, gradeId){

	return http.get('/teacherWork/page', 
        {params: {pageIndex: pageIndex, pageSize: pageSize, token: token,
					projectId: projectId, teacherId: teacherId, gradeId: gradeId}});
}
export function addWorks(params){
	
	return http.post('/teacherWork/add', params);
}
export function updWorks(params){
	
	return http.post('/teacherWork/upd', params);
}
export function delWorks(id){
	
	return http.post('/teacherWork/del', {id: id});
}

/** 上课安排 */
export function checkPlanTime(planTime, workId){

	return http.get('/projectPlans/checkTime', 
        {params: {planTime: planTime, workId: workId}});
}
export function getPagePlans(pageIndex, pageSize, token, gradeId, teacherId, projectId){

	return http.get('/projectPlans/page', 
        {params: {pageIndex: pageIndex, pageSize: pageSize, token: token, gradeId: gradeId,
										teacherId: teacherId, projectId: projectId}});
}
export function addPlans(params){
	
	return http.post('/projectPlans/add', params);
}
export function updPlans(params){
	
	return http.post('/projectPlans/upd', params);
}
export function delPlans(id){
	
	return http.post('/projectPlans/del', {id: id});
}