package ic.doc.downloads;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MarkDownFile extends Downloadable {

  public MarkDownFile(String title, String description)
      throws IOException {
    super(title, description, ".md", "text/markdown");
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
    sb.append("# ")
        .append(title)
        .append("\n")
        .append(description);
    try {
      writer.write(sb.toString());
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
