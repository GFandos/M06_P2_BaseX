import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

/**
 * Created by 47989768s on 13/03/17.
 */
public class BaseXdbManager {

    private BaseXClient session;

    public BaseXdbManager() throws IOException {
        // create session
        session = new BaseXClient("localhost", 1984, "admin", "admin");
        // create empty database
        session.execute("create db MondialDB");
        System.out.println(session.info());

        byte[] array = Files.readAllBytes(new File("Factbook.xml").toPath());

        // define input stream
        InputStream bais = new ByteArrayInputStream(array);

        // add document
        session.add("Factbook.xml", bais);
        System.out.println(session.info());

    }

    public void dropDatabase() throws IOException {
        session.execute("drop db MondialDB");
    }

    public void runQuery(String query) throws IOException {
        System.out.println(session.execute("xquery " + query));
    }

}
