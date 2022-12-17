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
}

