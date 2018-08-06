package hibernate.lesson2.homework.productDAOandNativeSQL;

import java.util.List;

public class Demo {
    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();

        Product product = productDAO.findById(25L);
        System.out.println(product.toString());

//        List<Product> products = productDAO.findByName("table new");

//        List<Product> products = productDAO.findByContainedName("new");

//        List<Product> products = productDAO.findByPrice(70, 10);

//        List<Product> products = productDAO.findByNameSortedAsc();

//        List<Product> products = productDAO.findByNameSortedDesc();

//        List<Product> products = productDAO.findByPriceSortedDesc(70, 10);
//
//        for (Product product : products) {
//            System.out.println(product);
//        }
    }
}
