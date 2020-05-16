package hw3;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;

@WebServlet("/Upload_2")
@MultipartConfig
public class Upload_2 extends HttpServlet {
	private static final long serialVersionUID = -7161546602282667046L;
	String saveDirectory = "/images_uploaded";
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException{
		
		req.setCharacterEncoding("Big5");
		res.setContentType("text/html; charset=Big5");
		PrintWriter out = res.getWriter();
		// 目標路徑
		String realPath = getServletContext().getRealPath(saveDirectory);
		File fsaveDirectory = new File(realPath);
		if(!fsaveDirectory.exists())
			fsaveDirectory.mkdir(); //如果資料路徑不存在該目錄，就自行建立
		
		//建立上傳part
		Part part = req.getPart("upfile1");
		
		//filename
		String filename= getFileNameFromPart(part);
		
		//組裝上傳路徑
		String check = realPath+"/"+filename;
		String route = saveDirectory+"/"+filename;
		part.write(check);
		//使用Session儲存路徑
		req.getSession().setAttribute("check",check); //刪除用
		req.getSession().setAttribute("route",route); //線上預覽用
		System.out.println(req.getSession().getAttribute("check"));
		System.out.println(req.getSession().getAttribute("route"));
	    
		//練習轉送
		req.setAttribute("option", "forward");	    
	    RequestDispatcher dispatcher = req.getRequestDispatcher("/Upload_2.jsp");
	    dispatcher.forward(req, res);

		
	}
		//建立取得檔名的方法
		public String getFileNameFromPart(Part part) {
			String header = part.getHeader("content-disposition");
			System.out.println("header=" + header); //測試標頭
			//從標題頭切割出路徑
			File file = new File(header.substring(header.lastIndexOf("=")+2,header.length()-1));
			String filename = file.getName();
			System.out.println("filename=" + filename); //測試切割狀況
			if(filename.length() == 0) {
				return null;
			}
			return filename;
		}
	
}
