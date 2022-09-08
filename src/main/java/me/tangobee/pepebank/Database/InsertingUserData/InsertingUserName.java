package me.tangobee.pepebank.Database.InsertingUserData;

import me.tangobee.pepebank.Database.DBSQL;

import java.sql.Connection;
import java.sql.SQLException;
public class InsertingUserName {

    static String name;
    static String email;
    static String dob;
    static int age;
    static String gender;
    static String phoneno;
    static String address;
    static String government;
    static int networth;
    static String accType;
    static String accountno;
    static String password;

    public InsertingUserName(String name, String email, String dob, int age, String gender, String phoneno, String address, String government, int networth, String accType, String accountno, String password) throws SQLException {
        InsertingUserName.name = name;
        InsertingUserName.email = email;
        InsertingUserName.dob = dob;
        InsertingUserName.age = age;
        InsertingUserName.gender = gender;
        InsertingUserName.phoneno = phoneno;
        InsertingUserName.address = address;
        InsertingUserName.government = government;
        InsertingUserName.networth = networth;
        InsertingUserName.accType = accType;
        InsertingUserName.accountno = accountno;
        InsertingUserName.password = password;


        Connection con = DBSQL.getConnection();
        assert con != null;
        insertingUserInfo(con);
    }
    public static void insertingUserInfo(Connection con) throws SQLException {
        con.prepareStatement("INSERT INTO `banking_system`.`user_info`(`name`, email, dob, age, gender, phoneno, address, governid, networth, acctype, accountno, password) VALUES('"+name+"','"+email+"', '"+dob+"', "+age+", '"+gender+"', '"+phoneno+"', '"+address+"', '"+government+"', "+networth+", '"+accType+"', '"+ accountno +"', '"+password+"');").executeUpdate();
        con.prepareStatement("INSERT INTO `banking_system`.`banking`(transaction, balance, email) VALUES(0, 500, '"+email+"');");
    }
}
