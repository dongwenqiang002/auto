import axios from 'axios';
import Vue from 'vue';

// http响应拦截器
//统一的错误处理
axios.interceptors.response.use(response => {
       // console.log(response);
        if (response.data && response.data.code) {
            if (response.data.code == "000000") {
                return response;
            } else {
                Vue.prototype.$message({
                    showClose: true,
                    dangerouslyUseHTMLString: true,
                    message: response.data.msg + "\n错误码：" + response.data.code,
                    type: "error"
                });
                return false;
            }
        }
        //对于没有code的，不做处理
        return response;
    },
    error => {
        if (error.response.status >= 500) {
            Vue.prototype.$message({
                showClose: true,
                dangerouslyUseHTMLString: true,
                message: "服务器处理后的的错误！错误码：" + error.response.data.code + "\n" +
                error.response.data.msg,
                type: "error"
            });
        } else if (error.response.status >= 400) {
            Vue.prototype.$message({
                showClose: true,
                dangerouslyUseHTMLString: true,
                message: "请求错误！错误码：" + error.response.data.code + "\n" +
                error.response.data.msg,
                type: "error"
            });
        }
        return Promise.reject(error);
    }
);

//统一的提交前处理
axios.interceptors.request.use(data => {
        //data.url = "/api"+data.url;
        // data.headers['Authorization'] = localStorage.getItem('token');
      //  console.log(data);
        if (data.headers['Content-Type'] == 'application/x-www-form-urlencoded') {
            let detKey = [];
            data.data.forEach((v, k) => {
                console.log(k + ":" + v);
                if (v === '' || v === null || v === undefined || v === ' ' || v === 'null' || v === 'undefined') {
                    detKey.push(k);
                }
            })
            detKey.forEach(value => {
                data.data.delete(value);
            })
        } else {
            deleteEmptyProperty(data.data);
        }

        return data;
    },
    error => {
        return error;
    }
)


function deleteEmptyProperty(object) {
    for (var i in object) {
        var value = object[i];
        if (typeof value === 'object') {
            if (Array.isArray(value)) {
                if (value.length == 0) {
                    delete object[i];
                    continue;
                }
            }
            deleteEmptyProperty(value);
            if (isEmpty(value)) {
                delete object[i];
            }
        } else {
            if (value === '' || value === null || value === undefined || value === ' ' || value === 'null' || value === 'undefined') {
                delete object[i];
            }
        }
    }
}

function isEmpty(object) {
    for (var name in object) {
        return false;
    }
    return true;
}


export default axios;
