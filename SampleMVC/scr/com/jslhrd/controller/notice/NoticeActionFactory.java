package com.jslhrd.controller.notice;

import com.jslhrd.service.Action;
import com.jslhrd.service.notice.NoticeListAction;
import com.jslhrd.service.notice.NoticeViewAction;
import com.jslhrd.service.notice.NoticeWriteAction;

public class NoticeActionFactory {
	private static NoticeActionFactory instance = new NoticeActionFactory();
	private NoticeActionFactory() {} 
	public static NoticeActionFactory getInstance() {
		return instance;
	}
	
	public Action getAction(String cmd) {
		Action action = null;
		if(cmd.equals("notice_list")) {
			action = new NoticeListAction();
		}else if(cmd.equals("notice_write")) {
			action = new NoticeWriteAction();
		}else if(cmd.equals("notice_view")) {
			action = new NoticeViewAction();
		}
		return action;
	}
}
