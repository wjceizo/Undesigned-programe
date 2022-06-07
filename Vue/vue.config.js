module.exports = {
    devServer: {
        host: "localhost",
        port: 8080,
        proxy: { 
            ['/']: {
                target: "http://localhost:8888/educational",
                ws: false,
                changeOrigin: true,
                pathReWrite: {
                    "^/": "/"
                }
            }
        }
    }
}