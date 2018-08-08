package hibernate.lesson1.simpleProductRepository;

public class Demo {
    public static void main(String[] args) {
        ProductRepository productRepository = new ProductRepository();

//        Product product1 = new Product();
//        product1.setId(1);
//        product1.setName("save test");
//        product1.setDescription("product for save");
//        product1.setPrice(70);
//
//        try {
//            productRepository.save(product1);
//        } catch (Exception e) {
//            System.err.println(e.getMessage());
//        }

        Product product2 = new Product();
        product2.setId(2);
        product2.setName("update test");
        product2.setDescription("product for update");
        product2.setPrice(70);

        try {
            productRepository.update(product2);
        }catch(Exception e){
            System.err.println(e.getMessage());
        }

//        productRepository.delete(2);
    }
}
