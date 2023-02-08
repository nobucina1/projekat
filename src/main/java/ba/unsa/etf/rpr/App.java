package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.CategoryManager;
import ba.unsa.etf.rpr.business.ClothesManager;
import ba.unsa.etf.rpr.dao.UserDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Category;
import ba.unsa.etf.rpr.domain.Clothes;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.ShopException;

import net.bytebuddy.asm.Advice;
import org.apache.commons.cli.*;


import java.sql.Date;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

/**
 * @author Nermin Obucina
 * CLI (Command Line Interface) implementation in following class
 */
public class App {
    /**
     * Defining final variables to describe all code having options
     */
    private static final Option addClothes = new Option("c","add-clothes",false, "Adding new clothes to database");
    private static final Option addCategory = new Option("cat","add-category",false, "Adding new category to quote-maker database");
    private static final Option getClothes = new Option("getC", "get-clothes",false, "Printing all clothes from database");
    private static final Option getCategories = new Option("getCat", "get-categories",false, "Printing all categories from database");
    private static final Option categoryDefinition = new Option(null, "category",false, "Defining category for next added clothing");
    private static final Option sizeDefinition = new Option(null, "size", false,"Defining size for nes added clothing");
    private static final Option priceDefinition = new Option(null, "price", false,"Defining price for nes added clothing");



    /**
     *
     * @param options
     *
     */
    public static void printFormattedOptions(Options options) {
        HelpFormatter helpFormatter = new HelpFormatter();
        PrintWriter printWriter = new PrintWriter(System.out);
        helpFormatter.printUsage(printWriter, 150, "java -jar projekat.jar [option] 'something else if needed' ");
        helpFormatter.printOptions(printWriter, 150, options, 2, 7);
        printWriter.close();
    }

    public static Options addOptions() {
        Options options = new Options();
        options.addOption(addClothes);
        options.addOption(addCategory);
        options.addOption(getClothes);
        options.addOption(getCategories);
        options.addOption(categoryDefinition);
        options.addOption(sizeDefinition);
        options.addOption(priceDefinition);
        return options;
    }

    public static Category searchThroughCategories(List<Category> listOfCategories, String categoryName) {

        Category category = null;
        category = listOfCategories.stream().filter(cat -> cat.getName().toLowerCase().equals(categoryName.toLowerCase())).findAny().get();
        return category;

    }


    /**
     *
     * @param args
     * @throws Exception
     */

    public static void main(String[] args) throws Exception {
        Options options = addOptions();

        CommandLineParser commandLineParser = new DefaultParser();

        CommandLine cl = commandLineParser.parse(options, args);

//        while(true) {
        if((cl.hasOption(addClothes.getOpt()) || cl.hasOption(addClothes.getLongOpt())) && cl.hasOption((categoryDefinition.getLongOpt())) && cl.hasOption(sizeDefinition.getLongOpt()) && cl.hasOption(priceDefinition.getLongOpt())){
            ClothesManager clothesManager = new ClothesManager();
            CategoryManager categoryManager = new CategoryManager();
            Category category = null;
            try {
                category = searchThroughCategories(categoryManager.getAll(), cl.getArgList().get(1));
            }catch(Exception e) {
                System.out.println("There is no category in the list! Try again.");
                System.exit(1);
            }

//                if(!category.getName().equals(cl.getArgList().get(1))){
//                    System.out.println("There is no category with passed name! Try again.");
//                    System.exit(-1);
//                }
            Clothes clothes = new Clothes();
            clothes.setIdcategory(category);
            clothes.setClothes_name(cl.getArgList().get(0));
            clothes.setSize(Integer.parseInt(cl.getArgList().get(2)));
            clothes.setPrice(Integer.parseInt(cl.getArgList().get(3)));
            clothesManager.add(clothes);
            System.out.println("You successfully added a clothing to database!");
//                break;
        } else if(cl.hasOption(getClothes.getOpt()) || cl.hasOption(getClothes.getLongOpt())){
            ClothesManager clothesManager = new ClothesManager();
            clothesManager.getAll().forEach(c -> System.out.println(c.getClothes_name()));
//                break;
        } else if(cl.hasOption(addCategory.getOpt()) || cl.hasOption(addCategory.getLongOpt())){
            try {
                CategoryManager categoryManager = new CategoryManager();
                Category cat = new Category();
                cat.setName(cl.getArgList().get(0));
                categoryManager.add(cat);
                System.out.println("Category has been added successfully");
//                    break;
            }catch(Exception e) {
                System.out.println("There is already category with same name in database! Try again");
                System.exit(1);
//                   break;
            }

        } else if(cl.hasOption(getCategories.getOpt()) || cl.hasOption(getCategories.getLongOpt())){
            CategoryManager categoryManager = new CategoryManager();
            categoryManager.getAll().forEach(c -> System.out.println(c.getName()));
//                break;
        } else {
            printFormattedOptions(options);
            System.exit(-1);
//                break;
        }
//        }
    }
}