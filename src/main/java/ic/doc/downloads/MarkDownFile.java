package ic.doc.downloads;

public class MarkDownFile implements Downloadable {

  private final String title;
  private final String description;

  public MarkDownFile(String title, String description) {
    this.title = title;
    this.description = description;
  }

  @Override
  public byte[] getTextContentBytes() {
    String content = "# " + title + "\n" + description;
    return content.getBytes();
  }
}
