package jdbc.lesson4.homework;

public class Demo {
    public static void main(String[] args) throws Exception {
        Controller controller = new Controller();

        //put test

        //first test
//        Storage storage = new Storage(1, new String[]{"txt", "jpg", "avi", "docs"}, "Ukraine", 20);
//        File file1 = new File(1, "test", "txt", 2);
//        controller.put(storage, file1);

        //second test
//        Storage storage = new Storage(1, new String[]{"txt", "jpg", "avi", "docs"}, "Ukraine", 20);
//        File file2 = new File(2, "test1", "mp3", 4);
//        controller.put(storage, file2);

        //third test
//        Storage storage = new Storage(1, new String[]{"txt", "jpg", "avi", "docs"}, "Ukraine", 20);
//        File file3 = new File(4, "test4", "jpg", 30);
//        controller.put(storage, file3);

        //delete test
//        Storage storage = new Storage(1, new String[]{"txt", "jpg", "avi", "docs"}, "Ukraine", 20);
//        File file1 = new File(1, "test", "txt", 2);
//        controller.delete(storage, file1);

        //transferAll test
//        Storage storage1 = new Storage(1, new String[]{"txt", "jpg", "avi", "docs"}, "Ukraine", 20);
//        Storage storage2 = new Storage(2, new String[]{"txt", "jpg", "avi", "docs", "mp3"}, "Poland", 50);
//
//        //controller.transferAll(storage1, storage2);
//        controller.transferAll(storage2, storage1);

        //transferFile test
        Storage storage1 = new Storage(1, new String[]{"txt", "jpg", "avi", "docs"}, "Ukraine", 20);
        Storage storage2 = new Storage(2, new String[]{"txt", "jpg", "avi", "docs", "mp3"}, "Poland", 50);

        controller.transferFile(storage2, storage1, 4);
    }
}
