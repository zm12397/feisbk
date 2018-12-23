package com.zfm.feisbk.pojo;


import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class UserDO {
	@GraphId
	private Long id;

	private String username;
	private String password;
	private Long createTime;
	private Long modifyTime;

	@Relationship(type = "TOFOLLOW")
	private Set<TofollowDO> followeds;

	@Relationship(type = "BEFOLLOW")
	private Set<BefollowDO> followers;

	@Relationship(type = "PUBLISH")
	private Set<PublishDO> publishes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Long modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Set<TofollowDO> getFolloweds() {
		return followeds;
	}

	public Set<BefollowDO> getFollowers() {
		return followers;
	}

	public void setFolloweds(Set<TofollowDO> followeds) {
		this.followeds = followeds;
	}

	public void setFollowers(Set<BefollowDO> followers) {
		this.followers = followers;
	}

	public Set<PublishDO> getPublishes() {
		return publishes;
	}

	public void setPublishes(Set<PublishDO> publishes) {
		this.publishes = publishes;
	}

	public UserDO(){}

	@Override
	public String toString() {
		return "UserDO{" +
				"id=" + id +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", createTime=" + createTime +
				", modifyTime=" + modifyTime +
				", followeds=" + followeds +
				", followers=" + followers +
				", publishes=" + publishes +
				'}';
	}
}
