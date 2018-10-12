package com.kuliao.auto.domain;


import java.util.ArrayList;
import java.util.List;

/**
 * @author 董文强
 * @version 1.0
 * @date 2018年10月10日
 */
public class Menu {

    private String icon;
    private String index;
    private String title;
    private List<Menu> subs;
    private Integer sort;

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Menu> getSubs(Boolean flag) {
        if (this.subs == null) {
            this.subs = new ArrayList<>();
        }
        return this.subs;
    }
    public List<Menu> getSubs() {
        return this.subs;
    }
    public void setSubs(List<Menu> subs) {
        this.subs = subs;
    }
}
