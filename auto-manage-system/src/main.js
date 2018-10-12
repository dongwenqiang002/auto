import Vue from 'vue';
import App from './App';
import router from './components/js/router';
import axios from './components/js/axios';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';    // 默认主题
// import '../static/css/theme-green/index.css';       // 浅绿色主题
import '../static/css/icon.css';
import "babel-polyfill";



Vue.use(ElementUI, { size: 'small' });
Vue.prototype.$axios = axios;


new Vue({
    router,
    render: h => h(App)
}).$mount('#app');

/*
router.onError(err =>{
    console.log('错啦')
    router.push('/login');
})
*/
