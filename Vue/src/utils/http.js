import axios from 'axios'
import qs from 'qs'
import {
	Message
} from 'element-ui'

axios.defaults.headers['Content-Type'] = 'application/x-www-form-urlencoded';

const service = axios.create({
	// withCredentials: true,
	// baseURL: '/fastmail/manage',
	timeout: 15000 // 请求超时时间
})

service.interceptors.request.use(config => {
	
	if(config.method === "post"){
		
		config.data = qs.stringify(config.data,  { indices: false });
	}
	
	return config;
}, error => {
	Promise.reject(error)
})

// respone拦截器
service.interceptors.response.use(
	success => {

		if (success.data.code == 0) {

			return success.data;
		} else {

			Message({
				message: success.data.msg,
				type: 'error',
				center: true
			});
			return Promise.reject(success);
		}
	},
	error => {
		Message({
			message: '系统异常，请求中断',
			type: 'error',
			center: true
		});
		return Promise.reject(error);
	}
)

export default service
