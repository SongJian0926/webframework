package cn.wycode.web.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class FishQuestionAnswer {

    @Id
    @GeneratedValue
    private Long id;

    private String content;

    private Date createTime;

    private int up;
    private int down;
    private int value;

    @ManyToOne
    private FishQuestion question;

    @ManyToOne
    private FishUser user;

    public FishQuestionAnswer() {
    }

    public FishQuestionAnswer(String content, FishQuestion question, FishUser user) {
        this.content = content;
        this.question = question;
        this.user = user;
        this.createTime = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getUp() {
        return up;
    }

    public void setUp(int up) {
        this.up = up;
    }

    public int getDown() {
        return down;
    }

    public void setDown(int down) {
        this.down = down;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public FishQuestion getQuestion() {
        return question;
    }

    public void setQuestion(FishQuestion question) {
        this.question = question;
    }

    public FishUser getUser() {
        return user;
    }

    public void setUser(FishUser user) {
        this.user = user;
    }

    public void up() {
        this.up++;
        this.value++;
    }

    public void down() {
        this.down++;
        this.value--;
    }

}
