/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.in.chat;

/**
 *
 * @author Admin
 */

import java.util.*;
public class ChatRoomList {

    private Map roomList;

    public synchronized void addRoom(ChatRoom chatroom)
    {
        roomList.put(chatroom.getName(), chatroom);
    }

    public synchronized void removeRoom(String s)
    {
        roomList.remove(s);
    }

    public ChatRoom getRoom(String s)
    {
        return (ChatRoom)roomList.get(s);
    }

    public ChatRoom getRoomOfChatter(String s)
    {
        ChatRoom achatroom[] = getRoomListArray();
        for(int i = 0; i < achatroom.length; i++)
        {
            boolean flag = achatroom[i].chatterExists(s);
            if(flag)
            {
                return achatroom[i];
            }
        }

        return null;
    }

    public Set getRoomList()
    {
        return roomList.entrySet();
    }

    public ChatRoom[] getRoomListArray()
    {
        ChatRoom achatroom[] = new ChatRoom[roomList.size()];
        Set set = getRoomList();
        Iterator iterator = set.iterator();
        for(int i = 0; iterator.hasNext(); i++)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            String s = (String)entry.getKey();
            achatroom[i] = (ChatRoom)entry.getValue();
        }

        return achatroom;
    }

    public boolean chatterExists(String s)
    {
        boolean flag = false;
        ChatRoom achatroom[] = getRoomListArray();
        for(int i = 0; i < achatroom.length; i++)
        {
            flag = achatroom[i].chatterExists(s);
            if(flag)
            {
                break;
            }
        }

        return flag;
    }

    public ChatRoomList()
    {
        roomList = new HashMap();
    }
}
