package com.cos.photogramstart.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

// USER-> object  INTEGER->primary key type
// 어노테이션이 없어도 JpaRepository를 상속하면 IoC 등록이 자동
public interface UserRepository extends JpaRepository<User, Integer>{

	//JPA query method
	User findByUsername(String usrename);
	
}
