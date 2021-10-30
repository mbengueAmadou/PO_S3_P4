package controller;


import Repository.AccordRepository;
import message.ResponseMessage;
import model.Accord;
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
public class AccordContoller {
    @Autowired
    AccordRepository accordRepository;

    @PostMapping("/addAccord")

    public ResponseEntity<?> saveAccord(@Valid @RequestBody Accord accord /* Integer id/*, BindingResult br*/) {

        Optional<Accord> p = accordRepository.findById(accord.getId());
        if(!p.isPresent()) {

            accordRepository.save(accord);
            return new ResponseEntity<ResponseMessage>(new ResponseMessage("accord enregistré"), HttpStatus.OK) ;
        } else {
            return new ResponseEntity<ResponseMessage>(new ResponseMessage("accord existe déja"), HttpStatus.NOT_FOUND) ;
        }
    }
    @GetMapping(value = "/allAccord")
    public ResponseEntity<?> getAllAccord() {
        List<Accord> accords = accordRepository.findAll();
        System.out.println("liste des accords : " + accords);
        if (accords==null)
            return new ResponseEntity<ResponseMessage>(new ResponseMessage("liste vide "), HttpStatus.OK);


        return new ResponseEntity<List<Accord>>(accords, HttpStatus.OK);
    }
    @PutMapping(value = "/update")
    public ResponseEntity<?> updateAccord( @RequestBody Accord accord) {

        Optional<Accord> c = accordRepository.findById(accord.getId());
        if(c.isPresent() ) {
            Accord currentAccord = c.get() ;
            currentAccord.setLibelle(accord.getLibelle());
            currentAccord.setFile(accord.getFile());
            currentAccord.setApproved_by(accord.getApproved_by());
            currentAccord.setApproved_date(accord.getApproved_date());
            currentAccord.setStatus(accord.getStatus());
            currentAccord.setUser_id(accord.getUser_id());

            accordRepository.save(currentAccord) ;
            return new ResponseEntity<ResponseMessage>(new ResponseMessage("Accord  modifiée avec succès"), HttpStatus.OK) ;
        } else {
            return new ResponseEntity<ResponseMessage>(new ResponseMessage("Accord de la modification"), HttpStatus.NOT_FOUND) ;
        }
    }
}
