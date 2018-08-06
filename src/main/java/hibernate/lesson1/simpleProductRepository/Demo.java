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
//        productRepository.save(product1);

//        Product product2 = new Product();
//        product2.setId(1);
//        product2.setName("update test");
//        product2.setDescription("product for update");
//        product2.setPrice(70);
//
//        productRepository.update(product2);

        productRepository.delete(1);
    }
}
