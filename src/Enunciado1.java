/** Software de gestión de Compañia aeroputuaria - Proyecto POO
 *
 * Diseñar y codificar una aplicación informática para una compañía de gestión aeroputuaria, satisfaciendo los siguientes
 * requisitos:
 *
 * - Para cada aeropuerto es necesario saber:
 *      a) Todas las compañías de vuelos que operan en él.
 *      b) Nombre del aeropuerto, la ciudad donde se ubica, y el país al que pertenece.
 *
 * - Cada Compañía se caracteriza por el nombre y la lista de vuelos que ofrece.
 * - Los vuelos están definidos por su identificador, la ciudad de origen, la ciudad de destino, el precio del viaje, la lista
 * de pasajeros, el número máximo de pasajeros permitidos en el vuelo, y el número real de pasajeros que ha reservado asiento
 * en el avión.
 * - Los aeropuertos pueden ser privados o públicos.
 *      a) Los aeropuertos privados tienen una serie de empresas que los patrocinan, y es necesario saber el nombre de cada
 *      una de esas empresas.
 *      b) Para los aeropuertos públicos se requiere saber la cantidad de dinero correspondiente a la subvención del gobierno.
 *
 * - Se necesita gestionar también la información de los pasajeros
 *      a) Para cada pasajero se necesita saber nombre, número de pasaporte y nacionalidad.
 *
 * ___________________________________________________________________________________________________________________________
 *
 * La aplicación tendrá un menú con las siguientes opciones:
 *
 * 1) Consultar los aeropuertos gestionados, indicando separadamente los aeropuertos públicos y los privados. Para cada uno de
 *    ellos deberá mostrar su nombre, la ciudad de ubicación, y el país al que pertenece.
 * 2) Visualizar las empresas que patrocinan un determinado aeropuerto en caso de que sea privado, o la cuantía de la subvención
 *    en caso de que se trate de un aeropuerto público.
 * 3) Para un determinado aeropuerto, se debe poder mostrar la lista de compañías que vuelan desde ese aeropuerto.
 * 4) Para una determinada compañia que opera en un aeropuerto concreto, listar todos los posibles vuelos que dicha compañía
 *    ofrece, mostrando su identificador, la ciudad origen y destino, y el precio del vuelo.
 * 5) Mostrar todos los posibles vuelos (identificador) que parten de una ciudad origen a otra ciudad destino (indicadas por
 *    el usuario), y mostrar su precio.
 */