package me.tangobee.pepebank.Banking;

import me.tangobee.pepebank.Database.DBSQL;
import me.tangobee.pepebank.functions.ClearScreen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class MainMenu {
    private static final String white = "\u001B[37m";
    private static final String red = "\u001B[31m";
    private static final String ylo = "\u001B[33m";
    private static final String cyan = "\u001B[36m";
    private static final String green = "\u001B[32m";
    public static void mainMenu(String name, String accountno, String email) throws InterruptedException, SQLException {

        int dep;
        int with = 0;

        boolean exit = false;

        while(!exit) {

            System.out.println(red+"""
                                
                $$$$$$$\\  $$$$$$$$\\ $$$$$$$\\  $$$$$$$$\\       $$$$$$$\\   $$$$$$\\  $$\\   $$\\ $$\\   $$\\\s
                $$  __$$\\ $$  _____|$$  __$$\\ $$  _____|      $$  __$$\\ $$  __$$\\ $$$\\  $$ |$$ | $$  |
                $$ |  $$ |$$ |      $$ |  $$ |$$ |            $$ |  $$ |$$ /  $$ |$$$$\\ $$ |$$ |$$  /\s
                $$$$$$$  |$$$$$\\    $$$$$$$  |$$$$$\\          $$$$$$$\\ |$$$$$$$$ |$$ $$\\$$ |$$$$$  / \s
                $$  ____/ $$  __|   $$  ____/ $$  __|         $$  __$$\\ $$  __$$ |$$ \\$$$$ |$$  $$<  \s
                $$ |      $$ |      $$ |      $$ |            $$ |  $$ |$$ |  $$ |$$ |\\$$$ |$$ |\\$$\\ \s
                $$ |      $$$$$$$$\\ $$ |      $$$$$$$$\\       $$$$$$$  |$$ |  $$ |$$ | \\$$ |$$ | \\$$\\\s
                \\__|      \\________|\\__|      \\________|      \\_______/ \\__|  \\__|\\__|  \\__|\\__|  \\__|\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040
                
                    """+white);

            System.out.print(ylo+"""
                    \040\040Welcome\040""" + cyan + name + ylo + """
                    
                    \040\040Your Account Number is\040""" + cyan + accountno + ylo + """
                                    
                                    
                    \040\040\040\040\040\040A. Check Balance
                    \040\040\040\040\040\040B. Deposit
                    \040\040\040\040\040\040C. Withdraw
                    \040\040\040\040\040\040D. Previous Transaction
                    \040\040\040\040\040\040E. EXIT
                                    
                    \040\040\040\040Input:\040"""+white);
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();

            System.out.println("\n\n\n");

            switch (userInput) {
                case "A" -> {
                    Connection con = DBSQL.getConnection();
                    assert con != null;
                    String balsql = "SELECT balance FROM banking WHERE email='"+email+"';";
                    PreparedStatement balprepStmt = con.prepareStatement(balsql);
                    ResultSet balresult = balprepStmt.executeQuery();
                    balresult.next();

                    String bal = balresult.getString(1);


                    System.out.println(green+"Your Balance is: "+bal+white);
                    Thread.sleep(2000);
                    ClearScreen.clearScreen();
                }
                case "B" -> {
                    Connection con = DBSQL.getConnection();
                    assert con != null;
                    String depsql = "SELECT balance FROM banking WHERE email='"+email+"';";
                    PreparedStatement depprepStmt = con.prepareStatement(depsql);
                    ResultSet depresult = depprepStmt.executeQuery();
                    depresult.next();

                    String tempdep = depresult.getString(1);

                    int totalbal = Integer.parseInt(tempdep);

                    System.out.print(green+"Enter your deposit amount: " +white);
                    dep = scanner.nextInt();
                    scanner.nextLine();
                    totalbal += dep;

                    con.prepareStatement("UPDATE banking SET balance="+totalbal+"").executeUpdate();

                    System.out.println("Your amount has been deposit!");
                    Thread.sleep(2000);
                    ClearScreen.clearScreen();
                }
                case "C" -> {
                    Connection con = DBSQL.getConnection();
                    assert con != null;
                    String withsql = "SELECT balance FROM banking WHERE email='"+email+"';";
                    PreparedStatement withprepStmt = con.prepareStatement(withsql);
                    ResultSet withresult = withprepStmt.executeQuery();
                    withresult.next();

                    String tempwith = withresult.getString(1);

                    int totalwith = Integer.parseInt(tempwith);

                    System.out.print(green+"Enter your withdrawal amount: "+white);
                    with = scanner.nextInt();
                    scanner.nextLine();
                    totalwith = totalwith - with;


                    con.prepareStatement("UPDATE banking SET balance="+totalwith+"").executeUpdate();

                    System.out.println("You have withdrawal your amount");
                    Thread.sleep(2000);
                    ClearScreen.clearScreen();
                }
                case "D" -> {
                    Connection con = DBSQL.getConnection();
                    assert con != null;
                    con.prepareStatement("UPDATE banking SET transaction="+with+"").executeUpdate();
                    String tbalsql = "SELECT transaction FROM banking WHERE email='"+email+"';";
                    PreparedStatement tbalprepStmt = con.prepareStatement(tbalsql);
                    ResultSet tbalresult = tbalprepStmt.executeQuery();
                    tbalresult.next();

                    String tbal = tbalresult.getString(1);

                    System.out.println(with);

                    System.out.println(green+"Your previous transaction is: "+tbal+white);
                    Thread.sleep(2000);
                    ClearScreen.clearScreen();
                }
                case "E" -> {
                    System.out.print(red+"Exiting");
                    Thread.sleep(500);
                    System.out.print(".");
                    Thread.sleep(500);
                    System.out.print(".");
                    Thread.sleep(500);
                    System.out.print(".");
                    ClearScreen.clearScreen();
                    exit = true;
                }
                default -> {
                    System.out.println(red+"Wrong Input!"+white);
                    Thread.sleep(2000);
                    ClearScreen.clearScreen();
                }
            }
        }
    }
}
