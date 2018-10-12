webpackJsonp([1], {
    "0HId": function (e, t, r) {
        "use strict";
        Object.defineProperty(t, "__esModule", {value: !0});
        var n = {
            data: function () {
                return {
                    ruleForm: {username: "admin", password: "123123"},
                    rules: {
                        username: [{required: !0, message: "请输入用户名", trigger: "blur"}],
                        password: [{required: !0, message: "请输入密码", trigger: "blur"}]
                    }
                }
            }, methods: {
                submitForm: function (e) {
                    var t = this, n = this;
                    this.$refs[e].validate(function (e) {
                        if (!e) return console.log("error submit!!"), !1;
                        localStorage.setItem("ms_username", t.ruleForm.username), t.$axios.get("/static/api/router.json").then(function (e) {
                            console.log(e), t.$router.addRoutes([{
                                path: "/", component: function (e) {
                                    return r.e(0).then(function () {
                                        var t = [r("Y5Ws")];
                                        e.apply(null, t)
                                    }.bind(this)).catch(r.oe)
                                }, meta: {title: "自述文件"}, children: n.setResolve(e.data)
                            }]), t.$router.push("/")
                        })
                    })
                }, setResolve: function (e) {
                    function t(t) {
                        return e.apply(this, arguments)
                    }

                    return t.toString = function () {
                        return e.toString()
                    }, t
                }(function (e) {
                    return e.forEach(function (e) {
                        if (e.children && setResolve(e.children), e.component) {
                            var t = e.component;
                            console.log("../../" + t), e.component = function (e) {
                                return r.e(4).then(function () {
                                    var n = [r("2eoi")("./" + t + ".vue")];
                                    e.apply(null, n)
                                }.bind(this)).catch(r.oe)
                            }
                        }
                    }), e
                })
            }
        }, o = {
            render: function () {
                var e = this, t = e.$createElement, r = e._self._c || t;
                return r("div", {staticClass: "login-wrap"}, [r("div", {staticClass: "ms-login"}, [r("div", {staticClass: "ms-title"}, [e._v("后台管理系统")]), e._v(" "), r("el-form", {
                    ref: "ruleForm",
                    staticClass: "ms-content",
                    attrs: {model: e.ruleForm, rules: e.rules, "label-width": "0px"}
                }, [r("el-form-item", {attrs: {prop: "username"}}, [r("el-input", {
                    attrs: {placeholder: "username"},
                    model: {
                        value: e.ruleForm.username, callback: function (t) {
                            e.$set(e.ruleForm, "username", t)
                        }, expression: "ruleForm.username"
                    }
                }, [r("el-button", {
                    attrs: {slot: "prepend", icon: "el-icon-lx-people"},
                    slot: "prepend"
                })], 1)], 1), e._v(" "), r("el-form-item", {attrs: {prop: "password"}}, [r("el-input", {
                    attrs: {
                        type: "password",
                        placeholder: "password"
                    }, nativeOn: {
                        keyup: function (t) {
                            if (!("button" in t) && e._k(t.keyCode, "enter", 13, t.key, "Enter")) return null;
                            e.submitForm("ruleForm")
                        }
                    }, model: {
                        value: e.ruleForm.password, callback: function (t) {
                            e.$set(e.ruleForm, "password", t)
                        }, expression: "ruleForm.password"
                    }
                }, [r("el-button", {
                    attrs: {slot: "prepend", icon: "el-icon-lx-lock"},
                    slot: "prepend"
                })], 1)], 1), e._v(" "), r("div", {staticClass: "login-btn"}, [r("el-button", {
                    attrs: {type: "primary"},
                    on: {
                        click: function (t) {
                            e.submitForm("ruleForm")
                        }
                    }
                }, [e._v("登录")])], 1), e._v(" "), r("p", {staticClass: "login-tips"}, [e._v("Tips : 用户名和密码随便填。")])], 1)], 1)])
            }, staticRenderFns: []
        };
        var s = r("VU/8")(n, o, !1, function (e) {
            r("B/bh")
        }, "data-v-34296ee9", null);
        t.default = s.exports
    }, "B/bh": function (e, t) {
    }
});
