package com.zfm.feisbk.dao;

import com.zfm.feisbk.pojo.PublishDO;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface PublishDao extends Neo4jRepository<PublishDO,Long> {

}
