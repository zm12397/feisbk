package com.zfm.feisbk.dao;

import com.zfm.feisbk.pojo.BlogDO;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface BlogDao extends Neo4jRepository<BlogDO,Long> {
}
