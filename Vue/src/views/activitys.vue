<template>
	<div class="fater-body-show">
		<el-card shadow="never">
			<div slot="header">
				信息查询
			</div>
			<div>
				<el-form :inline="true" :model="qryForm">
					<el-form-item >
						<el-input v-model="qryForm.title"
							placeholder="活动标题……"
							autocomplete="off"></el-input>
					</el-form-item>
					<el-form-item >
						<el-input v-model="qryForm.loc"
							placeholder="活动地点……"
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
					<el-table-column align="center" prop="title" label="活动标题"></el-table-column>
					<el-table-column align="center" prop="loc" label="活动地点"></el-table-column>
					<el-table-column align="center" label="活动描述">
						<template slot-scope="scope">
							<el-popover trigger="hover" placement="top">
								<el-descriptions class="margin-top" 
									title="活动描述" :column="1" border>
									<el-descriptions-item>
										<template slot="label">
											<span class="fa fa-clock-o"></span>
											活动时间
										</template>
										{{ scope.row.createTime }}
									</el-descriptions-item>
									<el-descriptions-item>
										<template slot="label">
											<span class="fa fa-map-marker"></span>
											活动要求
										</template>
										{{ scope.row.comm }}
									</el-descriptions-item>
									<el-descriptions-item>
										<template slot="label">
											<span class="fa fa-info-circle"></span>
											活动详情
										</template>
										{{ scope.row.intro }}
									</el-descriptions-item>	
								</el-descriptions>
								<div slot="reference" class="name-wrapper">
									<el-tag size="medium">活动描述</el-tag>
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

		<el-dialog title="添加信息" width="1000px" :visible.sync="showAddFlag">
			<el-form label-width="90px" :model="activitysForm">
				<el-row :gutter="15">
					<el-col :span="8">
						<el-form-item label="活动标题">
							<el-input v-model="activitysForm.title" 
								placeholder="请输入活动标题…" autocomplete="off"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="8">
						<el-form-item label="活动时间">
							<el-date-picker v-model="activitysForm.createTime" 
								type="date" placeholder="请输入活动时间…" 
								value-format="yyyy-MM-dd" 	autocomplete="off"></el-date-picker>
						</el-form-item>
					</el-col>
					<el-col :span="8">
						<el-form-item label="活动地点">
							<el-input v-model="activitysForm.loc" 
								placeholder="请输入活动地点…" autocomplete="off"></el-input>
						</el-form-item>
					</el-col>
				</el-row>
				<el-form-item label="活动要求">
					<el-input v-model="activitysForm.comm" type="textarea"
						:rows="3" placeholder="请输入活动要求…" autocomplete="off"></el-input>
				</el-form-item>
				<el-form-item label="活动详情">
					<el-input v-model="activitysForm.intro" type="textarea"
						:rows="5" placeholder="请输入活动详情…" autocomplete="off"></el-input>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click="showAddFlag = false">取 消</el-button>
				<el-button type="primary" @click="addInfo()">确 定</el-button>
			</div>
		</el-dialog>

		<el-dialog title="修改信息" width="1000px" :visible.sync="showUpdFlag">
			<el-form label-width="90px" :model="activitysForm">
				<el-row :gutter="15">
					<el-col :span="8">
						<el-form-item label="活动标题">
							<el-input v-model="activitysForm.title" 
								placeholder="请输入活动标题…" autocomplete="off"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="8">
						<el-form-item label="活动时间">
							<el-date-picker v-model="activitysForm.createTime" 
								type="date" placeholder="请输入活动时间…" 
								value-format="yyyy-MM-dd" autocomplete="off"></el-date-picker>
						</el-form-item>
					</el-col>
					<el-col :span="8">
						<el-form-item label="活动地点">
							<el-input v-model="activitysForm.loc" 
								placeholder="请输入活动地点…" autocomplete="off"></el-input>
						</el-form-item>
					</el-col>
				</el-row>
				<el-form-item label="活动要求">
					<el-input v-model="activitysForm.comm" type="textarea"
						:rows="3" placeholder="请输入活动要求…" autocomplete="off"></el-input>
				</el-form-item>
				<el-form-item label="活动详情">
					<el-input v-model="activitysForm.intro" type="textarea"
						:rows="5" placeholder="请输入活动详情…" autocomplete="off"></el-input>
				</el-form-item>
				
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click="showUpdFlag = false">取 消</el-button>
				<el-button type="primary" @click="updInfo()">确 定</el-button>
			</div>
		</el-dialog>
	</div>
</template>

<style>

</style>

<script>
	import {
		getPageActivitys,
		addActivitys,
		updActivitys,
		delActivitys
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
				points: [],
				qryForm: {
					title: "",
					loc: "",
				},
				activitysForm: {
					id: "",
					title: "",
					intro: "",
					loc: "",
					comm: "",
					createTime: "",
				}
			}
		},
		methods: {

			getPageInfo(pageIndex, pageSize) {

				getPageActivitys(pageIndex, pageSize, this.qryForm.title, this.qryForm.loc).then(resp => {

					this.pageInfos = resp.data.data;
					this.pageIndex = resp.data.pageIndex;
					this.pageSize = resp.data.pageSize;
					this.totalInfo = resp.data.count;

					this.loading = false;
				});
			},
			getPageLikeInfo() {

				getPageActivitys(1, this.pageSize, this.qryForm.title, this.qryForm.loc).then(resp => {

					this.pageInfos = resp.data.data;
					this.pageIndex = resp.data.pageIndex;
					this.pageSize = resp.data.pageSize;
					this.totalInfo = resp.data.count;
					this.loading = false;
				});
			},
			handleSizeChange(pageSize) {

				this.getPageInfo(this.pageIndex, pageSize, this.qryForm.title, this.qryForm.loc);
			},
			handleCurrentChange(pageIndex) {

				this.getPageInfo(pageIndex, this.pageSize, this.qryForm.title, this.qryForm.loc);
			},
			initForm() {

				this.activitysForm = {
					id: "",
					title: "",
					intro: "",
					loc: "",
					comm: "",
					createTime: "",
				};
			},
			showAddWin() {

				this.showAddFlag = true;
			},
			showUpdWin(row) {

				this.activitysForm = row;
				this.showUpdFlag = true;
			},
			addInfo() {

				addActivitys(this.activitysForm).then(resp => {

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

				updActivitys(this.activitysForm).then(resp => {

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
					
					delActivitys(id).then(resp =>{
					
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

			this.getPageInfo(1, this.pageSize);
		}
	}
</script>
