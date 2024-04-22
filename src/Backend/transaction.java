package Backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import Backend.MySql;

public class transaction {
    private short id;
    public transaction(){ id = (short) Math.abs(Math.random()*10000000); }
    public void addTransaction(long acNo, float amount, String type){
        try{
            Connection conn = MySql.getConn();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO transactions(transc_id, acNo, amount, status, type)\n" +
                    "Values(?, ?, ?, ?, ?)");
            stmt.setShort(1,id);
            stmt.setLong(2,acNo);
            stmt.setFloat(3,amount);
            stmt.setString(4, String.valueOf(true));
            stmt.setString(5, type);
            stmt.executeUpdate();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

}
