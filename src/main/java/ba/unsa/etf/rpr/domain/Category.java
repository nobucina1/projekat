package ba.unsa.etf.rpr.domain;

import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;

/**
 * Class for category
 * @author Nermin Obucina
 */

public class Category implements Idable {

    private int idcategory;
    private SimpleStringProperty name;

    @Override
    public void setId(int idcategory) {
        this.idcategory = idcategory;
    }

    @Override
    public int getId() {
        return idcategory;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
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

    public SimpleStringProperty nameProperty() {
        return name;
    }

    @Override
    public String toString() {
        return name.get();
    }
}
