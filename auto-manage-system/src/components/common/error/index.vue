<template>

        <component v-bind:is="currentTabComponent"></component>

</template>

<script>
    import page404 from './404.vue'
    import loading from './loading.vue'
    import noLogin from './noLogin.vue'

    export default {
        components: {
            page404, noLogin, loading
        },
        data() {
            return {
                currentTabComponent: 'page404'
            }
        },
        created() {
            if (localStorage.getItem('ms_username')) {
                if (this.$loading.apply().visible) {
                    //页面加载中
                    this.currentTabComponent = 'loading'
                } else {
                    this.$loading().close();
                    this.currentTabComponent = 'page404'
                }
            } else {
                this.currentTabComponent = 'noLogin'
            }
            console.log('一次页面触发')
            console.log(this.currentTabComponent)
        },
    }
</script>

<style scoped>

</style>
