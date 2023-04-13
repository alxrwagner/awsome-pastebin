package com.example.awsomepastebin.dto;

import com.example.awsomepastebin.model.Past;
import lombok.Data;

@Data
public class PastInfo {
    private String title;
    private String link;

    public static PastInfo from(Past past){
        PastInfo pastInfo = new PastInfo();
        pastInfo.setTitle(past.getTitle());
        pastInfo.setLink("/my-awesome-pastebin.tld/" + past.getId());
        return pastInfo;
    }
}
