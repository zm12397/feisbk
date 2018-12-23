package com.zfm.feisbk.pojo;


import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class BlogDO {
	@GraphId
	private Long id;

	//动态的文本内容
	private String contentTest = "";
	//动态的图片（url地址）
	private String contentImage = "";
	//创建（发布时间戳19位）
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
