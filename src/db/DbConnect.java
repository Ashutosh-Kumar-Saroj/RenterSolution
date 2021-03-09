package db;
import java.sql.*;
import javax.swing.JOptionPane;
public class DbConnect {
    public static Connection c;
    public static Statement st;
    public static PreparedStatement insertRoom;
    public static PreparedStatement insertRenter;
    public static PreparedStatement getRooms;
    public static PreparedStatement getAvailableRooms;
    public static PreparedStatement updateRoomVacantBed;
    public static PreparedStatement updateRenterStatus;
    public static PreparedStatement getRenters;
    public static PreparedStatement getRenterByID;
    public static PreparedStatement getActiveRenters;
    public static PreparedStatement insertPayment;
    public static PreparedStatement checkMonthYear;
    public static PreparedStatement getPaymentsByID;
    static{
        try{
            Class.forName("com.mysql.jdbc.Driver");
            c=DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/renters", "root", "incapp");
            st=c.createStatement();
            insertRoom=c.prepareStatement("insert into rooms values(?,?,?,?,?,?)");
            insertRenter=c.prepareStatement("insert into renter (name,phone,fname,fphone,gender,aadhaar,address,room_no,room_rent,room_security,start_date,payment_mode,status) values(?,?,?,?,?,?,?,?,?,?,?,?,'Active')");
            getRooms=c.prepareStatement("select * from rooms order by room_no ASC");
            checkMonthYear=c.prepareStatement("select * from payments where pay_month=? and pay_year=? and rid=?");
            getAvailableRooms=c.prepareStatement("select * from rooms where vacant_bed > 0 order by room_no ASC");
            getRenters=c.prepareStatement("select * from renter where name like ? order by name ASC");
            getRenterByID=c.prepareStatement("select * from renter where renter_id=?");
            getPaymentsByID=c.prepareStatement("select * from payments where rid=? order by pay_date DESC");
            getActiveRenters=c.prepareStatement("select * from renter where status='Active' order by name ASC");
            
            updateRoomVacantBed=c.prepareStatement("update rooms set vacant_bed=vacant_bed-? where room_no=?");
            insertPayment=c.prepareStatement("insert into payments (rid,name,pay_month,pay_year,amount,pay_mode,pay_date) values(?,?,?,?,?,?,CURDATE())");
            updateRenterStatus=c.prepareStatement("update renter set leave_date=?,status='Deactive' where renter_id=?");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
