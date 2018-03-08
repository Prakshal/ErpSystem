package com.brevitaz.service;

import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface AuthenticationService {

    public ResponseEntity<String> login(String username, String password);
    public boolean logout();
}
