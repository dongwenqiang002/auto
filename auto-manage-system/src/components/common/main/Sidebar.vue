<template>
    <div>
        <div class="sidebar">
            <v-logo/>
            <el-menu class="sidebar-el-menu" :default-active="onRoutes" :collapse="collapse" background-color="#324157"
                     text-color="#bfcbd9" active-text-color="#20a0ff" unique-opened router>
              <template v-for="item in items">
                    <template v-if="item.subs">
                        <menu-item :data="item"></menu-item>
                      <!--  <el-submenu :index="item.index" :key="item.index">
                            <template slot="title">
                                <i :class="item.icon"></i><span slot="title">{{ item.title }}</span>
                            </template>
                            <template v-for="subItem in item.subs">
                                <el-submenu v-if="subItem.subs" :index="subItem.index" :key="subItem.index">
                                    <template slot="title">
                                        <i :class="subItem.icon"></i><span slot="title">{{ subItem.title }}</span>
                                    </template>
                                    <el-menu-item v-for="(threeItem,i) in subItem.subs" :key="i"
                                                  :index="threeItem.index">
                                        <i :class="threeItem.icon"></i><span slot="title">{{ threeItem.title }}</span>
                                    </el-menu-item>
                                </el-submenu>
                                <el-menu-item v-else :index="subItem.index" :key="subItem.index">
                                    <i :class="subItem.icon"></i><span slot="title">{{ subItem.title }}</span>
                                </el-menu-item>
                            </template>
                        </el-submenu>-->
                    </template>
                    <template v-else>
                        <el-menu-item :index="item.index" :key="item.index">
                            <i :class="item.icon"></i><span slot="title">{{ item.title }}</span>
                        </el-menu-item>
                    </template>
                </template>

            </el-menu>
        </div>
    </div>
</template>

<script>
    import bus from '../../common/bus';
    import vLogo from './logo';
    import menuItem from './menuItem';
    export default {
        components: {
            vLogo,menuItem
        },
        data() {
            return {
                collapse: false,
                items: [  ]
            }
        },
        computed: {
            onRoutes() {
                return this.$route.path;//.replace('/', '');
            }
        },
        created() {
            this.$axios.get("/web/menu", {
                params: {role: localStorage.getItem('ms_username')}
            }).then(res => {
                this.items = res.data;
            });

            // 通过 Event Bus 进行组件间通信，来折叠侧边栏
            bus.$on('collapse', msg => {
                this.collapse = msg;
            })
        }
    }
</script>

<style scoped>
    .sidebar {
        background-color: rgb(50, 65, 87);
        display: block;
        position: absolute;
        left: 0;
        top: 0px;
        bottom: 0;
        overflow-y: scroll;
    }

    .sidebar::-webkit-scrollbar {
        width: 0;
    }

    .sidebar-el-menu:not(.el-menu--collapse) {
        width: 250px;
    }

    .sidebar > ul {
        height: auto;
    }


</style>
