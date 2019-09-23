package com.ahsj.baseuserinfo.entity;

import java.io.Serializable;

public class TreeEntity implements Serializable {
    private static final long serialVersionUID = 8350572296216436848L;
    private String id;
    private String pId;
    private String name;
    private boolean isParent;
    private boolean open;
    private boolean checked;
    private boolean chkDisabled = false;

    public TreeEntity() {
    }

    public boolean isChkDisabled() {
        return this.chkDisabled;
    }

    public void setChkDisabled(boolean chkDisabled) {
        this.chkDisabled = chkDisabled;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpId() {
        return this.pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIsParent() {
        return this.isParent;
    }

    public void setIsParent(boolean isParent) {
        this.isParent = isParent;
    }

    public boolean isOpen() {
        return this.open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isChecked() {
        return this.checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}

