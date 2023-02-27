public class AeroputuertoPrivado extends Aeroputuerto {
    private String[] listaEmpresas =new String[10];
    private int numEmpresa;

    public AeroputuertoPrivado(String nombre, String ciudad, String pais) {
        super(nombre, ciudad, pais);
    }

    public AeroputuertoPrivado(String nombre, String ciudad, String pais, Compañia[] listaCompañias, String[] empresas) {
        super(nombre, ciudad, pais, listaCompañias);
        this.listaEmpresas = empresas;
        numEmpresa=empresas.length;
    }
    public void insertarEmpresas(String[] empresa){
        this.listaEmpresas=empresa;
        this.numEmpresa=empresa.length; //insertamos las empresas como arrays.
    }
    public void insertarEmpresa(String empresa){
        listaEmpresas[numEmpresa]=empresa;
        numEmpresa++;
    }

    public String[] getEmpresas() {
        return listaEmpresas;
    }
}