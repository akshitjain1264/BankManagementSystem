package Backend;

import java.sql.*;
import java.lang.Math;
import java.util.Scanner;
import Backend.transaction;

public class bank {

    Connection conn = MySql.getConn();

    private float getBalance(long ac_no) throws SQLException {

        String sql = "SELECT Balance from user WHERE acNo = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setLong(1, ac_no);
        ResultSet r = stmt.executeQuery();
        r.next();
        float bal = r.getInt(1);

        return bal;
    }
    public void Deposit() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Account Number: ");
        long acNo = sc.nextLong();
        System.out.print("Enter amount: ");
        float amount = sc.nextFloat();
        Credit(acNo, amount);
        transaction t = new transaction();
        t.addTransaction(acNo, amount, "Deposit");
    }

    public void Withdraw() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Account Number: ");
        long acNo = sc.nextLong();
        System.out.print("Enter amount: ");
        float amount = sc.nextFloat();
        Credit(acNo, amount);
        transaction t = new transaction();
        t.addTransaction(acNo, amount, "Withdrawal");
    }



    private void Credit(long acNo, float amount) throws SQLException {
        String sql = "UPDATE user SET Balance = Balance+"+amount+" WHERE acNo = "+acNo;
        PreparedStatement stmt = conn.prepareStatement(sql);
        int i = stmt.executeUpdate();
        float bal = getBalance(acNo);
        System.out.println(acNo + " is Credited.");
        System.out.println("Balance after Credit: "+bal);
    }

    private void Debit(long acNo, float amount) throws SQLException {
        String sql = "UPDATE user SET Balance = Balance-"+amount+" WHERE acNo = "+acNo;
        PreparedStatement stmt = conn.prepareStatement(sql);
        int i = stmt.executeUpdate();
        float bal = getBalance(acNo);
        System.out.println(acNo + " is debited.");
        System.out.println("Balance after Debit: "+bal);

    }

    public void transfer(long acNo1, long acNo2, int amount) throws SQLException {
        Debit(acNo1, amount);
        Credit(acNo2, amount);
        new transaction().addTransaction(acNo1, amount, "Transferred To");
        new transaction().addTransaction(acNo2, amount, "Transferred From");
    }
}
