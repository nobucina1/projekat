package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Category;
import ba.unsa.etf.rpr.domain.Clothes;
import ba.unsa.etf.rpr.exceptions.ShopException;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

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
        return null;
    }

    /**
     * Returns all clothes that contains given text.
     *
     * @param text search string for clothes
     * @return list of clothes
     */
    @Override
    public List<Clothes> searchByText(String text) throws ShopException {
        return null;
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
