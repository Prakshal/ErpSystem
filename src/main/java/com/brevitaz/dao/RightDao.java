package com.brevitaz.dao;
import com.brevitaz.model.Right;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

public interface RightDao {

    public boolean insert(Right right) throws IOException;
    public List<Right> getAll() throws IOException;
    public boolean update(Right right, String id) throws IOException;
    public boolean delete(String id) throws IOException;
    public Right getById(String id) throws IOException;
}
