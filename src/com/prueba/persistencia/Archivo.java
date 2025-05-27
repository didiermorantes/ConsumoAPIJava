package com.prueba.persistencia;
import com.prueba.model.Titulo;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;

public class Archivo {
    private String dato;

    public String getDato(){
        return this.dato;
    }
    public String guardarDato(Titulo unTitulo){
        boolean resultado = false;
        String mensaje = "";
        this.dato = unTitulo.toString();
        System.out.println("Guardando en archivo de texto la siguiente informacion: \n"+this.dato);
        try{
            FileWriter objetoWriter = new FileWriter("persistenciaPelicula.txt");
            objetoWriter.write(this.dato);
            objetoWriter.close();
            System.out.println("Persistencia aplicada existosamente");
            resultado = true;
        }
        catch(Exception e){
            System.out.println("Ocurrio una excepcion guardando en archivo de texto: "+e.getMessage());
        }
        finally {
            System.out.println("Proceso de persistencia finalizado");
        }

        if(resultado== true){
           mensaje= "Información Almacenada exitosamente en el archivo de texto";
        }
        else{
            mensaje="No se almacenó información en el archivo de texto";
        }
        return mensaje;

    }

    public String leerDato(Titulo unTitulo){
        String datoLeido = "";
        String path= "persistenciaPelicula.txt";
        System.out.println("Leyendo archivo de texto  de la siguiente direccion: "+path);
        try{
            File file = new File(path);
            FileReader objetoReader = new FileReader(file);

            int data = objetoReader.read();
            while (data != -1) {
                datoLeido = datoLeido + (char) data;
                System.out.print((char) data);
                data = objetoReader.read();
            }
            objetoReader.close();

        }
        catch(Exception e){
            System.out.println("Error leyendo archivo de texto: "+e.getMessage());
        }
        finally{
            System.out.println("\nProceso de Lectura de archivo de texto finalizado");
        }
        return datoLeido;
    }
}
