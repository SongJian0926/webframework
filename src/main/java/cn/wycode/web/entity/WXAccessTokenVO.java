package cn.wycode.web.entity;

public class WXAccessTokenVO {

    private String access_token;
    private String expires_in;
    private long createTimeMills;

    public WXAccessTokenVO() {
    }


    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public long getCreateTimeMills() {
        return createTimeMills;
    }

    public void setCreateTimeMills(long createTimeMills) {
        this.createTimeMills = createTimeMills;
    }

    @Override
    public String toString() {
        return "WXAccessTokenVO{" +
                "access_token='" + access_token + '\'' +
                ", expires_in='" + expires_in + '\'' +
                ", createTimeMills=" + createTimeMills +
                '}';
    }
}
