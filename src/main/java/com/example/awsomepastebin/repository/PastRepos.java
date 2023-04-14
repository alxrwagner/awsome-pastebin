package com.example.awsomepastebin.repository;

import com.example.awsomepastebin.model.Past;
import com.example.awsomepastebin.projection.PastTitleAndBodyView;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Repository
public interface PastRepos extends JpaRepository<Past, UUID>, JpaSpecificationExecutor<Past> {
    PastTitleAndBodyView findPastById(String id);

    List<Past> findAll(Specification<Past> specification);

    void deleteAllByExpiryDateIsBefore(Instant now);
}
