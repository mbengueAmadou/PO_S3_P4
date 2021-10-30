package controller;


import Repository.PartnerRepository;
import message.ResponseMessage;
import model.Partner;
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
@RequestMapping("/po/smestre3/partner/*")
public class PartnerContoller {
    @Autowired
    PartnerRepository partnerRepository;

    @PostMapping("/addPartner")

    public ResponseEntity<?> savePartner(@Valid @RequestBody Partner partner /* Integer id/*, BindingResult br*/) {

        Optional<Partner> p = partnerRepository.findById(partner.getId());
        if(!p.isPresent()) {

            partnerRepository.save(partner);
            return new ResponseEntity<ResponseMessage>(new ResponseMessage("partner enregistré"), HttpStatus.OK) ;
        } else {
            return new ResponseEntity<ResponseMessage>(new ResponseMessage("partner existe déja"), HttpStatus.NOT_FOUND) ;
        }
    }
    @GetMapping(value = "/allPartner")
    public ResponseEntity<?> getAllPartner() {
        List<Partner> partners = partnerRepository.findAll();
        System.out.println("liste des partners : " + partners);
        if (partners==null)
            return new ResponseEntity<ResponseMessage>(new ResponseMessage("liste vide "), HttpStatus.OK);


        return new ResponseEntity<List<Partner>>(partners, HttpStatus.OK);
    }
    @PutMapping(value = "/update")
    public ResponseEntity<?> updatePartner( @RequestBody Partner partner) {

        Optional<Partner> c = partnerRepository.findById(partner.getId());
        if(c.isPresent() ) {
            Partner currentPartner = c.get() ;
            currentPartner.setArcheve(partner.getArcheve());
            currentPartner.setCreated_date(partner.getCreated_date());
            currentPartner.setLast_modified_by(partner.getLast_modified_by());
            currentPartner.setNom_complete(partner.getNom_complete());
            currentPartner.setStatus(partner.getStatus());
            currentPartner.setType(partner.getType());

            partnerRepository.save(currentPartner) ;
            return new ResponseEntity<ResponseMessage>(new ResponseMessage("Partner  modifiée avec succès"), HttpStatus.OK) ;
        } else {
            return new ResponseEntity<ResponseMessage>(new ResponseMessage("Partner de la modification"), HttpStatus.NOT_FOUND) ;
        }
    }
}
