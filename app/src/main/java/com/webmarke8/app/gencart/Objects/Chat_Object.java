package com.webmarke8.app.gencart.Objects;

import java.io.Serializable;

/**
 * Created by GeeksEra on 2/24/2018.
 */

public class Chat_Object implements Serializable {


    private String SenderEmail, SenderName, SendTime;
    private String ReciverEmail, ReciverName;
    private String Message;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getSenderEmail() {
        return SenderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        SenderEmail = senderEmail;
    }

    public String getSenderName() {
        return SenderName;
    }

    public void setSenderName(String senderName) {
        SenderName = senderName;
    }

    public String getSendTime() {
        return SendTime;
    }

    public void setSendTime(String sendTime) {
        SendTime = sendTime;
    }

    public String getReciverEmail() {
        return ReciverEmail;
    }

    public void setReciverEmail(String reciverEmail) {
        ReciverEmail = reciverEmail;
    }

    public String getReciverName() {
        return ReciverName;
    }

    public void setReciverName(String reciverName) {
        ReciverName = reciverName;
    }
}
