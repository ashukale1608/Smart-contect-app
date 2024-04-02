package smartcontect.example.smartcontect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Optional;
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
    public String homePage() {
        return "home";
    }

    // this is log in page
    @GetMapping("/logIn")
    public String logInPage(Model model) {
        model.addAttribute("userData", new UserData());
        return "logIn";
    }

    // this ia sign up page
    @GetMapping("/signUp")
    public String signUpPage(Model model) {
        model.addAttribute("userData", new UserData());
        return "signUp";
    }

    // add contect after sign up
    @PostMapping("/userCreated")
    public String createNewUser(Model model, @Valid UserData userData, BindingResult result) {
        try {
            model.addAttribute("contactData", new ContectData());
            return userDataService.createNewUser(userData, result);
        } catch (Exception e) {
            System.out.println(e);
            return "signUp";
        }
    }

    // add contect after login
    @PostMapping("/userLogin")
    public String userLogin(Model model, @Valid UserData userData, BindingResult result) {
        try {
            model.addAttribute("contactData", new ContectData());
            return userDataService.logInUser(userData, result);
        } catch (Exception e) {
            return "logIn";
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAllData() {
        return userDataService.deletedAllData();
    }

    @DeleteMapping("/deleteUserById")
    public ResponseEntity<Optional<UserData>> deleteDataById(@PathVariable("id") int id){
        return userDataService.deletedUserDataById(id);
    }
}
