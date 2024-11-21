import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class PatientManager {
    private HashMap<String, Patient> patientsByID;
    private HashMap<String, LinkedList<Patient>> patientsByServiceLine;

    public PatientManager() {
        patientsByID = new HashMap<>();
        patientsByServiceLine = new HashMap<>();
    }

    public void addPatient(Patient patient) {
        patientsByID.put(patient.getId(), patient);
        patientsByServiceLine
                .computeIfAbsent(patient.getServiceLine(), k -> new LinkedList<>())
                .add(patient);
    }

    public void removePatient(String patientId) {
        Patient patient = patientsByID.remove(patientId);
        if (patient != null) {
            patientsByServiceLine.get(patient.getServiceLine()).remove(patient);
        }
    }

    public Patient getPatientById(String patientId) {
        return patientsByID.get(patientId);
    }

    public List<Patient> getPatientsByName(String name) {
        return patientsByID.values().stream()
                .filter(p -> p.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Patient> getPatientsByServiceLine(String serviceLine) {
        return patientsByServiceLine.getOrDefault(serviceLine, new LinkedList<>());
    }

    public void updateServiceLine(String patientId, String newServiceLine) {
        Patient patient = patientsByID.get(patientId);
        if (patient != null) {
            patientsByServiceLine.get(patient.getServiceLine()).remove(patient);
            patient.setServiceLine(newServiceLine);
            patientsByServiceLine
                    .computeIfAbsent(newServiceLine, k -> new LinkedList<>())
                    .add(patient);
        }
    }

    public void addPhysician(String patientId, String physician) {
        Patient patient = patientsByID.get(patientId);
        if (patient != null) {
            patient.addPhysician(physician);
        }
    }
}