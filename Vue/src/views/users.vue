<template>
	<div class="fater-body-show">
		<el-card shadow="never">
			<div slot="header">
				信息查询
			</div>
			<div>
				<el-form :inline="true" :model="qryForm">
					<el-form-item >
						<el-input v-model="qryForm.userName"
							placeholder="用户账号……"
							autocomplete="off"></el-input>
					</el-form-item>
					<el-form-item >
						<el-input v-model="qryForm.name"
							placeholder="用户姓名……"
							autocomplete="off"></el-input>
					</el-form-item>
					<el-form-item>
						<el-button type="primary" 
							icon="el-icon-search" @click="getPageLikeInfo()"></el-button>
					</el-form-item>
				</el-form>
			</div>
		</el-card>

		<el-card shadow="never">
			<div slot="header">
				<el-button type="primary" size="mini" 
						icon="el-icon-plus" @click="showAddWin()"></el-button>
			</div>
			<div>
				<el-table v-loading="loading" element-loading-text="拼命加载中" element-loading-spinner="el-icon-loading"
					element-loading-background="rgba(124, 124, 124, 0.8)" :data="pageInfos" border>
					<el-table-column align="center" type="index"></el-table-column>
					<el-table-column align="center" prop="userName" label="用户账号"></el-table-column>
					<el-table-column align="center" prop="name" label="用户姓名"></el-table-column>
					<el-table-column align="center" label="用户身份">
						<template slot-scope="scope">
							<span v-if="scope.row.type == 0">系统管理员</span>
							<span v-else-if="scope.row.type == 1">学校教师</span>
							<span v-else>在校学生</span>
						</template>
					</el-table-column>
					<el-table-column align="center" label="详细信息">
						<template slot-scope="scope">
							<el-popover trigger="hover" placement="left">
								<el-descriptions v-if="scope.row.type == 0"
									title="详情描述" :column="2" border>
									<el-descriptions-item>
										<template slot="label">
											用户ID
										</template>
										{{ scope.row.id }}
									</el-descriptions-item>
									<el-descriptions-item>
										<template slot="label">
											用户姓名
										</template>
										{{ scope.row.name }}
										
									</el-descriptions-item>
									<el-descriptions-item>
										<template slot="label">
											用户性别
										</template>
										{{ scope.row.gender }}
									</el-descriptions-item>
									<el-descriptions-item>
										<template slot="label">
											用户年龄
										</template>
										{{ scope.row.age }}
									</el-descriptions-item>
								</el-descriptions>
								<el-descriptions v-else-if="scope.row.type == 1"
									title="教师详情" :column="2" border>
									<template v-if="scope.row.type != 0" slot="extra">
										<el-button @click="showEditInfoWin(scope.row)" type="primary" size="small">编辑</el-button>
									</template>
									<el-descriptions-item>
										<template slot="label">
											教师工号
										</template>
										{{ scope.row.id }}
									</el-descriptions-item>
									<el-descriptions-item>
										<template slot="label">
											教师姓名
										</template>
										{{ scope.row.name }}
									</el-descriptions-item>
									<el-descriptions-item>
										<template slot="label">
											教师性别
										</template>
										{{ scope.row.gender }}
									</el-descriptions-item>
									<el-descriptions-item>
										<template slot="label">
											教师年龄
										</template>
										{{ scope.row.age }}
									</el-descriptions-item>
									<el-descriptions-item v-if="scope.row.teacher">
										<template slot="label">
											教师学历
										</template>
										{{ scope.row.teacher.record }}
									</el-descriptions-item>
									<el-descriptions-item v-if="scope.row.teacher">
										<template slot="label">
											教师职称
										</template>
										{{ scope.row.teacher.job }}
									</el-descriptions-item>
									<el-descriptions-item v-if="scope.row.teacher">
										<template slot="label">
											入职时间
										</template>
										{{ scope.row.teacher.intoTime }}
									</el-descriptions-item>
								</el-descriptions>
								<el-descriptions v-else-if="scope.row.type == 2"
									title="学生详情" :column="2" border>
									<template v-if="scope.row.type != 0" slot="extra">
										<el-button @click="showEditInfoWin(scope.row)" type="primary" size="small">编辑</el-button>
									</template>
									<el-descriptions-item>
										<template slot="label">
											学生学号
										</template>
										{{ scope.row.id }}
									</el-descriptions-item>
									<el-descriptions-item>
										<template slot="label">
											学生姓名
										</template>
										{{ scope.row.name }}
									</el-descriptions-item>
									<el-descriptions-item>
										<template slot="label">
											学生性别
										</template>
										{{ scope.row.gender }}
									</el-descriptions-item>
									<el-descriptions-item>
										<template slot="label">
											学生年龄
										</template>
										{{ scope.row.age }}
									</el-descriptions-item>
									<el-descriptions-item v-if="scope.row.student">
										<template slot="label">
											所在年级
										</template>
										<span v-if="scope.row.student.gradeId"></span>
										{{ scope.row.student.gradeName }}
									</el-descriptions-item>
									<el-descriptions-item v-if="scope.row.student">
										<template slot="label">
											入学时间
										</template>
										{{ scope.row.student.intoTime }}
									</el-descriptions-item>
								</el-descriptions>
								<div slot="reference" class="name-wrapper">
									<el-tag size="medium">详情描述</el-tag>
								</div>
							</el-popover>
						</template>
					</el-table-column>
					<el-table-column align="center" label="操作处理">
						<template slot-scope="scope">
							<el-button icon="el-icon-edit"
								type="primary" size="mini" @click="showUpdWin(scope.row)"></el-button>
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

		<el-dialog title="添加信息" width="600px" :visible.sync="showAddFlag">
			<el-form label-width="90px" :model="usersForm">
				<el-row :gutter="15">
					<el-col :span="12">
						<el-form-item label="用户账号">
							<el-input v-model="usersForm.userName" 
								placeholder="请输入用户账号…" autocomplete="off"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="用户密码">
							<el-input type="password" v-model="usersForm.passWord" 
								placeholder="请输入用户密码…" autocomplete="off"></el-input>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row :gutter="15">
					<el-col :span="12">
						<el-form-item label="用户姓名">
							<el-input v-model="usersForm.name" 
								placeholder="请输入用户姓名…" autocomplete="off"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="用户年龄">
							<el-input v-model="usersForm.age" 
								placeholder="请输入用户年龄…" autocomplete="off"></el-input>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row :gutter="15">
					<el-col :span="12">
						<el-form-item label="用户身份">
							<el-radio v-model="usersForm.type" label="1">教师</el-radio>
							<el-radio v-model="usersForm.type" label="2">学生</el-radio>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="用户性别">
							<el-radio v-model="usersForm.gender" label="男">男</el-radio>
							<el-radio v-model="usersForm.gender" label="女">女</el-radio>
						</el-form-item>
					</el-col>
				</el-row>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click="showAddFlag = false">取 消</el-button>
				<el-button type="primary" @click="addInfo()">确 定</el-button>
			</div>
		</el-dialog>

		<el-dialog title="修改信息" width="600px" :visible.sync="showUpdFlag">
			<el-form label-width="90px" :model="usersForm">
				<el-row :gutter="15">
					<el-col :span="12">
						<el-form-item label="用户账号">
							<el-input v-model="usersForm.userName" 
								placeholder="请输入用户账号…" autocomplete="off"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="用户密码">
							<el-input type="password" v-model="usersForm.passWord" 
								placeholder="请输入用户密码…" autocomplete="off"></el-input>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row :gutter="15">
					<el-col :span="12">
						<el-form-item label="用户姓名">
							<el-input v-model="usersForm.name" 
								placeholder="请输入用户姓名…" autocomplete="off"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="用户年龄">
							<el-input v-model="usersForm.age" 
								placeholder="请输入用户年龄…" autocomplete="off"></el-input>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row :gutter="15">
					<el-col :span="12">
						<el-form-item label="用户身份">
							<el-radio v-model="usersForm.type" label="1">教师</el-radio>
							<el-radio v-model="usersForm.type" label="2">学生</el-radio>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="用户性别">
							<el-radio v-model="usersForm.gender" label="男">男</el-radio>
							<el-radio v-model="usersForm.gender" label="女">女</el-radio>
						</el-form-item>
					</el-col>
				</el-row>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click="showUpdFlag = false">取 消</el-button>
				<el-button type="primary" @click="updInfo()">确 定</el-button>
			</div>
		</el-dialog>

		<el-dialog title="编辑学生信息" width="600px" :visible.sync="showEditStuFlag">
			<el-form label-width="90px" :model="studentsForm">
				<el-form-item label="所在班级">
					<el-select style="width: 100%" v-model="studentsForm.gradeId" placeholder="请选择学生所在班级">
						<el-option v-for="(item, index) in grades" :key="index"
							:label="item.name" :value="item.id"></el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="入学时间">
					<el-date-picker style="width: 100%" v-model="studentsForm.intoTime"  
						value-format="yyyy-MM-dd" type="date" placeholder="选择学生入学日期"></el-date-picker>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click="showEditStuFlag = false">取 消</el-button>
				<el-button type="primary" @click="editStuInfo()">确 定</el-button>
			</div>
		</el-dialog>
		<el-dialog title="编辑教师信息" width="600px" :visible.sync="showEditTeaFlag">
			<el-form label-width="90px" :model="teachersForm">
				<el-form-item label="教师学历">
					<el-input v-model="teachersForm.record" 
						placeholder="请输入教师学历…" autocomplete="off"></el-input>
				</el-form-item>
				<el-form-item label="教师职称">
					<el-input v-model="teachersForm.job" 
						placeholder="请输入教师职称…" autocomplete="off"></el-input>
				</el-form-item>
				<el-form-item label="入职时间">
					<el-date-picker style="width: 100%" v-model="teachersForm.intoTime"  
						value-format="yyyy-MM-dd"	type="date" placeholder="请输入入职时间…"></el-date-picker>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click="showEditTeaFlag = false">取 消</el-button>
				<el-button type="primary" @click="editTeaInfo()">确 定</el-button>
			</div>
		</el-dialog>
	</div>
