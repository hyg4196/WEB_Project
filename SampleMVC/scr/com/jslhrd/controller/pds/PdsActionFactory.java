package com.jslhrd.controller.pds;
import com.jslhrd.service.Action;
import com.jslhrd.service.pds.PdsDeleteAcation;
import com.jslhrd.service.pds.PdsDownAction;
import com.jslhrd.service.pds.PdsListAction;
import com.jslhrd.service.pds.PdsModifyAction;
import com.jslhrd.service.pds.PdsViewAction;
import com.jslhrd.service.pds.PdsWrite;
import com.jslhrd.service.pds.PdsWritePost;


public class PdsActionFactory {
	private static PdsActionFactory instance = new PdsActionFactory();
	private PdsActionFactory() {};
	public static PdsActionFactory getInstance(){
		return instance;
	}
	
	public Action getAction(String cmd){
		Action action = null;
		
		if(cmd.equals("pds_list")) {
			action = new PdsListAction();
		}else if(cmd.equals("pds_write")) {
			action = new PdsWrite();
		}else if(cmd.equals("pds_view")) {
			action = new PdsViewAction();
		}else if(cmd.equals("pds_down")) {
			action = new PdsDownAction();
		}else if(cmd.equals("pds_modify")) {
			action = new PdsModifyAction();
		}else if(cmd.equals("pds_delete")) {
			action = new PdsDeleteAcation();
		}
		
		return action;
	}
	
	public Action getAction(String cmd, String p){
		Action action = null;
		
		if(cmd.equals("pds_list")) {
			action = new PdsListAction();
		}else if(cmd.equals("pds_write")) {
			action = new PdsWritePost();
		}else if(cmd.equals("pds_view")) {
			action = new PdsViewAction();
		}else if(cmd.equals("pds_down")) {
			action = new PdsDownAction();
		}else if(cmd.equals("pds_modify")) {
			action = new PdsModifyAction();
		}else if(cmd.equals("pds_delete")) {
			action = new PdsDeleteAcation();
		}
		
		return action;
	}

}
