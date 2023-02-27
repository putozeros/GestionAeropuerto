import java.util.Scanner;

public class Main {
    static Scanner entrada = new Scanner(System.in);
    final static int numAero=4; //numero de aeropuertos
    static Aeroputuerto[] aeroputuertos = new Aeroputuerto[numAero];
    public static void main(String[] args) {
        insertarDatosAeroputuerto(aeroputuertos);
        Menu();
    }
    public static void Menu(){
        int opc;
        String nombreAeropuerto,nombreCompañía,origen,destino;
        Aeroputuerto aeropuerto;
        Compañia compañía;
        do{
            System.out.println("""
                                MENÚ

                    1. Lista de Aeroputuertos gestionados.
                    2. Patrocinadores y subvenciones públicas.
                    3. Compañias que operan en x aeroputuerto.
                    4. Mostrar vuelos por Aeroputuerto y compañía.
                    5. Vuelos y precios.
                    6. Salir
                    """);
            System.out.print("\nOpción: ");
            opc=entrada.nextInt();
            switch(opc){
                case 1->{
                    //Lista de aeropuertos gestionados
                    System.out.println("\nLista de Aeroputuertos Gestionados\n");
                    mostrarAeropuertos(aeroputuertos);
                }
                case 2->{
                    //Patrocinadores y subvenciones públicas
                    System.out.println("\nLista de patrocinadores y subvenciones\n");
                    mostrarEmpresasSubvenciones(aeroputuertos);
                }
                case 3->{
                    //Compañías que operan en determinado aeropuerto
                    entrada.nextLine();
                    System.out.print("\nNombre del aeroputuerto: ");
                    nombreAeropuerto=entrada.nextLine();
                    aeropuerto=buscarAeropuerto(nombreAeropuerto,aeroputuertos);
                    if(aeropuerto==null){
                        System.out.println("Aeroputuerto no encontrado");
                    }
                    else{
                        mostrarCompañias(aeropuerto);
                    }
                }
                case 4->{
                    //Vuelos de determinada compañía
                    entrada.nextLine();
                    System.out.print("\nNombre del aeroputuerto: ");
                    nombreAeropuerto=entrada.nextLine();
                    aeropuerto=buscarAeropuerto(nombreAeropuerto,aeroputuertos);
                    try{
                        if(aeropuerto==null){
                            System.out.println("Aeroputuerto no encontrado");
                        }
                        else {
                            System.out.print("Nombre de la compañía: ");
                            nombreCompañía = entrada.nextLine();
                            if (nombreCompañía == null) {
                                System.out.println("Compañía no encontrada");
                            } else {
                                compañía = aeropuerto.getCompañia(nombreCompañía);
                                mostrarVuelos(compañía);
                            }
                        }
                    }catch(NullPointerException a){
                        System.out.println("Compañía no encontrada");
                    }
                }
                case 5->{
                    //Vuelos y precios.
                    entrada.nextLine();
                    System.out.print("Ciudad de origen: ");
                    origen=entrada.nextLine();
                    System.out.print("Ciudad de destino: ");
                    destino=entrada.nextLine();
                    mostrarVueloOrigenDestino(origen,destino,aeroputuertos);
                }
            }
        }while(opc!=6);
    }
    public static void mostrarAeropuertos(Aeroputuerto[] aeroputuertos){
        for (Aeroputuerto aeroputuerto : aeroputuertos) {
            if (aeroputuerto instanceof AeroputuertoPrivado) { //instanceof determina si el elemento pertenece a la clase.
                System.out.println("Aeroputuerto privado");
                System.out.println("Nombre: " + aeroputuerto.getNombre());
                System.out.println("Ciudad: " + aeroputuerto.getCiudad());
                System.out.println("País: " + aeroputuerto.getPais());
            } else {
                System.out.println("Aeroputuerto Público.");
                System.out.println("Nombre: " + aeroputuerto.getNombre());
                System.out.println("Ciudad: " + aeroputuerto.getCiudad());
                System.out.println("País: " + aeroputuerto.getPais());
            }
            System.out.println();
        }
    }
    public static void mostrarEmpresasSubvenciones(Aeroputuerto[] aeroputuertos){
        String[] empresas;
        for (Aeroputuerto i : aeroputuertos) {
            if (i instanceof AeroputuertoPrivado) {
                System.out.println("Aeroputuerto privado: " + i.getNombre());
                empresas = ((AeroputuertoPrivado) i).getEmpresas();
                for (String empresa : empresas) {
                    System.out.println(empresa);
                }
            } else {
                System.out.println("Aeroputuerto publico: " + i.getNombre());
                System.out.println("Subvención: "+((AeroputuertoPublico) i).getSubvencion());
            }
            System.out.println();
        }
    }

