package ic.doc.downloads;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public abstract class Downloadable {

  protected final String title;
  protected final String description;
  protected final String fileExtension;
  protected final String mime;

  protected File file = null;

  public Downloadable(String title, String description,
      String fileExtension, String mime) throws IOException {
    this.title = title;
    this.description = description;
    this.fileExtension = fileExtension;
    this.mime = mime;

    // Create temp file
    file = File.createTempFile("query", fileExtension);

    // Write to it
    saveToFile();
  }

  public String getContentType() {
    return mime;
  }

  public String getFilename() {
    return "query" + fileExtension;
  }

  public byte[] convertToBytes() {
    try {
      return Files.readAllBytes(file.toPath());
    } catch (IOException e) {
      return null;
    }
  }

  protected abstract void saveToFile();

}
