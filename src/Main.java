import Backend.*;

import java.sql.SQLException;
import java.util.*;
public class Main {
    public static void main(String[] args) throws SQLException {
        Account acc = new Account();
        acc.createAcc();

        System.out.println("Welcome to Bank Management System.");
        System.out.println("Enter your operation: ");
        System.out.println("1. Deposit\n2.Withdrawal\n4.Transfer");
    }
}
