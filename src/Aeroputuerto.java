public class Aeroputuerto {
    private String nombre,ciudad,pais;
    private Compañia[] listaCompañias=new Compañia[10];
    private int numCompañia;

    public Aeroputuerto(String nombre, String ciudad, String pais) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.pais = pais;
        this.numCompañia=0;
    }

    public Aeroputuerto(String nombre, String ciudad, String pais, Compañia[] c) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.pais = pais;
        listaCompañias = c;
        this.numCompañia=c.length;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getPais() {
        return pais;
    }

    public Compañia[] getListaCompañias() {
        return listaCompañias;
    }

    public int getNumCompañia() {
        return numCompañia;
    }
    public void insertarCompañia(Compañia compañia){
        listaCompañias[numCompañia]=compañia;
        numCompañia++;
    }
    public Compañia getCompañia(int i){
        return listaCompañias[i];
    }
    public Compañia getCompañia(String n){
        boolean encontrado=false;
        int i=0;
        Compañia c = null;
        while((!encontrado)&&(i<listaCompañias.length)){
            if(n.equals(listaCompañias[i].getNombre())){
                encontrado=true;
                c=listaCompañias[i];
            }
            else{
                c=null;
            }
            i++;
        }
        return c;
    }
}