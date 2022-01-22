package ic.doc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@WebServlet("/downloadServlet")
public class DownloadServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/markdown");
    response.setHeader("Content-disposition", "attachment; filename=query.md");

    try(OutputStream out = response.getOutputStream()) {

      String content = "# " +
              request.getParameter("title") +
              "\n" +
              request.getParameter("description");
      out.write(content.getBytes());
    }
  }
}