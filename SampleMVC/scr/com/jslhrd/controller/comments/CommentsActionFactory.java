package com.jslhrd.controller.comments;


import com.jslhrd.service.Action;
import com.jslhrd.service.comments.CommentsWriteAction;


public class CommentsActionFactory {
	private static CommentsActionFactory instance = new CommentsActionFactory();
	private CommentsActionFactory() {} 
	public static CommentsActionFactory getInstance() {
		return instance;
	}
	
	
	public Action getAction(String cmd) {
		Action action = null;
		System.out.println(cmd);
		if(cmd.equals("comments_write")) {
			action = new CommentsWriteAction();
		}
		
		return action;
	}

}
