package pers.donguo.open;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import pers.donguo.open.modules.sys.repo.SysRoleDao;
import pers.donguo.open.modules.sys.repo.SysUserDao;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration
public class UserRoleTest {
	@Autowired
	SysUserDao sysUserRepository;

	@Autowired
	SysRoleDao sysRoleRepository;

	@Test
	@Transactional
	@Rollback(false)
	public void manyToManyTest() {
//		SysUser user = new SysUser();
//		user.setUsername("东郭先生");

//		SysRole role = new SysRole();
//		role.setRoleName("Java程序员");
//
//
//		sysUserRepository.insert(user);
//		sysRoleRepository.insert(role);
//		role.getUsers().add(user);

	}
}
