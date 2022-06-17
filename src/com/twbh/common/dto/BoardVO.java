package com.twbh.common.dto;

import java.sql.Timestamp;

public class BoardVO {

	private int num;
	private String name;
	private String email;
	private String title;
	private String content;
	private int readcount;
	private Timestamp writedate;
	private String pictureUrl;
	private int ref; // 참조할 부모글 번호
	private int indent; // 원글 답글 구분을 위한 들여쓰기
	private int step;  //같은 답글 끼리 순서
	private int comment_count;
	private int like_it;

	public int getLike_it() {
		return like_it;
	}
	public void setLike_it(int like_it) {
		this.like_it = like_it;
	}
	public int getComment_count() {
		return comment_count;
	}
	public void setComment_count(int comment_count) {
		this.comment_count = comment_count;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getIndent() {
		return indent;
	}
	public void setIndent(int indent) {
		this.indent = indent;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public int getNum() {
		return num;
	}
	public String getPictureUrl() {
		return pictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public Timestamp getWritedate() {
		return writedate;
	}
	public void setWritedate(Timestamp writedate) {
		this.writedate = writedate;
	}
	@Override
	public String toString() {
		return "BoardVO [num=" + num + ", name=" + name + ", email=" + email  + ", title=" + title
				+ ", content=" + content + ", readcount=" + readcount + ", writedate=" + writedate + ", pictureUrl="
				+ pictureUrl + "]";
	}

	
	
}
