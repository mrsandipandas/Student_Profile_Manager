/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.in.resources;

import com.in.message.ChatMessage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
/**
 *
 * @author essndds
 */
@Path("/chat")
public class IMResource {
    public static HashMap chatStore = new HashMap();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/sendingMessage")
    public Response sendMessage(
            @QueryParam("sender") String sender,
            @QueryParam("receiver") String receiver,
            @QueryParam("message") String message) {
        storeMessages(sender, receiver, message);
//        System.out.println(chatMsg.getSenderName()+" : "+chatMsg.getMessage()+" : "+chatMsg.getReceiverName());
        //return Response.status(Response.Status.ACCEPTED).entity(chatMsg).build();
        return Response.status(Response.Status.ACCEPTED).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/typingMessage")
    public Response typingMessage(
            @QueryParam("sender") String sender,
            @QueryParam("receiver") String receiver,
            @QueryParam("isTyping") String isTyping) {
        storeTypingMessages(sender, receiver, isTyping);
        return Response.status(Response.Status.OK).entity(isTyping).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/incomingMessage")
    public Response receiveMessage(@QueryParam("receiver") String receiver) {
        List<ChatMessage> messages = retrieveMessages(receiver);
        if(null!=messages && messages.size()>0){
          GenericEntity entity = new GenericEntity<List<ChatMessage>>(messages) {};
            return Response.status(Response.Status.OK).entity(entity).build();
        }
        else{
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }

    private void storeTypingMessages(String sender,String receiver,String isTyping){
        synchronized(this){
            if(chatStore.containsKey(receiver)){
                String msg = (String) chatStore.get(receiver);
                chatStore.remove(receiver);
                chatStore.put(receiver, msg+"~:#:~"+sender+"#:~:#"+"<~::<"+isTyping+">::~>");
            }
            else{
                chatStore.put(receiver, sender+"#:~:#"+"<~::<"+isTyping+">::~>");
            }
        }
    }

    private void storeMessages(String sender,String receiver,String message){
        synchronized(this){
            if(chatStore.containsKey(receiver)){
                String msg = (String) chatStore.get(receiver);
                chatStore.remove(receiver);
                chatStore.put(receiver, msg+"~:#:~"+sender+"#:~:#"+message);
            }
            else{
                chatStore.put(receiver, sender+"#:~:#"+message);
            }
        }
    }

    private List<ChatMessage> retrieveMessages(String receiver){
        List<ChatMessage> messages = new ArrayList<ChatMessage>();
        synchronized(this){
            if(chatStore.containsKey(receiver)){
                String msg = (String) chatStore.get(receiver);
                chatStore.remove(receiver);
                
                String chat[] = msg.split("~:#:~");
                for(int i=0;i<chat.length;i++){
                    ChatMessage chmsg = new ChatMessage();
                    String details[] = chat[i].split("#:~:#");
                    
                    chmsg.setSenderName(details[0]);
                    if("<~::<true>::~>".equalsIgnoreCase(details[1])){
                        chmsg.setMessage("");
                        chmsg.setIsTyping(true);
                    }
                    else if("<~::<false>::~>".equalsIgnoreCase(details[1])){
                        chmsg.setMessage("");
                    }
                    else{
                        chmsg.setMessage(details[1]);
                    }
                    
                    chmsg.setReceiverName(receiver);
                    messages.add(chmsg);
                }
            }
        }
        return messages;
    }
}
