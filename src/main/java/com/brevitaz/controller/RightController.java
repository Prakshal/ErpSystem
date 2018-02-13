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
	RightDao rightDao;

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

    @RequestMapping(value = "/{rightId}",method={RequestMethod.PUT})
    public boolean update(@RequestBody Right right,@PathVariable String rightId) throws IOException //only to test
    {
        return rightDao.update(right,rightId);
    }

    @RequestMapping(value = "/{rightId}",method={RequestMethod.DELETE})
    public boolean delete(@PathVariable String rightId) throws IOException //only to test
    {
        return rightDao.delete(rightId);
    }

    @RequestMapping(value = "/{rightId}", method={RequestMethod.GET})
    public Right getById(@PathVariable String rightId) throws IOException //only to test
    {
       return rightDao.getById(rightId);
    }

	@RequestMapping(value="/{rightId}/assign/{roleId}",method={RequestMethod.POST})
	public boolean assign(@PathVariable String rightId, @PathVariable String roleId)
	{
		System.out.println("Right assigned");
		return true;
	}
	@RequestMapping(value="/{rightId}/unassign/{roleId}",method={RequestMethod.DELETE})
	public boolean unassign(@PathVariable String rightId, @PathVariable String roleId)
	{
		System.out.println("Right unassigned");
		return true;
	}
}