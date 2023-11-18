package testingClickupApp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)

public class Folder {

    @JsonProperty("name")
    private String name;

    @JsonProperty("id")
    private String folderId;

    public String getName(){
        return name;
    }
    public String getId(){
        return folderId;
    }

}
