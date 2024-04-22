import Backend.*;

import javax.sound.midi.Soundbank;
import java.sql.SQLException;
import java.util.*;
public class Main {
    public static void main(String[] args) throws SQLException {
        bank b = new bank();
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Bank Management System.");
        boolean flag = true;
        while(flag){
            System.out.println("1. Deposit\n2. Withdrawal\n3. Transfer\n4. See Bank Statement\n5. Exit");
            System.out.print("Enter your operation: ");
            char ch = sc.next().charAt(0);
            switch(ch){
                case '1' :  b.Deposit(); break;
                case '2' : b.Withdraw(); break;
                case '3' : b.transfer(); break;
                case '4' :
                    System.out.print("Enter account number: ");
                    long acNo = sc.nextLong();
                    b.getStatements(acNo);
                    break;
                case '5' : flag = false; break;
                default :
                    System.out.println("Please choose correct Option.");
            }
        }
    }
}
