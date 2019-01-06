package com.zfm.feisbk.dao;

import com.zfm.feisbk.pojo.BefollowDO;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface BeFollowDao extends Neo4jRepository<BefollowDO,Long> {
    @Query("MATCH (s:UserDO)-[r:BEFOLLOW]->(t:UserDO)\n" +
            " WHERE id(s) = {start} AND id(t) = {end}\n" +
            " DELETE r")
    void deleteByStartAndEnd(@Param("start")Long start, @Param("end")Long end);
}