    public static Aeroputuerto buscarAeropuerto(String nombre,Aeroputuerto[] aeroputuertos){
        boolean encontrado = false;
        int i=0;
        Aeroputuerto aero=null;
        while((!encontrado)&&(i<aeroputuertos.length)){
            if(nombre.equals(aeroputuertos[i].getNombre())){
                encontrado=true;
                aero=aeroputuertos[i];
            }
            i++;
        }
        return aero;
    }
    public static void mostrarCompañias(Aeroputuerto aeropuerto){
        System.out.println("Las compañías del aeroputuerto "+aeropuerto.getNombre()+" son:");
        for(int i=0;i<aeropuerto.getNumCompañia();i++){
            System.out.println(aeropuerto.getCompañia(i).getNombre());
        }
        System.out.println();
    }

    public static void mostrarVuelos(Compañia compañia){
        Vuelo vuelo;
        System.out.println("Los vuelos de la compañía "+compañia.getNombre()+" son:\n");
        for(int i=0;i<compañia.getNumVuelo();i++){
            vuelo=compañia.getVuelo(i);
            System.out.println("Identificador: "+ vuelo.getIdentificador());
            System.out.println("Origen: "+ vuelo.getCiudadOrigen());
            System.out.println("Destino: "+ vuelo.getCiudadDestino());
            System.out.println("Precio: "+ vuelo.getPrecio()+" euros");
            System.out.println();
        }
    }
    public static Vuelo[] buscarVuelosOrigenDestino(String origen, String destino, Aeroputuerto[] aeroputuertos){
        Vuelo vuelo;
        int contador=0;
        Vuelo[] listaVuelos;
        for (Aeroputuerto value : aeroputuertos) { //recorrer aeropuertos
            for (int j = 0; j < value.getNumCompañia(); j++) { //recorrer compañías
                for (int k = 0; k < value.getCompañia(j).getNumVuelo(); k++) { //recorrer vuelos
                    vuelo = value.getCompañia(j).getVuelo(k);
                    if ((origen.equals(vuelo.getCiudadOrigen())) && (destino.equals(vuelo.getCiudadDestino()))) {
                        contador++;
                    }
                }
            }
        }
        listaVuelos=new Vuelo[contador];
        int q=0;

        for (Aeroputuerto aeroputuerto : aeroputuertos) {
            for (int m = 0; m < aeroputuerto.getNumCompañia(); m++) {
                for (int n = 0; n < aeroputuerto.getCompañia(m).getNumVuelo(); n++) {
                    vuelo = aeroputuerto.getCompañia(m).getVuelo(n);
                    if ((origen.equals(vuelo.getCiudadOrigen())) && (destino.equals(vuelo.getCiudadDestino()))) {
                        listaVuelos[q] = vuelo;
                        q++;
                    }
                }
            }
        }
        return listaVuelos;
    }
    public static void mostrarVueloOrigenDestino(String origen,String destino,Aeroputuerto[] aeroputuertos){
        Vuelo[] vuelos;
        vuelos =buscarVuelosOrigenDestino(origen,destino,aeroputuertos);
        if(vuelos.length==0){
            System.out.println("\nNo hay vuelos disponibles.\n");
        }
        else{
            System.out.println("\nVuelos encontrados:\n");
            for (Vuelo vuelo : vuelos) {
                System.out.println("Identificador: " + vuelo.getIdentificador());
                System.out.println("Origen: " + vuelo.getCiudadOrigen());
                System.out.println("Destino: " + vuelo.getCiudadDestino());
                System.out.println("Precio: " + vuelo.getPrecio());
                System.out.println();
            }
        }
    }
    public static void insertarDatosAeroputuerto(Aeroputuerto[] aero){
        aero[0]= new AeroputuertoPublico("Barajas","Madrid","España",900000000);
        aero[0].insertarCompañia(new Compañia("Putoair"));
        aero[0].insertarCompañia(new Compañia("Lufthansa"));
        aero[0].insertarCompañia(new Compañia("Iberia"));
        aero[0].getCompañia("Putoair").insertarVuelo(new Vuelo("TK01","Madrid","Tokio",646,139));
        aero[0].getCompañia("Putoair").insertarVuelo(new Vuelo("DB01","Madrid","Dublin",44,139));
        aero[0].getCompañia("Lufthansa").insertarVuelo(new Vuelo("NY01","Madrid","New York",433,139));
        aero[0].getCompañia("Lufthansa").insertarVuelo(new Vuelo("BL01","Madrid","Berlin",68,139));
        aero[0].getCompañia("Iberia").insertarVuelo(new Vuelo("BL02","Madrid","Berlin",65,159));
        aero[0].getCompañia("Iberia").insertarVuelo(new Vuelo("PA01","Madrid","Paris",72,139));
        aero[0].getCompañia("Putoair").getVuelo("TK01").insertarPasajero(new Pasajero("Ana Belén Martinez","Su carné de payaso","Al-Andalusí"));
        aero[0].getCompañia("Putoair").getVuelo("TK01").insertarPasajero(new Pasajero("Víctor Medina","Su carné de payaso","Al-Andalusí"));
        aero[0].getCompañia("Putoair").getVuelo("DB01").insertarPasajero(new Pasajero("Victoria Arellano","Dracaris","Española"));
        aero[0].getCompañia("Putoair").getVuelo("DB01").insertarPasajero(new Pasajero("Martina Medina","Tiene","Española"));
        aero[0].getCompañia("Lufthansa").getVuelo("BL01").insertarPasajero(new Pasajero("David Triviño","Las drojas","Española"));
        aero[0].getCompañia("Lufthansa").getVuelo("BL01").insertarPasajero(new Pasajero("El Sisioh","Asegurar que el SFV es superior","Marroquí"));
        aero[0].getCompañia("Lufthansa").getVuelo("NY01").insertarPasajero(new Pasajero("Vicmi","El comunismo no necesita pasaportes","Española"));
        aero[0].getCompañia("Lufthansa").getVuelo("NY01").insertarPasajero(new Pasajero("Yeray","Sus músculos","Española"));
        aero[0].getCompañia("Iberia").getVuelo("BL02").insertarPasajero(new Pasajero("Don Peter","La navaja de afeitar abre puertas","Española"));
        aero[0].getCompañia("Iberia").getVuelo("BL02").insertarPasajero(new Pasajero("Perico Palotes","No lo necesita","Española"));
        aero[0].getCompañia("Iberia").getVuelo("PA01").insertarPasajero(new Pasajero("Juliana Anal","Juliana Anal","Argentina"));
        aero[0].getCompañia("Iberia").getVuelo("PA01").insertarPasajero(new Pasajero("Natalia Almada","Los productos de colorista son peligrosos","Argentina"));
        aero[1]= new AeroputuertoPublico("Granada-Jaen","Granada","España",100000000);
        aero[1].insertarCompañia(new Compañia("Putoair"));
        aero[1].insertarCompañia(new Compañia("Vueling"));
        aero[1].insertarCompañia(new Compañia("Iberworld"));
        aero[1].getCompañia("Putoair").insertarVuelo(new Vuelo("MD01","Granada","Madrid",43,139));
        aero[1].getCompañia("Putoair").insertarVuelo(new Vuelo("BC01","Granada","Barcelona",50,139));
        aero[1].getCompañia("Vueling").insertarVuelo(new Vuelo("CP01","Granada","Santiago",103,139));
        aero[1].getCompañia("Vueling").insertarVuelo(new Vuelo("BI01","Granada","Bilbao",71,139));
        aero[1].getCompañia("Iberworld").insertarVuelo(new Vuelo("ST01","Granada","Santander",89,139));
        aero[1].getCompañia("Iberworld").insertarVuelo(new Vuelo("LI01","Granada","Lisboa",111,139));
        aero[1].getCompañia("Putoair").getVuelo("MD01").insertarPasajero(new Pasajero("Ana Belén Martinez","Su carné de payaso","Al-Andalusí"));
        aero[1].getCompañia("Putoair").getVuelo("MD01").insertarPasajero(new Pasajero("Víctor Medina","Su carné de payaso","Al-Andalusí"));
        aero[1].getCompañia("Putoair").getVuelo("BC01").insertarPasajero(new Pasajero("Victoria Arellano","Dracaris","Española"));
        aero[1].getCompañia("Putoair").getVuelo("BC01").insertarPasajero(new Pasajero("Martina Medina","Tiene","Española"));
        aero[1].getCompañia("Vueling").getVuelo("CP01").insertarPasajero(new Pasajero("David Triviño","Las drojas","Española"));
        aero[1].getCompañia("Vueling").getVuelo("CP01").insertarPasajero(new Pasajero("El Sisioh","Asegurar que el SFV es superior","Marroquí"));
        aero[1].getCompañia("Vueling").getVuelo("BI01").insertarPasajero(new Pasajero("Vicmi","El comunismo no necesita pasaportes","Española"));
        aero[1].getCompañia("Vueling").getVuelo("BI01").insertarPasajero(new Pasajero("Yeray","Sus músculos","Española"));
        aero[1].getCompañia("Iberworld").getVuelo("ST01").insertarPasajero(new Pasajero("Don Peter","La navaja de afeitar abre puertas","Española"));
        aero[1].getCompañia("Iberworld").getVuelo("ST01").insertarPasajero(new Pasajero("Perico Palotes","No lo necesita","Española"));
        aero[1].getCompañia("Iberworld").getVuelo("LI01").insertarPasajero(new Pasajero("Juliana Anal","Juliana Anal","Argentina"));
        aero[1].getCompañia("Iberworld").getVuelo("LI01").insertarPasajero(new Pasajero("Natalia Almada","Los productos de colorista son peligrosos","Argentina"));
        aero[2]= new AeroputuertoPrivado("Castellón","Benilloch","España");
        aero[2].insertarCompañia(new Compañia("Executive Airlines"));
        aero[2].insertarCompañia(new Compañia("Gestair"));
        String[] empresas2={"Marina d'Or","Cacaolat","Nueva Rumasa"};
        ((AeroputuertoPrivado)aero[2]).insertarEmpresas(empresas2);
        aero[2].getCompañia("Executive Airlines").insertarVuelo(new Vuelo("MD02","Castellón","Madrid",200,50));
        aero[2].getCompañia("Executive Airlines").insertarVuelo(new Vuelo("BC02","Castellón","Barcelona",150,50));
        aero[2].getCompañia("Gestair").insertarVuelo(new Vuelo("PA02","Castellón","Paris",450,50));
        aero[2].getCompañia("Gestair").insertarVuelo(new Vuelo("LD02","Castellón","Londres",600,50));
        aero[2].getCompañia("Executive Airlines").getVuelo("MD02").insertarPasajero(new Pasajero("Ana Belén Martinez","Su carné de payaso","Al-Andalusí"));
        aero[2].getCompañia("Executive Airlines").getVuelo("MD02").insertarPasajero(new Pasajero("Víctor Medina","Su carné de payaso","Al-Andalusí"));
        aero[2].getCompañia("Executive Airlines").getVuelo("MD02").insertarPasajero(new Pasajero("Victoria Arellano","Dracaris","Española"));
        aero[2].getCompañia("Executive Airlines").getVuelo("MD02").insertarPasajero(new Pasajero("Martina Medina","Tiene","Española"));
        aero[2].getCompañia("Executive Airlines").getVuelo("BC02").insertarPasajero(new Pasajero("David Triviño","Las drojas","Española"));
        aero[2].getCompañia("Executive Airlines").getVuelo("BC02").insertarPasajero(new Pasajero("El Sisioh","Asegurar que el SFV es superior","Marroquí"));
        aero[2].getCompañia("Gestair").getVuelo("PA02").insertarPasajero(new Pasajero("Vicmi","El comunismo no necesita pasaportes","Española"));
        aero[2].getCompañia("Gestair").getVuelo("PA02").insertarPasajero(new Pasajero("Yeray","Sus músculos","Española"));
        aero[2].getCompañia("Gestair").getVuelo("PA02").insertarPasajero(new Pasajero("Don Peter","La navaja de afeitar abre puertas","Española"));
        aero[2].getCompañia("Gestair").getVuelo("LD02").insertarPasajero(new Pasajero("Perico Palotes","No lo necesita","Española"));
        aero[2].getCompañia("Gestair").getVuelo("LD02").insertarPasajero(new Pasajero("Juliana Anal","Juliana Anal","Argentina"));
        aero[2].getCompañia("Gestair").getVuelo("LD02").insertarPasajero(new Pasajero("Natalia Almada","Los productos de colorista son peligrosos","Argentina"));
        aero[3]=new AeroputuertoPrivado("Teruel","Teruel","España");
        aero[3].insertarCompañia(new Compañia("Luxury Putoair"));
        String[] empresas={"Teruel existe","Mafia Turolense SA","Talleres Paco","FBI"};
        ((AeroputuertoPrivado)aero[3]).insertarEmpresas(empresas);
        aero[3].getCompañia("Luxury Putoair").insertarVuelo(new Vuelo("P4T0","Teruel","Granada",1500,12));
        aero[3].getCompañia("Luxury Putoair").getVuelo("P4T0").insertarPasajero(new Pasajero("Ana Belén Martinez","Su carné de payaso","Al-Andalusí"));
        aero[3].getCompañia("Luxury Putoair").getVuelo("P4T0").insertarPasajero(new Pasajero("Víctor Medina","Su carné de payaso","Al-Andalusí"));
        aero[3].getCompañia("Luxury Putoair").getVuelo("P4T0").insertarPasajero(new Pasajero("Victoria Arellano","Dracaris","Española"));
        aero[3].getCompañia("Luxury Putoair").getVuelo("P4T0").insertarPasajero(new Pasajero("Martina Medina","Tiene","Española"));
        aero[3].getCompañia("Luxury Putoair").getVuelo("P4T0").insertarPasajero(new Pasajero("David Triviño","Las drojas","Española"));
        aero[3].getCompañia("Luxury Putoair").getVuelo("P4T0").insertarPasajero(new Pasajero("El Sisioh","Asegurar que el SFV es superior","Marroquí"));
        aero[3].getCompañia("Luxury Putoair").getVuelo("P4T0").insertarPasajero(new Pasajero("Vicmi","El comunismo no necesita pasaportes","Española"));
        aero[3].getCompañia("Luxury Putoair").getVuelo("P4T0").insertarPasajero(new Pasajero("Yeray","Sus músculos","Española"));
        aero[3].getCompañia("Luxury Putoair").getVuelo("P4T0").insertarPasajero(new Pasajero("Don Peter","La navaja de afeitar abre puertas","Española"));
        aero[3].getCompañia("Luxury Putoair").getVuelo("P4T0").insertarPasajero(new Pasajero("Perico Palotes","No lo necesita","Española"));
        aero[3].getCompañia("Luxury Putoair").getVuelo("P4T0").insertarPasajero(new Pasajero("Juliana Anal","Juliana Anal","Argentina"));
        aero[3].getCompañia("Luxury Putoair").getVuelo("P4T0").insertarPasajero(new Pasajero("Natalia Almada","Los productos de colorista son peligrosos","Argentina"));
    }
}