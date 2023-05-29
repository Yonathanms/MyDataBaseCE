import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class ClienteController {

    @GetMapping("/hello")
    public String hello() {
        // Realizar una solicitud HTTP GET a una URL
        String url = "https://ejemplo.com";
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);

        try {
            HttpResponse response = httpClient.execute(httpGet);
            // Aquí puedes procesar la respuesta HTTP, obtener datos, etc.
            return "¡Hola desde el servidor!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al realizar la solicitud HTTP";
        }
    }
}
