package cn.wycode.web.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
public class FishAnswerUpDownRecord {

    @Id
    @GeneratedValue
    private Long id;

    private Date createTime;
    private Date updateTime;

    private int up;
    private int down;

    @OneToOne
    private FishUser user;
    @OneToOne
    private FishQuestionAnswer answer;

    public FishAnswerUpDownRecord() {
    }

    public FishAnswerUpDownRecord(int up, int down, FishUser user, FishQuestionAnswer answer) {
        this.up = up;
        this.down = down;
        this.user = user;
        this.answer = answer;
        this.createTime = new Date();
        this.updateTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    public FishUser getUser() {
        return user;
    }

    public void setUser(FishUser user) {
        this.user = user;
    }

    public FishQuestionAnswer getAnswer() {
        return answer;
    }

    public void setAnswer(FishQuestionAnswer answer) {
        this.answer = answer;
    }
}
