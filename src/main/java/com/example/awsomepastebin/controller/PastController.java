package com.example.awsomepastebin.controller;

import com.example.awsomepastebin.dto.CreatePastDTO;
import com.example.awsomepastebin.dto.LinkDTO;
import com.example.awsomepastebin.dto.PastDTO;
import com.example.awsomepastebin.dto.PastInfo;
import com.example.awsomepastebin.service.PastService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/my-awesome-pastebin.tld")
public class PastController {

    private final PastService pastService;

    public PastController(PastService pastService) {
        this.pastService = pastService;
    }

    @PostMapping("/save")
    public LinkDTO create(@RequestBody CreatePastDTO past){
        return pastService.save(past);
    }

    @GetMapping("/last10")
    public ResponseEntity<List<PastInfo>> getTenLastPublicPasts(){
        return ResponseEntity.ok(pastService.getTenLast());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PastDTO> getById(@PathVariable String id){
        return ResponseEntity.ok(pastService.getById(id));
    }

    @GetMapping("/search")
    public List<PastDTO> search(@RequestParam(required = false) String title, @RequestParam(required = false) String body){
        return pastService.search(title, body);
    }
}
