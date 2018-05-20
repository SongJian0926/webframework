package cn.wycode.web.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * 剪切板表
 */
@Entity
public class Clipboard {
    @Id
    private Long id;
    private Date createDate;
    private Date lastUpdate;

    private String content;


    public Clipboard(Date createDate, Date lastUpdate, String content) {
        this.createDate = createDate;
        this.lastUpdate = lastUpdate;
        this.content = content;
    }

    public Clipboard() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
