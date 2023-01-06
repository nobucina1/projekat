package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Buying;
import ba.unsa.etf.rpr.exceptions.ShopException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

/**
 * MySQL's implementation of the DAO
 * @author Nermin Obucina
 */
public class BuyingDaoSQLImpl extends AbstractDao<Buying> implements BuyingDao{
    public BuyingDaoSQLImpl() {
        super("buying");
    }

    @Override
    public Buying row2object(ResultSet rs) throws ShopException {
        try {
            Buying cat = new Buying();
            cat.setId(rs.getInt("idbuying"));
            cat.setDate_of_buying(rs.getDate("date_of_buying"));
            cat.setIduser(DaoFactory.userDao().getById(rs.getInt("iduser")));
            cat.setIdclothes(DaoFactory.clothesDao().getById(rs.getInt("idclothes")));
            return cat;
        } catch (SQLException e) {
            throw new ShopException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Buying object) {
        Map<String, Object> item = new TreeMap<String, Object>();
        item.put("idbuying", object.getId());
        item.put("date_of_buying", object.getDate_of_buying());
        item.put("iduser", object.getIduser().getId());
        item.put("idclothes", object.getIdclothes().getId());
        return item;
    }
}
