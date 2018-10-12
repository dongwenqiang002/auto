webpackJsonp([3], {
    NHnr: function (t, e, n) {
        "use strict";
        Object.defineProperty(e, "__esModule", {value: !0});
        var r = n("7+uW"), a = {
            render: function () {
                var t = this.$createElement, e = this._self._c || t;
                return e("div", {attrs: {id: "app"}}, [e("router-view")], 1)
            }, staticRenderFns: []
        };
        var o = n("VU/8")(null, a, !1, function (t) {
            n("tROZ")
        }, null, null).exports, i = n("/ocq");
        r.default.use(i.a);
        var u = new i.a({
            routes: [{path: "/", redirect: "/dashboard"}, {
                path: "/", component: function (t) {
                    return n.e(0).then(function () {
                        var e = [n("Y5Ws")];
                        t.apply(null, e)
                    }.bind(this)).catch(n.oe)
                }, meta: {title: "自述文件"}
            }, {
                path: "/login", component: function (t) {
                    return n.e(1).then(function () {
                        var e = [n("0HId")];
                        t.apply(null, e)
                    }.bind(this)).catch(n.oe)
                }
            }, {path: "*", redirect: "/404"}]
        });
        u.beforeEach(function (t, e, n) {
            var a = localStorage.getItem("ms_username");
            a || "/login" === t.path ? t.meta.permission ? "admin" === a ? n() : n("/403") : navigator.userAgent.indexOf("MSIE") > -1 && "/editor" === t.path ? r.default.prototype.$alert("vue-quill-editor组件不兼容IE10及以下浏览器，请使用更高版本的浏览器查看", "浏览器不兼容通知", {confirmButtonText: "确定"}) : n() : n("/login")
        });
        var l = u, c = n("mtWM"), p = n.n(c).a, s = n("zL8q"), d = n.n(s);
        n("tvR6"), n("ympj"), n("j1ja");
        r.default.use(d.a, {size: "small"}), r.default.prototype.$axios = p, new r.default({
            router: l,
            render: function (t) {
                return t(o)
            }
        }).$mount("#app")
    }, tROZ: function (t, e) {
    }, tvR6: function (t, e) {
    }, ympj: function (t, e) {
    }
}, ["NHnr"]);
