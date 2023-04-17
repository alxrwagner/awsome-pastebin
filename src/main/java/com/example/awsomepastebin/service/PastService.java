package com.example.awsomepastebin.service;

import com.example.awsomepastebin.dto.CreatePastDTO;
import com.example.awsomepastebin.dto.LinkDTO;
import com.example.awsomepastebin.dto.PastDTO;
import com.example.awsomepastebin.dto.PastInfo;
import com.example.awsomepastebin.enums.Status;
import com.example.awsomepastebin.exception.IncorrectParamException;
import com.example.awsomepastebin.exception.PastNotFoundException;
import com.example.awsomepastebin.model.Past;
import com.example.awsomepastebin.repository.PastRepos;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class PastService {

    private final PastRepos pastRepos;
    private final String alphabet;

    public PastService(PastRepos pastRepos) {
        this.pastRepos = pastRepos;
        this.alphabet = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890!@#$%&";
    }

    public LinkDTO save(CreatePastDTO createPastDTO) {
        Past past = createPastDTO.to();
        past.setDateCreate(Instant.now());
        past.setStatus(createPastDTO.getStatus());
        past.setExpiryDate(createPastDTO.getExpiryDate().getDate());
        past.setId(generateId(8));
        createPastDTO.setLink("/my-awesome-pastebin.tld/" + past.getId());
        pastRepos.save(past);
        return LinkDTO.fromCreatePastDTO(createPastDTO);
    }

    public List<PastInfo> getTenLast() {

        return pastRepos.findTop10ByStatusAndExpiryDateIsAfterOrderByDateCreateDesc(Status.PUBLIC, Instant.now()).stream().map(PastInfo::from).collect(Collectors.toList());
    }

    public PastDTO getById(String id){
        if(id==null){
            throw new IncorrectParamException();
        }
        Past past = pastRepos.findPastById(id);
        if (past == null){
            throw new PastNotFoundException();
        }
        return PastDTO.from(past);
    }

    public List<PastDTO> search(String title, String body) {
            return pastRepos.findAllByTitleOrBody(Status.PUBLIC, title, body)
                    .stream()
                    .map(PastDTO::from)
                    .collect(Collectors.toList());
    }

    private String generateId(int lengthLink){
        Random random = new Random();
        StringBuilder id = new StringBuilder();

        for(int i = 0; i < lengthLink; i++){
            id.append(alphabet.toCharArray()[random.nextInt(alphabet.length())]);
        }
        return id.toString();
    }
}
