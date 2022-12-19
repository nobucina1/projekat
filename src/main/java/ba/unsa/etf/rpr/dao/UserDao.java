package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Category;
import ba.unsa.etf.rpr.domain.Clothes;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.ShopException;

import java.util.List;

/**
 * Dao interface for User domain bean
 *
 * @author Nermin Obucina
 */

public interface UserDao extends Dao<User>{
    /**
     * Returns all users that have given name.
     *
     * @param text search string for users
     * @return list of users
     */
    List<User> searchByName(String text) throws ShopException;

    /**
     * Returns all users that have given surname.
     *
     * @param category search string for users
     * @return list of users
     */
    List<User> searchBySurname(Category category) throws ShopException;

}
