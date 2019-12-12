package io.github.komyagin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class WebApp {

    private final String url = "jdbc:postgresql://localhost/postgres";
    private final String user = "postgres";
    private final String password = "qaz1@Wsx";

    /**
     * Connect to the PostgreSQL database
     *
     * @return a Connection object
     */
    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    public static void main(String[] args) {
        WebApp webApp = new WebApp();
        Connection conn = webApp.connect();

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
                rs.close();
                st.close();
            } catch (SQLException e) {
                System.out.println("SQL error!");
            }
        }

    }
}
