package controller;


import Repository.UserRepository;
import message.ResponseMessage;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin(origins = {"*"}, maxAge = 3600)
@RequestMapping("/po/smestre3/user/*")
public class UserContoller {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/addUser")

    public ResponseEntity<?> saveUser(@Valid @RequestBody User user /* Integer id/*, BindingResult br*/) {

        Optional<User> p = userRepository.findById(user.getId());
        if(!p.isPresent()) {

            userRepository.save(user);
            return new ResponseEntity<ResponseMessage>(new ResponseMessage("User enregistré"), HttpStatus.OK) ;
        } else {
            return new ResponseEntity<ResponseMessage>(new ResponseMessage("User existe déja"), HttpStatus.NOT_FOUND) ;
        }
    }
    @GetMapping(value = "/allUser")
    public ResponseEntity<?> getAllUser() {
        List<User> users = userRepository.findAll();
        System.out.println("liste des User : " + users);
        if (users==null)
            return new ResponseEntity<ResponseMessage>(new ResponseMessage("liste vide "), HttpStatus.OK);


        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
    @PutMapping(value = "/update")
    public ResponseEntity<?> updateUser( @RequestBody User user) {

        Optional<User> c = userRepository.findById(user.getId());
        if(c.isPresent() ) {
            User currentUser = c.get() ;
            currentUser.setNom(user.getNom());
            currentUser.setPrenom(user.getPrenom());
            currentUser.setAdresse(user.getAdresse());
            currentUser.setArcheved(user.getArcheved());
            currentUser.setPassword(user.getPassword());
            currentUser.setProfil(user.getProfil());
            currentUser.setTelephone(user.getTelephone());
            currentUser.setCreated_date(user.getCreated_date());
            currentUser.setLast_modified_date(user.getLast_modified_date());

            userRepository.save(currentUser) ;
            return new ResponseEntity<ResponseMessage>(new ResponseMessage("User  modifiée avec succès"), HttpStatus.OK) ;
        } else {
            return new ResponseEntity<ResponseMessage>(new ResponseMessage("User de la modification"), HttpStatus.NOT_FOUND) ;
        }
    }
}
