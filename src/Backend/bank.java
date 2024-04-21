package Backend;

import java.sql.*;
import java.lang.Math;
import Backend.MySql;

public class bank {
    private short id;
    public bank(){
        id = (short)(Math.abs(Math.random()*10000000));
    }


    public void Credit(long acNo, int amount) throws SQLException {
        String CrSql = "UPDATE user SET Balance = Balance+"+amount+" WHERE acNo = "+acNo;
        int bal = Transaction(acNo, CrSql);
        System.out.println(acNo + " is Credited.");
        System.out.println("Balance after Credit: "+bal);
    }

    public void Debit(long acNo, int amount) throws SQLException {
        String drSql = "UPDATE user SET Balance = Balance-"+amount+" WHERE acNo = "+acNo;
        int bal = Transaction(acNo, drSql);
        System.out.println(acNo + " is debited.");
        System.out.println("Balance after Debit: "+bal);

    }

    public void transerTo(long acNo1, long acNo2, int amount) throws SQLException {
        bank b = new bank();
        short id = b.id;
        boolean status = Boolean.parseBoolean(null);
        Debit(acNo1, amount);
        Credit(acNo2, amount);
        status = true;
        System.out.println(id);
        try{
            Connection conn = MySql.getConn();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO transactions(transc_id, acNo, amount, status)\n" +
                        "Values(?, ?, ?, ?)");
            stmt.setShort(1,id);
            stmt.setLong(2,acNo1);
            stmt.setLong(3,amount);
            stmt.setString(4, String.valueOf(status));
            stmt.executeUpdate();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    int Transaction(long ac_no, String sql1) throws SQLException {     // Amount is added to balance of given account number.
        try {
            Connection conn = MySql.getConn();
            String sql = sql1;
            PreparedStatement stmt = conn.prepareStatement(sql);
            int i = stmt.executeUpdate();
            if(i>1) throw new SQLException("More than one account Updated.");
            sql = "SELECT Balance from user WHERE acNo = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, ac_no);
            ResultSet r = stmt.executeQuery();
            r.next();
            int bal = r.getInt(1);

            return bal;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return 0;
    }


//    void deposit();
//    void checkBalance();
//    void Statements();
}
