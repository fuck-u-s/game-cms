package com.cms.model.sys;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 方法描述:把菜单以树形显示出来
 *
 * @author 小刘
 * @version v1.0
 * @date 2015/10/25
 */
public class TreeObject implements Serializable{
    private long id;
    private long parent_id;
    private String name;
    private String parentName;
    private String permission;
    private String url;
    private Integer sort;
    private String type;
    private String description;
    private String icon;
    private Integer ishide;
    //新增字段checked,表示是否选中
    private boolean checked = false;
    private boolean open=false;
    private List<TreeObject> children = new ArrayList<TreeObject>();
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public long getParent_id() {
        return parent_id;
    }

    public void setParent_id(long parent_id) {
        this.parent_id = parent_id;
    }

    public List<TreeObject> getChildren() {
        return children;
    }
    public void setChildren(List<TreeObject> children) {
        this.children = children;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getParentName() {
        return parentName;
    }
    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getIshide() {
        return ishide;
    }
    public void setIshide(Integer ishide) {
        this.ishide = ishide;
    }
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public Boolean getChecked() {
        return checked;
    }
    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
