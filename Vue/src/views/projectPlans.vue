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
					<el-table-column align="center" prop="gradeName" label="授课班级"></el-table-column>
					<el-table-column align="center" prop="projectName" label="授课名称"></el-table-column>
					<el-table-column align="center" prop="teacherName" v-if="users.type != 1" label="授课教师"></el-table-column>
					<el-table-column align="center" prop="roomName" label="授课教室"></el-table-column>
					<el-table-column align="center" prop="createTime"  label="授课时间"></el-table-column>
					<el-table-column align="center" v-if="users.type == 0" label="操作处理">
						<template slot-scope="scope">
							<el-button icon="el-icon-delete" type="danger" size="mini"
								@click="delInfo(scope.row.id)"></el-button>
						</template>
					</el-table-column>
				</el-table>
				<el-pagination v-if="totalInfo > 1" style="margin-top: 15px;" @size-change="handleSizeChange"
					@current-change="handleCurrentChange" :current-page="pageIndex" :page-sizes="[5, 10, 20, 50]"
					:page-size="pageSize" layout="total, sizes, prev, pager, next, jumper" :total="totalInfo">
				</el-pagination>
			</div>
		</el-card>
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
		getPagePlans,
		delPlans
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
					name: "",
				},
			}
		},
		methods: {

			getPageInfo(pageIndex, pageSize) {

				getPagePlans(pageIndex, pageSize, this.$store.state.token).then(resp => {

					this.pageInfos = resp.data.data;
					this.pageIndex = resp.data.pageIndex;
					this.pageSize = resp.data.pageSize;
					this.totalInfo = resp.data.count;

					this.loading = false;
				});
			},
			getPageLikeInfo() {

				getPagePlans(1, this.pageSize, this.$store.state.token, this.qryForm.gradeId, 
						this.qryForm.teacherId, this.qryForm.projectId).then(resp => {

					this.pageInfos = resp.data.data;
					this.pageIndex = resp.data.pageIndex;
					this.pageSize = resp.data.pageSize;
					this.totalInfo = resp.data.count;
					this.loading = false;
				});
			},
			handleSizeChange(pageSize) {

				this.getPageInfo(this.pageIndex, pageSize, this.$store.state.token, this.qryForm.gradeId, 
						this.qryForm.teacherId, this.qryForm.projectId);
			},
			handleCurrentChange(pageIndex) {

				this.getPageInfo(pageIndex, this.pageSize, this.$store.state.token, this.qryForm.gradeId, 
						this.qryForm.teacherId, this.qryForm.projectId);
			},
			delInfo(id){
				this.$confirm('即将删除相关信息, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					
					delPlans(id).then(resp =>{
					
						this.$message({
							message: resp.msg,
							type: 'success'
						});
						
						this.getPageInfo(1, this.pageSize);
					});
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
