package dk.oertel.resources;

import dk.oertel.model.Message;
import dk.oertel.service.MessageService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.BeanParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

    MessageService messageService = new MessageService();

    @GET
    public List<Message> getMessages() {
        return messageService.getAllMessages();
    }

    @GET
    @Path("/")
    public List<Message> getMessage(@BeanParam MessageFilterBean filterBean) {
        if (filterBean.getYear() > 0) {
            return messageService.getAllMessagesForYear(filterBean.getYear());
        }
        if (filterBean.getStart() > 0 && filterBean.getSize() > 0) {
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

//    @GET
//    @Path("/{messageId}")
//    public Message getMessage(@PathParam("messageId") long messageId) {
//        return messageService.getMessage(messageId);
//    }

    @GET
    @Path("/{messageId}")
    public Response getMessage(@PathParam("messageId") long messageId) {
        Message message = messageService.getMessage(messageId);
        return Response.status(Response.Status.OK)
                .entity(message)
                .build();
    }

//    @POST
//    public Message addMessage(Message message) {
//        return messageService.addMessage(message);
//    }

    @POST
    public Response addMessage(Message message, @Context UriInfo uriInfo) {
        Message newMessage = messageService.addMessage(message);
        String newId = String.valueOf(newMessage.getId());
        URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
        //return Response.status(Response.Status.CREATED)
        //return Response.created(new URI("/messenger/webapi/messages/" + newMessage.getId()))
        return Response.created(uri)
                .entity(newMessage)
                .build();
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
