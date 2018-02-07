package com.brevitaz.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.brevitaz.model.Right;


@RestController
@RequestMapping("/rights")
public class RightController
{
	@RequestMapping(method={RequestMethod.POST})
	public boolean create(@RequestBody Right rights)
	{
		System.out.println("Right created");
		return true;
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