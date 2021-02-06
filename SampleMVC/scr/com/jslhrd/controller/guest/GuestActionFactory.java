package com.jslhrd.controller.guest;
import com.jslhrd.service.Action;
import com.jslhrd.service.guest.GuestListAction;
import com.jslhrd.service.guest.GuestModifyAction;
import com.jslhrd.service.guest.GuestViewAction;
import com.jslhrd.service.guest.GuestWriteAction;



public class GuestActionFactory {
	private static GuestActionFactory instance = new GuestActionFactory();
	private GuestActionFactory() {};
	public static GuestActionFactory getInstance(){
		return instance;
	}
	
	public Action getAction(String cmd){
		Action action = null;

		if(cmd.equals("guest_list")) {
			action = new GuestListAction();
		}else if(cmd.equals("guest_write")) {
			action = new GuestWriteAction();
		}else if(cmd.equals("guest_view")) {
			action = new GuestViewAction();
		}else if(cmd.equals("guest_modify")) {
			action = new GuestModifyAction();
		}
		return action;
	}
	
}
