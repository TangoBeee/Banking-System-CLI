package me.tangobee.pepebank;

import me.tangobee.pepebank.Auth.Login;
import me.tangobee.pepebank.Auth.Signup;
import me.tangobee.pepebank.functions.ClearScreen;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {


    public static final String bankName = """
                                
                $$$$$$$\\  $$$$$$$$\\ $$$$$$$\\  $$$$$$$$\\       $$$$$$$\\   $$$$$$\\  $$\\   $$\\ $$\\   $$\\\s
                $$  __$$\\ $$  _____|$$  __$$\\ $$  _____|      $$  __$$\\ $$  __$$\\ $$$\\  $$ |$$ | $$  |
                $$ |  $$ |$$ |      $$ |  $$ |$$ |            $$ |  $$ |$$ /  $$ |$$$$\\ $$ |$$ |$$  /\s
                $$$$$$$  |$$$$$\\    $$$$$$$  |$$$$$\\          $$$$$$$\\ |$$$$$$$$ |$$ $$\\$$ |$$$$$  / \s
                $$  ____/ $$  __|   $$  ____/ $$  __|         $$  __$$\\ $$  __$$ |$$ \\$$$$ |$$  $$<  \s
                $$ |      $$ |      $$ |      $$ |            $$ |  $$ |$$ |  $$ |$$ |\\$$$ |$$ |\\$$\\ \s
                $$ |      $$$$$$$$\\ $$ |      $$$$$$$$\\       $$$$$$$  |$$ |  $$ |$$ | \\$$ |$$ | \\$$\\\s
                \\__|      \\________|\\__|      \\________|      \\_______/ \\__|  \\__|\\__|  \\__|\\__|  \\__|\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040\040
                """;
    public static final String white = "\u001B[37m";
    public static final String red = "\u001B[31m";
    public static final String ylo = "\u001B[33m";
    public static final String green = "\u001B[32m";
    public static final String blue = "\u001B[34m";

    public static void main(String[] args) throws InterruptedException, SQLException {

        boolean exit = false;

        while(!exit) {
            ClearScreen.clearScreen();
//        MAIN MENU
            System.out.println(red + bankName + white);
            System.out.println("|-----------------|\n\n");
            System.out.println(ylo + """
                        1. Login
                        
                        2. Signup
                        
                        3. Quit
                                    
                    """ + white);
            System.out.println("|-----------------|\n\n");
            System.out.print("Enter Your Input :- " + "\u001B[36m");

//        TAKING INPUT FROM USER
            Scanner scanner = new Scanner(System.in);
            int input = 0;
            try{
                input = scanner.nextInt();
            }
            catch(Exception e) {
                System.out.println(blue+"Please Enter Correct Input! eg - 1, 2, 3"+white);
                Thread.sleep(1000);
            }

//            CHECK USER CHOICE
            switch (input) {
                case 1 -> Login.login();
                case 2 -> Signup.signup();
                case 3 -> {
                    ClearScreen.clearScreen();
                    System.out.println(green+"""
                                                        
                            █▄▄ █▄█ █▀▀
                            █▄█ ░█░ ██▄
                                             
                                                        
                            █░█░█ █▀▀   █░█░█ █ █░░ █░░   █▀▄▀█ █ █▀ █▀   █▄█ █▀█ █░█
                            ▀▄▀▄▀ ██▄   ▀▄▀▄▀ █ █▄▄ █▄▄   █░▀░█ █ ▄█ ▄█   ░█░ █▄█ █▄█
                            """+white);
//                    System.out.println(green+"BYE! We will miss you. :("+white);
                    Thread.sleep(2000);
                    ClearScreen.clearScreen();
                    exit = true;
                }

                default -> {
                    System.out.println(blue+"\n\nWrong Input!"+white);
                    Thread.sleep(1000);
                }
            }
        }
    }
}
