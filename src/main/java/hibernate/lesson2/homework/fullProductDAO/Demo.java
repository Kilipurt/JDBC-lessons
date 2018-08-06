package hibernate.lesson2.homework.fullProductDAO;

import java.util.Arrays;

public class Demo {
    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();

//        Product product = new Product();
//        product.setName("save");
//        product.setDescription("save");
//        product.setPrice(10);
//
//        productDAO.save(product);
//
//        product.setName("updated");
//        product.setId(31);
//
//        productDAO.update(product);
//
//        productDAO.delete(product);

        Product product1 = new Product();
        product1.setName("save1");
        product1.setDescription("save1");
        product1.setPrice(10);

        Product product2 = new Product();
        product2.setName("save2");
        product2.setDescription("save2");
        product2.setPrice(10);

        Product product3 = new Product();
        product3.setName("save3");
        product3.setDescription("save3");
        product3.setPrice(10);

//        productDAO.saveAll(Arrays.asList(product1, product2, product3));

        product1.setId(33);
        product1.setName("updated1");

        product2.setId(35);
        product2.setName("updated2");

        product3.setId(37);
        product3.setName("updated3");

//        productDAO.updateAll(Arrays.asList(product1, product2, product3));

        productDAO.deleteAll(Arrays.asList(product1, product2, product3));
    }
}
