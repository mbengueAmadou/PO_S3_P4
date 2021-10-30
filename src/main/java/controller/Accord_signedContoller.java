package controller;


import Repository.AccordRepository;
import Repository.Accord_signedRepository;
import message.ResponseMessage;
import model.Accord;
import model.Accord_signed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin(origins = {"*"}, maxAge = 3600)
@RequestMapping("/po/smestre3/accord/*")
public class Accord_signedContoller {
    @Autowired
    Accord_signedRepository accord_signedRepository;

    @PostMapping("/addSigned")

    public ResponseEntity<?> saveAccord(@Valid @RequestBody Accord_signed accord_signed /* Integer id/*, BindingResult br*/) {

        Optional<Accord_signed> p = accord_signedRepository.findById(accord_signed.getId());
        if(!p.isPresent()) {

            accord_signedRepository.save(accord_signed);
            return new ResponseEntity<ResponseMessage>(new ResponseMessage("Accord signed enregistré"), HttpStatus.OK) ;
        } else {
            return new ResponseEntity<ResponseMessage>(new ResponseMessage("Accord signed existe déja"), HttpStatus.NOT_FOUND) ;
        }
    }
    @GetMapping(value = "/allAccord")
    public ResponseEntity<?> getAllAccord() {
        List<Accord_signed> signeds = accord_signedRepository.findAll();
        System.out.println("liste des accords : " + signeds);
        if (signeds==null)
            return new ResponseEntity<ResponseMessage>(new ResponseMessage("liste vide "), HttpStatus.OK);


        return new ResponseEntity<List<Accord_signed>>(signeds, HttpStatus.OK);
    }
}
