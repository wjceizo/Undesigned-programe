<template>
	<el-container>
		<Nav></Nav>
		<el-container>
			<Menu :sysMenu="this.$store.state.menus"></Menu>
			<el-main class="fater-body">
				<router-view></router-view>
				<qiandao></qiandao>
			</el-main>
		</el-container>
	</el-container>
</template>

<script>
	import Nav from "../components/layout/Nav.vue";
	import Menu from "../components/layout/Menu.vue";
	import qiandao from './qiandao.vue';
	export default {
		components: {
			Nav,
			Menu,
			qiandao
		},
	}
</script>

<style scoped="scoped">

</style>
