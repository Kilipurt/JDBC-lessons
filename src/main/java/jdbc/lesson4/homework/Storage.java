package jdbc.lesson4.homework;

import java.util.Arrays;

public class Storage {
    private long id;
    private String[] formatsSupported;
    private String storageCountry;
    private long storageSize;

    public Storage(long id, String[] formatsSupported, String storageCountry, long storageSize) throws Exception {
        if (id <= 0)
            throw new Exception("Wrong id " + id);

        this.id = id;
        this.formatsSupported = formatsSupported;
        this.storageCountry = storageCountry;
        this.storageSize = storageSize;
    }

    public long getId() {
        return id;
    }

    public String[] getFormatsSupported() {
        return formatsSupported;
    }

    public String getStorageCountry() {
        return storageCountry;
    }

    public long getStorageSize() {
        return storageSize;
    }

    public String formatsSupportedToString() {
        String formats = Arrays.toString(formatsSupported);
        return formats.substring(1, formats.length() - 2);
    }

    public boolean isFormatSupported(String format) {
        for (String f : formatsSupported) {
            if (f.equals(format))
                return true;
        }

        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Storage storage = (Storage) o;

        if (id != storage.id) return false;
        if (storageSize != storage.storageSize) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(formatsSupported, storage.formatsSupported)) return false;
        return storageCountry != null ? storageCountry.equals(storage.storageCountry) : storage.storageCountry == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + Arrays.hashCode(formatsSupported);
        result = 31 * result + (storageCountry != null ? storageCountry.hashCode() : 0);
        result = 31 * result + (int) (storageSize ^ (storageSize >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", formatsSupported=" + Arrays.toString(formatsSupported) +
                ", storageCountry='" + storageCountry + '\'' +
                ", storageSize=" + storageSize +
                '}';
    }
}
