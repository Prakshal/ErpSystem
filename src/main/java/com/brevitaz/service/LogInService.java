package com.brevitaz.service;

import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface LogInService {

    public ResponseEntity<String> login(String username, String password);
    public boolean logout();
}
