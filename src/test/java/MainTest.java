import org.junit.jupiter.api.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    private static Process serverProcess;

    @BeforeAll
    static void startServer() throws Exception {
        serverProcess = new ProcessBuilder(
                "java", "-cp",
                System.getProperty("java.class.path"),
                "org.example.Main"
        )
                .inheritIO()
                .start();
        Thread.sleep(2000);
    }

    @AfterAll
    static void stopServer() {
        serverProcess.destroy();
    }

    @Test
    void testHelloEndpoint() throws Exception {
        URL url = new URL("http://localhost:7000/hello");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        assertEquals(200, con.getResponseCode());

        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String body = in.readLine();
            assertEquals("Hello, Javalin!", body);
        }
    }

    @Test
    void testCriarUsuarioEndpoint() throws Exception {
        URL url = new URL("http://localhost:7000/usuarios");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setRequestProperty("Content-Type", "application/json");
        String jsonInput = "{\"nome\":\"Test\",\"email\":\"test@example.com\",\"idade\":25}";
        con.getOutputStream().write(jsonInput.getBytes());

        assertEquals(201, con.getResponseCode());
    }

    @Test
    void testGetUsuarioByEmailEndpoint() throws Exception {
        URL urlCreate = new URL("http://localhost:7000/usuarios");
        HttpURLConnection conCreate = (HttpURLConnection) urlCreate.openConnection();
        conCreate.setRequestMethod("POST");
        conCreate.setDoOutput(true);
        conCreate.setRequestProperty("Content-Type", "application/json");
        String jsonInput = "{\"nome\":\"isabella\",\"email\":\"isabella@gmail.com\",\"idade\":20}";
        conCreate.getOutputStream().write(jsonInput.getBytes());
        assertEquals(201, conCreate.getResponseCode());

        URL urlGet = new URL("http://localhost:7000/usuarios/isabella@gmail.com");
        HttpURLConnection conGet = (HttpURLConnection) urlGet.openConnection();
        conGet.setRequestMethod("GET");
        assertEquals(200, conGet.getResponseCode());
    }

    @Test
    void testListUsuariosEndpoint() throws Exception {
        URL url = new URL("http://localhost:7000/usuarios");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        assertEquals(200, con.getResponseCode());

        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String body = in.readLine();
            assertTrue(body.startsWith("["));
        }
    }
}
