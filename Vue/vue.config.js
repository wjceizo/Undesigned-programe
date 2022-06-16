module.exports = {
    devServer: {
        host: "localhost",
        port: 8080,
        //proxy:{'/api':{}},代理器中设置/api,项目中请求路径为/api的替换为target
        proxy: { 
            ['/']: {
                target: "http://localhost:8888/educational",//代理地址，这里设置的地址会代替axios中设置的baseURL
                ws: false,  // 如果接口跨域，需要进行这个参数配置
                            //ws: true, // proxy websockets
                            //pathRewrite方法重写url
                changeOrigin: true,
                pathReWrite: {
                    "^/": "/"
                //pathRewrite: {'^/api': '/'} 重写之后url为 http://localhost:8888/xxxx
                //pathRewrite: {'^/api': '/api'} 重写之后url为 http://localhost:8888/api/xxxx
                }
            }
        }
    }
}