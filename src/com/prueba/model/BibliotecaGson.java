package com.prueba.model;

import com.google.gson.Gson;
import com.prueba.model.TituloGson;

public class BibliotecaGson {
    // incluimos el .jar de la biblioteca Gson para su uso sin utilizar maven o gradle
    // se descarga de maven repository https://mvnrepository.com/artifact/com.google.code.gson/gson/2.13.1
    // se incluye en intelliJ con file->Project Structure ->modules ->Dependencies -> + (Add) ->JARs or Directories

    public TituloGson procesarJson(String elJson){
        Gson gson = new Gson();
        TituloGson respuestaTitulo = gson.fromJson(elJson, TituloGson.class );
        return respuestaTitulo;
    }

    public TituloRecord procesarJsonRecord(String elJson){
        Gson gson = new Gson();
        TituloRecord respuestaTituloRecord = gson.fromJson(elJson, TituloRecord.class );
        return respuestaTituloRecord;
    }
}
