package dk.oertel.resources;

import dk.oertel.model.Message;
import dk.oertel.service.MessageService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

    MessageService messageService = new MessageService();

    @GET
    public List<Message> getMessages() { return messageService.getAllMessages();}

    @GET
    @Path("/")
    public List<Message> getMessage(@BeanParam MessageFilterBean filterBean) {
        if(filterBean.getYear() > 0)
        {
            return messageService.getAllMessagesForYear(filterBean.getYear());
        }
        if(filterBean.getStart() > 0 && filterBean.getSize() > 0)
        {
            return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
        }
        return messageService.getAllMessages();
    }


//    @GET
//    @Path("/year/{year}")
//    public List<Message> getMessagesByYear(@PathParam("year") int year)
//    {
//        return messageService.getAllMessagesForYear(year);
//    }
//
//    @GET
//    @Path("/{start}/{size}")
//    public List<Message> getMessagesBySize(@PathParam("start") int start, @PathParam("size") int size)
//    {
//        return messageService.getAllMessagesPaginated(start, size);
//    }

    @GET
    @Path("/{messageId}")
    public Message getMessage(@PathParam("messageId") long messageId) {
        return messageService.getMessage(messageId);
    }

    @POST
    public Message addMessage(Message message) {
        return messageService.addMessage(message);
    }

    @PUT
    @Path("/{messageId}")
    public Message updateMessage(@PathParam("messageId") long messageId, Message message) {
        message.setId(messageId);
        return messageService.updateMessage(message);
    }

    @DELETE
    @Path("/{messageId}")
    public void deleteMessage(@PathParam("messageId") long messageId) {
        messageService.removeMessage(messageId);
    }

    // Subresources

    @Path("/{messageId}/comments")
    public CommentResource getCommentResource() {
        return new CommentResource();
    }

}
