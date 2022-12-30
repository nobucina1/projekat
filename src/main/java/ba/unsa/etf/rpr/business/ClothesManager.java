package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Clothes;
import ba.unsa.etf.rpr.exceptions.ShopException;

import java.util.List;

/**
 * Business Logic Layer for Clothes
 *
 * @author Nermin Obucina
 */
public class ClothesManager {

    public List<Clothes> getAll() throws ShopException {
        return DaoFactory.clothesDao().getAll();
    }
}
