package ba.unsa.etf.rpr.domain;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;

/**
 * Class for Clothes
 * @author Nermin Obucina
 */

public class Clothes implements Idable{

    private int idclothes;
    private SimpleStringProperty clothes_name;
    private Category idcategory;
    private IntegerProperty size;
    private IntegerProperty price;

    @Override
    public int getId() {
        return idclothes;
    }

    @Override
    public void setId(int id) {
        this.idclothes = id;
    }

    public String getClothes_name() {
        return clothes_name.get();
    }

    public void setClothes_name(String clothes_name) {
        this.clothes_name.set(clothes_name);
    }

    public Category getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(Category idcategory) {
        this.idcategory = idcategory;
    }

    public int getSize() {
        return size.get();
    }

    public void setSize(int size) {
        this.size.set(size);
    }

    public int getPrice() {
        return price.get();
    }

    public void setPrice(int price) {
        this.price.set(price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clothes clothes = (Clothes) o;
        return idclothes == clothes.idclothes && idcategory == clothes.idcategory && size == clothes.size && price == clothes.price && clothes_name.equals(clothes.clothes_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idclothes, clothes_name, idcategory, size, price);
    }

    public SimpleStringProperty clothes_nameProperty() {
        return clothes_name;
    }

    public IntegerProperty sizeProperty() {
        return size;
    }

    public IntegerProperty priceProperty() {
        return price;
    }

    @Override
    public String toString() {
        return "Clothes{" +
                "idclothes=" + idclothes +
                ", clothes_name='" + clothes_name.get() + '\'' +
                ", idcategory=" + idcategory +
                ", size=" + size.get() +
                ", price=" + price.get() +
                '}';
    }
}
