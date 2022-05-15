package com.example.demo.api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/secure")

public class SecureController {
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity reachSecureEndpoint() {

        return new ResponseEntity("If your are reading this you reached a secure endpoint", HttpStatus.OK);
    }
}
