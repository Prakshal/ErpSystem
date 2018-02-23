package com.brevitaz.dao;
import com.brevitaz.model.Right;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

public interface RightDao {

    public boolean insert(Right right);
    public List<Right> getAll();
    public boolean update(Right right, String id);
    public boolean delete(String id);
    public Right getById(String id);
}
