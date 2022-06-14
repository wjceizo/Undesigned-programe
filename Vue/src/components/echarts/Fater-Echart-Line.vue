<template>
	<div id="line"  ref="faterEchartLine" style="width: 600px;height:400px;"></div>
</template>

<script>
	import * as echarts from "echarts";
	export default {
		props: {
			dataX: [],
			dataY: [],
			dataL: []
		},
		data() {
			return {
				option: {
					title: {
						text: "数据统计"
					},
					tooltip: {
						trigger: 'axis'
					},
					legend: {
						data: this.dataL
					},
					xAxis: {
						data: this.dataX
					},
					yAxis: {
						type: 'value'
					},
					series: [{
						type: 'line',
						data: this.dataY
					}]
				}
			}
		},
		methods: {

			drowImg() {
				var myChart = echarts.init(this.$refs.faterEchartLine);
				myChart.setOption(this.option);
			}
		},
		mounted() {
			this.drowImg();
		}
	}
</script>

<style>
</style>
