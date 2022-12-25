package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Category;
import ba.unsa.etf.rpr.domain.Clothes;
import ba.unsa.etf.rpr.exceptions.ShopException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ClothesDaoSQLImpl extends AbstractDao<Clothes> implements ClothesDao{
    public ClothesDaoSQLImpl() {
        super("Clothes");
    }

    @Override
    public Clothes row2object(ResultSet rs) throws ShopException {
        try {
            Clothes c = new Clothes();
            c.setId(rs.getInt("idclothes"));
            c.setClothes_name(rs.getString("clothes_name"));
            c.setIdcategory(DaoFactory.categoryDao().getById(rs.getInt("idcategory")));
            c.setSize(rs.getInt("size"));
            c.setPrice(rs.getInt("price"));
            return c;
        } catch (Exception e) {
            throw new ShopException(e.getMessage(),e);
        }

    }

    @Override
    public Map<String, Object> object2row(Clothes object) {
        Map<String, Object> item = new TreeMap<String, Object>();
        item.put("idclothes", object.getId());
        item.put("clothes_name", object.getClothes_name());
        item.put("category_id", object.getIdcategory().getId());
        item.put("size", object.getSize());
        item.put("price", object.getPrice());
        return item;
    }

    /**
     * Returns all clothes that contains given text.
     *
     * @param text search string for clothes
     * @return list of clothes
     */
    @Override
    public List<Clothes> searchByText(String text) throws ShopException {
        //mora sa concat jer inace nece raditi jer radi sa key chars
        String query = "SELECT * FROM quotes WHERE clothes_name LIKE concat('%', ?, '%')";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setString(1, text);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Clothes> clothesLista = new ArrayList<>();
            while (rs.next()) {
                clothesLista.add(row2object(rs));
            }
            return clothesLista;
        } catch (SQLException e) {
            throw new ShopException(e.getMessage(), e);
        }
    }

    /**
     * Returns all clothes that are given Category.
     *
     * @param category search string for clothes
     * @return list of clothes
     */
    @Override
    public List<Clothes> searchByCategory(Category category) throws ShopException {
        return null;
    }

    /**
     * Returns all clothes that are given size.
     *
     * @param size search string for clothes
     * @return list of clothes
     */
    @Override
    public List<Clothes> searchBySize(int size) throws ShopException {
        return null;
    }
}
