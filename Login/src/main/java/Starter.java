import java.sql.*;

public class Starter {

    final private String defaultUrl;
    final private String dbUserName;
    final private String dbPassword;



    public Starter() {
        this.defaultUrl = "jdbc:postgresql://localhost:5432/postgres";
        this.dbUserName = "postgres";
        this.dbPassword = "a19391945";
    }

    public void createDataBase() {

        // this query checks whether "login" database is available or not
        String checkDbExistsQuery = "SELECT 1 FROM pg_database WHERE datname = 'login'";
        String createDbQuery = "CREATE DATABASE login";
        String loginDbUrl = "jdbc:postgresql://localhost:5432/login";
        String tableCreateQuery = "CREATE TABLE users ("
                + "id SERIAL PRIMARY KEY,"
                + "username VARCHAR(255),"
                + "password VARCHAR(255))";
        try (Connection con = DriverManager.getConnection(defaultUrl,dbUserName,dbPassword);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(checkDbExistsQuery)
        ){
            if (!rs.next()) {
                // if "login" database is not available, it is being created.
                st.executeUpdate(createDbQuery);
                System.out.println("Database initialized successfully....");

                // now the table is created inside the database.
                try (Connection loginCon = DriverManager.getConnection(loginDbUrl,dbUserName,dbPassword);
                Statement loginSt = loginCon.createStatement()
                ) {
                    loginSt.executeUpdate(tableCreateQuery);
                    System.out.println("Created users table in login database...");
                }

            } else {
                System.out.println("Database already exists.");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
