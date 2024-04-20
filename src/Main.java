import Backend.bank;

import java.sql.SQLException;
import java.util.*;
public class Main {
    public static void main(String[] args) throws SQLException {
        bank b = new bank();
        System.out.print("Enter first account no: ");
        Scanner sc = new Scanner(System.in);
        long acNo1 = sc.nextLong();
        System.out.print("Enter second account no: ");
        long acNo2 = sc.nextLong();
        System.out.print("Enter amount: ");
        int amt = sc.nextInt();
        b.transerTo(acNo1, acNo2, amt);
    }
}
