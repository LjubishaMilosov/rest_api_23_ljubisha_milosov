package testingClickupApp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Comment {


    @JsonProperty("comment_text")
    private String comment_text;

    @JsonProperty("commentId")
    private String commentId;

    public String getCommentText(){
        return comment_text;
    }
    public String getId(){
        return commentId;
    }


}
