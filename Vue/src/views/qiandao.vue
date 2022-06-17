<template>
	<el-dialog title="请及时签到" :visible.sync="dialogVisible" width="20%" :before-close="handleClose" v-if="user.type==2"
		style="text-align: center;">
		<el-button type="primary" circle @click="qiandao(user.id)"
			style="width: 100px;height: 100px;margin-bottom: 40px;margin-top: 40px;">
			<i class="el-icon-edit" style="font-size: 25px;opacity: 0.5;"></i> <span
				style="font-size: 30px;opacity: 0.5;">{{ this.count}}</span>
		</el-button>
	</el-dialog>
</template>

<script>
	import {
		ischeck,
		qiandao,
		outtime
	} from "../api";
	export default {
		data() {

			return {
				dialogVisible: false,
				user: this.$store.state.user,
				seconds: 30,
				count: '',
				timer: null,
			}
		},
		methods: {

			handleClose(done) {
				this.$confirm('确认不签到吗？')
					.then(_ => {
						done();
						outtime(this.$store.state.user.id).then(resp => {
							this.$once('hook:beforeDestroy', () => {
								clearInterval(this.timer)
								this.timer = null;
							})
						})
					})
					.catch(_ => {});
			},
			qiandao(stuId) {
				qiandao(stuId).then(resp => {
					if (resp.code === 0) {
						this.dialogVisible = false;
						this.$message({
							message: '签到成功！',
							type: 'success',
							duration: 1500,
							onClose: () => {
								window.location.reload()
							}
						})
						clearInterval(this.timer)
						this.timer = null;
					}

				})
			},
			countDown() {
				if (this.seconds > -1) {
					let s = parseInt(this.seconds % 60);
					s = s < 10 ? "0" + s : s
					this.count = s
				}
			},
		},
		created() {
			ischeck(this.user.id).then(resp => {
				console.log(resp)
				if (resp.data.ischeck === false) {
					this.dialogVisible = true;
					this.timer = setInterval(() => {
						this.seconds -= 1
						this.shijian += 1;
						if (this.seconds == 0) {
							this.dialogVisible = false;
							outtime(this.$store.state.user.id).then(resp => {
								if (resp.code === 0) {
									this.$message({
										message: '签到超时',
										type: 'warning'
									});
								}
							})
						}
						this.countDown()
					}, 1000)
				}
			})
			this.$once('hook:beforeDestroy', () => {
				clearInterval(this.timer)
				this.timer = null;
			})
		}
	}
</script>

<style>
</style>
