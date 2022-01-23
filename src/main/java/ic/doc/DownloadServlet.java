package ic.doc;

import ic.doc.downloads.Downloadable;
import ic.doc.downloads.HtmlFile;
import ic.doc.downloads.MarkDownFile;
import ic.doc.downloads.PdfFile;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/downloadServlet")
public class DownloadServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest request,
      HttpServletResponse response) throws IOException {
    try {
      String fileType = request.getParameter("file_type");
      if (fileType == null) {
        throw new IllegalArgumentException("Please, choose the download option");
      }
      Downloadable file;
      switch (fileType) {
        case "HTML":
          file = new HtmlFile(request.getParameter("title"), request.getParameter("description"));
          break;
        case "Markdown":
          file = new MarkDownFile(request.getParameter("title"),
              request.getParameter("description"));
          break;
        case "PDF":
          file = new PdfFile(request.getParameter("title"), request.getParameter("description"));
          break;
        default:
          throw new IllegalArgumentException(
              "Could not download due to the absence of query result");
      }

      response.setContentType(file.getContentType());
      response
          .setHeader("Content-disposition", "attachment; filename=\"" + file.getFilename() + "\"");

      OutputStream out = response.getOutputStream();
      byte[] content = file.convertToBytes();
      out.write(content);

    } catch (IllegalArgumentException e) {
      response.setContentType("text/html");
      PrintWriter writer = response.getWriter();
      writer.println("<h2>" + e.getMessage() + "</h2>");
      writer.println("<p><a href=\"/\">Back to Search Page</a></p>");
    }
  }
}