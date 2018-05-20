package cn.wycode.web.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class FishBaike {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String type; //类别：金鱼 海水鱼 设备。。
    private int readCount; //阅读量
    private String title; //标题
    private String detail; //详情，markdow地址，xxx.md 放在/upload/fish/下
    private Date createDate; //创建时间，备用
    private String imageName; // xxx.png 放在/upload/fish/下

    public FishBaike() {
    }

    public FishBaike(String type, String title, String detail, String imageName, Date createDate) {
        this.type = type;
        this.title = title;
        this.detail = detail;
        this.createDate = createDate;
        this.imageName = imageName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getReadCount() {
        return readCount;
    }

    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
