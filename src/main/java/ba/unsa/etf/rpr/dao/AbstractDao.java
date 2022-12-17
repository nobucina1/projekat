package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Idable;
import ba.unsa.etf.rpr.exceptions.ShopException;
import java.sql.*;
import java.util.*;

/**
 * Abstract class that implements core DAO CRUD methods for every entity
 */
public abstract class AbstractDao<T extends Idable> implements Dao<T> {
    private Connection connection;
    private String tableName;

    public AbstractDao(String tableName) {
        try {
            this.tableName = tableName;
            Properties p = new Properties();
            p.load(ClassLoader.getSystemResource("db.properties").openStream());
            String url = p.getProperty("url");
            String user = p.getProperty("user");
            String password = p.getProperty("password");
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println("Nemoguce uraditi konekciju na bazu");
            e.printStackTrace();

        }
    }

    public Connection getConnection() {
        return this.connection;
    }

    public abstract T row2object(ResultSet rs) throws ShopException;

    public abstract Map<String, Object> object2row(T object);

    public T getById(int id) throws ShopException {
        String query = "SELECT * FROM " + this.tableName + " WHERE id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) { // result set is iterator.
                T result = row2object(rs);
                rs.close();
                return result;
            } else {
                throw new ShopException("Object not found");
            }
        } catch (SQLException e) {
            throw new ShopException(e.getMessage(), e);
        }
    }

    public List<T> getAll() throws ShopException {
        String query = "SELECT * FROM " + tableName;
        List<T> results = new ArrayList<T>();
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) { // result set is iterator.
                T object = row2object(rs);
                results.add(object);
            }
            rs.close();
            return results;
        } catch (SQLException e) {
            throw new ShopException(e.getMessage(), e);
        }
    }

    public void delete(int id) throws ShopException {
        String sql = "DELETE FROM " + tableName + " WHERE id = ?";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ShopException(e.getMessage(), e);
        }
    }


    /**
     * Accepts KV storage of column names and return CSV of columns and question marks for insert statement
     * Example: (id, name, date) ?,?,?
     */
    private Map.Entry<String, String> prepareInsertParts(Map<String, Object> row) {
        StringBuilder columns = new StringBuilder();
        StringBuilder questions = new StringBuilder();

        int counter = 0;
        for (Map.Entry<String, Object> entry : row.entrySet()) {
            counter++;
            if (entry.getKey().equals("id")) continue; //skip insertion of id due autoincrement
            columns.append(entry.getKey());
            questions.append("?");
            if (row.size() != counter) {
                columns.append(",");
                questions.append(",");
            }
        }
        return new AbstractMap.SimpleEntry<String, String>(columns.toString(), questions.toString());
    }


}
