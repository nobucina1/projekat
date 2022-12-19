package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Category;
import ba.unsa.etf.rpr.domain.Clothes;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.ShopException;
import java.sql.*;
import java.util.ArrayList;
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
        Map<String, Object> item = new TreeMap<String, Object>();
        item.put("iduser", object.getId());
        item.put("name", object.getName());
        item.put("surname", object.getSurname());
        item.put("mail", object.getMail());
        item.put("address", object.getAddress());
        return item;
    }

    /**
     * @param text search string for users with given name
     * @return list of users
     * @author ahajro2
     */
    @Override
    public List<User> searchByName(String text) throws ShopException {
        //mora sa concat jer inace nece raditi jer radi sa key chars
        String query = "SELECT * FROM User WHERE name LIKE concat('%', ?, '%')";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setString(1, text);
            ResultSet rs = stmt.executeQuery();
            ArrayList<User> quoteLista = new ArrayList<>();
            while (rs.next()) {
                quoteLista.add(row2object(rs));
            }
            return quoteLista;
        } catch (SQLException e) {
            throw new ShopException(e.getMessage(), e);
        }
    }

    @Override
    public List<User> searchBySurname(Category category) throws ShopException {
        return null;
    }


}
