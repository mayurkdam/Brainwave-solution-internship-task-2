import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.*;
import java.util.*;

@SpringBootApplication
public class HospitalManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(HospitalManagementApplication.class, args);
    }
}

@Entity
class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    
    public Patient() {}
    public Patient(String name) { this.name = name; }
    public Long getId() { return id; }
    public String getName() { return name; }
}

interface PatientRepository extends JpaRepository<Patient, Long> {}

@RestController
@RequestMapping("/patients")
class PatientController {
    @Autowired
    private PatientRepository repository;
    
    @PostMapping("/register")
    public Patient registerPatient(@RequestParam String name) {
        return repository.save(new Patient(name));
    }
    
    @GetMapping("/list")
    public List<Patient> getPatients() {
        return repository.findAll();
    }
}

@Entity
class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String patientName;
    private String date;
    
    public Appointment() {}
    public Appointment(String patientName, String date) {
        this.patientName = patientName;
        this.date = date;
    }
    public Long getId() { return id; }
    public String getPatientName() { return patientName; }
    public String getDate() { return date; }
}

interface AppointmentRepository extends JpaRepository<Appointment, Long> {}

@RestController
@RequestMapping("/appointments")
class AppointmentController {
    @Autowired
    private AppointmentRepository repository;
    
    @PostMapping("/schedule")
    public Appointment scheduleAppointment(@RequestParam String patientName, @RequestParam String date) {
        return repository.save(new Appointment(patientName, date));
    }
    
    @GetMapping("/list")
    public List<Appointment> getAppointments() {
        return repository.findAll();
    }
}

@Entity
class Billing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String patientName;
    private double amount;
    
    public Billing() {}
    public Billing(String patientName, double amount) {
        this.patientName = patientName;
        this.amount = amount;
    }
    public Long getId() { return id; }
    public String getPatientName() { return patientName; }
    public double getAmount() { return amount; }
}

interface BillingRepository extends JpaRepository<Billing, Long> {}

@RestController
@RequestMapping("/billing")
class BillingController {
    @Autowired
    private BillingRepository repository;
    
    @PostMapping("/generate")
    public Billing generateBill(@RequestParam String patientName, @RequestParam double amount) {
        return repository.save(new Billing(patientName, amount));
    }
    
    @GetMapping("/list")
    public List<Billing> getBills() {
        return repository.findAll();
    }
}

@Entity
class MedicalInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itemName;
    private int quantity;
    
    public MedicalInventory() {}
    public MedicalInventory(String itemName, int quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
    }
    public Long getId() { return id; }
    public String getItemName() { return itemName; }
    public int getQuantity() { return quantity; }
}

interface MedicalInventoryRepository extends JpaRepository<MedicalInventory, Long> {}

@RestController
@RequestMapping("/inventory")
class MedicalInventoryController {
    @Autowired
    private MedicalInventoryRepository repository;
    
    @PostMapping("/add")
    public MedicalInventory addInventory(@RequestParam String itemName, @RequestParam int quantity) {
        return repository.save(new MedicalInventory(itemName, quantity));
    }
    
    @GetMapping("/list")
    public List<MedicalInventory> getInventory() {
        return repository.findAll();
    }
}

@Entity
class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String role;
    
    public Staff() {}
    public Staff(String name, String role) {
        this.name = name;
        this.role = role;
    }
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getRole() { return role; }
}

interface StaffRepository extends JpaRepository<Staff, Long> {}

@RestController
@RequestMapping("/staff")
class StaffController {
    @Autowired
    private StaffRepository repository;
    
    @PostMapping("/add")
    public Staff addStaff(@RequestParam String name, @RequestParam String role) {
        return repository.save(new Staff(name, role));
    }
    
    @GetMapping("/list")
    public List<Staff> getStaff() {
        return repository.findAll();
    }
}
