package com.spstudio.love.web.common;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

public class DatabaseHelper {
	
	public void doQuery(DataSource ds, String sql){
		Connection conn = null;
        Statement stmt = null;
        try {
            conn = ds.getConnection();
            stmt = conn.createStatement();
            stmt.execute(sql);
        }catch (SQLException e){
            return;
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlex) {
                    // ignore, as we can't do anything about it here
                }
                stmt = null;
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException sqlex) {
                    // ignore, as we can't do anything about it here
                }
                conn = null;
            }
        }
	}
}
