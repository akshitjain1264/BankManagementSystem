package Backend;
import java.sql.*;
import java.lang.Math;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Backend.MySql;

public class Account {
    private String name;
    private long acc_no;
    float interestRate;
    private float bal;
    private String acc_type;
    private String pan;
    private String Address;
    private int pin;

    public Account(){
        acc_no = (long)(Math.abs(Math.random()*1000000000));
    }
    public void setDetails(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        try{
            System.out.print("Enter name: ");
            name = sc.nextLine();
            System.out.print("Enter ac_type: ");
            acc_type = sc.nextLine();
            System.out.print("Enter initial Balance: ");
            bal = sc.nextFloat();
            System.out.print("Enter PAN: ");
            pan = sc.next();
            System.out.print("Enter address: ");
            Address = reader.readLine();
            int pin2 = Integer.MIN_VALUE;
            while(pin!=pin2){
                    System.out.print("Enter pin(4 digits): ");
                    pin = Integer.parseInt(sc.next());
                    System.out.print("Renter PIN: ");
                    pin2 = Integer.parseInt(sc.next());
                if(String.valueOf(pin).length() > 4 ){
                    System.out.println("Please Enter correct pin: ");
                    pin2 = Integer.MIN_VALUE;
                }
                if(pin != pin2){
                    System.out.println("PIN is not correct.");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
                sc.close();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };
    public void createAcc(){
        setDetails();
        try{
            Connection conn = MySql.getConn();
            String sql = "INSERT INTO user(acNo, name, address, PAN, bal, PIN, accType) Values (?,?,?,?,?,?,?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1,acc_no);
            ps.setString(2,name);
            ps.setString(3,Address);
            ps.setString(4,pan);
            ps.setFloat(5,bal);
            ps.setInt(6,pin);
            ps.setString(7,acc_type);
            int i = ps.executeUpdate();
            if(i==1){
                System.out.println("\nYour Account is successfully created.");
                System.out.println("Your Account Number is "+ acc_no+"\n");
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
