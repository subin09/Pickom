/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.66
 * Generated at: 2021-06-24 15:10:20 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.member;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class signUp_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("\r\n");
      out.write("<script src=\"https://code.jquery.com/jquery-3.6.0.min.js\" integrity=\"sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=\" crossorigin=\"anonymous\"></script>\r\n");
      out.write("\r\n");
      out.write("<title>회원가입</title>\r\n");
      out.write("<style>\r\n");
      out.write("	/* number 태그 화살표 제거 */\r\n");
      out.write("	input[type=\"number\"]::-webkit-outer-spin-button, input[type=\"number\"]::-webkit-inner-spin-button\r\n");
      out.write("		{\r\n");
      out.write("		-webkit-appearance: none;\r\n");
      out.write("		margin: 0;\r\n");
      out.write("	}\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("	");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../common/header.jsp", out, false);
      out.write("\r\n");
      out.write("	<div class=\"container\">\r\n");
      out.write("		<!-- \r\n");
      out.write("			상대경로에서 사용하는 기호\r\n");
      out.write("			(빈칸) : 현재 폴더\r\n");
      out.write("			/      : 하위 폴더 \r\n");
      out.write("			..     : 상위 폴더 \r\n");
      out.write("		 -->\r\n");
      out.write("	<form action=\"signUp\" method=\"POST\" name =\"signUpForm\" onsubmit=\"return validate();\">\r\n");
      out.write("		<label for=\"id\">아이디*</label>\r\n");
      out.write("        <input type=\"text\" id=\"id\" name=\"id\">\r\n");
      out.write("        <div>\r\n");
      out.write("            <span id=\"checkId\">&nbsp;</span>\r\n");
      out.write("        </div>\r\n");
      out.write("		<!-- <button>중복확인</button><br> -->\r\n");
      out.write("		\r\n");
      out.write("		\r\n");
      out.write("        <label for=\"pw\">비밀번호*</label>\r\n");
      out.write("        <input type=\"password\" id=\"pw1\" name=\"pw1\"><br>\r\n");
      out.write("        <div>\r\n");
      out.write("            <span id=\"checkPwd1\">&nbsp;</span>\r\n");
      out.write("        </div>\r\n");
      out.write("    \r\n");
      out.write("        <label for=\"pw\">비밀번호 확인*</label>\r\n");
      out.write("        <input type=\"password\" id=\"pw2\" name=\"pw2\"><br>\r\n");
      out.write("        <div>\r\n");
      out.write("            <span id=\"checkPwd2\">&nbsp;</span>\r\n");
      out.write("        </div>\r\n");
      out.write("    \r\n");
      out.write("        <label for=\"nickname\">닉네임*</label>\r\n");
      out.write("        <input type=\"text\" id=\"nickname\" name=\"nickname\"><br>\r\n");
      out.write("        <div>\r\n");
      out.write("            <span id=\"checkNickname\">&nbsp;</span>\r\n");
      out.write("        </div>\r\n");
      out.write("    \r\n");
      out.write("        <label for=\"email\">이메일*</label>\r\n");
      out.write("        <input type=\"email\" id=\"email\" name=\"email\"><br>\r\n");
      out.write("        <div>\r\n");
      out.write("            <span id=\"checkEmail\">&nbsp;</span>\r\n");
      out.write("        </div>\r\n");
      out.write("    \r\n");
      out.write("        <label for=\"name\">이름*</label>\r\n");
      out.write("        <input type=\"text\" id=\"name\" name=\"name\"><br>\r\n");
      out.write("        <div>\r\n");
      out.write("            <span id=\"checkName\">&nbsp;</span>\r\n");
      out.write("        </div>\r\n");
      out.write("        \r\n");
      out.write("        <label for=\"phone\">휴대폰*</label>\r\n");
      out.write("        <select class=\"custom-select\" id=\"phone1\" name=\"phone\" required>\r\n");
      out.write("            <option>010</option>\r\n");
      out.write("            <option>011</option>\r\n");
      out.write("            <option>016</option>\r\n");
      out.write("            <option>017</option>\r\n");
      out.write("            <option>019</option>\r\n");
      out.write("        </select>\r\n");
      out.write("        <input type=\"number\" name=\"phone\" class=\"phone\" id=\"phone2\"><br>\r\n");
      out.write("        <input type=\"number\" name=\"phone\" class=\"phone\"  id=\"phone3\"><br>\r\n");
      out.write("        <div>\r\n");
      out.write("            <span id=\"checkPhone\">&nbsp;</span>\r\n");
      out.write("        </div>\r\n");
      out.write("		\r\n");
      out.write("		<br>\r\n");
      out.write("		<div>\r\n");
      out.write("            <label for=\"postcodify_search_button\">우편번호</label>\r\n");
      out.write("	    </div>\r\n");
      out.write("	    <div>\r\n");
      out.write("	        <input type=\"text\" name=\"address\" class=\"form-control postcodify_postcode5\">\r\n");
      out.write("	    </div>\r\n");
      out.write("	    <div>\r\n");
      out.write("	        <button type=\"button\" class=\"btn btn-primary\" id=\"postcodify_search_button\">검색</button>\r\n");
      out.write("	    </div>\r\n");
      out.write("	\r\n");
      out.write("	    <div>\r\n");
      out.write("	        <label for=\"address1\">도로명 주소</label>\r\n");
      out.write("	    </div>\r\n");
      out.write("	    <div>\r\n");
      out.write("	        <input type=\"text\" class=\"form-control postcodify_address\" name=\"address\" id=\"address1\">\r\n");
      out.write("	    </div>\r\n");
      out.write("	\r\n");
      out.write("	    <div>\r\n");
      out.write("	        <label for=\"address2\">상세주소</label>\r\n");
      out.write("	    </div>\r\n");
      out.write("	    <div>\r\n");
      out.write("	        <input type=\"text\" class=\"form-control postcodify_details\" name=\"address\" id=\"address2\">\r\n");
      out.write("        </div>\r\n");
      out.write("        \r\n");
      out.write("        <hr class=\"mb-4\">\r\n");
      out.write("        \r\n");
      out.write("		<button class=\"btn btn-primary btn-lg btn-block\" type=\"submit\">가입하기</button>\r\n");
      out.write("\r\n");
      out.write("  \r\n");
      out.write("		<br>\r\n");
      out.write("		<br>\r\n");
      out.write("		    \r\n");
      out.write("	</form>\r\n");
      out.write("\r\n");
      out.write("    <!--유효성 검사하는 스크립트 !! --> \r\n");
      out.write("    <script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/resources/js/member.js\"></script>\r\n");
      out.write("\r\n");
      out.write("    <script src=\"//d1p7wdleee1q2z.cloudfront.net/post/search.min.js\"></script>\r\n");
      out.write("	<script>\r\n");
      out.write("		// 검색 단추를 누르면 팝업 레이어가 열리도록 설정한다.\r\n");
      out.write("		$(function () {\r\n");
      out.write("			$(\"#postcodify_search_button\").postcodifyPopUp();\r\n");
      out.write("		});\r\n");
      out.write("	</script>\r\n");
      out.write("	\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}