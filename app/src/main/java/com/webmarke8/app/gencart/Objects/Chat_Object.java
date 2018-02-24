package com.webmarke8.app.gencart.Objects;

import java.io.Serializable;

/**
 * Created by GeeksEra on 2/24/2018.
 */

public class Chat_Object implements Serializable {
    String sendermailid, message, receciveremailid, rececieverusername;

    public Chat_Object() {
    }

    public Chat_Object(String sendermailid, String message, String receciveremailid, String rececieverusername) {
        this.sendermailid = sendermailid;
        this.message = message;
        this.receciveremailid = receciveremailid;
        this.rececieverusername = rececieverusername;
    }

    public String getSendermailid() {
        return sendermailid;
    }

    public void setSendermailid(String sendermailid) {
        this.sendermailid = sendermailid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRececiveremailid() {
        return receciveremailid;
    }

    public void setRececiveremailid(String receciveremailid) {
        this.receciveremailid = receciveremailid;
    }

    public String getRececieverusername() {
        return rececieverusername;
    }

    public void setRececieverusername(String rececieverusername) {
        this.rececieverusername = rececieverusername;
    }
}
