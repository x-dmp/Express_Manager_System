import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/',
            redirect: '/dashboard'
        },
        {
            path: '/',
            component: () => import(/* webpackChunkName: "home" */ '../components/common/Home.vue'),
            meta: { title: '自述文件' },
            children: [
                {
                    path: '/dashboard',
                    component: () => import(/* webpackChunkName: "dashboard" */ '../components/page/Dashboard.vue'),
                    meta: { title: '系统首页' }
                },
                {
                    path: '/table',
                    component: () => import(/* webpackChunkName: "table" */ '../components/page/BaseTable.vue'),
                    meta: { title: '基础表格' }
                },
                {
                    path: '/icon',
                    component: () => import(/* webpackChunkName: "icon" */ '../components/page/Icon.vue'),
                    meta: { title: '自定义图标' }
                },
                {
                    path: '/tabs',
                    component: () => import(/* webpackChunkName: "tabs" */ '../components/page/Tabs.vue'),
                    meta: { title: 'tab选项卡' }
                },
                {
                    path: '/form',
                    component: () => import(/* webpackChunkName: "form" */ '../components/page/BaseForm.vue'),
                    meta: { title: '基本表单' }
                },
                {
                    // 图片上传组件
                    path: '/upload',
                    component: () => import(/* webpackChunkName: "upload" */ '../components/page/Upload.vue'),
                    meta: { title: '文件上传' }
                },
                {
                    // vue-schart组件
                    path: '/charts',
                    component: () => import(/* webpackChunkName: "chart" */ '../components/page/BaseCharts.vue'),
                    meta: { title: 'schart图表' }
                },
                {
                    // 拖拽列表组件
                    path: '/drag',
                    component: () => import(/* webpackChunkName: "drag" */ '../components/page/DragList.vue'),
                    meta: { title: '拖拽列表' }
                },
                {
                    // 权限页面
                    path: '/permission',
                    component: () => import(/* webpackChunkName: "permission" */ '../components/page/Permission.vue'),
                    meta: { title: '权限测试', permission: true }
                },
                {
                    path: '/404',
                    component: () => import(/* webpackChunkName: "404" */ '../components/page/404.vue'),
                    meta: { title: '404' }
                },
                {
                    path: '/403',
                    component: () => import(/* webpackChunkName: "403" */ '../components/page/403.vue'),
                    meta: { title: '403' }
                },
                {
                    path: '/usertable',
                    component: () => import(/* webpackChunkName: "table" */ '../components/page/UserTable'),
                    meta: { title: '用户信息表格' }
                },
                {
                    path: '/userform',
                    component: () => import(/* webpackChunkName: "form" */ '../components/page/UserForm'),
                    meta: { title: '新增用户信息' }
                },
                {
                    path: '/companytable',
                    component: () => import(/* webpackChunkName: "table" */ '../components/page/CompanyTable'),
                    meta: { title: '公司信息表格' }
                },
                {
                    path: '/companyform',
                    component: () => import(/* webpackChunkName: "form" */ '../components/page/CompanyForm'),
                    meta: { title: '新增公司信息' }
                },
                {
                    path: '/expresstable',
                    component: () => import(/* webpackChunkName: "table" */ '../components/page/ExpressTable'),
                    meta: { title: '快递信息表格' }
                },
                {
                    path: '/expressform',
                    component: () => import(/* webpackChunkName: "form" */ '../components/page/ExpressForm'),
                    meta: { title: '新增快递信息' }
                },
                {
                    path: '/sendtable',
                    component: () => import(/* webpackChunkName: "table" */ '../components/page/SendTable'),
                    meta: { title: '快递信息表格' }
                },
                {
                    path: '/sendform',
                    component: () => import(/* webpackChunkName: "form" */ '../components/page/SendForm'),
                    meta: { title: '新增快递信息' }
                },
            ]
        },
        {
            path: '/login',
            component: () => import(/* webpackChunkName: "login" */ '../components/page/Login.vue'),
            meta: { title: '登录' }
        },
        {
            path: '*',
            redirect: '/404'
        }
    ]
});
