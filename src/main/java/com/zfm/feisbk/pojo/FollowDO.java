package com.zfm.feisbk.pojo;

//import groovy.util.logging.Log4j;
import org.neo4j.ogm.annotation.*;


@RelationshipEntity(type = "FOLLOW")
public class FollowDO {
	@GraphId
	private Long id;
	@StartNode
	private UserDO follower;
	@EndNode
	private UserDO followed;
	private long followTime;
	private long createTime;
	private long modifyTime;

	public FollowDO(UserDO follower, UserDO followed){
		this.follower=follower;
		this.followed=followed;
		followTime = System.currentTimeMillis();
		createTime = System.currentTimeMillis();
		modifyTime = System.currentTimeMillis();
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFollower(UserDO follower) {
		this.follower = follower;
	}

	public void setFollowed(UserDO followed) {
		this.followed = followed;
	}

	public void setFollowTime(long followTime) {
		this.followTime = followTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public void setModifyTime(long modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Long getId() {
		return id;
	}

	public UserDO getFollower() {
		return follower;
	}

	public UserDO getFollowed() {
		return followed;
	}

	public long getFollowTime() {
		return followTime;
	}

	public long getCreateTime() {
		return createTime;
	}

	public long getModifyTime() {
		return modifyTime;
	}
}
