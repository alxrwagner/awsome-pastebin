package com.example.awsomepastebin.service;

import com.example.awsomepastebin.dto.PastDTO;
import com.example.awsomepastebin.enums.Status;
import com.example.awsomepastebin.exception.IncorrectParamException;
import com.example.awsomepastebin.exception.PastNotFoundException;
import com.example.awsomepastebin.model.Past;
import com.example.awsomepastebin.repository.PastRepos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        testPast1.setBody("body1");
        testPast2.setStatus(Status.UNLISTED);
        testPast2.setTitle("test2");
        testPast1.setBody("body2");
    }

    @Test
    void save() {
        when(pastReposMock.save(testPast1)).thenReturn(testPast1);
        Past testPast = pastReposMock.save(testPast1);
        Assertions.assertEquals("test1", testPast.getTitle());
        verify(pastReposMock).save(testPast1);
    }

    @Test
    void getById() {
        testPast1.setId("123");
        when(pastReposMock.findPastById("123")).thenReturn(testPast1);
        PastDTO pastDTO = pastService.getById("123");
        Assertions.assertEquals("test1", pastDTO.getTitle());
    }

    @Test
    void getById_ifNotFound() {
        Assertions.assertThrows(PastNotFoundException.class, () -> pastService.getById("qqq"));
    }

    @Test
    void getById_ifIdIsNull() {
        Assertions.assertThrows(IncorrectParamException.class, () -> pastService.getById(null));
    }

    @Test
    void search() {
        List<Past> testList = new ArrayList<>();
        testPast1.setExpiryDate(Instant.now().plus(10, ChronoUnit.MINUTES));
        testList.add(testPast1);
        when(pastReposMock.findAllByTitleOrBody(Status.PUBLIC,"title1", "body1")).thenReturn(testList);
        List<PastDTO> pastsDTO = pastService.search("title1", "body1");

        Assertions.assertEquals(testList.size(), pastsDTO.size());
        Assertions.assertEquals(testPast1.getTitle(), pastsDTO.get(0).getTitle());
    }
}
