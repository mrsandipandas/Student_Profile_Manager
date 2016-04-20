/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.in.message;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author essndds
 */
@XmlRootElement(name="chatMessages")
@XmlType(propOrder = { "chatId", "senderName", "receiverName", "message", "isTyping" })
public class ChatMessage {
    private String senderName;
    private String receiverName;
    private String message;
    private String chatId;
    private boolean isTyping = false;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public boolean isIsTyping() {
        return isTyping;
    }

    public void setIsTyping(boolean isTyping) {
        this.isTyping = isTyping;
    }
    
}
