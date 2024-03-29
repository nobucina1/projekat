package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.business.UserManager;
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

    private static UserDaoSQLImpl instance = null;

    private static final String SELECT_QUERY = "SELECT * FROM user WHERE mail = ? and password = ?";
    private static final String INSERT_QUERY = "INSERT INTO user (name, surname, mail, password) VALUES (?, ?, ?, ?)";
    public UserDaoSQLImpl() {
        super("user","iduser");
    }

    public static UserDaoSQLImpl getInstance() {
        if (instance == null)
            instance = new UserDaoSQLImpl();
        return instance;
    }

    /**
     * Method for validation of user
     * @param mail
     * @param password
     * @return
     * @throws SQLException
     */
    @Override
    public boolean validate(String mail, String password) throws SQLException {
        try {
                PreparedStatement stmt = getConnection().prepareStatement(SELECT_QUERY);
                stmt.setString(1,mail);
                stmt.setString(2, password);

                System.out.println(stmt);

            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return true;
            }


        } catch (SQLException e) {
            printSQLException(e);
        }
        return false;
    }

    /**
     * Method for setting user's details in database
     * @param name
     * @param surname
     * @param mail
     * @param password
     * @throws SQLException
     */
    @Override
    public void insertRecord(String name, String surname, String mail, String password) throws SQLException {
        try {
            PreparedStatement stmt = getConnection().prepareStatement(INSERT_QUERY);
            stmt.setString(1,name);
            stmt.setString(2, surname);
            stmt.setString(3,mail);
            stmt.setString(4,password);

            System.out.println(stmt);

            stmt.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    @Override
    public User row2object(ResultSet rs) throws ShopException {
        try {
            User q = new User();
            q.setId(rs.getInt("iduser"));
            q.setName(rs.getString("name"));
            q.setSurname(rs.getString("surname"));
            q.setMail(rs.getString("mail"));
            q.setPassword(rs.getString("password"));
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
        item.put("password", object.getPassword());
        return item;
    }

    /**
     * @param text search string for users with given name
     * @return list of users
     * @author Nermin Obucina
     */
    @Override
    public List<User> searchByName(String text) throws ShopException {
        //mora sa concat jer inace nece raditi jer radi sa key chars
        String query = "SELECT * FROM user WHERE name LIKE concat('%', ?, '%')";
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

    /**
     * @param text search string for users with given surname
     * @return list of users
     * @author Nermin Obucina
     */
    @Override
    public List<User> searchBySurname(String text) throws ShopException {
        //mora sa concat jer inace nece raditi jer radi sa key chars
        String query = "SELECT * FROM user WHERE surname LIKE concat('%', ?, '%')";
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


}
