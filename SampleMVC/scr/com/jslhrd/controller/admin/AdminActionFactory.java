package com.jslhrd.controller.admin;

import com.jslhrd.service.Action;
import com.jslhrd.service.admin.AdminBoardListAction;
import com.jslhrd.service.admin.AdminBoardViewAction;
import com.jslhrd.service.admin.AdminIndexAction;
import com.jslhrd.service.admin.AdminLoginAction;
import com.jslhrd.service.admin.AdminNoticeListAction;
import com.jslhrd.service.board.*;

public class AdminActionFactory {
	private static AdminActionFactory instance = new AdminActionFactory();
	private AdminActionFactory() {} 
	public static AdminActionFactory getInstance() {
		return instance;
	}
	
	
	public Action getAction(String cmd) {
		Action action = null;
		System.out.println(cmd);
		if(cmd.equals("admin_index")) {
			action = new AdminIndexAction();
		}else if(cmd.equals("admin_boardList")) {
			action = new AdminBoardListAction();
		}else if(cmd.equals("admin_boardView")) {
			action = new AdminBoardViewAction();
		}else if(cmd.equals("admin_login")) {
			action = new AdminLoginAction();
		}else if(cmd.equals("notice_list")) {
			action = new AdminNoticeListAction();
		}
		
		return action;
	}
	
	public Action getAction(String cmd, String post) {
		Action action = null;
		if(cmd.equals("admin_boardList")) {
			action = new AdminBoardListAction();
		}else if(cmd.equals("admin_boardView")) {
			action = new AdminBoardViewAction();
		}else if(cmd.equals("admin_login")) {
			action = new AdminLoginAction();
		}
		
		return action;
	}
	
	
}
