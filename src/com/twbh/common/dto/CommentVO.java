package com.twbh.common.dto;

import java.sql.Timestamp;

public class CommentVO {
	private int comment_num; 
	private int board_num;
	private String userid;
	private String content;
	private Timestamp writedate;
	public int getComment_num() {
		return comment_num;
	}
	public void setComment_num(int comment_num) {
		this.comment_num = comment_num;
	}
	public int getBoard_num() {
		return board_num;
	}
	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getWritedate() {
		return writedate;
	}
	public void setWritedate(Timestamp writedate) {
		this.writedate = writedate;
	}
	@Override
	public String toString() {
		return "CommentVO [comment_num=" + comment_num + ", board_num=" + board_num + ", userid=" + userid
				+ ", content=" + content + ", writedate=" + writedate + "]";
	}
	
	
}
