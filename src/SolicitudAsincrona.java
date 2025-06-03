import com.prueba.model.Titulo;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class SolicitudAsincrona {
    public String endpoint ;
    public String apiKey;
    public String url;
    private String respuestaAPIRequest = "";
    private String respuestaAPIResponse = "";
    private String pelicula = "";

    public SolicitudAsincrona(){
        // this.endpoint = "http://www.omdbapi.com/?t=tt3896198&apikey=";
        this.endpoint = "http://www.omdbapi.com/?t=";
        this.apiKey = "&apikey=c184ab6c";

        this.asignarPelicula();

    }

    public SolicitudAsincrona(String muchasSolicitudes){
        // cuando se manda un String en el constructor, significa que no se hará el llamado al método asignar pelicula

        // this.endpoint = "http://www.omdbapi.com/?t=tt3896198&apikey=";
        this.endpoint = "http://www.omdbapi.com/?t=";
        this.apiKey = "&apikey=c184ab6c";

    }





    public String getRespuestaAPIRequest(){
        return this.respuestaAPIRequest;
    }
    public void setRespuestaAPIRequest(String respuestaAPIRequest){
        this.respuestaAPIRequest = respuestaAPIRequest;
    }

    public String getRespuestaAPIResponse(){
        return this.respuestaAPIResponse;
    }
    public void setRespuestaAPIResponse(String respuestaAPIResponse){
        this.respuestaAPIResponse = respuestaAPIResponse;
    }

    public String getPelicula(){
        return this.pelicula;
    }
    public void setPelicula(String pelicula){
        this.pelicula = pelicula;
    }


    public String mostrarURL(){
        return this.url;
    }

    public void asignarPelicula(){
        Scanner atrapar = new Scanner(System.in);
        String nombrePelicula = "";
        String nuevoNombrePelicula = "";
        System.out.println("Digite nombre de la pelicula");
        nombrePelicula = atrapar.nextLine();
        nuevoNombrePelicula = this.transformarEspacios(nombrePelicula);
        setPelicula(nuevoNombrePelicula);
        // esta instruccion configura el endpoint a consumir de acuerdo con el nombre de la pelicula digitado
        this.url = this.endpoint.concat(this.getPelicula()).concat(this.apiKey);
    }


    public String transformarEspacios(String urlConEspacios){
        String urlTransformada = "";
        urlTransformada = urlConEspacios.replace(" ", "%20");
        System.out.println("Url Solicitada: "+urlConEspacios);
        System.out.println("Url Transformada: "+urlTransformada);
        return urlTransformada;
    }

    public void consumirEndpointRequest(){
        try{
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(this.url))
                    .build();

            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    //.thenAccept(System.out::println)
                    // .thenAccept(this::setRespuestaAPI)
                    //.thenAccept(respuesta -> System.out.println("La respuesta tipo request es: " +respuesta))
                    .thenAccept(respuesta -> this.setRespuestaAPIRequest(respuesta))
                    .join();
        }
        catch(Exception e ){
            System.out.println("Error consumiendo Endpoint. Tipo Error: "+ e.getMessage());
            System.out.println("Error: "+ e);
        }
    }

    public void consumirEndpointResponse(){
        try{
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(this.url))
                    .build();

            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            // System.out.println("La respuesta tipo response es: "+response.body());

            this.setRespuestaAPIResponse(response.body());



        }
        catch(Exception e ){
            System.out.println("Error consumiendo Endpoint tipo Response. Tipo Error: "+ e.getMessage());
            System.out.println("Error: "+ e);
        }
    } // fin consumirEndpointResponse
}
