package com.example.awsomepastebin.projection;

import com.example.awsomepastebin.dto.PastInfo;

public interface LinkAndTitleView {
    String getTitle();
    String getId();

    default PastInfo toPastInfo(){
        PastInfo pastInfo = new PastInfo();
        pastInfo.setTitle(getTitle());
        pastInfo.setLink("/my-awesome-pastebin.tld/" + getId());
        return pastInfo;
    }
}
