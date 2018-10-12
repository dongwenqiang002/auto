package com.kuliao.auto.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 董文强
 * @version 1.0
 * @date 2018年10月10日
 */
public class Router {
    private String path;
    private String redirect;
    private String component;
    private Map<String,String> meta;
    private List<Router> children;

    public Map<String,String> getMeta() {
        if(this.meta == null){
            this.meta = new HashMap<>();
        }
        return meta;
    }

    public void setMeta(Map<String,String> meta) {
        this.meta = meta;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }



    public List<Router> getChildren() {
        return this.children;
    }

    public List<Router> getChildren(Boolean flag) {
        if(this.children == null){
            this.children = new ArrayList<>();
        }
        return this.children;
    }
    public void setChildren(List<Router> children) {
        this.children = children;
    }
}
