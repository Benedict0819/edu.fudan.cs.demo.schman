package edu.fudan.cs.schapp.dao

import java.security.PublicKey;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository;

import edu.fudan.cs.schapp.model.Course
import edu.fudan.cs.schapp.model.Role
import edu.fudan.cs.schapp.model.User
import edu.fudan.cs.schapp.util.CachedDataProvider;
@Repository
class UsersRolesCacheDAO {

	@Autowired
	CachedDataProvider cdp

	public User findUserById(Long id){
		cdp.users.find {it.id==id}
	}
	public User findUserByName(String name){
		cdp.users.find {it.username==name}
	}
	public User findUserByCode(String code){
		cdp.users.find {it.code==code}
	}
	public void createUser(User example){
		example.id=cdp.seq_users++
		cdp.users.add(example)
		def a=1
	}
	public void updateUser(User example){
		User item=findUserById(example.id)
		item.code=example.code
		item.username=example.username
		item.password=example.password
		item.department=example.department
		item.major=example.major
		item.main_role=example.main_role
	}
	public void deleteUser(long id){
		int x=cdp.users.findIndexOf {it.id==id}
		if (x>=0)
			cdp.users.remove(x)
	}
	public List<User> findUsers(){
		cdp.users
	}
	public List<User> findUsersByRoleName(String roleName){
		 cdp.users.findAll{
			 it.main_role?.name==roleName
		 }
	}
	public Role findRoleById(long id){
		cdp.roles.find {it.id==id}
	}
	public Role findRoleByName(String name){
		cdp.roles.find {it.name==name}
	}
	public List<Role> findRoles(){
		cdp.roles
	}
}
