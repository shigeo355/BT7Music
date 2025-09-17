// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package murach.download;

import java.io.IOException;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import murach.business.User;
import murach.data.UserIO;
import murach.util.CookieUtil;

public class DownloadServlet extends HttpServlet {
   public DownloadServlet() {
   }

   public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
      String action = request.getParameter("action");
      if (action == null) {
         action = "viewAlbums";
      }

      String url = "/index.jsp";
      if (action.equals("viewAlbums")) {
         url = "/index.jsp";
      } else if (action.equals("checkUser")) {
         url = this.checkUser(request, response);
      } else if (action.equals("viewCookies")) {
         url = "/view_cookies.jsp";
      } else if (action.equals("deleteCookies")) {
         url = this.deleteCookies(request, response);
      }

      this.getServletContext().getRequestDispatcher(url).forward(request, response);
   }

   public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
      String action = request.getParameter("action");
      String url = "/index.jsp";
      if (action.equals("registerUser")) {
         url = this.registerUser(request, response);
      }

      this.getServletContext().getRequestDispatcher(url).forward(request, response);
   }

   private String checkUser(HttpServletRequest request, HttpServletResponse response) {
      String productCode = request.getParameter("productCode");
      HttpSession session = request.getSession();
      session.setAttribute("productCode", productCode);
      User user = (User)session.getAttribute("user");
      String url;
      if (user == null) {
         Cookie[] cookies = request.getCookies();
         String emailAddress = CookieUtil.getCookieValue(cookies, "emailCookie");
         if (emailAddress != null && !emailAddress.equals("")) {
            ServletContext sc = this.getServletContext();
            String path = sc.getRealPath("/WEB-INF/EmailList.txt");
            user = UserIO.getUser(emailAddress, path);
            session.setAttribute("user", user);
            url = "/" + productCode + "_download.jsp";
         } else {
            url = "/register.jsp";
         }
      } else {
         url = "/" + productCode + "_download.jsp";
      }

      return url;
   }

   private String registerUser(HttpServletRequest request, HttpServletResponse response) {
      String email = request.getParameter("email");
      String firstName = request.getParameter("firstName");
      String lastName = request.getParameter("lastName");
      User user = new User();
      user.setEmail(email);
      user.setFirstName(firstName);
      user.setLastName(lastName);
      ServletContext sc = this.getServletContext();
      String path = sc.getRealPath("/WEB-INF/EmailList.txt");
      UserIO.add(user, path);
      HttpSession session = request.getSession();
      session.setAttribute("user", user);
      Cookie c1 = new Cookie("emailCookie", email);
      c1.setMaxAge(63072000);
      c1.setPath("/");
      response.addCookie(c1);
      Cookie c2 = new Cookie("firstNameCookie", firstName);
      c2.setMaxAge(63072000);
      c2.setPath("/");
      response.addCookie(c2);
      String productCode = (String)session.getAttribute("productCode");
      String url = "/" + productCode + "_download.jsp";
      return url;
   }

   private String deleteCookies(HttpServletRequest request, HttpServletResponse response) {
      Cookie[] cookies = request.getCookies();
      Cookie[] var4 = cookies;
      int var5 = cookies.length;

      for(int var6 = 0; var6 < var5; ++var6) {
         Cookie cookie = var4[var6];
         cookie.setMaxAge(0);
         cookie.setPath("/");
         response.addCookie(cookie);
      }

      String url = "/delete_cookies.jsp";
      return url;
   }
}
