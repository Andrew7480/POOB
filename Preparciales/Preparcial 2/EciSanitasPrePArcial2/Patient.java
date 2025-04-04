import java.time.LocalDate;
import java.util.ArrayList;

public class Patient {
    private String idCard;
    private String name;
    private String address;
    private LocalDate birthDate;
    private LocalDate registrationDate;
    private ArrayList<Appointment> appointments;
    private MedicalHistory medicalHistory;
    
}
