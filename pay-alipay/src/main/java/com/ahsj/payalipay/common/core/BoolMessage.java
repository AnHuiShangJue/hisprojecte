package com.ahsj.payalipay.common.core;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import core.entity.BaseEntity;
import core.message.AbstractMessage;

import java.util.List;

public class BoolMessage extends AbstractMessage {
    private boolean success;
    private String message;
    private BaseEntity record;
    private List<?> records;

    public List<?> getRecords() {
        return this.records;
    }

    public void setRecords(List<?> records) {
        this.records = records;
    }

    public BaseEntity getRecord() {
        return this.record;
    }

    public void setRecord(BaseEntity record) {
        this.record = record;
    }

    public BoolMessage() {
    }

    public BoolMessage(JSONObject json) {
        this.setJson(json);
    }

    public BoolMessage(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public JSONObject buildJSON() {
        this.setJson(this.currentJson());

        try {
            this.getJson().put("message", this.message);
            this.getJson().put("success", this.success);
        } catch (JSONException var2) {
            var2.printStackTrace();
        }

        return this.currentJson();
    }

    public void putInJSON(JSONObject record) {
        try {
            record.put("message", this.message);
            record.put("success", this.success);
        } catch (JSONException var3) {
            var3.printStackTrace();
        }

    }

    public void putMessage(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            BoolMessage that = (BoolMessage)o;
            return this.success == that.success;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return this.success ? 1 : 0;
    }
}
