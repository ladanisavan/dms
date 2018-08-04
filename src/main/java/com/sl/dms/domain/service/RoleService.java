package com.sl.dms.domain.service;

import java.util.List;

import com.sl.dms.domain.Role;

public interface RoleService {
	public List<Role> getAllRoles();
	public Role getRoleByName(String roleName);
}
