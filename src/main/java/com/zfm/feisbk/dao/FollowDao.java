package com.zfm.feisbk.dao;

import com.zfm.feisbk.pojo.FollowDO;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface FollowDao extends Neo4jRepository<FollowDO,Long> {
}
