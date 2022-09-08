package me.tangobee.pepebank.Database;
//  mysql -u root -p
import java.sql.Connection;
import java.sql.DriverManager;

public class DBSQL {
    public static void main(String[] args) {
        Connection connection = getConnection();
        assert connection != null;
    }


    public static Connection getConnection() {

        try{
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/";
            String username = "root";
            String pass = "yourpass";
            Class.forName(driver);

            Connection conn;

            conn = DriverManager.getConnection(url,username,pass);

            conn.prepareStatement("CREATE DATABASE IF NOT EXISTS banking_system;").executeUpdate();
            conn.prepareStatement("USE banking_system").executeUpdate();
            conn.prepareStatement("CREATE TABLE IF NOT EXISTS user_info(`name` varchar(20) NOT NULL, `email` varchar(100) UNIQUE NOT NULL, `dob` DATE NOT NULL, `age` TINYINT unsigned NOT NULL, `gender` varchar(6) NOT NULL, `phoneno` varchar(20) NOT NULL, `address` text NOT NULL, `governid` varchar(20), `networth` int NOT NULL, `acctype` varchar(8) NOT NULL, `accountno` varchar(100) UNIQUE NOT NULL, `password` varchar(50) NOT NULL);").executeUpdate();
            conn.prepareStatement("CREATE TABLE IF NOT EXISTS banking(transaction INT DEFAULT 0, balance INT DEFAULT 500, email varchar(100) UNIQUE NOT NULL);").executeUpdate();

            return conn;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}



/*
CREATE DATABASE IF NOT EXISTS banking_system;
USE banking_system;
CREATE TABLE IF NOT EXISTS user_info(`name` varchar(20) NOT NULL, `email` varchar(50) unique NOT NULL, `dob` date NOT NULL, `age` tinyint unsigned NOT NULL, `gender` varchar(6) NOT NULL, `phoneno` varchar(20) NOT NULL, `address` text NOT NULL, `governid` varchar(20), `networth` int NOT NULL, `acctype` varchar(8) NOT NULL, `accountno` varchar(20) UNIQUE NOT NULL);
 */
