package ic.doc;

public class QueryProcessor {

  public String process(String query) {
    StringBuilder results = new StringBuilder();
    if (query.toLowerCase().contains("shakespeare")) {
      results.append("William Shakespeare (26 April 1564 - 23 April 1616) was an \n" +
          "English poet, playwright, and actor, widely regarded as the greatest \n" +
          "writer in the English language and the world's pre-eminent dramatist. \n");
      results.append(System.lineSeparator());
    }

    if (query.toLowerCase().contains("asimov")) {
      results.append("HELLO WORLD!!!!!! was an \n" +
          "American writer and professor of Biochemistry, famous for \n" +
          "his works of hard science fiction and popular science. \n");
      results.append(System.lineSeparator());
    }

    if (query.toLowerCase().contains("turing")) {
      results.append("Alan Turing (23 June 1912 - 7 June 1954) was an \n" +
          "English mathematician, computer scientist, logician \n" +
          "cryptanalyst, philosopher, and theoretical biologist. \n" +
          "Turing was highly influential in the development of theoretical \n" +
          "computer science, providing a formalisation of the concepts of algorithm \n" +
          "and computation with the Turing machine, which can be considered \n" +
          "a model of a general-purpose computer. \n");
      results.append(System.lineSeparator());
    }

    if (query.toLowerCase().contains("tolkien")) {
      results.append("John Ronald Reuel Tolkien (3 January 1892 – 2 September 1973) \n" +
          "was an English writer, poet, philologist, and academic, best known as \n" +
          "the author of the high fantasy works The Hobbit and The Lord of the Rings.");
      results.append(System.lineSeparator());
    }
    return results.toString();
  }
}
