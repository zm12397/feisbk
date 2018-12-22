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
	private long publishTime;
	private long createTime;
	private long modifyTime;

	public PublishDO(UserDO author, BlogDO blogDO) {
		this.author = author;
		this.blogDO = blogDO;
		publishTime = System.currentTimeMillis();
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

	public void setPublishTime(long publishTime) {
		this.publishTime = publishTime;
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

	public long getPublishTime() {
		return publishTime;
	}

	public long getCreateTime() {
		return createTime;
	}

	public long getModifyTime() {
		return modifyTime;
	}
}
