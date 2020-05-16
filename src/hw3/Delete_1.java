package hw3;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;

@WebServlet("/Delete_1")
@MultipartConfig
public class Delete_1 extends HttpServlet {
	private static final long serialVersionUID = -7161546602282667046L;
	String saveDirectory = "/images_uploaded";
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException{
		
		req.setCharacterEncoding("Big5");
		res.setContentType("text/html; charset=Big5");
		PrintWriter out = res.getWriter();
		
		//從context撈到路徑
		System.out.println(req.getServletContext().getAttribute("check")); //測試是否抓到刪除的阿飄路徑

		//刪除檔案
		File file1 = new File((String)req.getServletContext().getAttribute("check"));
		file1.delete();
		
		//加上預覽圖+刪除按鈕
		out.println("你的檔案已經刪除");		
		
		//注意本題的特色，上傳的預覽路徑與刪除的路徑並不盡相同~!!
	}	
}
