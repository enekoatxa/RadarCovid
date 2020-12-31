package AppService;

import DAO.DAOGestor;
import DAO.DAOPositiveGestor;
import Objects.User;

/**
 * AppService que delega las llamadas relacionadas con el registro de positivos al {@link DAOGestor}.
 * @author Alumno
 *
 */
public class PositiveGestor {
    private static PositiveGestor positivegestor = null;

    private PositiveGestor(){}
    /**
     * Metodo que crea o recupera la instancia del {@link PositiveGestor}. Sigue el patron Singleton.
     * @return positivegestor
     */
    public static PositiveGestor getPositivegestor(){
        synchronized (PositiveGestor.class)
        {
            if(positivegestor == null) positivegestor = new PositiveGestor();
        }
        return positivegestor;
    }
    /**
     * Metodo para registrar el positivo en RadarCovid. Delega la funcion en el {@link DAOGestor#registerPositive(double, double, int, int, int)}.
     * @param latitude
     * @param longitude
     * @param year
     * @param month
     * @param day
     * @return boolean (para indicar si se ha registrado con exito o no)
     */
    public boolean registerPositive(double latitude, double longitude, int year, int month, int day) {
        return DAOGestor.getDAOgestor().registerPositive(latitude, longitude, year, month, day);
    }
    /**
     * Metodo para seleccionar los positivos registrados en RadarCovid. Delega la funcion en el {@link DAOGestor#positivesList()}.
     * @return
     */
    public String searchPositives() {
        return DAOGestor.getDAOgestor().positivesList();
    }

    /**
     * Metodo para alertar al usuario loggeado de si se encuentra en una zona con un gran numero de contagios o no. Delega la funcion en el {@link DAOGestor#alertOfRisk(double, double)}.
     * @param latitude
     * @param longitude
     * @return boolean (para indicar si se encuentra en una zona de riesgo o no)
     */
    public boolean alarmSystem (double latitude, double longitude){
        return DAOGestor.getDAOgestor().alertOfRisk(latitude, longitude);
    }
}




