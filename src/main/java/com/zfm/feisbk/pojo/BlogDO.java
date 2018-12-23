package com.zfm.feisbk.pojo;


import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class BlogDO {
	@GraphId
	private Long id;

	private String contentTest = "";
	private String contentImage = "";
	private long createTime;
	private long modifyTime;

	public void setId(Long id) {
		this.id = id;
	}

	public void setContentTest(String contentTest) {
		this.contentTest = contentTest;
	}

	public void setContentImage(String contentImage) {
		this.contentImage = contentImage;
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

	public String getContentTest() {
		return contentTest;
	}

	public String getContentImage() {
		return contentImage;
	}

	public long getCreateTime() {
		return createTime;
	}

	public long getModifyTime() {
		return modifyTime;
	}

	public BlogDO(){}
}
