package com.example.awsomepastebin.service;

import com.example.awsomepastebin.dto.PastDTO;
import com.example.awsomepastebin.dto.PastInfo;
import com.example.awsomepastebin.enums.Status;
import com.example.awsomepastebin.model.Past;
import com.example.awsomepastebin.projection.PastTitleAndBodyView;
import com.example.awsomepastebin.repository.PastRepos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PastServiceTest {

    @Mock
    private PastRepos pastReposMock;
    @InjectMocks
    PastService pastService;
    private Past testPast1 = new Past();
    private Past testPast2 = new Past();

    @BeforeEach
    void setUp() {
        testPast1.setStatus(Status.PUBLIC);
        testPast1.setTitle("test1");
        testPast2.setStatus(Status.UNLISTED);
        testPast2.setTitle("test2");
    }

    @Test
    void save() {
        when(pastReposMock.save(testPast1)).thenReturn(testPast1);
        Past testPast = pastReposMock.save(testPast1);
        Assertions.assertEquals("test1", testPast.getTitle());
        verify(pastReposMock).save(testPast1);
    }

    @Test
    void getTenLast() {
//        List<PastInfo> testList = new ArrayList<>();
//        testList.add(PastInfo.from(testPast1));
//        testList.add(PastInfo.from(testPast2));
//
//        Assertions.assertEquals(1, pastService.getTenLast().size());
    }


    @Test
    void getById() {
//        PastDTO pastDTO = new PastDTO();
//        pastDTO.setTitle("testT");
//        pastDTO.setBody("testB");
//
//        when(pastReposMock.findPastById(anyString())).);
//        Assertions.assertEquals("testT", pastService.getById("123").getTitle());
    }

    @Test
    void search() {
    }
}
