package com.prueba.model;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.prueba.model.TituloGson;

import java.util.ArrayList;

import java.util.List;

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

    public TituloMinusculaRecord procesarJsonRecordMinuscula(String elJson){
        // se utiliza el patron Builder para implementar la politica en el objeto gson que permite que las propiedades que vengan de la API se almacenen sin distinguir las mayusculas
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
        TituloMinusculaRecord respuestaTituloMinuscula = gson.fromJson(elJson, TituloMinusculaRecord.class );
        return respuestaTituloMinuscula;
    }

    public TituloNumerosRecord procesarJsonRecordNumeros(String elJson){
        // se utiliza el patron Builder para implementar la politica en el objeto gson que permite que las propiedades que vengan de la API se almacenen sin distinguir las mayusculas
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
        TituloNumerosRecord respuestaTituloNumeros = gson.fromJson(elJson, TituloNumerosRecord.class );
        return respuestaTituloNumeros;
    }

    public String convertirAJson(List laLista){
        // se utiliza el patron Builder para implementar la politica en el objeto gson que permite que las propiedades que vengan de la API se almacenen sin distinguir las mayusculas
        // se utiliza la politica para que los nombre comiencen en minuscula y sean entendidos por la biblioteca
        // se utiliza la mejora prettyPrinting para que l json generado se vea organizado y bonito
        Gson gson = new GsonBuilder()
                                    .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                                    .setPrettyPrinting()
                                    .create();
        String elJson = gson.toJson(laLista );
        return elJson;
    }
}
