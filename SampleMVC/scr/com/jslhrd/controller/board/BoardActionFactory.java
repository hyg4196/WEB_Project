package com.jslhrd.controller.board;

import com.jslhrd.service.Action;
import com.jslhrd.service.board.*;

public class BoardActionFactory {
	private static BoardActionFactory instance = new BoardActionFactory();
	private BoardActionFactory() {} 
	public static BoardActionFactory getInstance() {
		return instance;
	}
	
	
	public Action getAction(String cmd) {
		Action action = null;
		
		if(cmd.equals("board_list")) {
			action = new BoardListAction();
		}else if(cmd.equals("board_write")) {
			action = new BoardWriteAction();
		}else if(cmd.equals("board_view")) {
			action = new BoardViewAction();
		}else if(cmd.equals("board_delete")) {
			action = new BoardDeleteAction();
		}else if(cmd.equals("board_modify")) {
			action = new BoardModifyAction();
		}
		
		return action;
	}
	
	public Action getAction(String cmd, String post) {
		Action action = null;
		if(cmd.equals("board_delete")) {
			action = new BoardDeleteActionPost();
		}else if(cmd.equals("board_write")) {
			action = new BoardWriteAction();
		}if(cmd.equals("board_list")) {
			action = new BoardListAction();
		}else if(cmd.equals("board_modify")) {
			action = new BoardModifyAction();
		}
		
		return action;
	}
	
	
}
