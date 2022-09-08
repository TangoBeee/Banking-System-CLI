package me.tangobee.pepebank.Auth;

import me.tangobee.pepebank.Database.DBSQL;
import me.tangobee.pepebank.Database.InsertingUserData.InsertingUserName;
import me.tangobee.pepebank.functions.ClearScreen;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;
import java.util.UUID;

public class Signup {
    private static final String white = "\u001B[37m";
    private static final String red = "\u001B[31m";
    private static final String ylo = "\u001B[33m";
    private static final String cyan = "\u001B[36m";
    private static final String green = "\u001B[32m";
    public static void signup() throws InterruptedException, SQLException {

        String name = "";
        String email = "";
        String dob = "";
        int age = 0;
        String gender = "";
        String phoneno = "";
        String address = "";
        String government = "";
        int networth = 0;
        String accType = "";


        String accountNo = "";
        String password = "";

        ClearScreen.clearScreen();

        boolean exit = false;

        while(!exit) {

            Object temp;

            System.out.println(red + """
                                        
                     _______ _________ _______  _                 _______\s
                    (  ____ \\\\__   __/(  ____ \\( (    /||\\     /|(  ____ )
                    | (    \\/   ) (   | (    \\/|  \\  ( || )   ( || (    )|
                    | (_____    | |   | |      |   \\ | || |   | || (____)|
                    (_____  )   | |   | | ____ | (\\ \\) || |   | ||  _____)
                          ) |   | |   | | \\_  )| | \\   || |   | || (     \s
                    /\\____) |___) (___| (___) || )  \\  || (___) || )     \s
                    \\_______)\\_______/(_______)|/    )_)(_______)|/      \s
                                                                         \s
                                       
                    """ + white);

            System.out.println(green + "|------------------------------------------------------------|" + white);

            System.out.println("\n");
            System.out.println(ylo + "0. QUIT\n\n" + white);


// GETTING INFO FROM USER

            Scanner scanner = new Scanner(System.in);

            try {
            System.out.print("1. Enter Your Name :- " + "\u001B[36m");
            name = scanner.nextLine();
            if (Objects.equals(name, "0")) {
                break;
            }
            System.out.println("\n");

            System.out.print(white + "2. Enter Your Email-ID :- " + "\u001B[36m");
            temp = scanner.nextLine();
            if (Objects.equals(temp, "0")) {
                break;
            }

//            Verifying if email already exists or not in DB
            Connection con = DBSQL.getConnection();
                assert con != null;
                Statement stmt = con.createStatement();
            String query = "SELECT count(*) FROM `user_info` WHERE email='" + temp.toString() + "';";
            ResultSet emailresult = stmt.executeQuery(query);

            emailresult.next();
            String result = emailresult.getString(1);

            if(temp.toString().matches("^(.+)@(.+)$") && result.equals("0")) {
                email = (String) temp;
            }

//            Verification ENDS here

            else {
                System.out.println(red+"\n\n\n\n\nWrong Input or Email is Already Taken! Please Try Again\n\n\n\n\n");
                Thread.sleep(2000);
                ClearScreen.clearScreen();
                continue;
            }
            System.out.println("\n");

            System.out.print(white + "3. Enter Your DOB (YEAR/MONTH/DAY) :- " + "\u001B[36m");
            temp = scanner.nextLine();

            LocalDate date = LocalDate.parse(temp.toString().replaceAll("/", "-"));
            LocalDate curDate = LocalDate.now();

            int tempdob = 0;


                if (Objects.equals(temp, "0")) break;

            if ((date != null) && (curDate != null)) {
                tempdob = Period.between(date, curDate).getYears();
            }
            else {
                System.out.println(red+"\n\n\n\n\nYou are not adult!\n\n\n\n\n");
                Thread.sleep(2000);
                ClearScreen.clearScreen();
                continue;
            }

            if(temp.toString().matches("^(19|20)\\d\\d[-/.](0[1-9]|1[012])[-/.](0[1-9]|[12][0-9]|3[01])$") && tempdob >= 18 && tempdob <= 200){
                dob = (String) temp;
            }
            else {
                System.out.println(red+"\n\n\n\n\nWrong Input! Please Try Again\n\n\n\n\n");
                Thread.sleep(2000);
                ClearScreen.clearScreen();
                continue;
            }
            System.out.println("\n");

            System.out.print(white + "4. Enter Your Age :- " + "\u001B[36m");
            temp = scanner.nextInt();
            if (Objects.equals(temp, 0)) {
                break;
            }
            else if((Integer)temp >= 18 && (Integer)temp <= 200) {
                age = (Integer) temp;
            }
            else {
                System.out.println(red+"\n\n\n\n\nWrong Input! Please Try Again\n\n\n\n\n");
                Thread.sleep(2000);
                ClearScreen.clearScreen();
                continue;
            }
            System.out.println("\n");

            scanner.nextLine();

            System.out.print(white + "5. Enter Your Gender ('M'ale, 'F'emale, 'O'ther) :- " + "\u001B[36m");
            temp = scanner.nextLine();
            if (Objects.equals(temp, "0")) {
                break;
            }
            else if(temp.toString().matches("^(?:m|M|male|Male|f|F|female|Female|o|O|other|Other)$")) {
                gender = temp.toString();
            }
            else {
                System.out.println(red+"\n\n\n\n\nWrong Input! Please Try Again\n\n\n\n\n");
                Thread.sleep(2000);
                ClearScreen.clearScreen();
                continue;
            }
            System.out.println("\n");

            System.out.print(white + "6. Enter Your Phone-No :- " + "\u001B[36m");
            temp = scanner.nextLine();
            if (Objects.equals(temp, "0")) {
                break;
            }
            else if(temp.toString().matches("^(\\+91[\\-\\s]?)?0?(91)?[6789]\\d{9}$")) {
                phoneno = temp.toString();
            }
            else {
                System.out.println(red+"\n\n\n\n\nWrong Input! Please Try Again\n\n\n\n\n");
                Thread.sleep(2000);
                ClearScreen.clearScreen();
                continue;
            }
            System.out.println("\n");

            System.out.print(white + "7. Enter Your Address :- " + "\u001B[36m");
            address = scanner.nextLine();
            if (Objects.equals(address, "0")) {
                break;
            }
            System.out.println("\n");

            System.out.print(white + "8. Enter Your Government-ID (Optional) :- " + "\u001B[36m");
            government = scanner.nextLine();
            if (Objects.equals(government, "0")) {
                break;
            }
            System.out.println("\n");

            System.out.print(white + "9. Enter Your Net Worth :- " + "\u001B[36m");
            temp = scanner.nextInt();
            if (Objects.equals(temp, 0)) {
                break;
            }
            else if((Integer)temp>0) {
                networth = (Integer)temp;
            }
            else {
                System.out.println(red+"\n\n\n\n\nWrong Input! Please Try Again\n\n\n\n\n");
                Thread.sleep(2000);
                ClearScreen.clearScreen();
                continue;
            }
            System.out.println("\n");

            scanner.nextLine();

            System.out.print(white + "10. What Type Of Accout You Need? 'S'aving/'C'urrent :- " + "\u001B[36m");
            temp = scanner.nextLine();
            if (Objects.equals(temp, "0")) {
                break;
            }
            else if(temp.toString().matches("^(?:s|S|saving|Saving|c|C|current|Current)$")) {
                accType = temp.toString();
            }
            else {
                System.out.println(red+"\n\n\n\n\nWrong Input! Please Try Again\n\n\n\n\n");
                Thread.sleep(2000);
                ClearScreen.clearScreen();
                continue;
            }
            System.out.println("\n\n");
        }

            catch (InputMismatchException e){System.out.println(red+"\n\n\n\n\nWrong Input! Please Try Again\n\n\n\n\n");}

            System.out.println(green + "|------------------------------------------------------------|"+white);

            long temp1 = (long) (Math.random() * 100000000000000L);
            temp = 5200000000000000L + temp1;

            String result = verifyAccount(temp);

            if(!result.equals("0")) {
                verifyAccount(temp);
            }

            else{
                accountNo = temp.toString();
            }

            UUID genpass = UUID.randomUUID();

            password = genpass.toString().replaceAll("_", "").replaceAll("-", "");

            new InsertingUserName(name, email, dob, age, gender, phoneno, address, government, networth, accType, accountNo, password);
            boolean innerexit = false;
            ClearScreen.clearScreen();
            while(!innerexit) {
                System.out.println(" • Your Account No. is: " + accountNo);
                System.out.println(" • Your Account Password is: " + password);
                System.out.print("Have you noted your Account no. and Account password? (yes/no): ");
                String input = scanner.nextLine();
                if(input.equals("yes") || input.equals("Yes") || input.equals("YES")) {
                    innerexit = true;
                }
                else if(input.equals("no") || input.equals("No") || input.equals("NO")) {
                    continue;
                }
                else{
                    System.out.println(red+"Wrong Input!!!"+white);
                }

                ClearScreen.clearScreen();

            }
            System.out.print(cyan+"""
                                        
                    ██╗░░░██╗░█████╗░██╗░░░██╗██████╗░  ░█████╗░░█████╗░░█████╗░░█████╗░██╗░░░██╗███╗░░██╗████████╗
                    ╚██╗░██╔╝██╔══██╗██║░░░██║██╔══██╗  ██╔══██╗██╔══██╗██╔══██╗██╔══██╗██║░░░██║████╗░██║╚══██╔══╝
                    ░╚████╔╝░██║░░██║██║░░░██║██████╔╝  ███████║██║░░╚═╝██║░░╚═╝██║░░██║██║░░░██║██╔██╗██║░░░██║░░░
                    ░░╚██╔╝░░██║░░██║██║░░░██║██╔══██╗  ██╔══██║██║░░██╗██║░░██╗██║░░██║██║░░░██║██║╚████║░░░██║░░░
                    ░░░██║░░░╚█████╔╝╚██████╔╝██║░░██║  ██║░░██║╚█████╔╝╚█████╔╝╚█████╔╝╚██████╔╝██║░╚███║░░░██║░░░
                    ░░░╚═╝░░░░╚════╝░░╚═════╝░╚═╝░░╚═╝  ╚═╝░░╚═╝░╚════╝░░╚════╝░░╚════╝░░╚═════╝░╚═╝░░╚══╝░░░╚═╝░░░
                                        
                    ██╗░░██╗░█████╗░░██████╗  ██████╗░███████╗███████╗███╗░░██╗
                    ██║░░██║██╔══██╗██╔════╝  ██╔══██╗██╔════╝██╔════╝████╗░██║
                    ███████║███████║╚█████╗░  ██████╦╝█████╗░░█████╗░░██╔██╗██║
                    ██╔══██║██╔══██║░╚═══██╗  ██╔══██╗██╔══╝░░██╔══╝░░██║╚████║
                    ██║░░██║██║░░██║██████╔╝  ██████╦╝███████╗███████╗██║░╚███║
                    ╚═╝░░╚═╝╚═╝░░╚═╝╚═════╝░  ╚═════╝░╚══════╝╚══════╝╚═╝░░╚══╝
                                        
                    ░█████╗░██████╗░███████╗░█████╗░████████╗███████╗██████╗░██╗  ░██╗░░░░░░░██╗███████╗  ░█████╗░██████╗░███████╗
                    ██╔══██╗██╔══██╗██╔════╝██╔══██╗╚══██╔══╝██╔════╝██╔══██╗██║  ░██║░░██╗░░██║██╔════╝  ██╔══██╗██╔══██╗██╔════╝
                    ██║░░╚═╝██████╔╝█████╗░░███████║░░░██║░░░█████╗░░██║░░██║██║  ░╚██╗████╗██╔╝█████╗░░  ███████║██████╔╝█████╗░░
                    ██║░░██╗██╔══██╗██╔══╝░░██╔══██║░░░██║░░░██╔══╝░░██║░░██║╚═╝  ░░████╔═████║░██╔══╝░░  ██╔══██║██╔══██╗██╔══╝░░
                    ╚█████╔╝██║░░██║███████╗██║░░██║░░░██║░░░███████╗██████╔╝██╗  ░░╚██╔╝░╚██╔╝░███████╗  ██║░░██║██║░░██║███████╗
                    ░╚════╝░╚═╝░░╚═╝╚══════╝╚═╝░░╚═╝░░░╚═╝░░░╚══════╝╚═════╝░╚═╝  ░░░╚═╝░░░╚═╝░░╚══════╝  ╚═╝░░╚═╝╚═╝░░╚═╝╚══════╝
                                        
                    ░██████╗███████╗███╗░░██╗██████╗░██╗███╗░░██╗░██████╗░  ██╗░░░██╗░█████╗░██╗░░░██╗  ████████╗░█████╗░
                    ██╔════╝██╔════╝████╗░██║██╔══██╗██║████╗░██║██╔════╝░  ╚██╗░██╔╝██╔══██╗██║░░░██║  ╚══██╔══╝██╔══██╗
                    ╚█████╗░█████╗░░██╔██╗██║██║░░██║██║██╔██╗██║██║░░██╗░  ░╚████╔╝░██║░░██║██║░░░██║  ░░░██║░░░██║░░██║
                    ░╚═══██╗██╔══╝░░██║╚████║██║░░██║██║██║╚████║██║░░╚██╗  ░░╚██╔╝░░██║░░██║██║░░░██║  ░░░██║░░░██║░░██║
                    ██████╔╝███████╗██║░╚███║██████╔╝██║██║░╚███║╚██████╔╝  ░░░██║░░░╚█████╔╝╚██████╔╝  ░░░██║░░░╚█████╔╝
                    ╚═════╝░╚══════╝╚═╝░░╚══╝╚═════╝░╚═╝╚═╝░░╚══╝░╚═════╝░  ░░░╚═╝░░░░╚════╝░░╚═════╝░  ░░░╚═╝░░░░╚════╝░
                                        
                    ███╗░░░███╗░█████╗░██╗███╗░░██╗  ███╗░░░███╗███████╗███╗░░██╗██╗░░░██╗░░░
                    ████╗░████║██╔══██╗██║████╗░██║  ████╗░████║██╔════╝████╗░██║██║░░░██║░░░
                    ██╔████╔██║███████║██║██╔██╗██║  ██╔████╔██║█████╗░░██╔██╗██║██║░░░██║░░░
                    ██║╚██╔╝██║██╔══██║██║██║╚████║  ██║╚██╔╝██║██╔══╝░░██║╚████║██║░░░██║░░░
                    ██║░╚═╝░██║██║░░██║██║██║░╚███║  ██║░╚═╝░██║███████╗██║░╚███║╚██████╔╝██╗
                    ╚═╝░░░░░╚═╝╚═╝░░╚═╝╚═╝╚═╝░░╚══╝  ╚═╝░░░░░╚═╝╚══════╝╚═╝░░╚══╝░╚═════╝░╚═╝""");
            Thread.sleep(500);
            System.out.print("➊\040\040");
            Thread.sleep(500);
            System.out.print("➋\040\040");
            Thread.sleep(500);
            System.out.println("➌\040"+white);
            Thread.sleep(2000);
            ClearScreen.clearScreen();

            exit = true;
        }

    }

    public static String verifyAccount(Object temp) throws SQLException {
        Connection con = DBSQL.getConnection();
        assert con != null;
        Statement stmt = con.createStatement();
        String query = "SELECT count(*) FROM `user_info` WHERE accountno='" + temp.toString() + "';";
        ResultSet account = stmt.executeQuery(query);

        account.next();
        return account.getString(1);
    }
}
