package com.example.awsomepastebin.repository;

import com.example.awsomepastebin.enums.Status;
import com.example.awsomepastebin.model.Past;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Repository
public interface PastRepos extends JpaRepository<Past, UUID>, JpaSpecificationExecutor<Past> {
    Past findPastById(String id);

    List<Past> findAllByTitleContainsIgnoreCaseOrBodyContainsIgnoreCaseAndStatusAndExpiryDateIsAfter(String title, String text, Status status, Instant instant);

    @Modifying
    @Query(value="delete from Past p where p.expiryDate < now()")
    void deleteAll(Instant now);

    List<Past> findTop10ByStatusAndExpiryDateIsAfterOrderByDateCreateDesc(Status status, Instant instant);
}
