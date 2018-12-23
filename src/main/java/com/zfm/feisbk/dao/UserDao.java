package com.zfm.feisbk.dao;

import com.zfm.feisbk.pojo.UserDO;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;


public interface UserDao extends Neo4jRepository<UserDO,Long> {
	UserDO findByUsername(@Param("username") String username);

	@Query("MATCH (user:UserDO) where user.username=~{query} RETURN user")
	Collection<UserDO> findUserDOByUsernameIsLike(@Param("query") String query);
}
