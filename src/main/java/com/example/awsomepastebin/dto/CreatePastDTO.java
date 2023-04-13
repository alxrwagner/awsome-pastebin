package com.example.awsomepastebin.dto;

import com.example.awsomepastebin.enums.ExpiryDate;
import com.example.awsomepastebin.enums.Status;
import com.example.awsomepastebin.model.Past;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class CreatePastDTO {
    private String title;
    private String body;
    @JsonIgnore
    private String link;

//    public static CreatePastDTO from(Past past){
//        CreatePastDTO = new CreatePastDTO();
//        createPastDTO.setStatus(past.getStatus());
//        createPastDTO.setTitle(past.getTitle());
//        createPastDTO.setBody(past.getBody());
//        createPastDTO.setExpiryDate(ExpiryDate.valueOf(past.getExpiryDate().toString()));
//        return createPastDTO;
//    }

    public Past to(){
        Past past = new Past();
        past.setTitle(getTitle());
        past.setBody(getBody());
        return past;
    }
}
