package dikampus.com.aprianil_1202150082_modul6.model;

/**
 * Created by MOTION-2 on 01/04/2018.
 */

public class Post {
    String id;
    private String userID;
    private String username;
    private String titlePost;
    private String post;
    private String image;
    private long timestamp;

    public Post(){}

    public Post(String id, String userID, String username, String titlePost, String post, String image, long timestamp) {
        this.id = id;
        this.userID = userID;
        this.username = username;
        this.titlePost = titlePost;
        this.post = post;
        this.image = image;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitlePost() {
        return titlePost;
    }

    public void setTitlePost(String titlePost) {
        this.titlePost = titlePost;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
