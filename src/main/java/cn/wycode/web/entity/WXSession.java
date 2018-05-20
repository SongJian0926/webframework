package cn.wycode.web.entity;

/**
 * 微信session
 * 以code换取 用户唯一标识openid 和 会话密钥session_key。
 */
public class WXSession {

    public String openid; //用户唯一标识
    public String session_key; //会话密钥
    public String unionid; //用户在开放平台的唯一标识符
    public int errcode;//40029
    public String errmsg;// invalid code


    public WXSession() {
    }

    @Override
    public String toString() {
        return "WXSession{" +
                "openid='" + openid + '\'' +
                ", session_key='" + session_key + '\'' +
                ", unionid='" + unionid + '\'' +
                ", errcode=" + errcode +
                ", errmsg='" + errmsg + '\'' +
                '}';
    }
}
