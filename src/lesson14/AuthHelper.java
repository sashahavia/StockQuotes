package lesson14;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbutil.DbHelper;

public class AuthHelper {
	
	public static UserData authenticateUser (String username, String password) {
		UserData user = null;
		if (null!=username && null != password) {
			String query = "SELECT first_name, last_name FROM users WHERE username=? AND pwd=?";
			try (Connection con = DbHelper.getConnection(ProjectConstants.getAuthDataSourceName());
				 PreparedStatement pstmt = con.prepareStatement(query)){
				pstmt.setString(1, username);
				pstmt.setString(2, password);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					user = new UserData(rs.getString(1), rs.getString(2), username);
				}
			} catch (SQLException e) {
				DbHelper.handleSQLException("Failed to authenticate the user", e);
			} catch (Exception e) {
				DbHelper.handleNonSQLException("Failed to authenticate the user", e);
			}
		}
		return user;
	}

}
