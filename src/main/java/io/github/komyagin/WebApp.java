package io.github.komyagin;

import io.github.komyagin.util.DbConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class WebApp {


    public static void main(String[] args) {
        Connection conn = DbConnection.getConnection();

        if(conn != null) {
            try {
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM lab.person");
                while (rs.next()) {
                    System.out.println("Persons: ");
                    System.out.print(rs.getInt(1) + " ");
                    System.out.print(rs.getString(2) + " ");
                    System.out.print(rs.getString(3) + " ");
                    System.out.print(rs.getString(4) + " ");
                    System.out.print(rs.getString(5) + " ");
                }

                // TEST insert from code - SUCCESS!
                //String sql = "INSERT INTO lab.person VALUES (5, 'Vitaliy', 'Milonov', 'milonov.v@gmail.com', 'mobiles')";
                //st.executeQuery(sql);

                rs.close();
                st.close();
            } catch (SQLException e) {
                System.out.println("SQL error!");
            }
        }

    }
}
