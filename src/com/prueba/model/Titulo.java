package com.prueba.model;

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
        String clavesIgnoradas = "";
        for(int i=0; i < arrClaves.length; i++){
            // extraemos el titulo del arreglo
            if(arrClaves[i].equals("Title")){
                this.setNombre(arrValores[i]);
            }
            else if(arrClaves[i].equals("Released")){
                this.setLanzamiento(arrValores[i]);
            }
            else if(arrClaves[i].equals("Runtime")){
                this.setDuracion(arrValores[i]);
            }
            else if(arrClaves[i].equals("Genre")){
                this.setGenero(arrValores[i]);
            }
            else if(arrClaves[i].equals("Director")){
                this.setDirector(arrValores[i]);
            }
            else if(arrClaves[i].equals("Plot")){
                this.setTrama(arrValores[i]);
            }
            else if(arrClaves[i].equals("Country")){
                this.setPais(arrValores[i]);
            }
            else{
                // guardamos en un string las claves no usadas para mostrar despues
                clavesIgnoradas = clavesIgnoradas.concat(" ,").concat(arrClaves[i]);
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



}// fin clase


