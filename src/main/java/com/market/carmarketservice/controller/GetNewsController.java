package com.market.carmarketservice.controller;

import com.market.carmarketservice.service.news.GetNewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class GetNewsController {
    private final GetNewsService getNewsService;

    @PostMapping("/getPost")
    public ResponseEntity<Object> getPost(@RequestBody String link) {
        return new ResponseEntity<>(getNewsService.getPost(link), HttpStatus.OK);
    }

    @PostMapping("/getRss")
    public ResponseEntity<Object> getRss(@RequestBody String link) {
        return new ResponseEntity<>(getNewsService.getRss(link), HttpStatus.OK);
    }
}
