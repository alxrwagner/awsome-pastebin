package com.example.awsomepastebin.repository;

import com.example.awsomepastebin.model.Past;
import com.example.awsomepastebin.projection.LinkAndTitleView;
import com.example.awsomepastebin.projection.PastTitleAndBodyView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public interface PastRepos extends JpaRepository<Past, UUID> {
//    @Query(value = "select Past.title, Past.body from Past where id = ?1")
    PastTitleAndBodyView findPastById(String id);

//    @Query(value = "select Past.title, Past.id from Past")
//    List<LinkAndTitleView> getLast10();
}
