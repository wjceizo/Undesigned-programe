<template>
	<div class="fater-body-show">
		<el-card shadow="never">
			<div slot="header">
				信息查询
			</div>
			<div>
				<el-form :inline="true" :model="qryForm">
					<el-form-item >
						<el-select 
							v-model="qryForm.projectId" placeholder="选择课程">
							<el-option v-for="(item, index) in projects" :key="index"
								:label="item.name" :value="item.id"></el-option>
						</el-select>
					</el-form-item>
					<el-form-item v-if="users.type != 2">
						<el-select
							v-model="qryForm.gradeId" placeholder="选择班级">
							<el-option v-for="(item, index) in grades" :key="index"
								:label="item.name" :value="item.id"></el-option>
						</el-select>
					</el-form-item>
					<el-form-item>
						<el-button type="primary" 
							icon="el-icon-search" @click="getPageLikeInfo()"></el-button>
					</el-form-item>
				</el-form>
			</div>
		</el-card>
		<el-card shadow="never">
			<div>
				<el-table v-loading="loading" element-loading-text="拼命加载中" element-loading-spinner="el-icon-loading"
					element-loading-background="rgba(124, 124, 124, 0.8)" :data="pageInfos" border>
					<el-table-column align="center" type="index"></el-table-column>
					<el-table-column align="center" prop="gradeName" label="班级名称"></el-table-column>
					<el-table-column align="center" prop="projectName" label="课程名称"></el-table-column>
					<el-table-column align="center" prop="teacherName" v-if="users.type != 1"  label="教师姓名"></el-table-column>
					<el-table-column align="center" v-if="users.type == 0" label="时间安排">
						<template slot-scope="scope">
							<el-button type="primary" plain 
								size="mini" @click="showEditPlanWin(scope.row.id)">上课时间</el-button>
						</template>
					</el-table-column>
					<el-table-column align="center" v-if="users.type == 0" label="操作处理">
						<template slot-scope="scope">
							<el-button icon="el-icon-delete" type="danger" size="mini"
								@click="delInfo(scope.row.id)"></el-button>
						</template>
					</el-table-column>
					<el-table-column align="center" v-if="users.type ==1 " label="操作处理">
						<template slot-scope="scope">
							<el-button icon="el-icon-edit" type="success" size="mini"
								@click="addcheck(scope.row.id)">发起签到</el-button>
						</template>
					</el-table-column>
<!-- 					<el-table-column align="center" v-if="users.type ==1 " label="签到记录">
						<template slot-scope="scope">
							<el-button type="text" size="mini"
								@click="addcheck(scope.row.id)">签到记录</el-button>
						</template>
					</el-table-column> -->
				</el-table>
				<el-pagination v-if="totalInfo > 1" style="margin-top: 15px;" @size-change="handleSizeChange"
					@current-change="handleCurrentChange" :current-page="pageIndex" :page-sizes="[5, 10, 20, 50]"
					:page-size="pageSize" layout="total, sizes, prev, pager, next, jumper" :total="totalInfo">
				</el-pagination>
			</div>
		</el-card>

		
		<el-dialog title="上课时间安排" width="600px" :visible.sync="showEditPlanFlag">
			<el-form label-width="90px" :model="projectPlansForm">
				<el-form-item label="授课教室">
					<el-select style="width: 100%" 
						v-model="projectPlansForm.roomId" placeholder="请选择授课教室">
						<el-option v-for="(item, index) in rooms" :key="index"
							:label="item.name" :value="item.id"></el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="授课时间">
					<el-date-picker style="width: 100%" type="datetime" v-model="projectPlansForm.createTime" 
						value-format="yyyy-MM-dd HH:mm:ss" placeholder="请输入授课课时间…"></el-date-picker>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click="showEditPlanFlag = false">取 消</el-button>
				<el-button type="primary" @click="editPlanInfo()">确 定</el-button>
			</div>
		</el-dialog>
	</div>
</template>

<style>

</style>

<script>
	import {
		getStudentInfo,
		getAllGrades,
		getAllowProjects,
		getLoginUser,
		getAllClassrooms,
		getPageWorks,
		delWorks,
		addPlans,
		checkPlanTime,
		addCheck,
	} from "../api";

	export default {

		data() {

			return {
				users: {},
				projects: [],
				grades: [],
				pageInfos: [],
				pageIndex: 1,
				pageSize: 10,
				totalInfo: 0,
				loading: true,
				qryForm: {
					gradeId: "",
					projectId: "",
					teacherId: "",
				},
				showEditPlanFlag: false,
				rooms: [],
				projectPlansForm: {
					workId: "",
					roomId: "",
					createTime: "",
				}
			}
		},
		methods: {

			getPageInfo(pageIndex, pageSize) {
				
				getPageWorks(1, this.pageSize, this.$store.state.token).then(resp => {

					this.pageInfos = resp.data.data;
					this.pageIndex = resp.data.pageIndex;
					this.pageSize = resp.data.pageSize;
					this.totalInfo = resp.data.count;
					this.loading = false;
					console.log(this.pageInfos)
				});
				
			},
			getPageLikeInfo() {

				getPageWorks(1, this.pageSize, this.$store.state.token, this.qryForm.projectId, 
						this.qryForm.teacherId, this.qryForm.gradeId).then(resp => {

					this.pageInfos = resp.data.data;
					this.pageIndex = resp.data.pageIndex;
					this.pageSize = resp.data.pageSize;
					this.totalInfo = resp.data.count;
					this.loading = false;
				});
			},
			handleSizeChange(pageSize) {

				this.getPageInfo(this.pageIndex, pageSize, this.$store.state.token, this.qryForm.projectId, 
						this.qryForm.teacherId, this.qryForm.gradeId);
			},
			handleCurrentChange(pageIndex) {

				this.getPageInfo(pageIndex, this.pageSize, this.$store.state.token, this.qryForm.projectId, 
						this.qryForm.teacherId, this.qryForm.gradeId);
			},
			delInfo(id){
				this.$confirm('即将删除相关信息, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					
					delWorks(id).then(resp =>{
					
						this.$message({
							message: resp.msg,
							type: 'success'
						});
						
						this.getPageInfo(1, this.pageSize);
					});
				});	
			},
			initPlanForm(){

				this.projectPlansForm = {
					workId: "",
					roomId: "",
					createTime: "",
				}
			},
			showEditPlanWin(id){

				getAllClassrooms().then(resp =>{

					this.rooms = resp.data;
					this.projectPlansForm.workId = id;
					this.showEditPlanFlag = true;
				});
			},
			addcheck(id){
				addCheck(id).then(resp =>{
			this.$message({
				message: '成功发起签到',
				type: 'success'
			});
				});
			},
			editPlanInfo(){

				checkPlanTime(this.projectPlansForm.createTime, 
						this.projectPlansForm.workId).then(resp =>{
					
					console.log(resp.data);

					if(resp.data){

						addPlans(this.projectPlansForm).then(resp =>{

							this.$message({
								message: resp.msg,
								type: 'success'
							});

							this.initPlanForm();
							this.showEditPlanFlag = false;
						});
					}else{

						this.$message({
							message: "时段/教室已经安排授课，请重新选择课程或教室",
							type: "warning"
						});
					}
				});
			}
		},
		mounted() {

			getLoginUser(this.$store.state.token).then(resp =>{

				this.users = resp.data;
			});
			getAllGrades().then(resp =>{

				this.grades = resp.data;
			});
			getAllowProjects().then(resp =>{

				this.projects = resp.data;
			});
			this.getPageInfo(1, this.pageSize);
		}
	}
</script>
