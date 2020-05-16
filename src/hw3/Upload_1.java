package hw3;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;

@WebServlet("/Upload_1")
@MultipartConfig
public class Upload_1 extends HttpServlet {
	private static final long serialVersionUID = -7161546602282667046L;
	String saveDirectory = "/images_uploaded";
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException{
		
		req.setCharacterEncoding("Big5");
		res.setContentType("text/html; charset=Big5");
		PrintWriter out = res.getWriter();
//		out.println("ContentType=" +req.getContentType()); //測試ContentType
//		out.println("<br>");
		// 目標路徑
		String realPath = getServletContext().getRealPath(saveDirectory);
//		out.println("realPath=" +realPath); //測試目標路徑
//		out.println("<br>");
		File fsaveDirectory = new File(realPath);
		if(!fsaveDirectory.exists())
			fsaveDirectory.mkdir(); //如果資料路徑不存在該目錄，就自行建立
		
		//建立上傳part
		Part part = req.getPart("upfile1");
		
		//filename
		String filename= getFileNameFromPart(part);
		
		//組裝上傳路徑
		String check = realPath+"/"+filename;
		part.write(check);
		String route = saveDirectory+"/"+filename;
		//使用ServletContext儲存路徑
		req.getServletContext().setAttribute("check",check);
		req.getServletContext().setAttribute("route",route);
		System.out.println(req.getServletContext().getAttribute("check"));
		System.out.println(req.getServletContext().getAttribute("route"));
		
		//加上預覽圖+刪除按鈕
//		out.println("<img src=\""+getServletContext().getAttribute("check")+"\">"); 錯誤寫法會踩到阿飄路徑的坑
		out.println("<img src=\""+req.getContextPath()+req.getServletContext().getAttribute("route")+"\">");
		out.println("<FORM action=\"Delete_1\" method=post  enctype=\"multipart/form-data\">");
	    out.println("<input type=\"submit\" value=\"刪除\">");
	    out.println("</FORM>");
		
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
