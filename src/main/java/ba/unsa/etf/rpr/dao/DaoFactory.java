package ba.unsa.etf.rpr.dao;

/**
 * Factory method for singleton implementation of DAOs
 *
 * @author Nermin Obucina
 */
public class DaoFactory {

    private static final CategoryDao categoryDao = new CategoryDaoSQLImpl();
    private static final UserDao userDao = new UserDaoSQLImpl();
    private static final ClothesDao clothesDao = new ClothesDaoSQLImpl();
    private static final BuyingDao buyingDao = new BuyingDaoSQLImpl();


    private DaoFactory(){
    }

    public static CategoryDao categoryDao(){
        return categoryDao;
    }

    public static UserDao userDao(){
        return userDao;
    }

    public static ClothesDao clothesDao(){
        return clothesDao;
    }

    public static BuyingDao buyingDao() {
        return buyingDao;
    }

}
