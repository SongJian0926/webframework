package cn.wycode.web.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class FishSuggest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date createDate;
    private String content;
    private String contact;

    public FishSuggest() {
    }

    public FishSuggest(Date createDate, String content, String contact) {
        this.createDate = createDate;
        this.content = content;
        this.contact = contact;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
