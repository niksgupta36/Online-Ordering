

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.servlet.http.*;
//import org.apache.commons.fileupload.*;
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
        String path = ("/Users/nikhil.gupta/Downloads/");
		String name = request.getParameter("name");
		String pwd = request.getParameter("psw");
		String email = request.getParameter("email");
		Part filePart = request.getPart("file");
		String submitType = request.getParameter("submit");
		String fileName = getFileName(filePart);
		
		OutputStream out = null;
	    InputStream filecontent = null;
	    final PrintWriter writer = response.getWriter();

	    try {
	        out = new FileOutputStream(new File(path + File.separator
	                + fileName));
	        filecontent = filePart.getInputStream();

	        int read = 0;
	        final byte[] bytes = new byte[1024];

	        while ((read = filecontent.read(bytes)) != -1) {
	            out.write(bytes, 0, read);
	        }
	        writer.println("New file " + fileName + " created at " + path);
	    } catch (FileNotFoundException fne) {
	        writer.println("You either did not specify a file to upload or are "
	                + "trying to upload a file to a protected or nonexistent "
	                + "location.");
	        writer.println("<br/> ERROR: " + fne.getMessage());

	    } finally {
	        if (out != null) {
	            out.close();
	        }
	        if (filecontent != null) {
	            filecontent.close();
	        }
	        if (writer != null) {
	            writer.close();
	        }
	    }
	    
	    FaceDetect detect = new FaceDetect();
	File file1 = new File("/Users/nikhil.gupta/Downloads/"+fileName);
		
		String s1 = detect.getFaceId(file1);
	    System.out.println(s1);
		if(submitType.equals("signupbtn")){
		Customer l = new Customer();
		l.setName(name); 
		l.setPassword(pwd);
		l.setEmailid(email);
		
		cust.addCustomer(l);
		//System.out.println(cust);
		//request.getRequestDispatcher("success.html").forward(request, response);
		
	}
	}
	private String getFileName(final Part part) {
	    final String partHeader = part.getHeader("content-disposition");
	   
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(
	                    content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}
}
