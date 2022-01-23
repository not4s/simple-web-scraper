package ic.doc.downloads;

import java.io.IOException;

public class PdfFile extends Downloadable {

  public PdfFile(String title, String description) throws IOException {
    super(title, description, ".pdf", "application/pdf");
  }

  @Override
  protected void saveToFile() {
    // Create markdown file
    Downloadable md;
    try {
      md = new MarkDownFile(title, description);
    } catch (IOException e) {
      e.printStackTrace();
      return;
    }

    // Start pandoc process
    ProcessBuilder pb = new ProcessBuilder("/usr/local/bin/pandoc",
        "-f",
        "markdown",

        "-t",
        "pdf",

        "-o",
        file.getAbsolutePath(),

        md.file.getAbsolutePath());

    Process p;
    try {
      p = pb.start();
    } catch (IOException e) {
      e.printStackTrace();
      return;
    }
    try {
      p.waitFor();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
