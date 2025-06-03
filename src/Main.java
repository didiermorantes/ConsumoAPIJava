import com.prueba.excepcion.ExcepcionPersonalizada;
import com.prueba.model.*;
import com.prueba.persistencia.Archivo;

import java.util.ArrayList;
import java.util.List;

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

        // objeto para almacenar en archivo de texto
        Archivo objetoArchivo = new Archivo();
        String mensajeAlmacenamiento="";
        String mensajeAlmacenamientoJson="";
        String mensajeLectura ="";
        String mensajeLecturaScanner ="";

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
            // aplicamos la persistencia
            mensajeAlmacenamiento = objetoArchivo.guardarDato(objetoTitulo);
            System.out.println(mensajeAlmacenamiento);
        }





        // Esto arrojará error porque la clase TituloNumerosRecord posee una propiedad de tipo int que es incompatible con el string que entrega la api. Es imposible mapear
        try{
            // encapsulamos la porción de código susceptible de error

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
            // esta porcion es la que podría lanzar una excepcion porque asigna a un objeto titulo lo que viene de tituloRecord
        Titulo objetoTituloGsonRecord = new Titulo(objetoTituloRecord);
        // imprimimos con el metodo toString sobreescrito
        System.out.println(objetoTituloGsonRecord);
        // aplicamos la persistencia
        mensajeAlmacenamiento = objetoArchivo.guardarDato(objetoTituloGsonRecord);
        System.out.println(mensajeAlmacenamiento);

        // leemos del archivo de texto
        mensajeLectura = objetoArchivo.leerDato();
        System.out.println("Datos leidos del archivo de texto: ");
        System.out.println(mensajeLectura);

        // leemos del archivo de texto con objeto Scanner
        mensajeLecturaScanner = objetoArchivo.leerDatoScanner();
        System.out.println("Datos leidos del archivo de texto con objeto Scanner: ");
        System.out.println(mensajeLecturaScanner);




            // procesamos el Json con la biblioteca Gson y probar sus capacidades. Lo hacemos con un objeto diferente porque esta porción arrojara error
            BibliotecaGson objetoGsonError = new BibliotecaGson();
            System.out.println("Inicia proceso de clase TituloNumerosRecord");
            TituloNumerosRecord objetoTituloNumeros = objetoGsonError.procesarJsonRecordNumeros(objetoSolicitud.getRespuestaAPIResponse());
            System.out.println("Objeto Record Numeros: "+objetoTituloNumeros);
        }
        catch(ExcepcionPersonalizada e){
            System.out.println("Ocurrio una expcepción personalizada: "+e.getMessage());
        }
        catch(Exception e){
            System.out.println("Ocurrio una excepcion general con la clase TituloNumerosRecord: "+e.getMessage());
        }
        finally {
            System.out.println("Finaliza proceso de clase TituloNumerosRecord");
        }


// PROCESAMIENTO DE MUCHOS TITULOS
        // TENEMOS QUE CREAR NUEVOS OBJETOS PORQUE LA PRIMERA EJECUCION QUEDO DENTRO DE UN BLOQUE TRY-CATCH
        // EL TRY PERTENECE A UN UNIVERSO DIFERENTE
        System.out.println("++++++++++ PROCESAMIENTO MUCHOS TITULOS ++++++++++");
        // variable para almacenar el nombre de la pelicula
            String laPelicula = "";
        // creamos una lista para almacenar los titulos consultados
        List<Titulo> titulos = new ArrayList<>();
        // variable para controlar el ciclo de muchas peliculas
            boolean salir = false;

            // construimos el objeto para realizar la solicitud a la API
            SolicitudAsincrona objetoMuchasSolicitudes = new SolicitudAsincrona("Muchos");

            // procesamos el Json con la biblioteca Gson y probar sus capacidades
            BibliotecaGson objetoGsonMuchas = new BibliotecaGson();

            while(salir != true){
                // capturamos el nombre de la pelicula
                objetoMuchasSolicitudes.asignarPelicula();
                // validamos el nombre de la pelicula para saber si salimos del ciclo o procesamos la solicitud
                if(objetoMuchasSolicitudes.getPelicula().equals("salir") || objetoMuchasSolicitudes.getPelicula().equals("Salir") || objetoMuchasSolicitudes.getPelicula().equals("SALIR")  ){
                    salir = true;
                }
                else{
                    // realizamos la solicitud
                    objetoMuchasSolicitudes.consumirEndpointResponse();
                    System.out.println("objetoMuchasSolicitudes.getRespuestaAPIResponse()");
                    System.out.println(objetoMuchasSolicitudes.getRespuestaAPIResponse());

                    // procesamos el Json con la biblioteca Gson y el Record. Se implementa el metodo procesarJsonRecord para adaptar el retorno de la función a la clase TituloRecord
                    TituloRecord objetoTituloRecordMuchas = objetoGsonMuchas.procesarJsonRecord(objetoMuchasSolicitudes.getRespuestaAPIResponse());
                    System.out.println(" ObjetoTituloRecordMuchas");
                    System.out.println(objetoTituloRecordMuchas);
                    // Enviamos los elementos obtenidos con la biblioteca Gson y el record a un objeto tipo titulo, que tiene la funcionalidad que esperamos
                    // de esta manera usamos el DTO. El objeto que ya tenemos y que siempre se utiliza -De la clase Titulo-, lo usamos para recibir datos extraidos con el record
                    // Titulo es el objeto que hemos definido para presentar la información
                    // esta porcion es la que podría lanzar una excepcion porque asigna a un objeto titulo lo que viene de tituloRecord
                    Titulo objetoTituloGsonRecordMuchas = new Titulo(objetoTituloRecordMuchas);
                    // guardamos en la lista el objeto procesado por la biblioteca GSON y procesados por el DTO
                    titulos.add(objetoTituloGsonRecordMuchas);
                }


            }// fin while


            mensajeAlmacenamientoJson=objetoArchivo.guardarJson( objetoGsonMuchas.convertirAJson(titulos));
            System.out.println(mensajeAlmacenamientoJson);

            // mostramos la lista de titulos
            System.out.println("+++++++++++++ LISTA DE TITULOS OBTENIDA +++++++++++++ ");
            System.out.println(titulos);
            System.out.println("++++++++++ FIN PROCESAMIENTO MUCHOS TITULOS ++++++++++");








    }// fin main
}// fin clase