</template>

<style>

</style>

<script>
	import {
		getPageUsers,
		addUsers,
		updUsers,
		delUsers,
		getAllGrades,
		addStudents,
		updStudents,
		addTeachers,
		updTeachers
	} from "../api";

	export default {

		data() {

			return {
				pageInfos: [],
				pageIndex: 1,
				pageSize: 10,
				totalInfo: 0,
				loading: true,
				showAddFlag: false,
				showUpdFlag: false,
				qryForm: {
					name: "",
					userName: "",
				},
				usersForm: {
					id: "",
					userName: "",
					passWord: "",
					name: "",
					age: "",
					type: "",
					gender: "",
					createTime: "",
				},
				showEditStuFlag: false,
				isAddStu: false,
				grades: [],
				studentsForm: {
					id: "",
					gradeId: "",
					intoTime: "",
				},
				showEditTeaFlag: false,
				isAddTea: false,
				teachersForm: {
					id: "",
					record: "",
					job: "",
					intoTime: "",
				}
			}
		},
		methods: {

			getPageInfo(pageIndex, pageSize) {

				getPageUsers(pageIndex, pageSize).then(resp => {

					this.pageInfos = resp.data.data;
					this.pageIndex = resp.data.pageIndex;
					this.pageSize = resp.data.pageSize;
					this.totalInfo = resp.data.count;

					this.loading = false;
				});
			},
			getPageLikeInfo() {

				getPageUsers(1, this.pageSize, this.qryForm.userName, this.qryForm.name).then(resp => {

					this.pageInfos = resp.data.data;
					this.pageIndex = resp.data.pageIndex;
					this.pageSize = resp.data.pageSize;
					this.totalInfo = resp.data.count;
					this.loading = false;
				});
			},
			handleSizeChange(pageSize) {

				this.getPageInfo(this.pageIndex, pageSize, this.qryForm.userName, this.qryForm.name);
			},
			handleCurrentChange(pageIndex) {

				this.getPageInfo(pageIndex, this.pageSize, this.qryForm.userName, this.qryForm.name);
			},
			initForm() {

				this.usersForm = {
					id: "",
					userName: "",
					passWord: "",
					name: "",
					age: "",
					type: "",
					gender: "",
					createTime: "",
				};
			},
			showAddWin() {

				this.showAddFlag = true;
			},
			showUpdWin(row) {

				this.usersForm = row;
				this.showUpdFlag = true;
			},
			addInfo() {

				addUsers(this.usersForm).then(resp => {

					this.$message({
						message: resp.msg,
						type: 'success'
					});

					this.getPageInfo(1, this.pageSize);

					this.showAddFlag = false;

					this.initForm();
				});
			},
			updInfo() {

				updUsers(this.usersForm).then(resp => {

					this.$message({
						message: resp.msg,
						type: 'success'
					});

					this.getPageInfo(1, this.pageSize);

					this.showUpdFlag = false;

					this.initForm();
				});
			},
			delInfo(id){
				this.$confirm('即将删除相关信息, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					
					delUsers(id).then(resp =>{
					
						this.$message({
							message: resp.msg,
							type: 'success'
						});
						
						this.getPageInfo(1, this.pageSize);
					});
				});	
				
			},
			showEditInfoWin(row) {
				
				if(row.type == 1){
					if(row.teacher){
						
						this.teachersForm = row.teacher;
						this.teachersForm.id = row.id;
						this.showEditTeaFlag = true;
					}else{
						
						this.teachersForm.id = row.id;
						this.isAddTea = true;
						
						this.showEditTeaFlag = true;
						
					}
				}else if(row.type == 2){
					getAllGrades().then(resp =>{

						this.grades = resp.data;
						if(row.student){

							this.studentsForm = row.student;
							this.studentsForm.id = row.id;
						}else{

							this.studentsForm.id = row.id;
							this.isAddStu = true;
						}
						this.showEditStuFlag = true;
					});
				}
			},
			initStuForm(){
				this.studentsForm = {
					id: "",
					gradeId: "",
					intoTime: "",
				};
				this.isAddStu = false;
			},
			editStuInfo(){
				console.log(this.isAddStu);
				if(this.isAddStu){

					addStudents(this.studentsForm).then(resp =>{

						this.$message({
							message: resp.msg,
							type: 'success'
						});

						this.getPageInfo(1, this.pageSize);

						this.showEditStuFlag = false;

						this.initStuForm();
					});
				}else{

					updStudents(this.studentsForm).then(resp =>{

						this.$message({
							message: resp.msg,
							type: 'success'
						});

						this.getPageInfo(1, this.pageSize);

						this.showEditStuFlag = false;

						this.initStuForm();
					});
				}
			},
			initTeaForm(){
				this.teachersForm = {
					id: "",
					record: "",
					job: "",
					intoTime: "",
				}
				this.isAddTea = false;
			},
			editTeaInfo(){

				if(this.isAddTea){

					addTeachers(this.teachersForm).then(resp =>{

						this.$message({
							message: resp.msg,
							type: 'success'
						});

						this.getPageInfo(1, this.pageSize);

						this.showEditTeaFlag = false;

						this.initTeaForm();
					});
				}else{

					updTeachers(this.teachersForm).then(resp =>{

						this.$message({
							message: resp.msg,
							type: 'success'
						});

						this.getPageInfo(1, this.pageSize);

						this.showEditTeaFlag = false;

						this.initTeaForm();
					});
				}
			}
		},
		mounted() {

			this.getPageInfo(1, this.pageSize);
		}
	}
</script>
