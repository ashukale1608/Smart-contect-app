package smartcontect.example.smartcontect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.Valid;
import smartcontect.example.smartcontect.moled.ContectData;
import smartcontect.example.smartcontect.service.ContactDataService;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/contact")
public class ContactDataController {

    @Autowired
    ContactDataService contactDataService;

    @GetMapping("/getAddContectPage")
    public String getAddContectPage(){
        return "toAddContact";
    }

    @GetMapping("/showAllContact")
    public String showAllContact() {
        return "showAllContact";
    }
    

    @PostMapping("/addNewContact")
    public String addNewContact(@Valid ContectData contectData , BindingResult result ){
        return contactDataService.addNewContact(contectData,result);
    }
}
