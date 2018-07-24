

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.servlet.http.*;
import org.apache.commons.fileupload.*;
import javax.servlet.annotation.*;


@MultipartConfig
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
	//	String file3 =   (String) request.getAttribute("file");
		Part part = request.getPart("file");
		String submitType = request.getParameter("submit");
		
		FaceDetect detect = new FaceDetect();
		//String s1 = detect.getFaceId(file3);
		
	//	System.out.println(file3);
		
		if(submitType.equals("signupbtn")){
		Customer l = new Customer();
		l.setName(name); 
		l.setPassword(pwd);
		l.setEmailid(email);
		
		cust.addCustomer(l);
		//System.out.println(cust);
		request.getRequestDispatcher("success.html").forward(request, response);
		
	}
	}
}
