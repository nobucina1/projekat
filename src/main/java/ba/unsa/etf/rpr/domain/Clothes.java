package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * Class for Clothes
 * @author Nermin Obucina
 */

public class Clothes {

    private int idclothes;
    private String clothes_name;
    private int idcategory;
    private int size;
    private int price;

    public int getIdclothes() {
        return idclothes;
    }

    public void setIdclothes(int idclothes) {
        this.idclothes = idclothes;
    }

    public String getClothes_name() {
        return clothes_name;
    }

    public void setClothes_name(String clothes_name) {
        this.clothes_name = clothes_name;
    }

    public int getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(int idcategory) {
        this.idcategory = idcategory;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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

    @Override
    public String toString() {
        return "Clothes{" +
                "idclothes=" + idclothes +
                ", clothes_name='" + clothes_name + '\'' +
                ", idcategory=" + idcategory +
                ", size=" + size +
                ", price=" + price +
                '}';
    }
}
