package jdbc.lesson4.homework;

public class Controller {
    private static FileDAO fileDAO = new FileDAO();
    private static OperationsValidator operationsValidator = new OperationsValidator();

    public void put(Storage storage, File file) throws Exception {
        operationsValidator.validateFilePut(storage, file);

        file.setStorage(storage);
        fileDAO.update(file);
    }

    public void delete(Storage storage, File file) throws Exception {
        operationsValidator.validateFileDelete(storage, file);

        file.setStorage(null);
        fileDAO.update(file);
    }

    public void transferAll(Storage storageFrom, Storage storageTo) throws Exception {
        operationsValidator.validateFilesTransfer(storageFrom, storageTo);

        fileDAO.changeStorage(fileDAO.findFilesByStorageId(storageFrom.getId()), storageTo.getId());
    }

    public void transferFile(Storage storageFrom, Storage storageTo, long id) throws Exception {
        File file = fileDAO.findById(id);

        operationsValidator.validateFileTransfer(storageFrom, storageTo, file);

        file.setStorage(storageTo);

        fileDAO.update(file);
    }
}
