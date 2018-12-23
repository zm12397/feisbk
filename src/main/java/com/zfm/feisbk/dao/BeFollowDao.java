package com.zfm.feisbk.dao;

import com.zfm.feisbk.pojo.BefollowDO;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface BeFollowDao extends Neo4jRepository<BefollowDO,Long> {
}
