package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.exceptions.ShopException;

/**
 * Business Logic Layer for management of Categories
 *
 * @author Nermin Obucina
 */
public class CategoryManager {

    public void validateCategoryName(String name) throws ShopException {
        if (name == null || name.length() > 45 || name.length() < 3) {
            throw new ShopException("Category name must be between 3 and 45 chars");
        }
    }

}
