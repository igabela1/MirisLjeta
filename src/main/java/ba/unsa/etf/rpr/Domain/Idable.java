package ba.unsa.etf.rpr.Domain;
/**
 * Interface that forces all POJO beans to have ID field.
 *@author Ilhana Gabela
 */
public interface Idable {

    /**
     * Sets id.
     *
     * @param id the id
     */
    void setId(int id);

    /**
     * Gets id.
     *
     * @return the id
     */
    int getId();
}
