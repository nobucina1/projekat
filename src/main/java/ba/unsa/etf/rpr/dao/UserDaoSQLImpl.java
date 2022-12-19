package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Category;
import ba.unsa.etf.rpr.domain.Clothes;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.ShopException;
import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * MySQL's implementation of the DAO
 * @author Nermin Obucina
 */

public class UserDaoSQLImpl extends AbstractDao<User> implements UserDao{
    public UserDaoSQLImpl(String tableName) {
        super("Users");
    }

    @Override
    public User row2object(ResultSet rs) throws ShopException {
        try {
            User q = new User();
            q.setId(rs.getInt("iduser"));
            q.setName(rs.getString("name"));
            q.setSurname(rs.getString("surname"));
            q.setMail(rs.getString("mail"));
            q.setAddress(rs.getString("address"));
            return q;
        } catch (Exception e) {
            throw new ShopException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(User object) {
        return null;
    }

    @Override
    public List<Clothes> searchByName(String text) throws ShopException {
        return null;
    }

    @Override
    public List<Clothes> searchBySurname(Category category) throws ShopException {
        return null;
    }
}
