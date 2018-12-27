package com.taeyang.controller;

import com.taeyang.admin.controller.action.AdminIndexAction;
import com.taeyang.admin.controller.action.AdminLoginAction;
import com.taeyang.admin.controller.action.AdminLogoutAction;
import com.taeyang.admin.controller.action.AdminProductDetailAction;
import com.taeyang.admin.controller.action.AdminProductListAction;
import com.taeyang.admin.controller.action.AdminProductWriteAction;
import com.taeyang.admin.controller.action.AdminProductWriteFormAction;
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
import com.taeyang.controller.action.MyPageAction;
import com.taeyang.controller.action.OrderAllAction;
import com.taeyang.controller.action.OrderDetailAction;
import com.taeyang.controller.action.OrderInsertAction;
import com.taeyang.controller.action.OrderListAction;
import com.taeyang.controller.action.ProductDetailAction;
import com.taeyang.controller.action.ProductKindAction;
import com.taeyang.controller.action.QnaListAction;
import com.taeyang.controller.action.QnaViewAction;
import com.taeyang.controller.action.QnaWriteAction;
import com.taeyang.controller.action.QnaWriteFormAction;

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
		if(command.equals("order_insert")) {
			action = new OrderInsertAction();
		}
		if(command.equals("order_list")) {
			action = new OrderListAction();
		}
		if(command.equals("mypage")) {
			action = new MyPageAction();
		}
		if(command.equals("order_detail")) {
			action = new OrderDetailAction();
		}
		if(command.equals("order_all")) {
			action = new OrderAllAction();
		}
		if(command.equals("qna_list")) {
			action = new QnaListAction();
		}
		if(command.equals("qna_write_form")) {
			action = new QnaWriteFormAction();
		}
		if(command.equals("qna_write")) {
			action = new QnaWriteAction();
		}
		if(command.equals("qna_view")) {
			action = new QnaViewAction();
		}
		if(command.equals("admin_login_form")) {
			action = new AdminIndexAction();
		}
		if(command.equals("admin_login")) {
			action = new AdminLoginAction();
		}
		if(command.equals("admin_logout")) {
			action = new AdminLogoutAction();
		}
		if(command.equals("admin_product_list")) {
			action = new AdminProductListAction();
		}
		if(command.equals("admin_product_write_form")) {
			action = new AdminProductWriteFormAction();
		}
		if(command.equals("admin_product_write")) {
			action = new AdminProductWriteAction();
		}
		if(command.equals("admin_product_detail")) {
			action = new AdminProductDetailAction();
		}
		return action;
	}
}
