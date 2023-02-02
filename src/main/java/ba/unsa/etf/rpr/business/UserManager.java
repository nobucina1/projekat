package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Category;
import ba.unsa.etf.rpr.domain.Clothes;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.ShopException;

import java.sql.SQLException;
import java.util.List;

public class UserManager {
    public List<User> getAll() throws ShopException {
        return DaoFactory.userDao().getAll();
    }

    public List<User> searchByName(String text) throws ShopException {
        return DaoFactory.userDao().searchByName(text);
    }

    public List<User> searchBySurname(String text) throws ShopException {
        return DaoFactory.userDao().searchBySurname(text);
    }

    public void delete(int id) throws ShopException {
        DaoFactory.userDao().delete(id);
    }

    public User getById(int userid) throws ShopException {
        return DaoFactory.userDao().getById(userid);
    }

    public void update (User u) throws ShopException {
        DaoFactory.userDao().update(u);
    }

    public User add(User u) throws ShopException {
        return DaoFactory.userDao().add(u);
    }

    public boolean validate(String mail, String password) throws SQLException{
        return DaoFactory.userDao().validate(mail, password);
    }

    public void insertRecord(String name, String surname, String mail, String password) throws SQLException {
        DaoFactory.userDao().insertRecord(name, surname, mail, password);
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
}
