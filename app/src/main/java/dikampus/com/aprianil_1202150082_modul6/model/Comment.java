package dikampus.com.aprianil_1202150082_modul6.model;

/**
 * Created by MOTION-2 on 01/04/2018.
 */

public class Comment {
    String id;
    String username;
    String comment;
    long timestamp;

    public Comment(){}

    public Comment(String id, String username, String comment, long timestamp) {
        this.id = id;
        this.username = username;
        this.comment = comment;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
