package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * Class for category
 * @author Nermin Obucina
 */

public class Category implements Idable {

    private int idcategory;
    private String name;

    public int getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(int idcategory) {
        this.idcategory = idcategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return idcategory == category.idcategory && name.equals(category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idcategory, name);
    }

    @Override
    public String toString() {
        return "category{" +
                "idcategory=" + idcategory +
                ", name='" + name + '\'' +
                '}';
    }
}
