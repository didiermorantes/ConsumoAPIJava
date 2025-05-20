import java.io.UncheckedIOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.prueba.model.BibliotecaGson;
import com.prueba.model.Titulo;
import com.prueba.model.TituloGson;
import com.prueba.model.Json;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        SolicitudAsincrona objetoSolicitud = new SolicitudAsincrona();
        System.out.println("Mensaje main: La URL es: "+objetoSolicitud.mostrarURL());
        objetoSolicitud.consumirEndpointRequest();
        System.out.println("Mensaje main: La respuesta tipo request es: " +objetoSolicitud.getRespuestaAPIRequest());
        objetoSolicitud.consumirEndpointResponse();
        System.out.println("Mensaje main: La respuesta tipo response es:"+objetoSolicitud.getRespuestaAPIResponse());
        // construimos un objeto para procesar el JSON y obtener arreglos con las claves y valores
        Json objetoJson = new Json();
        Boolean jsonProcesado = false;

        jsonProcesado =  objetoJson.procesarJson(objetoSolicitud.getRespuestaAPIResponse());


        if(jsonProcesado){ // si el json se proceso creamos el objeto titulo con los datos procesados del json
            // observamos los par clave valor si procesarJson retorna true
            // objetoJson.verParClaveValores(); // observamos la informacion almacenada en el objetoJson

            // construimos un objeto para asignar los arreglos con clave y valor a las variables del objeto
            Titulo objetoTitulo = new Titulo();

            // String[] tempClaves = objetoJson.getClaves();
            // String[] tempValores = objetoJson.getValores();
            // System.out.println("Clave posicion 1: "+tempClaves[1]);
            // System.out.println("Valor posicion 1: "+tempValores[1]);

            objetoTitulo.extraerInformacionArreglos(objetoJson.getClaves(), objetoJson.getValores());
            objetoTitulo.listarInformacionAlmacenada();

        }

        // procesamos el Json con la biblioteca Gson y probar sus capacidades
        BibliotecaGson objetoGson = new BibliotecaGson();

        TituloGson objetoTituloGson = objetoGson.procesarJson(objetoSolicitud.getRespuestaAPIResponse());
        objetoTituloGson.listarInformacionAlmacenada();




    }
}