package ic.doc.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.servlet.http.HttpServletResponse;

public class HTMLResultPage implements Page {

  private final String query;
  private final String answer;

  public HTMLResultPage(String query, String answer) {
    this.query = query;
    this.answer = answer;
  }

  private String scrapWiki() {
    File tempFile;
    try {
      tempFile = File.createTempFile("extra_html_content", "txt");
    } catch (IOException e) {
      return null;
    }
    ProcessBuilder pb = new ProcessBuilder("python3",
            "lucky.py",
            query,
            tempFile.getAbsolutePath());

    Process p;
    try {
      p = pb.start();
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
    try {
      p.waitFor();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    try {
      return Files.readString(Path.of(tempFile.getAbsolutePath()));
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  public void writeTo(HttpServletResponse resp) throws IOException {
    resp.setContentType("text/html");
    PrintWriter writer = resp.getWriter();

    // Header
    writer.println("<html>");
    writer.println("<head><title>" + query + "</title></head>");
    writer.println("<body>");

    // Content
    if (answer == null || answer.isEmpty()) {
      writer.println("<h1>Sorry</h1>");
      writer.print("<p>Sorry, we didn't understand <em>" + query + "</em></p>");
    } else {
      writer.println("<h1>" + query + "</h1>");
      writer.println("<p>" + answer.replace("\n", "<br>") + "</p>");
    }

    writer.println("<p><a href=\"/\">Back to Search Page</a></p>");

    String description = "";
    if (!(answer == null || answer.isEmpty())) {
      description = answer;
    }
    // Download Options and Button
    writer.println(
        "<p><form name=\"downloadForm\" method=\"post\" action=\"downloadServlet\">" +
            "<input type=\"text\" name=\"title\" style=\"display:none\" value=\"" +
            query + "\"/>" +
            "<input type=\"text\" name=\"description\" style=\"display:none\" value=\"" +
            description + "\"/>" +
            "<input type=\"radio\" id=\"html\" name=\"file_type\" value=\"HTML\">\n" +
            "<label for=\"html\">HTML file</label><br>\n" +
            "<input type=\"radio\" id=\"md\" name=\"file_type\" value=\"Markdown\">\n" +
            "<label for=\"md\">Markdown file</label><br>" +
            "<input type=\"radio\" id=\"pdf\" name=\"file_type\" value=\"PDF\">\n" +
            "<label for=\"pdf\">PDF</label><br>" +
            "<input type=\"submit\" value=\"Download\">" +
            "</form>" +
            "</p>"
    );

    if (answer != null) {
      String extraContent = scrapWiki();
      if (extraContent != null) {
        writer.println("<p>Additional Content</p>");
        writer.println(extraContent);
      }
    }

    // Footer
    writer.println("</body>");
    writer.println("</html>");
  }
}
