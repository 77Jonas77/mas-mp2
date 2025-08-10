import java.io.*;
import java.util.*;

    public class ObjectExtension implements Serializable {
    private static Map<Class, List> extent = new HashMap<>();
    public static final String FILE_NAME = "extent.niechceitn";

    public ObjectExtension() {
        addToExtent();
    }

    protected void addToExtent() {
        List list = extent.computeIfAbsent(this.getClass(), _ -> new ArrayList<>());
        list.add(this);
    }

    protected void removeFromExtent() {
        List list = extent.get(this.getClass());
        if(list != null) {
            list.remove(this);
        }
    }

    public static void saveExtent() throws IOException {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))){
            oos.writeObject(extent);
//            oos.writeObject(LabTechnician.getLabName());
        }
    }

    public static void loadExtent() throws IOException, ClassNotFoundException {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))){
            extent = (Map<Class, List>) ois.readObject();
//            LabTechnician.setLabName((String) ois.readObject());
        }
    }

    public static <T> List<T> getExtentFromClass(Class<T> c) {
        extent.computeIfAbsent(c, _ -> new ArrayList<>());
        return Collections.unmodifiableList(extent.get(c));
    }

}
