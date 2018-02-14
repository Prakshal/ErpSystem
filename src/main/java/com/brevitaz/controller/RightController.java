package com.brevitaz.controller;

import com.brevitaz.dao.RightDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.brevitaz.model.Right;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/rights")
public class RightController
{
	@Autowired
	private RightDao rightDao;

	@RequestMapping(method={RequestMethod.POST})
	public boolean create(@RequestBody Right right) throws IOException {
		System.out.println("Right created");
		return rightDao.insert(right);
	}

    @RequestMapping(method={RequestMethod.GET})
    public List<Right> getAll() throws IOException// only to test
    {
        return rightDao.getAll();
    }

    @RequestMapping(value = "/{id}",method={RequestMethod.PUT})
    public boolean update(@RequestBody Right right,@PathVariable String id) throws IOException //only to test
    {
        return rightDao.update(right,id);
    }

    @RequestMapping(value = "/{id}",method={RequestMethod.DELETE})
    public boolean delete(@PathVariable String id) throws IOException //only to test
    {
        return rightDao.delete(id);
    }

    @RequestMapping(value = "/{id}", method={RequestMethod.GET})
    public Right getById(@PathVariable String id) throws IOException //only to test
    {
       return rightDao.getById(id);
    }

	@RequestMapping(value="/{id}/assign/{roleId}",method={RequestMethod.POST})
	public boolean assign(@PathVariable String id, @PathVariable String roleId)
	{
		System.out.println("Right assigned");
		return true;
	}
	@RequestMapping(value="/{id}/unassign/{roleId}",method={RequestMethod.DELETE})
	public boolean unassign(@PathVariable String id, @PathVariable String roleId)
	{
		System.out.println("Right unassigned");
		return true;
	}
}