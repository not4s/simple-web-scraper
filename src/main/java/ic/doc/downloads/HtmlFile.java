package ic.doc.downloads;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class HtmlFile extends Downloadable {

  public HtmlFile(String title, String description) throws IOException {
    super(title, description, ".html", "text/html");
  }

  @Override
  protected void saveToFile() {
    StringBuilder sb = new StringBuilder();
    BufferedWriter writer;
    try {
      writer = new BufferedWriter(new FileWriter(file));
    } catch (IOException e) {
      e.printStackTrace();
      return;
    }
    sb.append("<!DOCTYPE html>\n")
        .append("<html lang=\"en\">\n")
        .append("<head>\n")
        .append("<meta charset=\"UTF-8\">\n")
        .append("<title>")
        .append(title)
        .append("</title>\n")
        .append("</head>\n")
        .append("<body>\n")
        .append("<h1>\n")
        .append(title)
        .append("</h1>\n")
        .append(description)
        .append("</body>\n")
        .append("</html>\n");
    try {
      writer.write(sb.toString());
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
