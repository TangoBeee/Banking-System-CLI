package me.tangobee.pepebank.Auth;

import me.tangobee.pepebank.Banking.MainMenu;
import me.tangobee.pepebank.Database.DBSQL;
import me.tangobee.pepebank.functions.ClearScreen;

import java.sql.*;
import java.util.Objects;
import java.util.Scanner;

public class Login {
    public static final String white = "\u001B[37m";
    public static final String red = "\u001B[31m";
    public static final String ylo = "\u001B[33m";
    public static final String green = "\u001B[32m";

    public static void login() throws InterruptedException, SQLException {
        boolean exit = false;
        ClearScreen.clearScreen();

        while(!exit) {

            System.out.println(red + """
                    
                     _        _______  _______ _________ _      \s
                    ( \\      (  ___  )(  ____ \\\\__   __/( (    /|
                    | (      | (   ) || (    \\/   ) (   |  \\  ( |
                    | |      | |   | || |         | |   |   \\ | |
                    | |      | |   | || | ____    | |   | (\\ \\) |
                    | |      | |   | || | \\_  )   | |   | | \\   |
                    | (____/\\| (___) || (___) |___) (___| )  \\  |
                    (_______/(_______)(_______)\\_______/|/    )_)
                                                                \s
                               
                    """ + white);

            System.out.print(ylo + "Enter Your Email ('Q'uit) :- " + "\u001B[36m");
            Scanner scanner = new Scanner(System.in);
            String iUsername = scanner.nextLine();

            if(Objects.equals(iUsername, "Q") || Objects.equals(iUsername, "q")) {
                exit = true;
                continue;
            }

            System.out.print(ylo + "\nEnter Your Password ('Q'uit) :- " + "\u001B[36m");
            String iPassword = scanner.nextLine();

            if(Objects.equals(iPassword, "Q") || Objects.equals(iPassword, "q")) {
                exit = true;
                continue;
            }

            Connection con = DBSQL.getConnection();
            assert con != null;
            String emailsql = "SELECT email FROM user_info WHERE email='"+iUsername+"';";
            PreparedStatement emailprepStmt = con.prepareStatement(emailsql);
            ResultSet emailresult = emailprepStmt.executeQuery();


            String passsql = "SELECT password FROM user_info WHERE email='"+iUsername+"' AND password='"+iPassword+"';";
            PreparedStatement passprepStmt = con.prepareStatement(passsql);
            ResultSet passresult = passprepStmt.executeQuery();

            System.out.println(white);

            if(emailresult.next() && passresult.next()) {
                ClearScreen.clearScreen();

                String namesql = "SELECT name FROM user_info WHERE email='"+iUsername+"' AND password='"+iPassword+"';";
                PreparedStatement nameprepStmt = con.prepareStatement(namesql);
                ResultSet nameresult = nameprepStmt.executeQuery();
                nameresult.next();
                String name = nameresult.getString(1);


                String accsql = "SELECT accountno FROM user_info WHERE email='"+iUsername+"' AND password='"+iPassword+"';";
                PreparedStatement accprepStmt = con.prepareStatement(accsql);
                ResultSet accresult = accprepStmt.executeQuery();
                accresult.next();
                String acc = accresult.getString(1);

                MainMenu.mainMenu(name, acc, iUsername);
                Thread.sleep(2000);
                ClearScreen.clearScreen();
            }
            else{
                ClearScreen.clearScreen();
                System.out.println(white+"Wrong Credential!\n"+white);
            }

        }
    }
}
