package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Category;
import ba.unsa.etf.rpr.exceptions.ShopException;

import java.sql.*;
import java.util.Map;
import java.util.TreeMap;

/**
 * MySQL's implementation of the DAO
 * @author Nermin Obucina
 */
public class CategoryDaoSQLImpl extends AbstractDao<Category> implements CategoryDao{
    private static CategoryDaoSQLImpl instance = null;

    public CategoryDaoSQLImpl() {
        super("category","idcategory");
    }

    public static CategoryDaoSQLImpl getInstance() {
        if (instance == null)
            instance = new CategoryDaoSQLImpl();
        return instance;
    }

    @Override
    public Category row2object(ResultSet rs) throws ShopException {
        try {
            Category cat = new Category();
            cat.setId(rs.getInt("idcategory"));
            cat.setName(rs.getString("name"));
            return cat;
        } catch (SQLException e) {
            throw new ShopException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Category object) {
        Map<String, Object> row = new TreeMap<String, Object>();
        row.put("idcategory", object.getId());
        row.put("name", object.getName());
        return row;
    }
}
