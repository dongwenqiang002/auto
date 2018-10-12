import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);


let router = new Router({
    routes: [
        {
            path: '/',
            redirect: '/dashboard'
        },
          /*  {
                path: '/',
                component: resolve => require(['../../common/main/index'], resolve),
                meta: { title: '自述文件' },
                children:[
                    {
                        path: '/dashboard',
                        component: resolve => require(['../../page/Dashboard.vue'], resolve),
                        meta: { title: '系统首页' }
                    },
                    {
                        path: '/icon',
                        component: resolve => require(['../../page/Icon.vue'], resolve),
                        meta: { title: '自定义图标' }
                    },
                    {
                        path: '/table',
                        component: resolve => require(['../../page/BaseTable.vue'], resolve),
                        meta: { title: '基础表格' }
                    },
                    {
                        path: '/tabs',
                        component: resolve => require(['../../page/Tabs.vue'], resolve),
                        meta: { title: 'tab选项卡' }
                    },
                    {
                        path: '/form',
                        component: resolve => require(['../../page/BaseForm.vue'], resolve),
                        meta: { title: '基本表单' }
                    },
                    {
                        // 富文本编辑器组件
                        path: '/editor',
                        component: resolve => require(['../../page/VueEditor.vue'], resolve),
                        meta: { title: '富文本编辑器' }
                    },
                    {
                        // markdown组件
                        path: '/markdown',
                        component: resolve => require(['../../page/Markdown.vue'], resolve),
                        meta: { title: 'markdown编辑器' }
                    },
                    {
                        // 图片上传组件
                        path: '/upload',
                        component: resolve => require(['../../page/Upload.vue'], resolve),
                        meta: { title: '文件上传' }
                    },
                    {
                        // vue-schart组件
                        path: '/charts',
                        component: resolve => require(['../../page/BaseCharts.vue'], resolve),
                        meta: { title: 'schart图表' }
                    },
                    {
                        // 拖拽列表组件
                        path: '/drag',
                        component: resolve => require(['../../page/DragList.vue'], resolve),
                        meta: { title: '拖拽列表' }
                    },
                    {
                        // 权限页面
                        path: '/permission',
                        component: resolve => require(['../../page/Permission.vue'], resolve),
                        meta: { title: '权限测试', permission: true }
                    },
                    {
                        path: '/404',
                        component: resolve => require(['../../page/404.vue'], resolve),
                        meta: { title: '404' }
                    },
                    {
                        path: '/403',
                        component: resolve => require(['../../page/403.vue'], resolve),
                        meta: { title: '403' }
                    }
                ]
            },*/
        {
            path: '/login',
            component: resolve => require(['../../login/index'], resolve)
        },
        {
            path: '*',
            component: resolve => require(['../../common/error'], resolve)
           // redirect: '/404'
        }
    ]
})



//使用钩子函数对路由进行权限跳转
router.beforeEach((to, from, next) => {
    const role = localStorage.getItem('ms_username');
    if(!role && to.path !== '/login'){
        next('/login');
        Vue.prototype.$message({
            showClose: true,
            dangerouslyUseHTMLString: true,
            message: '请先登录！',
            type: "warning"
        });
       // window.location.href = '/#/login';
    }else if(to.meta.permission){
        // 如果是管理员权限则可进入，这里只是简单的模拟管理员权限而已
        role === 'admin' ? next() : next('/403');
    }else{
        // 简单的判断IE10及以下不进入富文本编辑器，该组件不兼容
        if(navigator.userAgent.indexOf('MSIE') > -1 && to.path === '/editor'){
            Vue.prototype.$alert('vue-quill-editor组件不兼容IE10及以下浏览器，请使用更高版本的浏览器查看', '浏览器不兼容通知', {
                confirmButtonText: '确定'
            });
        }else{
            next();
        }
    }
})

export default router;
