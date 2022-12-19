package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Category;
import ba.unsa.etf.rpr.domain.Clothes;
import ba.unsa.etf.rpr.exceptions.ShopException;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class ClothesDaoSQLImpl extends AbstractDao<Clothes> implements ClothesDao{
    public ClothesDaoSQLImpl(String tableName) {
        super(tableName);
    }

    @Override
    public Clothes row2object(ResultSet rs) throws ShopException {
        return null;
    }

    @Override
    public Map<String, Object> object2row(Clothes object) {
        return null;
    }

    @Override
    public List<Clothes> searchByText(String text) throws ShopException {
        return null;
    }

    @Override
    public List<Clothes> searchByCategory(Category category) throws ShopException {
        return null;
    }

    @Override
    public List<Clothes> searchBySize(int size) throws ShopException {
        return null;
    }
}
