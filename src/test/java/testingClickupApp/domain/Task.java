package testingClickupApp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)


public class Task {

    @JsonProperty("name")
    private String name;

    @JsonProperty("id")
    private String id;

    @JsonProperty("err")
    private String err;

    public String getName(){
        return name;
    }
    public String getId(){
        return id;
    }
    public String getErr(){
        return err;
    }

}
