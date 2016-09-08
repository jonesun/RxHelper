package jone.helper.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * "_id": "5767931d421aa94f3da6d715",
 "createdAt": "2016-06-20T14:54:21.921Z",
 "desc": "\u5728\u5e03\u5c40\u91cc\u6dfb\u52a0\u81ea\u5b9a\u4e49\u5b57\u4f53",
 "publishedAt": "2016-06-21T12:00:17.657Z",
 "source": "chrome",
 "type": "Android",
 "url": "https://github.com/daniribalbert/CustomFontLib",
 "used": true,
 "who": "\u848b\u670b"
 * Created by jone.sun on 2016/6/21.
 */

public class GankIoEntity implements Serializable{
    @SerializedName("_id") private String id;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }
}
