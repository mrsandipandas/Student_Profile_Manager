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
public class ChatRoom {

    private String name;
    private String description;
    private Map chatters;
    private List messages;
    private int messages_size;

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public synchronized void addChatter(Chatter chatter)
    {
        chatters.put(chatter.getName(), chatter);
    }

    public synchronized Object removeChatter(String s)
    {
        return chatters.remove(s);
    }

    public Chatter getChatter(String s)
    {
        return (Chatter)chatters.get(s);
    }

    public boolean chatterExists(String s)
    {
        return chatters.containsKey(s);
    }

    public int getNoOfChatters()
    {
        return chatters.size();
    }

    public Set getChatters()
    {
        return chatters.entrySet();
    }

    public Chatter[] getChattersArray()
    {
        Chatter achatter[] = new Chatter[chatters.size()];
        Set set = getChatters();
        Iterator iterator = set.iterator();
        for(int i = 0; iterator.hasNext(); i++)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            String s = (String)entry.getKey();
            achatter[i] = (Chatter)entry.getValue();
        }

        return achatter;
    }

    public synchronized void addMessage(Message message)
    {
        if(messages.size() == messages_size)
        {
            ((LinkedList)messages).removeFirst();
        }
        messages.add(message);
    }

    public ListIterator getMessages()
    {
        return messages.listIterator();
    }

    public Message[] getMessages(long l)
    {
        ListIterator listiterator = messages.listIterator();
        ArrayList arraylist = new ArrayList();
        while(listiterator.hasNext())
        {
            Message message = (Message)listiterator.next();
            if(message.getTimeStamp() >= l)
            {
                arraylist.add(message);
            }
        }
        Object aobj[] = arraylist.toArray();
        Message amessage[] = new Message[aobj.length];
        for(int i = 0; i < amessage.length; i++)
        {
            amessage[i] = (Message)aobj[i];
        }

        return amessage;
    }

    public int getNoOfMessages()
    {
        return messages.size();
    }

    public void setMaximumNoOfMessages(int i)
    {
        messages_size = i;
    }

    public int getMaxiumNoOfMessages()
    {
        return messages_size;
    }

    private final void _mththis()
    {
        name = null;
        description = null;
        chatters = new HashMap();
        messages = new LinkedList();
        messages_size = 25;
    }

    public ChatRoom(String s, String s1)
    {
        _mththis();
        name = s;
        description = s1;
    }
}
