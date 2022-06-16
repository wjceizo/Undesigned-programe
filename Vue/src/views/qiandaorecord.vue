<template>
	<div class="fater-body-show">
		<el-card shadow="never">
			<div slot="header">
				信息查询
			</div>
			<div>
				<el-form :inline="true" :model="qryForm">
					<el-form-item>
						<el-select v-model="qryForm.projectId" placeholder="选择课程">
							<el-option v-for="(item, index) in projects" :key="index" :label="item.name"
								:value="item.id"></el-option>
						</el-select>
					</el-form-item>
					<el-form-item v-if="users.type != 2">
						<el-select v-model="qryForm.gradeId" placeholder="选择班级">
							<el-option v-for="(item, index) in grades" :key="index" :label="item.name" :value="item.id">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item>
						<el-button type="primary" icon="el-icon-search" @click="getPageLikeInfo()"></el-button>
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
					<el-table-column align="center" prop="studentName" label="学生姓名"></el-table-column>
					<el-table-column align="center" prop="teacherName" v-if="users.type != 1" label="教师姓名">
					</el-table-column>
					<el-table-column prop="isCheck" label="是否签到" align="center" width="150" header-align="center"
						:filters="[{ text: '未签到', value: false },{ text: '已签到', value: true }]"
						:filter-method="filterTag" filter-placement="bottom-end">
						>
						<template slot-scope="scope">
							<span v-if="scope.row.isCheck==false" style="color: #F56C6C;font-weight: bolder;">未签到</span>
							<span v-if="scope.row.isCheck==true" style="color:#67C23A;font-weight: bolder;">已签到</span>
						</template>
					</el-table-column>
					<el-table-column align="center" prop="checkTime" label="签到时间"></el-table-column>
					<el-table-column align="center" prop="addTime" label="发起时间"></el-table-column>
					<el-table-column align="center" v-if="users.type == 0" label="操作处理">
						<template slot-scope="scope">
							<el-button icon="el-icon-delete" type="danger" size="mini" @click="delInfo(scope.row.id)">
							</el-button>
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
		getRecord,
		getLoginUser,
		getAllGrades,
		getAllowProjects,
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
				options: [{
					value: '0',
					label: '未签到'
				}, {
					value: '1',
					label: '已签到'
				}],
				value: '',
			}
		},
		methods: {

			getPageInfo(pageIndex, pageSize) {

				getRecord(1, this.pageSize, this.$store.state.token).then(resp => {

					this.pageInfos = resp.data.data;
					this.pageIndex = resp.data.pageIndex;
					this.pageSize = resp.data.pageSize;
					this.totalInfo = resp.data.count;
					this.loading = false;
					console.log(this.pageInfos)
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
			getPageLikeInfo() {

				getRecord(1, this.pageSize, this.$store.state.token, this.qryForm.projectId,
					this.qryForm.teacherId, this.qryForm.gradeId).then(resp => {

					this.pageInfos = resp.data.data;
					this.pageIndex = resp.data.pageIndex;
					this.pageSize = resp.data.pageSize;
					this.totalInfo = resp.data.count;
					this.loading = false;
				});
			},
			filterTag(value, row) {
				return row.isCheck === value;
			},
	},
	mounted() {
		getAllGrades().then(resp => {

			this.grades = resp.data;
		});
		getAllowProjects().then(resp => {

			this.projects = resp.data;
		});
		getLoginUser(this.$store.state.token).then(resp => {

			this.users = resp.data;
		});
		this.getPageInfo(1, this.pageSize);
	}
	}
</script>
