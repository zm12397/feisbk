package com.zfm.feisbk.pojo;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "TOFOLLOW")
public class TofollowDO{
    @GraphId
    private Long id;

    @StartNode
    private UserDO startNode;
    @EndNode
    private UserDO endNode;

    private long createTime;
    private long modifyTime;

    public TofollowDO(){}

    public TofollowDO(UserDO startNode,UserDO endNode){
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

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(long modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        return "TofollowDO{" +
                "id=" + id +
                ", startNode=" + startNode.getId() +
                ", endNode=" + endNode.getId() +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }
}
