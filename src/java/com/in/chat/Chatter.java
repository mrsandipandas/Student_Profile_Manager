/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.in.chat;

/**
 *
 * @author Admin
 */
public class Chatter {

    private String name;
    private String sex;
    private String comment;
    private String email;
    private long loginTime;
    private long enteredInRoomAt;
    private int age;

    public String getName()
    {
        return name;
    }

    public String getSex()
    {
        return sex;
    }

    public void setComment(String s)
    {
        comment = s;
    }

    public String getComment()
    {
        return comment;
    }

    public void setAge(int i)
    {
        age = i;
    }

    public int getAge()
    {
        return age;
    }

    public void setEmail(String s)
    {
        email = s;
    }

    public String getEmail()
    {
        return email;
    }

    public long getLoginTime()
    {
        return loginTime;
    }

    public void setEnteredInRoomAt(long l)
    {
        enteredInRoomAt = l;
    }

    public long getEnteredInRoomAt()
    {
        return enteredInRoomAt;
    }

    private final void _mththis()
    {
        name = null;
        sex = null;
        comment = null;
        email = null;
        loginTime = -1;
        enteredInRoomAt = -1;
        age = -1;
    }

    public Chatter(String s, String s1, long l)
    {
        _mththis();
        name = s;
        sex = s1;
        loginTime = l;
    }
}
