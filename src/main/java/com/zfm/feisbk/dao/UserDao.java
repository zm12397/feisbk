package com.zfm.feisbk.dao;

import com.zfm.feisbk.pojo.UserDO;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;


public interface UserDao extends Neo4jRepository {
	UserDO findByUsername(@Param("username") String username);
}
