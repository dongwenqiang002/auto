<template>
    <div id="app">
        <router-view></router-view>
    </div>
</template>
<script>
    export default {
        created() {
            if (localStorage.getItem('ms_username') && localStorage.getItem('role')) {
                //此处应是已登录并且已获取过角色才可以加载角色相关的路由
                //TODO
                console.log('应用加载路由')
                this.loadRouter();
            }else {
                this.$router.push("/login");
            }

        },
        methods: {
            loadRouter() {
                let that = this;
              //  console.log(this.$loading.apply().visible)
                //let loading = Loading.service({ fullscreen: true ,text:'页面加载中'});
                const loading = this.$loading({
                    lock: true,
                    text: 'Loading',
                    spinner: 'el-icon-loading',
                    background: 'rgba(0, 0, 0, 0.7)'
                });
                this.$axios.get('/web/router',{
                    params:{role:localStorage.getItem('ms_username')}
                }).then(res => {
                   // console.log(res);
                    this.$router.addRoutes([
                        {
                            path: '/',
                            component: resolve => require(['./components/common/main'], resolve),
                            meta: {title: '自述文件'},
                            children: that.setResolve(res.data)
                        }
                    ]);
                    loading.close();
                    this.$router.push('/');
                });
            },
            setResolve(route) {
                route.forEach(function (rotePath) {
                    if (rotePath.children) this.setResolve(rotePath.children);
                    if (rotePath.component) {
                        let rr = rotePath.component;
                        rotePath.component = resolve => require([`./components/${rr}.vue`], resolve);
                    }
                });
                return route;
            }
        }
    }


</script>
<style>
    @import "../static/css/main.css";
    @import "../static/css/color-dark.css"; /*深色主题*/
    /*@import "../static/css/theme-green/color-green.css";   浅绿色主题*/
</style>
