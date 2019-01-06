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
	private String name;		//昵称
	private String tel;
	private String email;
	private String address;
	private Short sex;			//1男 2女
	private Long birthday;
	private String description;	//个人描述的一句话，或者个性签名

	private Long createTime;	//创建时间
	private Long modifyTime;	//修改时间

	private Short state;		//用户状态 1激活 2禁用

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

	public Short getState() {
		return state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Short getSex() {
		return sex;
	}

	public void setSex(Short sex) {
		this.sex = sex;
	}

	public Long getBirthday() {
		return birthday;
	}

	public void setBirthday(Long birthday) {
		this.birthday = birthday;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UserDO(){}

	@Override
	public String toString() {
		return "UserDO{" +
				"id=" + id +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", name='" + name + '\'' +
				", tel='" + tel + '\'' +
				", email='" + email + '\'' +
				", address='" + address + '\'' +
				", sex=" + sex +
				", birthday=" + birthday +
				", description='" + description + '\'' +
				", createTime=" + createTime +
				", modifyTime=" + modifyTime +
				", state=" + state +
				", followeds=" + followeds +
				", followers=" + followers +
				", publishes=" + publishes +
				'}';
	}
}
