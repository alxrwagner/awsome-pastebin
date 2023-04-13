package com.example.awsomepastebin.projection;

import com.example.awsomepastebin.dto.PastDTO;

public interface PastTitleAndBodyView {
    String getTitle();
    String getBody();

    default PastDTO to(){
        PastDTO pastDTO = new PastDTO();
        pastDTO.setTitle(getTitle());
        pastDTO.setBody(getBody());
        return pastDTO;
    }
}
