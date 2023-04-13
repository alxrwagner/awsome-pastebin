package com.example.awsomepastebin.service;

import com.example.awsomepastebin.dto.CreatePastDTO;
import com.example.awsomepastebin.dto.PastDTO;
import com.example.awsomepastebin.dto.LinkDTO;
import com.example.awsomepastebin.dto.PastInfo;
import com.example.awsomepastebin.enums.ExpiryDate;
import com.example.awsomepastebin.enums.Status;
import com.example.awsomepastebin.exception.IncorrectArgumentException;
import com.example.awsomepastebin.model.Past;
import com.example.awsomepastebin.projection.LinkAndTitleView;
import com.example.awsomepastebin.projection.PastTitleAndBodyView;
import com.example.awsomepastebin.repository.PastRepos;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PastService {

    private final PastRepos pastRepos;
    @PersistenceContext
    private EntityManager entityManager;

    public PastService(PastRepos pastRepos) {
        this.pastRepos = pastRepos;
    }

    public LinkDTO save(Status status, ExpiryDate expiryDate, CreatePastDTO createPastDTO) {
        Past past = createPastDTO.to();
        past.setDateCreate(Instant.now());
        past.setStatus(status);
        past.setExpiryDate(expiryDate.getDate());
        past.setId(UUID.randomUUID().toString().substring(0, 7));
        createPastDTO.setLink("/my-awesome-pastebin.tld/" + past.getId());
        pastRepos.save(past);
        return LinkDTO.fromCreatePastDTO(createPastDTO);
    }

    public List<PastInfo> getTenLast() {
        TypedQuery<Past> query = entityManager.createQuery("from Past p where p.status = 'PUBLIC' order by dateCreate desc", Past.class);
        return query.setMaxResults(10).getResultList().stream().map(PastInfo::from).collect(Collectors.toList());
    }

    public PastDTO getById(String id){
        PastTitleAndBodyView past = pastRepos.findPastById(id);
        if (past == null){
            throw new IncorrectArgumentException();
        }
        return past.to();
    }
}
