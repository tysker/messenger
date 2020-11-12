package dk.oertel.service;

import dk.oertel.database.DatabaseClass;
import dk.oertel.model.Comment;
import dk.oertel.model.ErrorMessage;
import dk.oertel.model.Message;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommentService {

    private Map<Long, Message> messages = DatabaseClass.getMessages();

    public List<Comment> getAllComments(long messageId) {
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        return new ArrayList<Comment>(comments.values());
    }

    //    public Comment getComment(long messageId, long commentId) {
//        Map<Long, Comment> comments = messages.get(messageId).getComments();
//        return comments.get(commentId);
//    }
    public Comment getComment(long messageId, long commentId) {
        ErrorMessage errorMessage = new ErrorMessage("Not found", 404, "http://localhost:8080/messenger/");
        Response response = Response.status(Response.Status.NOT_FOUND)
                .entity(errorMessage)
                .build();
        Message message = messages.get(messageId);
        if(message == null) {
            throw new WebApplicationException(response);
        }
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        Comment comment = comments.get(commentId);
        if(comment == null) {
            throw new WebApplicationException(response);
        }
        return comment;
    }

    public Comment addComment(long messageId, Comment comment) {
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        comment.setId(comments.size() + 1L);
        comments.put(comment.getId(), comment);
        return comment;
    }

    public Comment updateComment(long messageId, Comment comment) {
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        if (comment.getId() <= 0) {
            return null;
        }
        comments.put(comment.getId(), comment);
        return comment;
    }

    public Comment removeComment(long messageId, long commentId) {
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        return comments.remove(commentId);
    }

}
