import java.util.LinkedList;

public class Patient extends Person {
    private String serviceLine;
    private LinkedList<String> physicians;

    public Patient(String name, String id, String serviceLine) {
        super(name, id);
        this.serviceLine = serviceLine;
        this.physicians = new LinkedList<>();
    }

    // Getters and setters
    public String getServiceLine() { return serviceLine; }
    public void setServiceLine(String serviceLine) { this.serviceLine = serviceLine; }
    public LinkedList<String> getPhysicians() { return physicians; }

    public void addPhysician(String physician) {
        physicians.add(physician);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", serviceLine='" + serviceLine + '\'' +
                ", physicians=" + physicians +
                '}';
    }
}