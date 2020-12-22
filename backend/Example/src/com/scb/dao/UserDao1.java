package com.scb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.scb.helper.DBConnection;
import com.scb.model.Profit;
import com.scb.model.Trader;

public class UserDao1 {

	public static int getUser1(String email, String password) throws SQLException {
		System.out.println(email);

		Trader trader = new Trader();
		boolean c = false;
		String query = "select * from trade where email = '" + email + "' and password = '" + password + "'";
		System.out.println(query);
		ResultSet rs = DBConnection.getResultSet(query);
		System.out.println(rs);

		try {
			if (rs.next()) {

				trader.setName(rs.getString("name"));
				trader.setGender(rs.getString("gender"));
				trader.setEmail(rs.getString("email"));
				System.out.println(trader.getEmail());
				trader.setPassword(rs.getString("password"));
				trader.setRepassword(rs.getString("repassword"));
				trader.setPhone(rs.getString("phone"));
				c = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (c) {
			System.out.println("record found");
			return 1;
		} else {
			return 0;
		}

	}

	public static int addUser1(Trader user) {
		String query = "INSERT INTO TRADE  VALUES('" + user.getName() + "','" + user.getGender() + "','"
				+ user.getEmail() + "','" + user.getPassword() + "','" + user.getRepassword() + "','" + user.getPhone()
				+ "')";
		int status = DBConnection.dmlAction(query);
		return status;
	}

	public static List<Profit> getAllUserDetails(String q) {
		System.out.println("under userdao");
		String query = "SELECT * FROM LEDGER1 WHERE EMAIL = '" + q + "'";
		System.out.println(query);
		ResultSet rs = DBConnection.getResultSet(query);
		List<Profit> users = new ArrayList<>();
		try {
			Profit user = null;
			while (rs.next()) {
				user = new Profit();
				user.setTrans_id(rs.getInt("TRANS_ID"));
				user.setEmail(rs.getString("EMAIL"));
				user.setDate(rs.getString("dat"));
				user.setEntry_price(rs.getFloat("ENTRY_PRICE"));
				user.setExit_price(rs.getFloat("EXIT_PRICE"));
				user.setProfit(rs.getFloat("PROFIT"));
				users.add(user);
			}
			System.out.println("total : " + users.size());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}

	public static int addUser(Profit user) {
		String query = "INSERT INTO LEDGER1 VALUES(trans_id.NEXTVAL,'" + user.getEmail()
				+ "',to_char(sysdate, 'yyyy/mm/dd hh24:mi:ss'),'" + user.getEntry_price() + "','" + user.getExit_price()
				+ "','" + user.getProfit() + "')";
		System.out.println(query);
		int status = DBConnection.dmlAction(query);
		return status;
	}
}
