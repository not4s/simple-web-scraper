package ic.doc;

import ic.doc.downloads.Downloadable;
import ic.doc.downloads.HtmlFile;
import ic.doc.downloads.MarkDownFile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

@WebServlet("/downloadServlet")
public class DownloadServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {
    try {
      String fileType = request.getParameter("file_type");
      if (fileType == null) {
        throw new IllegalArgumentException("Please, choose the download option");
      }
      Downloadable file;
      if (fileType.equals("HTML")) {
        file = new HtmlFile(request.getParameter("title"), request.getParameter("description"));
        response.setContentType("text/html");
        response.setHeader("Content-disposition", "attachment; filename=query.html");
      } else if (fileType.equals("Markdown")) {
        file = new MarkDownFile(request.getParameter("title"), request.getParameter("description"));
        response.setContentType("text/markdown");
        response.setHeader("Content-disposition", "attachment; filename=query.md");
      } else {
        throw new IllegalArgumentException("Could not download due to the absence of query result");
      }
      OutputStream out = response.getOutputStream();
      byte[] content = file.getTextContentBytes();
      out.write(content);

    } catch (IllegalArgumentException e) {

      response.setContentType("text/html");
      PrintWriter writer = response.getWriter();
      writer.println("<h2>" + e.getMessage() + "</h2>");
      writer.println("<p><a href=\"/\">Back to Search Page</a></p>");
    }
  }
}