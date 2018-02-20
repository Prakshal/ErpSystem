package com.brevitaz.service;

import java.io.IOException;

public interface LogInService {

    public String login(String username, String password) throws IOException;
    public boolean logout();
}
