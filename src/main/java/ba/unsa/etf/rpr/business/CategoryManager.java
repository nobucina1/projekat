package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Category;
import ba.unsa.etf.rpr.exceptions.ShopException;

import java.util.List;

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

    public Category add(Category cat) throws ShopException {
        if (cat.getId() != 0) {
            throw new ShopException("Can't add category with ID. ID is autogenerated");
        }
        validateCategoryName(cat.getName());
        try {
            return DaoFactory.categoryDao().add(cat);
        }catch(ShopException e) {
            if (e.getMessage().contains("UQ_NAME")) {
                throw new ShopException("Category with same name exists");
            }
            throw e;
        }
    }

    public void delete (int categoryId) throws ShopException {
        try {
            DaoFactory.categoryDao().delete(categoryId);
        }catch(ShopException e) {
            if (e.getMessage().contains("FOREIGN KEY")) {
                throw new ShopException("Cannot delete category which is related to clothes. First delete related clothes before deleting category.");
            }
            throw e;
        }
    }

    public Category update (Category cat) throws ShopException {
        validateCategoryName(cat.getName());
        return DaoFactory.categoryDao().update(cat);
    }

    public List<Category> getAll() throws ShopException {
        return DaoFactory.categoryDao().getAll();
    }

}
