<template>
	<el-aside class="fater-left">
		<el-menu  router>
			<template v-for="menu in sysMenu">
				<el-submenu :key="menu.id" :index="menu.path"
					v-if="menu.children && menu.children.length > 0">
					<template slot="title">
						<span :class="menu.icon"></span>
						<span slot="title">{{ menu.name }}</span>
					</template>
					<el-menu-item :key="child.id" :index="child.path"
						v-for="(child, indexC) in menu.children">
						<span :class="child.icon"></span>
						<span slot="title">{{ child.name }}</span>
					</el-menu-item>
				</el-submenu>
				<el-menu-item v-else :key="menu" :index="menu.path">
					<span :class="menu.icon"></span>
					<span slot="title">{{menu.name}}</span>
				</el-menu-item>
			</template>
		</el-menu>
	</el-aside>
</template>

<script>
	export default{
		props: {
			sysMenu: []
		},
		
	}
</script>

<style>
</style>
