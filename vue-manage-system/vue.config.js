module.exports = {
    baseUrl: './',
    assetsDir: 'static',
    productionSourceMap: false,
    devServer: {
        proxy: {
            '/api':{
                target:'http://localhost:81/j185_group_05_javaeeManagerSystem/',
                changeOrigin:true,
                pathRewrite:{
                    '/api':''
                }
            }
        }
    }
}