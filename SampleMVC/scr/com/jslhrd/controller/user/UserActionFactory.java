package com.jslhrd.controller.user;
import com.jslhrd.service.Action;
import com.jslhrd.service.user.UserCheckAction;
import com.jslhrd.service.user.UserInsertAction;
import com.jslhrd.service.user.UserLoginAction;
import com.jslhrd.service.user.UserLoginActionPost;
import com.jslhrd.service.user.UserLoginokAction;
import com.jslhrd.service.user.UserLogoutAction;
import com.jslhrd.service.user.UserModifyAction;
import com.jslhrd.service.user.UserModifyActionPost;


public class UserActionFactory {
	private static UserActionFactory instance = new UserActionFactory();
	private UserActionFactory() {};
	public static UserActionFactory getInstance(){
		return instance;
	}
	
	public Action getAction(String cmd){
		Action action = null;

		if(cmd.equals("user_login")) {
			action = new UserLoginAction();
		}else if(cmd.equals("user_loginok")) {
			action = new UserLoginokAction();
		}else if(cmd.equals("user_modify")) {
			action = new UserModifyAction();
		}else if(cmd.equals("user_insert")) {
			action = new UserInsertAction();
		}else if(cmd.equals("user_check")) {
			action = new UserCheckAction();
		}else if(cmd.equals("user_logout")) {
			action = new UserLogoutAction();
		}
		
		return action;
	}
	
	public Action getAction(String cmd, String post){
		Action action = null;

		if(cmd.equals("user_login")) {
			action = new UserLoginActionPost();
		}else if(cmd.equals("user_modify")) {
			action = new UserModifyActionPost();
		}else if(cmd.equals("user_insert")) {
			action = new UserInsertAction();
		}else if(cmd.equals("user_check")) {
			action = new UserCheckAction();
		}else if(cmd.equals("user_logout")) {
			action = new UserLogoutAction();
		}
		
		return action;
	}
}
