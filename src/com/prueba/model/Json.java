package com.prueba.model;

public class Json {

    private String[] Claves;
    private String[] Valores;
    private int longitudArreglo;


    public void setLongitudArreglo(int longitudArreglo){
        this.longitudArreglo = longitudArreglo;
    }
    public int getLongitudArreglo(){
        return this.longitudArreglo;
    }

    public String[] getClaves(){
        return this.Claves;
    }

    public String[] getValores(){
        return this.Valores;
    }


    public void procesarJson(String eljson){
        System.out.println("Json recibido: "+eljson);
        System.out.println("Tipo de dato Json recibido: "+eljson.getClass());
        String nuevoJson ="";
        // Remove curly braces and split by commas
        // son dos operaciones, primero quitamos las llaves del json y los remplazamos por comiilas
        nuevoJson = eljson.replace("{", "").replace("}", "");
        // luego separamos cada uno de los elementos del json a través de la coma, y guardamos cada resultado en un arreglo
        String[] keyValuePairs = eljson.split(",");
        // almacenamos la logitud del arreglo
        this.longitudArreglo = keyValuePairs.length;
        // Definimos arreglos para las claves y los valores con dimensión igual al arreglo que tiene todos los clave-valor
        String[] arregloClaves = new String[this.longitudArreglo];
        String[] arregloValores = new String[this.longitudArreglo];

        // variables para determinar si hay datos adicionales en el JSON que fueron cortados al hacer split con la coma (,)
        String elementosAdicionales = "";
        Boolean flagElementosAdicionales = false;


        System.out.println("=========================");
        // finalmente recorremos el arreglo de claves par llave-valor, para crear un arreglo para llaves y otro para valores
        for (int i = 0; i < this.longitudArreglo; i++) {
            System.out.println("Key:Value =>"+keyValuePairs[i]);
            // separamos cada uno de los elementos del resultado para extraer la clave y el valor por serparado
            String[] arrElementos = keyValuePairs[i].split(":");

            for (int j = 0; j < arrElementos.length; j++) {
                System.out.println("Elementos internos =>"+arrElementos[j]);
                // si el primer caracter es una comilla o una llave
                String primerCaracter = arrElementos[j].substring(0, 1);
                String ultimoCaracter = arrElementos[j].substring(arrElementos[j].length() - 1);
                String propiedadLimpia = "";


                System.out.println("Primer Caracter =>"+primerCaracter);
                System.out.println("Ultimo Caracter =>"+ultimoCaracter);

                if((primerCaracter.equals("\"") || primerCaracter.equals("{")) && ( ultimoCaracter.equals("}") || ultimoCaracter.equals("\""))){
                    propiedadLimpia = arrElementos[j].replace("{", "").replace("}", "").replace("\"", "");
                    System.out.println("Propiedad Limpia =>"+propiedadLimpia);
                }
                else{
                    flagElementosAdicionales = true;
                    elementosAdicionales = elementosAdicionales+ arrElementos[j] + " ,";
                    System.out.println("---------------------------------ADICIONALES---------------------------------- ");
                    System.out.println("Hay Datos adicionales: "+ elementosAdicionales);
                    if(ultimoCaracter.equals("\"")){
                        // establecemos la bandera de datos adicionales a false, porque se terminaron de concatenar los datos adicionales
                        flagElementosAdicionales = false;
                        propiedadLimpia = elementosAdicionales.replace("{", "").replace("}", "").replace("\"", "");
                        System.out.println("Propiedad Limpia elementos adicionales =>"+propiedadLimpia);

                        //si no hay mas datos adicionales limpiamos elementosAdicionales para nueva iteracion
                        elementosAdicionales = "";
                        flagElementosAdicionales = false;

                        System.out.println("---------------------------------FIN ADICIONALES---------------------------------- ");
                    }// fin  if interno
                }// fin else

                // si j es igual a cero estamos observando las claves y almacenamos en el arregloClaves
                if(j==0){
                    arregloClaves[i] = propiedadLimpia;
                }
                else{ // si j no es cero, estamos observando los valores y almacenamos en el arregloValores
                    // si la bandera de datos adicionales esta en false , significa que ya se terminaron de concatenar los datos adicionales
                    if(flagElementosAdicionales == false){
                        arregloValores[i] = propiedadLimpia;
                    }
                    // de lo contrario no hacemos nada hasta que los datos adicionales se hayan concatenado

                }
            }//fin for interno


            System.out.println("=========================");

        }// fin for externo



        // ALMACENAMOS EN EL OBJETO LOS ARREGLOS DE CLAVES Y VALORES OBTENIDOS
        this.Claves = arregloClaves;
        this.Valores = arregloValores;

        System.out.println("*************************");
        System.out.println("PROCESAMIENTO DE LOS ARREGLOS DE CLAVES Y VALORES");
        for (int i = 0; i < this.longitudArreglo; i++) {
            System.out.println("Clave =>"+this.Claves[i]);
            System.out.println("Valor =>"+this.Valores[i]);
            System.out.println("*************************");
        }// fin for arreglos


    }// fin procesar JSON

}
