package lesson3;

public class Demo {
    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();
        Product product = new Product(10, "test", "test description", 99);

        //productDAO.save(product);

        //System.out.println(productDAO.getProducts());

//        try {
//            productDAO.update(product);
//        } catch (Exception e) {
//            System.err.println(e.getMessage());
//        }

        try {
            productDAO.delete(10);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
