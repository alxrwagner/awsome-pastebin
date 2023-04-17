package com.example.awsomepastebin.repository;

import com.example.awsomepastebin.enums.Status;
import com.example.awsomepastebin.model.Past;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface PastRepos extends JpaRepository<Past, String> {
    Past findPastById(String id);

    @Modifying
    @Query(value="delete from Past p where p.expiryDate < now()")
    void deleteAll(Instant now);

    List<Past> findTop10ByStatusAndExpiryDateIsAfterOrderByDateCreateDesc(Status status, Instant instant);

    @Query(value = "SELECT p FROM Past p where p.status = ?1 and p.expiryDate > now() and (p.title = ?2 or p.body = ?3)")
    List<Past> findAllByTitleOrBody(Status status, String title, String body);
}
