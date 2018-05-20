package cn.wycode.web.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class FishQuestion {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String content;

    @ElementCollection
    private List<String> images;

    private Date createTime;
    private Date updateTime;
    @ManyToOne
    private FishUser user;

    public FishQuestion() {
    }

    public FishQuestion(String title, String content, FishUser user, List<String> images) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.images = images;
        this.createTime = new Date();
        this.updateTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public FishUser getUser() {
        return user;
    }

    public void setUser(FishUser user) {
        this.user = user;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
