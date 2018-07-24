

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet("/CustomerSection")
public class CustomerSection extends HttpServlet {
	private static final long serialVersionUID = 1L;

  
    public CustomerSection() {
        // TODO Auto-generated constructor stub
    	super();
    }

	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
        CustomerDAO cust = new CustomerDAOImpl();
		
		String name = request.getParameter("name");
		String pwd = request.getParameter("psw");
		String email = request.getParameter("email");
		String submitType = request.getParameter("submit");
		
		if(submitType.equals("signupbtn")){
		Customer l = new Customer();
		l.setName(name); 
		l.setPassword(pwd);
		l.setEmailid(email);
	
		cust.addCustomer(l);
		System.out.println(cust);
		request.getRequestDispatcher("success.html").forward(request, response);
		
	}
	}
}
