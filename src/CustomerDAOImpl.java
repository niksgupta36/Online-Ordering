import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;  


public class CustomerDAOImpl implements CustomerDAO {
	
	static Connection conn;
	static PreparedStatement ps;
	
	@Override
	public void addCustomer(Customer cust) {
		// TODO Auto-generated method stub
		conn = ConnectionProvider.getCon();
		//System.out.println(conn);
		try {
			ps = conn.prepareStatement("insert into Customer values(?,?,?,?)");
			ps.setString(1, cust.getName());
			ps.setString(2, cust.getPassword());
			ps.setString(3, cust.getEmailid());
			ps.setString(4, "jhwg");
			ps.execute();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
