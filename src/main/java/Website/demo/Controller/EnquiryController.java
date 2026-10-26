package Website.demo.Controller;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Website.demo.Entity.Enquiry;
import Website.demo.Repository.EnquiryRepository;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/enquiries")
@CrossOrigin(origins = {
        "http://127.0.0.1:5500",
        "http://localhost:5500"
    })
public class EnquiryController {


	@Autowired
	private  EnquiryRepository repository;

	// ==========================================
    // CREATE ENQUIRY
    // ==========================================

    @PostMapping
    public Enquiry createEnquiry(
            @Valid @RequestBody Enquiry enquiry) {

        // Automatically set enquiry date
        enquiry.setEnquiryDate(LocalDate.now());

        // Automatically set status
        enquiry.setStatus("New");

        return repository.save(enquiry);
    }
   

 // ==========================================
    // GET PAGINATED ENQUIRIES
    // ==========================================

    @GetMapping
    public Page<Enquiry> getEnquiries(Pageable pageable) {

        return repository.findAll(pageable);
    }


    // ==========================================
    // GET ALL ENQUIRIES
    // ==========================================

    @GetMapping("/all")
    public List<Enquiry> getAllEnquiries() {

        return repository.findAll();
    }

   

        // ==========================================
        // UPDATE STATUS
        // ==========================================

        @PutMapping("/{id}")
        public Enquiry updateEnquiryStatus(
                @PathVariable Long id,
                @RequestBody Enquiry enquiry) {

            Enquiry existingEnquiry =
                    repository.findById(id)
                    .orElseThrow(() ->
                        new RuntimeException(
                            "Enquiry not found with ID: " + id
                        )
                    );

            existingEnquiry.setStatus(
                    enquiry.getStatus()
            );

            return repository.save(existingEnquiry);
        }


        // ==========================================
        // DELETE ENQUIRY
        // ==========================================
        @DeleteMapping("/{id}")
        public String deleteEnquiry(
                @PathVariable Long id) {

            if (!repository.existsById(id)) {

                throw new RuntimeException(
                    "Enquiry not found with ID: " + id
                );
            }

            repository.deleteById(id);

            return "Enquiry deleted successfully";
        }

        // ==========================================
        // VALIDATION ERROR HANDLER
        // ==========================================

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<Map<String, String>>
        handleValidationExceptions(
                MethodArgumentNotValidException ex) {

            Map<String, String> errors =
                    new HashMap<>();

            ex.getBindingResult()
                    .getFieldErrors()
                    .forEach(error ->
                        errors.put(
                            error.getField(),
                            error.getDefaultMessage()
                        )
                    );

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errors);
    }
}
