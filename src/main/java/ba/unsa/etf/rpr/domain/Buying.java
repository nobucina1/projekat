package ba.unsa.etf.rpr.domain;

import java.util.Date;
import java.util.Objects;

/**
 * Class for buying
 * @author Nermin Obucina
 */

public class Buying implements Idable{
    private int idbuying;
    private Date date_of_buying;
    private User iduser;
    private Clothes idclothes;

    @Override
    public int getId() {
        return idbuying;
    }

    @Override
    public void setId(int id) {
        this.idbuying = id;
    }

    public Date getDate_of_buying() {
        return date_of_buying;
    }

    public void setDate_of_buying(Date date_of_buying) {
        this.date_of_buying = date_of_buying;
    }

    public User getIduser() {
        return iduser;
    }

    public void setIduser(User iduser) {
        this.iduser = iduser;
    }

    public Clothes getIdclothes() {
        return idclothes;
    }

    public void setIdclothes(Clothes idclothes) {
        this.idclothes = idclothes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Buying buying = (Buying) o;
        return idbuying == buying.idbuying && iduser == buying.iduser && idclothes == buying.idclothes && date_of_buying.equals(buying.date_of_buying);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idbuying, date_of_buying, iduser, idclothes);
    }

    @Override
    public String toString() {
        return "Buying{" +
                "idbuying=" + idbuying +
                ", date_of_buying=" + date_of_buying +
                ", iduser=" + iduser +
                ", idclothes=" + idclothes +
                '}';
    }
}
