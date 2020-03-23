package online.luokd.vo;

import java.io.Serializable;

public class WxRetMsgVo implements Serializable {
    private static final long serialVersionUID = -1238293895619852560L;
    private String errcode;
    private String errmsg;
    private String msgid;

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }
}
