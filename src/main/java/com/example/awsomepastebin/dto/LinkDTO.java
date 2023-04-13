package com.example.awsomepastebin.dto;

import lombok.Data;

@Data
public class LinkDTO {
    private String link;

    public static LinkDTO fromCreatePastDTO(CreatePastDTO createPastDTO){
        LinkDTO linkDTO = new LinkDTO();
        linkDTO.setLink(createPastDTO.getLink());
        return linkDTO;
    }
}
