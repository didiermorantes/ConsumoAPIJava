import java.io.UncheckedIOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.prueba.model.*;
import com.google.gson.Gson;

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

        // procesamos el Json con la biblioteca Gson y el Record. Se implementa el metodo procesarJsonRecord para adaptar el retorno de la función a la clase TituloRecord
        TituloRecord objetoTituloRecord = objetoGson.procesarJsonRecord(objetoSolicitud.getRespuestaAPIRequest());
        System.out.println("Objeto Record: "+objetoTituloRecord);

        // procesamos el Json con la biblioteca Gson y el Record con minuscula y la politica. Se implementa el metodo  procesarJsonRecordMinuscula para adaptar el retorno de la función a la clase TituloMinusculaRecord
        TituloMinusculaRecord objetoTituloMinuscula = objetoGson.procesarJsonRecordMinuscula(objetoSolicitud.getRespuestaAPIResponse());
        System.out.println("Objeto Record Minuscula: "+objetoTituloMinuscula);

        // Enviamos los elementos obtenidos con la biblioteca Gson y el record a un objeto tipo titulo, que tiene la funcionalidad que esperamos
        // de esta manera usamos el DTO. El objeto que ya tenemos y que siempre se utiliza -De la clase Titulo-, lo usamos para recibir datos extraidos con el record
        // Titulo es el objeto que hemos definido para presentar la información
        Titulo objetoTituloGsonRecord = new Titulo(objetoTituloRecord);
        // imprimimos con el metodo toString sobreescrito
        System.out.println(objetoTituloGsonRecord);

    // Esto arrojará error porque la clase TituloNumerosRecord posee una propiedad de tipo int que es incompatible con el string que entrega la api. Es imposible mapear
        try{
            // encapsulamos la porción de código susceptible de error
            System.out.println("Inicia proceso de clase TituloNumerosRecord");
            TituloNumerosRecord objetoTituloNumeros = objetoGson.procesarJsonRecordNumeros(objetoSolicitud.getRespuestaAPIResponse());
            System.out.println("Objeto Record Numeros: "+objetoTituloNumeros);
        }
        catch(Exception e){
            System.out.println("Ocurrio una excepcion general con la clase TituloNumerosRecord: "+e.getMessage());
        }
        finally {
            System.out.println("Finaliza proceso de clase TituloNumerosRecord");
        }








    }
}