package com.zfm.feisbk.dao;

import com.zfm.feisbk.pojo.TofollowDO;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ToFollowDao extends Neo4jRepository<TofollowDO,Long> {
}
