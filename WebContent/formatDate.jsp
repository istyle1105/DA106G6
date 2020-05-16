<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
<title>formatDate.jsp 簡測</title>
<style>
     q  {quotes: "\00bb"  ""  ""  "";  color: darkblue;  font-weight: bold;  font-size: 30px;} 
     pre{text-decoration: underline ;  margin-top: 0px;  margin-left: 30px;  font-size: 20px;} 
     b  {color: red; margin:10px;}
</style>
</head>
<body>
                                                                                              <br><q>
參見: new java.text.SimpleDateFormat(<b>"yyyy-MM-dd HH:mm:ss"</b>)                                                                                                              </q><br><pre>

yyyy-MM-dd HH:mm:ss      :<% 
                             java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                             String formatDate = df.format(new java.util.Date());
                             out.println(formatDate);
                          %></pre>                                                            <br><br><q>




參見: &lt;fmt:formatDate  value="\${now}"  pattern=<b>"yyyy-MM-dd  HH:mm:ss"</b> /&gt;                                                                                                  </q> 

<jsp:useBean id="now" scope="page" class="java.util.Date" />                                  <pre>

yyyy-MM-dd               :<fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/>

yyyy-MM-dd HH:mm:ss      :<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss"/>

yyyy-MM-dd HH:mm:ss.SSS  :<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss.SSS"/>  (S:毫秒)

yyyy-MM-dd HH:mm:ss.SSSZ :<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss.SSSZ"/> (Z:時區)

</pre><br>

</body>
</html>