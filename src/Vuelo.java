public class Vuelo {
    private String identificador,ciudadOrigen,ciudadDestino;
    private double precio;
    private int numMaxPasajeros,numActualPasajeros;
    private Pasajero[] listaPasajeros;

    public Vuelo(String identificador, String ciudadOrigen, String ciudadDestino, double precio, int numMaxPasajeros) {
        this.identificador = identificador;
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
        this.precio = precio;
        this.numMaxPasajeros = numMaxPasajeros;
        this.numActualPasajeros = 0;
        this.listaPasajeros=new Pasajero[numMaxPasajeros];
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getCiudadOrigen() {
        return ciudadOrigen;
    }

    public String getCiudadDestino() {
        return ciudadDestino;
    }

    public double getPrecio() {
        return precio;
    }

    public int getNumMaxPasajeros() {
        return numMaxPasajeros;
    }

    public int getNumActualPasajeros() {
        return numActualPasajeros;
    }

    public Pasajero getPasajero(String pasaporte) {
        boolean encontrado=false;
        int i=0;
        Pasajero pas=null;
        while((!encontrado)&&(i<listaPasajeros.length)){
            if(pasaporte.equals(listaPasajeros[i].getPasaporte())){ //.equals = "=="
                encontrado=true;
                pas=listaPasajeros[i];
            }
            i++;
        }
        return pas;
    }

    public Pasajero getPasajero(int i){
       return listaPasajeros[i];
    }

    public void insertarPasajero(Pasajero p){
        listaPasajeros[numActualPasajeros] = p; //el pasajero que nos pase actualizará a la lista de pasajeros.
        numActualPasajeros++;
    }
}