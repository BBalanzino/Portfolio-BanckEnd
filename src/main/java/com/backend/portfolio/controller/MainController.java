package com.backend.portfolio.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/")
public class MainController {

    @GetMapping()
    public ResponseEntity<String> healthCheck(@RequestParam String param) {
        return new ResponseEntity<String>("ping", HttpStatus.OK);
    }

}