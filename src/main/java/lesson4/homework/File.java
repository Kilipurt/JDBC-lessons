package lesson4.homework;

public class File {
    private long id;
    private String name;
    private String format;
    private long fileSize;
    private Storage storage;

    public File(long id, String name, String format, long fileSize) throws Exception {
        validateFile(id, name);

        this.id = id;
        this.name = name;
        this.format = format;
        this.fileSize = fileSize;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFormat() {
        return format;
    }

    public long getFileSize() {
        return fileSize;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        File file = (File) o;

        if (id != file.id) return false;
        return name != null ? name.equals(file.name) : file.name == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    private void validateFile(long id, String name) throws Exception {
        if (name.length() > 10)
            throw new Exception("Name " + name + " too much long");

        if (id <= 0)
            throw new Exception("Wrong id " + id);
    }
}
