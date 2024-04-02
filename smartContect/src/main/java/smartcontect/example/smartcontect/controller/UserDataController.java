package smartcontect.example.smartcontect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import jakarta.validation.Valid;
import smartcontect.example.smartcontect.moled.ContectData;
import smartcontect.example.smartcontect.moled.UserData;
import smartcontect.example.smartcontect.service.UserDataService;

@Controller
@RequestMapping("/smartContect")
public class UserDataController {

    @Autowired
    UserDataService userDataService;
   
    // go to home page this is first page
    @GetMapping("/")
    public String homePage(){
        return "home";
    }

    // this is log in page 
    @GetMapping("/logIn")
    public String logInPage(Model model){
        model.addAttribute("userData", new UserData());
        return "logIn";
    }

    // this ia sign up page
    @GetMapping("/signUp")
    public String signUpPage( Model model){
        model.addAttribute("userData",new UserData());
        return "signUp";
    }


    // add contect after login and sign up
    @PostMapping("/contact")
    public String addContact(Model model ,@Valid UserData userData , BindingResult result){
        // model.addAttribute("contactData", new ContectData()); 
        // return userDataService.createNewUser(userData,result);  
        return userDataService.contactHome(userData,result);
    }
    

    @GetMapping("/allContectHome")
    public String allContect(Model model , @Valid UserData newUserDataToAddContect , BindingResult result){
        model.addAttribute("contactData", new ContectData()); 
        System.out.println("in allContectHome");
        return userDataService.showAllContect(newUserDataToAddContect,result);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAllData(){
        return userDataService.deletedAllData();
    }  

    @RequestMapping("/addNewContect")
    public String addNewContect(@Valid ContectData contectData , BindingResult result){
        return userDataService.addContectData(contectData , result);
    }


    
    // @PostMapping("/jast")
    // public ResponseEntity<String> jast(@RequestBody UserData userData){
    //     return userDataService.addData(userData);
    // }  

    public int jastForTest(int num1 , int num2){
        return num1+num2;
    }
    
    @GetMapping("/toUpdateContect")
    public String toUpdateContect(){
        return "toUpdateContect";
    }
}

