package ic.doc;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class QueryProcessorTest {

    QueryProcessor queryProcessor = new QueryProcessor();

    @Test
    public void returnsEmptyStringIfCannotProcessQuery() throws Exception {
        assertThat(queryProcessor.process("test"), is(""));
    }

    @Test
    public void knowsAboutShakespeare() throws Exception {
        assertThat(queryProcessor.process("Shakespeare"), containsString("playwright"));
    }

    @Test
    public void knowsAboutAsimov() throws Exception {
        assertThat(queryProcessor.process("Asimov"), containsString("science fiction"));
    }

    @Test
    public void knowsAboutTuring() throws Exception {
        assertThat(queryProcessor.process("Alan Turing"), containsString("cryptanalyst"));
    }

    @Test
    public void knowsAboutTolkien() throws Exception {
        assertThat(queryProcessor.process("John Tolkien"),
                containsString("The Hobbit and The Lord of the Rings"));
    }

    @Test
    public void isNotCaseSensitive() throws Exception {
        assertThat(queryProcessor.process("shakespeare"), containsString("playwright"));
    }


}
