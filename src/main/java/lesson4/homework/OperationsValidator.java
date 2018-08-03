package lesson4.homework;

public class OperationsValidator {
    private static StorageDAO storageDAO = new StorageDAO();
    private static FileDAO fileDAO = new FileDAO();

    public void validateFilesTransfer(Storage storageFrom, Storage storageTo) throws Exception {
        checkStorageForExistence(storageFrom);
        checkStorageForExistence(storageTo);

        if (filesContainedSize(storageFrom) > storageTo.getStorageSize() - filesContainedSize(storageTo)) {
            throw new Exception("Not enough space for transfer all files from storage "
                    + storageFrom.getId() + " to storage " + storageTo.getId());
        }

        for (File file : fileDAO.findFilesByStorageId(storageFrom.getId())) {
            checkFormat(storageTo, file);
        }
    }

    public void validateFileTransfer(Storage storageFrom, Storage storageTo, File file) throws Exception {
        checkStorageForExistence(storageFrom);
        checkStorageForExistence(storageTo);
        checkFileForExistence(file);
        checkFileInStorage(storageFrom, file);
        checkFormat(storageTo, file);
        checkFreeSpace(storageTo, file);
    }

    public void validateFileDelete(Storage storage, File file) throws Exception {
        checkStorageForExistence(storage);
        checkFileForExistence(file);

        File deletedFile = fileDAO.findById(file.getId());
        checkFileInStorage(storage, deletedFile);

        if (deletedFile.getId() != storage.getId())
            throw new Exception("Storage " + storage.getId() + " does not contain file " + file.getId());
    }

    public void validateFilePut(Storage storage, File file) throws Exception {
        checkStorageForExistence(storage);
        checkFileForExistence(file);
        checkFormat(storage, file);
        checkFreeSpace(storage, file);
    }

    private void checkFreeSpace(Storage storage, File file) throws Exception {
        if (!isEnoughSpaceForFile(storage, file)) {
            throw new Exception("Not enough space for put file " + file.getId()
                    + " to storage " + storage.getId());
        }
    }

    private void checkFormat(Storage storage, File file) throws Exception {
        if (!storage.isFormatSupported(file.getFormat())) {
            throw new Exception("File " + file.getId() + " was not put to storage "
                    + storage.getId() + ". Format isn't supported.");
        }
    }

    private void checkStorageForExistence(Storage storage) throws NullPointerException {
        if (storage == null || !storage.equals(storageDAO.findById(storage.getId())))
            throw new NullPointerException("Storage was not found");
    }

    private void checkFileForExistence(File file) throws Exception {
        if (file == null || !file.equals(fileDAO.findById(file.getId())))
            throw new NullPointerException("File was not found");
    }

    private boolean isEnoughSpaceForFile(Storage storage, File file) throws Exception {
        return filesContainedSize(storage) + file.getFileSize() <= storage.getStorageSize();
    }

    private long filesContainedSize(Storage storage) throws Exception {
        long filesSize = 0;

        for (File file : fileDAO.findFilesByStorageId(storage.getId())) {
            filesSize += file.getFileSize();
        }

        return filesSize;
    }

    private void checkFileInStorage(Storage storage, File file) throws Exception {
        if (file.getStorage() == null || file.getStorage().getId() != storage.getId())
            throw new Exception("Storage " + storage.getId() + " does not contain file " + file.getId());
    }
}