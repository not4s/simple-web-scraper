package ic.doc.web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

public class HTMLResultPage implements Page {

  private final String query;
  private final String answer;

  public HTMLResultPage(String query, String answer) {
    this.query = query;
    this.answer = answer;
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

    // Footer
    writer.println("</body>");
    writer.println("</html>");
  }
}
