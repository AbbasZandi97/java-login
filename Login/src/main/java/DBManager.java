import java.sql.*;

// This class handles everything related to database of our application
public class DBManager {

    final private String defaultUrl;
    final private String dbUserName;
    final private String dbPassword;
    final private String dbUrl;



    public DBManager() {
        this.defaultUrl = "jdbc:postgresql://localhost:5432/postgres";
        this.dbUserName = "postgres";
        this.dbPassword = "a19391945";
        this.dbUrl = "jdbc:postgresql://localhost:5432/login";
        startDataBase();
    }

    public void startDataBase() {

        // this query checks whether "login" database is available or not
        String checkDbExistsQuery = "SELECT 1 FROM pg_database WHERE datname = 'login'";
        String createDbQuery = "CREATE DATABASE login";
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
                try (Connection loginCon = DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
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

    public void saveToDB(String userN, String passW) {
        String sql = "INSERT INTO users(username, password) VALUES(?,?)";
        try (Connection con = DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
        PreparedStatement pst = con.prepareStatement(sql)) {
            System.out.println("Inserting records into the table...");
            pst.setString(1,userN);
            pst.setString(2,passW);
            int rowsInserted = pst.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("User added to the database successfully !!");
            } else {
                System.out.println("nothing added to database... OoOOOOops !!");
            }

        } catch (SQLException e) {
            System.out.println("SQL EXCEPTION OCCURRED...");
        }
    }


    public void identityCheck(String userN, String passW) {
        String sql = "SELECT username, password FROM users WHERE username = ? AND password = ?";
        try (Connection con = DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1,userN);
            pst.setString(2,passW);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Welcome in !");
                } else {
                    System.out.println("User not found!");
                    System.out.println("Try later.");
                }
            }

        } catch (SQLException e) {
            System.out.println("SQL EXCEPTION OCCURRED...");
        }
    }
}
