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

	@Relationship(type = "FOLLOW", direction = Relationship.INCOMING)
	Set<UserDO> followers = new HashSet<UserDO>();

	@Relationship(type = "FOLLOW", direction = Relationship.OUTGOING)
	Set<UserDO> followed = new HashSet<UserDO>();

	@Relationship(type = "FOLLOW")
	Set<PublishDO> blogs = new HashSet<PublishDO>();

	public FollowDO follow(UserDO user) {
		FollowDO followDO = new FollowDO(this, user);
		this.followed.add(user);
		user.followers.add(this);
		return followDO;
	}

	public PublishDO publish(BlogDO blogDO) {
		PublishDO publishDO = new PublishDO(this, blogDO);
		this.blogs.add(publishDO);
		return publishDO;
	}

	public void unfollow(UserDO user) {
		this.followed.remove(user);
		user.followers.remove(this);
	}

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

	public Set<UserDO> getFollowers() {
		return followers;
	}

	public void setFollowers(Set<UserDO> followers) {
		this.followers = followers;
	}

	public Set<UserDO> getFollowed() {
		return followed;
	}

	public void setFollowed(Set<UserDO> followed) {
		this.followed = followed;
	}

	public Set<PublishDO> getBlogs() {
		return blogs;
	}

	public void setBlogs(Set<PublishDO> blogs) {
		this.blogs = blogs;
	}
}
