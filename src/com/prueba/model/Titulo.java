package com.prueba.model;

import com.prueba.excepcion.ExcepcionPersonalizada;

public class Titulo {

    private String nombre;
    private String lanzamiento ="";
    private String duracion = "";
    private String genero = "";
    private String director ="";
    private String trama ="";
    private String pais = "";
    private String clavesNoUsadas = "";


    public Titulo(){
        this.setNombre("");
        this.setLanzamiento("");
        this.setDuracion("");
        this.setGenero("");
        this.setDirector("");
        this.setTrama("");
        this.setPais("");

    }


    // creamos un constructor que recibe como argumento un objeto de clase TituloRecord
    // el record ya posee integrados los getters y los setters, y se pueden obtener las propiedades mediante nombreObjeto.nombrePropiedad()
    // ejemplo objetoTituloRecord.Title();
    public Titulo(TituloRecord objetoTituloRecord){
        this.setNombre(objetoTituloRecord.Title());
        this.setLanzamiento(objetoTituloRecord.Released());
        // lanzamos la excepcion personalizada cuando  Runtime traiga N/A. Probar con el titulo bichos
        if(objetoTituloRecord.Runtime().contains("N/A")){
            throw new ExcepcionPersonalizada("Excepcion Personalizada: ->Encontre N/A en campo Runtime");
        }
        this.setDuracion(objetoTituloRecord.Runtime());
        this.setGenero(objetoTituloRecord.Genre());
        this.setDirector(objetoTituloRecord.Director());
        this.setTrama(objetoTituloRecord.Plot());
        this.setPais(objetoTituloRecord.Country());

    }


    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public String getNombre(){
        return this.nombre;
    }

    public void setLanzamiento(String lanzamiento){
        this.lanzamiento = lanzamiento;
    }
    public String getLanzamiento(){
        return this.lanzamiento;
    }

    public void setDuracion(String duracion){ this.duracion = duracion;}
    public String getDuracion(){return this.duracion;}

    public void setGenero(String genero){ this.genero = genero;}
    public String getGenero(){return this.genero;}

    public void setDirector(String director){ this.director = director;}
    public String getDirector(){return this.director;}

    public void setTrama(String trama){ this.trama = trama;}
    public String getTrama(){return this.trama;}

    public void setPais(String pais){ this.pais = pais;}
    public String getPais(){return this.pais;}

    public void setClavesNoUsadas(String clavesNoUsadas){ this.clavesNoUsadas = clavesNoUsadas;}
    public String getClavesNoUsadas(){return this.clavesNoUsadas;}


    public void extraerInformacionArreglos(String[] arrClaves, String[] arrValores){
        // recorremos el arreglo de claves para extraer la información específica para el objeto Titulo
        // System.out.println("Metodo interno extraerInformacionArreglos arrClaves[1]: "+ arrClaves[1]);
       //  System.out.println("Metodo interno extraerInformacionArreglos arrValores[1]: "+ arrValores[1]);
        String elementoProcesar = "";
        String clavesIgnoradas = "-";
        for(int i=0; i < arrClaves.length; i++){
            elementoProcesar = arrClaves[i];
            // System.out.println("Elemento a procesar: "+elementoProcesar);
            // extraemos el titulo del arreglo
            if("Title".equals(elementoProcesar)){
                this.setNombre(arrValores[i]);
            }
            else if("Released".equals(elementoProcesar)){
                this.setLanzamiento(arrValores[i]);
            }
            else if("Runtime".equals(elementoProcesar)){
                this.setDuracion(arrValores[i]);
            }
            else if("Genre".equals(elementoProcesar)){
                this.setGenero(arrValores[i]);
            }
            else if("Director".equals(elementoProcesar)){
                this.setDirector(arrValores[i]);
            }
            else if("Plot".equals(elementoProcesar)){
                this.setTrama(arrValores[i]);
            }
            else if("Country".equals(elementoProcesar)){
                this.setPais(arrValores[i]);
            }
            else{
                // guardamos en un string las claves no usadas para mostrar despues
               // clavesIgnoradas = clavesIgnoradas.concat(" ,").concat(elementoProcesar);
                clavesIgnoradas = clavesIgnoradas +" ,"+ elementoProcesar;
            }
        }//fin for
        // almacenamos las claves no usadas en el objeto
        this.setClavesNoUsadas(clavesIgnoradas);
    }// fin extraerInformacion

    public void listarInformacionAlmacenada(){
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("INFORMACION DE LA PELICULA");
        System.out.println("Titulo de la pelicula: "+this.getNombre());
        System.out.println("Fecha de lanzamiento de la pelicula: "+this.getLanzamiento());
        System.out.println("Duracion de la pelicula: "+this.getDuracion());
        System.out.println("Genero de la pelicula: "+this.getGenero());
        System.out.println("Director de lanzamiento de la pelicula: "+this.getDirector());
        System.out.println("Trama de la pelicula: "+this.getTrama());
        System.out.println("Pais de la pelicula: "+this.getPais());
        System.out.println("Claves no usadas del Json: "+this.getClavesNoUsadas());
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%");
    }// fin listarInformacion

    // metodo toString para mostrar informacion del objeto imprimiendo directamente el objeto sin invocar algun metodo
    // el metodo ya existe en el objeto asi que se tiene que sobre escribir
    @Override
    public String toString(){
        return  "oooooooooooooooooooooooooooooooooooooooo"+
                "\n Salida metodo toString() clase Titulo "+
                "\n Titulo de la pelicula: "+this.getNombre()+
                "\n Fecha de lanzamiento de la pelicula: "+this.getLanzamiento()+
                "\n Duracion de la pelicula: "+this.getDuracion()+
                "\n Genero de la pelicula: "+this.getGenero()+
                "\n Director de lanzamiento de la pelicula: "+this.getDirector()+
                "\n Trama de la pelicula: "+this.getTrama()+
                "\n Pais de la pelicula: "+this.getPais()+
                "\n oooooooooooooooooooooooooooooooooooooooo";
    }// fin toString



}// fin clase


