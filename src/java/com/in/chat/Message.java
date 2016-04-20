/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.in.chat;

/**
 *
 * @author Admin
 */
public class Message {

    private String chatterName;
    private String message;
    private long timeStamp;

    public String getChatterName()
    {
        return chatterName;
    }

    public String getMessage()
    {
        return message;
    }

    public long getTimeStamp()
    {
        return timeStamp;
    }

    private final void _mththis()
    {
        chatterName = null;
        message = null;
    }

    public Message(String s, String s1, long l)
    {
        _mththis();
        chatterName = s;
        message = s1;
        timeStamp = l;
    }
}
