package com.example.awsomepastebin.sheduler;

import com.example.awsomepastebin.repository.PastRepos;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Slf4j
@Component
public class RemovePast {
    private final PastRepos pastRepos;

    public RemovePast(PastRepos pastRepos) {
        this.pastRepos = pastRepos;
    }

    @Scheduled(fixedDelay = 1000)
    @Transactional
    public void clearTokens(){
        log.info("Removing pasts");
        pastRepos.deleteAllByExpiryDateIsBefore(Instant.now());
    }
}
