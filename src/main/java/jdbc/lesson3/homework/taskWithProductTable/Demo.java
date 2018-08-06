package jdbc.lesson3.homework.taskWithProductTable;

public class Demo {
    public static void main(String[] args) {
        Solution solution = new Solution();

        //System.out.println(solution.findProductsByPrice(100, 50));

//        try {
//            System.out.println(solution.findProductsByName("toy"));
//        } catch (Exception e) {
//            System.err.println(e.getMessage());
//        }

        System.out.println(solution.findProductsWithEmptyDescription());
    }
}
