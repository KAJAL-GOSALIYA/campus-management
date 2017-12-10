package com.android.collegemanagementsystem;

/**
 * Created by Dell on 02-07-2016.
 */

public class MessageModel {

    public MessageModel() {
    }

    String textName,textNumber,textEmail;
    int messageID;
    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public MessageModel(int messageID, String textEmail, String textName, String textNumber) {
        this.messageID = messageID;
        this.textEmail = textEmail;
        this.textName = textName;
        this.textNumber = textNumber;
    }

    public String getTextEmail() {

        return textEmail;
    }

    public void setTextEmail(String textEmail) {
        this.textEmail = textEmail;
    }

    public String getTextName() {
        return textName;
    }

    public void setTextName(String textName) {
        this.textName = textName;
    }

    public String getTextNumber() {
        return textNumber;
    }

    public void setTextNumber(String textNumber) {
        this.textNumber = textNumber;
    }
}
