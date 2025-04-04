import java.time.LocalDate;
import java.util.ArrayList;

public class Appointment implements Schedulable{
    private String id;
    private LocalDate fecha;
    private int time;
    private String reason;
    private Doctor doctor;
    private Office office;
    private ArrayList<Treatment> patientTreatments;
    private ArrayList<PatientIllness> patientIllnesses;
}
