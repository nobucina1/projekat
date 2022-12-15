package ba.unsa.etf.rpr.domain;

import java.util.Date;
import java.util.Objects;

/**
 * Class for buying
 * @author Nermin Obucina
 */

public class Buying {
    private int idbuying;
    private Date date_of_buying;
    private int iduser;
    private int idclothes;

    public int getIdbuying() {
        return idbuying;
    }

    public void setIdbuying(int idbuying) {
        this.idbuying = idbuying;
    }

    public Date getDate_of_buying() {
        return date_of_buying;
    }

    public void setDate_of_buying(Date date_of_buying) {
        this.date_of_buying = date_of_buying;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getIdclothes() {
        return idclothes;
    }

    public void setIdclothes(int idclothes) {
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
