package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Category;
import ba.unsa.etf.rpr.domain.Clothes;
import ba.unsa.etf.rpr.exceptions.ShopException;

import java.util.List;

/**
 * Business Logic Layer for management of Clothes
 *
 * @author Nermin Obucina
 */
public class ClothesManager {

    public List<Clothes> getAll() throws ShopException {
        return DaoFactory.clothesDao().getAll();
    }

    public List<Clothes> searchClothes(String text) throws ShopException {
        return DaoFactory.clothesDao().searchByText(text);
    }

    public List<Clothes> searchByCategory(Category category) throws ShopException {
        return DaoFactory.clothesDao().searchByCategory(category);
    }

    public void delete(int id) throws ShopException {
        DaoFactory.clothesDao().delete(id);
    }

    public Clothes getById(int clothesId) throws ShopException {
        return DaoFactory.clothesDao().getById(clothesId);
    }

    public void update (Clothes c) throws ShopException {
        DaoFactory.clothesDao().update(c);
    }

    public Clothes add(Clothes c) throws ShopException {
        return DaoFactory.clothesDao().add(c);
    }
}
