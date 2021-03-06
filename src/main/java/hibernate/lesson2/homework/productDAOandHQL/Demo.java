package hibernate.lesson2.homework.productDAOandHQL;

import java.util.List;

public class Demo {
    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();

//        Product product = productDAO.findById(-2L);
//        if (product != null) {
//            System.out.println(product.toString());
//        } else {
//            System.out.println("Product was not found");
//        }

//        List<Product> products = productDAO.findByName(null);

//        List<Product> products = productDAO.findByContainedName("new");

        List<Product> products = productDAO.findByPrice(700, 100);

//        List<Product> products = productDAO.findByNameSortedAsc();

//        List<Product> products = productDAO.findByNameSortedDesc();

//        List<Product> products = productDAO.findByPriceSortedDesc(70, 10);

        for (Product product : products) {
            System.out.println(product);
        }
    }
}
