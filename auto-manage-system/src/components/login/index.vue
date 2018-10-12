<template>
    <div class="login-wrap">
        <div class="ms-login">
            <div class="ms-title">后台管理系统</div>
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="0px" class="ms-content">
                <el-form-item prop="username">
                    <el-input v-model="ruleForm.username" placeholder="username">
                        <el-button slot="prepend" icon="el-icon-lx-people"></el-button>
                    </el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input type="password" placeholder="password" v-model="ruleForm.password"
                              @keyup.enter.native="submitForm('ruleForm')">
                        <el-button slot="prepend" icon="el-icon-lx-lock"></el-button>
                    </el-input>
                </el-form-item>
                <div class="login-btn">
                    <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
                </div>
                <p class="login-tips">Tips : 用户名和密码随便填。</p>
            </el-form>
        </div>
    </div>
</template>

<script>
    export default {
        data: function () {
            return {
                ruleForm: {
                    username: 'admin',
                    password: '123123'
                },
                rules: {
                    username: [
                        {required: true, message: '请输入用户名', trigger: 'blur'}
                    ],
                    password: [
                        {required: true, message: '请输入密码', trigger: 'blur'}
                    ]
                }
            }
        },
        methods: {
            submitForm(formName) {
                let that = this;
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        localStorage.setItem('ms_username', this.ruleForm.username);
                        //假设登录名就是角色
                        let role =  localStorage.getItem('ms_username');
                        //与上一次登录的角色相同，则加载路径，不同则登录完成后重新刷新页面
                        if(role == localStorage.getItem('role')){
                            that.loadRouter();
                        }else{
                            localStorage.setItem('role', role);
                            location.reload();
                        }
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            loadRouter() {
                let that = this;
                const loading = this.$loading({
                    lock: true,
                    text: 'Loading',
                    spinner: 'el-icon-loading',
                    background: 'rgba(0, 0, 0, 0.7)'
                });
                this.$axios.get('/web/router',{
                    params:{role:localStorage.getItem('ms_username')}
                }).then(res => {
                    this.$router.addRoutes([
                        {
                            path: '/',
                            component: resolve => require(['../../components/common/main'], resolve),
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
                        rotePath.component = resolve => require([`./../${rr}.vue`], resolve);
                    }
                });
                return route;
            }


        }
    }
</script>

<style scoped>
    .login-wrap {
        position: relative;
        width: 100%;
        height: 100%;
        background-image: url(../../assets/login-bg.jpg);
        background-size: 100%;
    }

    .ms-title {
        width: 100%;
        line-height: 50px;
        text-align: center;
        font-size: 20px;
        color: #fff;
        border-bottom: 1px solid #ddd;
    }

    .ms-login {
        position: absolute;
        left: 50%;
        top: 50%;
        width: 350px;
        margin: -190px 0 0 -175px;
        border-radius: 5px;
        background: rgba(255, 255, 255, 0.3);
        overflow: hidden;
    }

    .ms-content {
        padding: 30px 30px;
    }

    .login-btn {
        text-align: center;
    }

    .login-btn button {
        width: 100%;
        height: 36px;
        margin-bottom: 10px;
    }

    .login-tips {
        font-size: 12px;
        line-height: 30px;
        color: #fff;
    }
</style>
