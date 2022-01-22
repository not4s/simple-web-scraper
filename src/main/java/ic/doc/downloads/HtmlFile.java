package ic.doc.downloads;

public class HtmlFile implements Downloadable {

  private final String title;
  private final String description;

  public HtmlFile(String title, String description) {
    this.title = title;
    this.description = description;
  }

  @Override
  public byte[] getTextContentBytes() {
    String content =
            "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
              "<meta charset=\"UTF-8\"\n>" +
              "<title>" + title + "</title>\n" +
            "</head>\n" +
            "<body>\n" +
              "<h1>\n" +
                title +
              "</h1>\n" +
              description +
            "</body>\n" +
            "</html>\n";
    return content.getBytes();
  }
}
