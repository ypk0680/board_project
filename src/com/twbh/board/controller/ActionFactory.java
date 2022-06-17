package com.twbh.board.controller;

import com.twbh.board.controller.action.Action;
import com.twbh.board.controller.action.BoardDeleteAction;
import com.twbh.board.controller.action.BoardListAction;
import com.twbh.board.controller.action.BoardReplyAction;
import com.twbh.board.controller.action.BoardReplyFormAction;
import com.twbh.board.controller.action.BoardUpdateAction;
import com.twbh.board.controller.action.BoardUpdateFormAction;
import com.twbh.board.controller.action.BoardViewAction;
import com.twbh.board.controller.action.BoardWriteAction;
import com.twbh.board.controller.action.BoardWriteFormAction;
import com.twbh.board.controller.action.CommentWriteAction;
import com.twbh.board.controller.action.LikeUpdateAction;

public class ActionFactory {
	
	private static ActionFactory instance = new ActionFactory();
	
	private ActionFactory() {
		super();
	}
	
	public static ActionFactory getInstance(){
		return instance;
	}
	
	public Action getAction(String command){
		Action action = null;
		System.out.println("ActionFactory:" + command);
		
		if(command.equals("board_list")){
			action = new BoardListAction();
		}  else if(command.equals("board_write_form")){
			action = new BoardWriteFormAction();
		} else if(command.equals("board_write")){
			action = new BoardWriteAction();
		}  else if(command.equals("board_view")){
			action = new BoardViewAction();
		} else if(command.equals("board_update_form")){
			action = new BoardUpdateFormAction();
		} else if(command.equals("board_update")){
			action = new BoardUpdateAction();
		}else if(command.equals("board_delete")){
			action = new BoardDeleteAction();
		}else if(command.equals("board_reply_form")){
			action = new BoardReplyFormAction();
		}else if(command.equals("board_reply")){
			action = new BoardReplyAction();
		}else if(command.equals("board_comment")){
			action = new CommentWriteAction();
		}else if(command.equals("like_it")){
			action = new LikeUpdateAction();
		}
		
		return action;
	}


}
