package com.zfm.feisbk.dao;

import com.zfm.feisbk.pojo.TofollowDO;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface ToFollowDao extends Neo4jRepository<TofollowDO,Long> {
    @Query("MATCH (s:UserDO)-[r:TOFOLLOW]->(t:UserDO)\n" +
            " WHERE id(s) = {start} AND id(t) = {end}\n" +
            " DELETE r")
    void deleteByStartAndEnd(@Param("start")Long start, @Param("end")Long end);
}
