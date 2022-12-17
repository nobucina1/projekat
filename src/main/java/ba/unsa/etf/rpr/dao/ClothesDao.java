package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Category;
import ba.unsa.etf.rpr.domain.Clothes;
import ba.unsa.etf.rpr.exceptions.ShopException;

import java.util.List;

/**
 * Dao interface for Clothes domain bean
 *
 * @author Nermin Obucina
 */
public interface ClothesDao extends Dao<Clothes>{

    /**
     * Returns all clothes that contains given text.
     *
     * @param text search string for clothes
     * @return list of clothes
     */
    List<Clothes> searchByText(String text) throws ShopException;

    /**
     * Returns all clothes that contains given text.
     *
     * @param category search string for clothes
     * @return list of clothes
     */
    List<Clothes> searchByCategory(Category category) throws ShopException;

    /**
     * Returns all clothes in specific size
     *
     * @param size search string for clothes
     * @return list of clothesg
     */
    List<Clothes> searchBySize(int size) throws ShopException;
}
