package com.zfm.feisbk.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "BEFOLLOW")
public class BefollowDO {
    @GraphId
    private Long id;

    @StartNode
    @JsonIgnore
    private UserDO startNode;
    @EndNode
    @JsonIgnore
    private UserDO endNode;

    private long createTime;
    private long modifyTime;

    public BefollowDO(){}

    public BefollowDO(UserDO startNode, UserDO endNode){
        this.startNode = startNode;
        this.endNode = endNode;
        createTime = System.currentTimeMillis();
        modifyTime= System.currentTimeMillis();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDO getStartNode() {
        return startNode;
    }

    public UserDO getEndNode() {
        return endNode;
    }



    @Override
    public String toString() {
        return "BefollowDO{" +
                "id=" + id +
                ", startNode=" + startNode.getId() +
                ", endNode=" + endNode.getId() +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }
}
