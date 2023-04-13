package com.example.awsomepastebin.controller;

import com.example.awsomepastebin.dto.CreatePastDTO;
import com.example.awsomepastebin.dto.PastDTO;
import com.example.awsomepastebin.dto.LinkDTO;
import com.example.awsomepastebin.dto.PastInfo;
import com.example.awsomepastebin.enums.ExpiryDate;
import com.example.awsomepastebin.enums.Status;
import com.example.awsomepastebin.service.PastService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/my-awesome-pastebin.tld")
public class PastController {

    private final PastService pastService;

    public PastController(PastService pastService) {
        this.pastService = pastService;
    }

    @PostMapping("/save")
    public LinkDTO create(@RequestParam(name = "status") Status status,
                                          @RequestParam(name = "expiry date") ExpiryDate expiryDate,
                                          @RequestBody CreatePastDTO past){
        return pastService.save(status, expiryDate, past);
    }

    @GetMapping("/last10")
    public ResponseEntity<List<PastInfo>> getTenLastPublicPasts(){
        return ResponseEntity.ok(pastService.getTenLast());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PastDTO> getById(@PathVariable String id){
        return ResponseEntity.ok(pastService.getById(id));
    }
}
