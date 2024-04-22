package Backend;

import java.sql.*;
import java.lang.Math;
import java.util.Scanner;
import Backend.transaction;

public class bank {

    Connection conn = MySql.getConn();

    private float getBalance(long ac_no) throws SQLException {

        String sql = "SELECT bal from user WHERE acNo = ?";
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
        Debit(acNo, amount);
        transaction t = new transaction();
        t.addTransaction(acNo, amount, "Withdrawal");
    }



    private void Credit(long acNo, float amount) throws SQLException {
        String sql = "UPDATE user SET bal = bal+"+amount+" WHERE acNo = "+acNo;
        PreparedStatement stmt = conn.prepareStatement(sql);
        int i = stmt.executeUpdate();
        float bal = getBalance(acNo);
        System.out.println("Account number "+acNo + " is Credited.");
        System.out.println("Balance after Credit: "+bal);
        System.out.println();
    }

    private void Debit(long acNo, float amount) throws SQLException {
        String sql = "UPDATE user SET bal = bal-"+amount+" WHERE acNo = "+acNo;
        PreparedStatement stmt = conn.prepareStatement(sql);
        int i = stmt.executeUpdate();
        float bal = getBalance(acNo);
        System.out.println("Account number "+acNo + " is debited.");
        System.out.println("Balance after Debit: "+bal);
        System.out.println();
    }

    public void transfer() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter account number: ");
        long acNo1 = sc.nextLong();
        System.out.print("Enter account in which money has to be transferred: ");
        long acNo2 = sc.nextLong();
        System.out.print("Enter amount: ");
        float amount = sc.nextFloat();
        Debit(acNo1, amount);
        Credit(acNo2, amount);
        new transaction().addTransaction(acNo1, amount, "Transferred To");
        new transaction().addTransaction(acNo2, amount, "Transferred From");
        System.out.println();
    }

    public void getStatements(long acNo){
        try{
            String sql = "DESC transactions";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int n=0;
            System.out.println();
            while(rs.next()){
                System.out.print(rs.getString("Field")+"\t\t");
                n++;
            }
            System.out.println();
            sql = "SELECT * from transactions where acNo = " + acNo;
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                for(int i = 1;i<=n;i++) System.out.print(rs.getString(i)+"\t\t");
                System.out.println();
            }
            System.out.println();
        }catch(SQLException e){
            System.out.println(e);
        }

    }
}
