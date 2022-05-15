package com.example.demo.api;

import com.example.demo.services.FilesStorageService;
import com.example.demo.entities.Personnel;
import com.example.demo.entities.Role;
import com.example.demo.repositories.PersonnelRepository;
import com.example.demo.repositories.RoleRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(path = "/api")
public class PersonnelController {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PersonnelRepository personnelRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder ;


    @PostMapping("/record")
    public void signUp(@RequestBody Personnel user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        personnelRepository.save(user);
    }
    @GetMapping("/users/emailPassword")
    public Personnel getPersonnelByEmailPassword(HttpServletRequest request) throws HttpClientErrorException.BadRequest {
        System.out.println("called!!");
        Personnel emailPassword = new Personnel();
        emailPassword.setPassword(request.getParameter("password"));
        emailPassword.setEmail(request.getParameter("email"));
        try {
            Personnel personnel = personnelRepository.findByEmailEquals(emailPassword.getEmail());

            if(personnel != null){
                String password = personnel.getPassword();
                if(bCryptPasswordEncoder.matches(emailPassword.getPassword(),password)){
                    personnel.setPassword(null);
                    return personnel;
                }
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    };
    @GetMapping("/test")
    public String printText(HttpServletRequest request){
      return request.getParameter("email");
    };

    @Autowired
    FilesStorageService storageService;
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            storageService.save(file);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/users", headers = "Content-Type= multipart/form-data")
    public ResponseEntity<String> ajouterPersonnel(@RequestBody PersonnelCostom personnel,@RequestPart("file") MultipartFile file) throws IOException {

        System.out.println(file.toString());

        if(personnelRepository.findByEmailEquals(personnel.getEmail()) == null){
            List<Role> roles = new ArrayList<Role>();

            personnel.getRoles().forEach(role->{
                roles.add(roleRepository.findByRoleEquals(role));
            });
            Personnel personnel1 = new Personnel();
            personnel1.setNom(personnel.getNom());
            personnel1.setPrenom(personnel.getPrenom());
            personnel1.setEmail(personnel.getEmail());
            personnel1.setPassword(bCryptPasswordEncoder.encode(personnel.getPassword()));
            personnel1.setFonction(personnel.getFonction());
            personnel1.setRoles(roles);
            personnelRepository.save(personnel1);
            return ResponseEntity.ok().body("l'utilisateur "+personnel.getNom()+ " "+ personnel.getPrenom()+" a été crée!");
        }

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("un compet avec cet email est déjà crée!");
    };
    @Data
    static class PersonnelCostom{
        private  String nom;
        private  String prenom;
        private  String email;
        private  String password;
        private  String fonction;
        private   List<String> roles= new ArrayList<String>();
        public PersonnelCostom(){
        }
    }

    //@PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<List<PersonnelCS>> listerPersonnel(){
        List<PersonnelCS> personnels = new ArrayList<PersonnelCS>();
        personnelRepository.findAll().forEach(personnel -> {
            PersonnelCS personnelCS = new PersonnelCS();
            personnelCS.setEmail(personnel.getEmail());
            personnelCS.setFonction(personnel.getFonction());
            personnelCS.setNomPrenom(personnel.getNom()+ " "+ personnel.getPrenom());
            personnelCS.setId(personnel.getId());
            personnelCS.setRoles(personnel.getRoles());
            personnelCS.setImage(personnel.getImage());
            personnels.add(personnelCS);
        });
        return ResponseEntity.ok().body(personnels);
    }

    @Data
    @NoArgsConstructor
    static class PersonnelCS{
        private String nomPrenom;
        private String email;
        private long id;
        private List<Role> roles;
        private String fonction;
        private String image;
        private Object file;
    }
}