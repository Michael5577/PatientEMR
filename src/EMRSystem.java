import java.util.List;
import java.util.Scanner;

public class EMRSystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static final PatientManager patientManager = new PatientManager();

    public static void main(String[] args) {
        initializePatients();

        while (true) {
            printMenu();
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1 -> searchPatient();
                case 2 -> addPatient();
                case 3 -> removePatient();
                case 4 -> editServiceLine();
                case 5 -> addPhysician();
                case 6 -> {
                    System.out.println("Exiting the system. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void initializePatients() {
        patientManager.addPatient(new Patient("John Smith", "1234", "Radiology"));
        patientManager.addPatient(new Patient("Joe Stephens", "3453", "Cardiology"));
        patientManager.addPatient(new Patient("Michael Jones", "2334", "Radiation Oncology"));
        patientManager.addPatient(new Patient("Mike Simpson", "4322", "Lab"));
        patientManager.addPatient(new Patient("Marge Bennett", "3442", "Pharmacy"));
    }

    private static void printMenu() {
        System.out.println("\n--- Health Care EMR System ---");
        System.out.println("1. Search Patient");
        System.out.println("2. Add Patient");
        System.out.println("3. Remove Patient");
        System.out.println("4. Edit Service Line");
        System.out.println("5. Add Physician");
        System.out.println("6. Exit");
    }

    private static void searchPatient() {
        System.out.println("\n--- Search Patient ---");
        System.out.println("1. Search by Patient ID");
        System.out.println("2. Search by Patient Name");
        System.out.println("3. Search by Service Line");
        int choice = getIntInput("Enter your choice: ");

        switch (choice) {
            case 1 -> {
                String id = getStringInput("Enter Patient ID: ");
                Patient patient = patientManager.getPatientById(id);
                if (patient != null) {
                    System.out.println("Patient found: " + patient);
                } else {
                    System.out.println("No patient found with that ID.");
                }
            }
            case 2 -> {
                String name = getStringInput("Enter Patient Name: ");
                List<Patient> patients = patientManager.getPatientsByName(name);
                printPatients(patients);
            }
            case 3 -> {
                String serviceLine = getStringInput("Enter Service Line: ");
                List<Patient> patients = patientManager.getPatientsByServiceLine(serviceLine);
                printPatients(patients);
            }
            default -> System.out.println("Invalid choice.");
        }
    }

    private static void addPatient() {
        String name = getStringInput("Enter Patient Name: ");
        String id = getStringInput("Enter Patient ID: ");
        String serviceLine = getStringInput("Enter Service Line: ");

        Patient newPatient = new Patient(name, id, serviceLine);
        patientManager.addPatient(newPatient);
        System.out.println("Patient added successfully.");
    }

    private static void removePatient() {
        String id = getStringInput("Enter Patient ID to remove: ");
        patientManager.removePatient(id);
        System.out.println("Patient removed (if existed).");
    }

    private static void editServiceLine() {
        String id = getStringInput("Enter Patient ID: ");
        String newServiceLine = getStringInput("Enter new Service Line: ");
        patientManager.updateServiceLine(id, newServiceLine);
        System.out.println("Service Line updated (if patient existed).");
    }

    private static void addPhysician() {
        String id = getStringInput("Enter Patient ID: ");
        String physician = getStringInput("Enter Physician Name: ");
        patientManager.addPhysician(id, physician);
        System.out.println("Physician added to patient (if patient existed).");
    }

    private static void printPatients(List<Patient> patients) {
        if (patients.isEmpty()) {
            System.out.println("No patients found.");
        } else {
            for (Patient patient : patients) {
                System.out.println(patient);
            }
        }
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}