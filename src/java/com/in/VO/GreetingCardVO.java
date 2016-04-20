/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.in.VO;

import java.util.Date;

/**
 *
 * @author sada3260
 */
public class GreetingCardVO {

    private String ecardId;
    private String greetingId;
    private String greetingSenderName;
    private String greetingSenderEmail;
    private String greetingReceiverName;
    private String greetingReceiverEmail;
    private String greetingMessage;
    private String greeting_isVisited;
    private String greetingRandomNo;
    private String greetingFormat;
    private String greetingCardType;
    private String greetingBackgroundColor;
    private Date greetingSendDate;
    private Date greetingCreateDate;

    public String getEcardId() {
        return ecardId;
    }

    public void setEcardId(String ecardId) {
        this.ecardId = ecardId;
    }

    public Date getGreetingCreateDate() {
        return greetingCreateDate;
    }

    public void setGreetingCreateDate(Date greetingCreateDate) {
        this.greetingCreateDate = greetingCreateDate;
    }

    public String getGreetingCardType() {
        return greetingCardType;
    }

    public void setGreetingCardType(String greetingCardType) {
        this.greetingCardType = greetingCardType;
    }

    public String getGreetingFormat() {
        return greetingFormat;
    }

    public void setGreetingFormat(String greetingFormat) {
        this.greetingFormat = greetingFormat;
    }

    public String getGreetingId() {
        return greetingId;
    }

    public void setGreetingId(String greetingId) {
        this.greetingId = greetingId;
    }

    public String getGreetingMessage() {
        return greetingMessage;
    }

    public void setGreetingMessage(String greetingMessage) {
        this.greetingMessage = greetingMessage;
    }

    public String getGreetingRandomNo() {
        return greetingRandomNo;
    }

    public void setGreetingRandomNo(String greetingRandomNo) {
        this.greetingRandomNo = greetingRandomNo;
    }

    public String getGreetingReceiverEmail() {
        return greetingReceiverEmail;
    }

    public void setGreetingReceiverEmail(String greetingReceiverEmail) {
        this.greetingReceiverEmail = greetingReceiverEmail;
    }

    public String getGreetingReceiverName() {
        return greetingReceiverName;
    }

    public void setGreetingReceiverName(String greetingReceiverName) {
        this.greetingReceiverName = greetingReceiverName;
    }

    public Date getGreetingSendDate() {
        return greetingSendDate;
    }

    public void setGreetingSendDate(Date greetingSendDate) {
        this.greetingSendDate = greetingSendDate;
    }

    public String getGreetingSenderEmail() {
        return greetingSenderEmail;
    }

    public void setGreetingSenderEmail(String greetingSenderEmail) {
        this.greetingSenderEmail = greetingSenderEmail;
    }

    public String getGreetingSenderName() {
        return greetingSenderName;
    }

    public void setGreetingSenderName(String greetingSenderName) {
        this.greetingSenderName = greetingSenderName;
    }

    public String getGreeting_isVisited() {
        return greeting_isVisited;
    }

    public void setGreeting_isVisited(String greeting_isVisited) {
        this.greeting_isVisited = greeting_isVisited;
    }

    public String getGreetingBackgroundColor() {
        return greetingBackgroundColor;
    }

    public void setGreetingBackgroundColor(String greetingBackgroundColor) {
        this.greetingBackgroundColor = greetingBackgroundColor;
    }
    
}
