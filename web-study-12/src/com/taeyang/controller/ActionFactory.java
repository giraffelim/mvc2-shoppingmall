package com.taeyang.controller;

import com.taeyang.controller.action.Action;
import com.taeyang.controller.action.CartDeleteAction;
import com.taeyang.controller.action.CartInsertAction;
import com.taeyang.controller.action.CartListAction;
import com.taeyang.controller.action.ContractAction;
import com.taeyang.controller.action.FindZipNumAction;
import com.taeyang.controller.action.IdCheckFormAction;
import com.taeyang.controller.action.IndexAction;
import com.taeyang.controller.action.JoinAction;
import com.taeyang.controller.action.JoinFormAction;
import com.taeyang.controller.action.LoginAction;
import com.taeyang.controller.action.LoginFormAction;
import com.taeyang.controller.action.LogoutAction;
import com.taeyang.controller.action.ProductDetailAction;
import com.taeyang.controller.action.ProductKindAction;

public class ActionFactory {
	private ActionFactory() {
		super();
	}
	private static ActionFactory instance = new ActionFactory();
	public static ActionFactory getInstance() {
		return instance;
	}
	public Action getAction(String command) {
		Action action = null;
		System.out.println("ActionFactory: "+command);
		if(command.equals("index")) {
			action = new IndexAction();
		}
		if(command.equals("product_detail")) {
			action = new ProductDetailAction();
		}
		if(command.equals("catagory")) {
			action = new ProductKindAction();
		}
		if(command.equals("contract")) {
			action = new ContractAction();
		}
		if(command.equals("join_form")) {
			action = new JoinFormAction();
		}
		if(command.equals("id_check_form")) {
			action = new IdCheckFormAction();
		}
		if(command.equals("find_zip_num")) {
			action = new FindZipNumAction();
		}
		if(command.equals("join")) {
			action = new JoinAction();
		}
		if(command.equals("login_form")) {
			action = new LoginFormAction();
		}
		if(command.equals("login")) {
			action = new LoginAction();
		}
		if(command.equals("logout")) {
			action = new LogoutAction();
		}
		if(command.equals("cart_insert")) {
			action = new CartInsertAction();
		}
		if(command.equals("cart_list")) {
			action = new CartListAction();
		}
		if(command.equals("cart_delete")) {
			action = new CartDeleteAction();
		}
		return action;
	}
}
