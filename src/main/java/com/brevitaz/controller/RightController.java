package com.brevitaz.controller;

import com.brevitaz.dao.RightDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.brevitaz.model.Right;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("api/right")
public class RightController
{
	@Autowired
	private RightDao rightDao;

    @PreAuthorize("hasAuthority('Right:CREATE')")
	@RequestMapping(method={RequestMethod.POST})
	public boolean create(@RequestBody Right right){
		return rightDao.insert(right);
	}

    @PreAuthorize("hasAuthority('Right:READ')")
    @RequestMapping(method={RequestMethod.GET})
    public List<Right> getAll()// only to test
    {
        return rightDao.getAll();
    }

    @PreAuthorize("hasAuthority('Right:UPDATE')")
    @RequestMapping(value = "/{id}",method={RequestMethod.PUT})
    public boolean update(@RequestBody Right right,@PathVariable String id) //only to test
    {
        return rightDao.update(right,id);
    }

    @PreAuthorize("hasAuthority('Right:DELETE')")
    @RequestMapping(value = "/{id}",method={RequestMethod.DELETE})
    public boolean delete(@PathVariable String id) //only to test
    {
        return rightDao.delete(id);
    }

    @PreAuthorize("hasAuthority('Right:READ')")
    @RequestMapping(value = "/{id}", method={RequestMethod.GET})
    public Right getById(@PathVariable String id) //only to test
    {
       return rightDao.getById(id);
    }

    @PreAuthorize("hasAuthority('Right:ASSIGN')")
	@RequestMapping(value="/{id}/assign/{roleId}",method={RequestMethod.POST})
	public boolean assign(@PathVariable String id, @PathVariable String roleId)
	{
		System.out.println("Right assigned");
		return true;
	}

    @PreAuthorize("hasAuthority('Right:UN-ASSIGN')")
	@RequestMapping(value="/{id}/unassign/{roleId}",method={RequestMethod.DELETE})
	public boolean unassign(@PathVariable String id, @PathVariable String roleId)
	{
		System.out.println("Right unassigned");
		return true;
	}
}