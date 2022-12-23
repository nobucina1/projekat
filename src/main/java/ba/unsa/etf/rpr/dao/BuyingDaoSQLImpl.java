package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Buying;
import ba.unsa.etf.rpr.exceptions.ShopException;

import java.sql.ResultSet;
import java.util.Map;

/**
 * MySQL's implementation of the DAO
 * @author Nermin Obucina
 */
public class BuyingDaoSQLImpl extends AbstractDao<Buying> implements BuyingDao{
    public BuyingDaoSQLImpl(String tableName) {
        super(tableName);
    }

    @Override
    public Buying row2object(ResultSet rs) throws ShopException {
        return null;
    }

    @Override
    public Map<String, Object> object2row(Buying object) {
        return null;
    }
}
