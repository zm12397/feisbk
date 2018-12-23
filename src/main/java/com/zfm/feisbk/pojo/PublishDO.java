package com.zfm.feisbk.pojo;


import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "PUBLISH")
public class PublishDO {
	@GraphId
	private Long id;
	@StartNode
	private UserDO author;
	@EndNode
	private BlogDO blogDO;
	private long createTime;
	private long modifyTime;

	public PublishDO(UserDO author, BlogDO blogDO) {
		this.author = author;
		this.blogDO = blogDO;
		createTime = System.currentTimeMillis();
		modifyTime = System.currentTimeMillis();
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setAuthor(UserDO author) {
		this.author = author;
	}

	public void setBlogDO(BlogDO blogDO) {
		this.blogDO = blogDO;
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

	public UserDO getAuthor() {
		return author;
	}

	public BlogDO getBlogDO() {
		return blogDO;
	}

	public long getCreateTime() {
		return createTime;
	}

	public long getModifyTime() {
		return modifyTime;
	}

	public PublishDO() {
	}

	@Override
	public String toString() {
		return "PublishDO{" +
				"id=" + id +
				", author=" + author.getId() +
				", blogDO=" + blogDO.getId() +
				", createTime=" + createTime +
				", modifyTime=" + modifyTime +
				'}';
	}
}
