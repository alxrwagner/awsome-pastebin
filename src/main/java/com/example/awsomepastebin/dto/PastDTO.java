package com.example.awsomepastebin.dto;

import com.example.awsomepastebin.model.Past;
import lombok.Data;

@Data
public class PastDTO {
    private String title;
    private String body;

    public static PastDTO from(Past past){
        PastDTO pastDTO = new PastDTO();
        pastDTO.setTitle(past.getTitle());
        pastDTO.setBody(past.getBody());
        return pastDTO;
    }
}
