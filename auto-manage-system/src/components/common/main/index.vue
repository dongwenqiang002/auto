<template>
    <div class="wrapper">
        <v-sidebar/>
        <div>
            <!--   <v-header/>-->
            <div class="content-box" :class="{'content-collapse':collapse}">
                <v-header/>
                <v-tags></v-tags>
                <div class="content">
                    <transition name="move" mode="out-in">
                        <keep-alive :include="tagsList">
                            <router-view></router-view>
                        </keep-alive>
                    </transition>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import vHeader from './Header'
    import vSidebar from './Sidebar'
    import vTags from './Tags.vue';
    import bus from '../bus';

    export default {
        data() {
            return {
                tagsList: [],
                collapse: false
            }
        },
        components: {
            vHeader, vSidebar, vTags
        },
        created() {
            bus.$on('collapse', msg => {
                this.collapse = msg;
            })

            // 只有在标签页列表里的页面才使用keep-alive，即关闭标签之后就不保存到内存中了。
            bus.$on('tags', msg => {
                let arr = [];
                for (let i = 0, len = msg.length; i < len; i++) {
                    msg[i].name && arr.push(msg[i].name);
                }
                this.tagsList = arr;
            })
        }
    }
</script>

<style scoped>

</style>
