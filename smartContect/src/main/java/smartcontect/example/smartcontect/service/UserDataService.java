package smartcontect.example.smartcontect.service;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;
import smartcontect.example.smartcontect.dav.UserDataRepo;
import smartcontect.example.smartcontect.moled.ContectData;
import smartcontect.example.smartcontect.moled.UserData;

@Service
public class UserDataService {
    @Autowired
    UserDataRepo userDataRepo;

    UserData newUserDataToAddContect;

    public String createNewUser(@Valid UserData userData, BindingResult result) {

        UserData newUserData = userDataRepo.findByEmail(userData.getEmail());
        if (result.hasErrors()) {
            return "signUp";
        } else if (newUserData == null) {
            userDataRepo.save(userData);
            return "toAddContact";
        } else if (newUserData.getName().equals(userData.getName())
                && newUserData.getEmail().equals(userData.getEmail())
                && newUserData.getPassword().equals(userData.getPassword())) {
            return "toAddContact";
        } else {
            return "signUp";
        }
    }

    public String showAllContect(@Valid UserData newUserDataToAddContect, BindingResult result) {
        UserData newUserData = userDataRepo.findByEmail(newUserDataToAddContect.getEmail());
        if (newUserDataToAddContect.getEmail().isEmpty() || newUserDataToAddContect.getPassword().isEmpty()) {
            return "logIn";
        } else if (newUserData == null) {
            return "signUp";
        } else if (newUserData.getEmail().equals(newUserDataToAddContect.getEmail())
                && newUserData.getPassword().equals(newUserDataToAddContect.getPassword())) {
            this.newUserDataToAddContect = newUserData;
            return "toAddContact";
        } else {
            return "signUp";
        }
    }

    public ResponseEntity<?> deletedAllData() {
        userDataRepo.deleteAll();
        return new ResponseEntity<>("successfully", HttpStatus.OK);
    }

    // public ResponseEntity<String> addData(UserData userData) {
    //     ContectData contectData = new ContectData();
    //     contectData.setUserData(userData);

    //     userData.setContectData(Collections.singletonList(contectData));
    //     System.out.println(userDataRepo.save(userData));
    //     return new ResponseEntity<>("success", HttpStatus.OK);
    // }

    public String addContectData(ContectData contectData, BindingResult result) {
        if(result.hasErrors()){
            System.out.println("in error");
            System.out.println(result);
            return "toAddContact";
        }
        contectData.setUserData(newUserDataToAddContect);
        newUserDataToAddContect.setContectData(Collections.singletonList(contectData));
        // userDataRepo.save(newUserDataToAddContect);
        return "toAddContact";
    }

}
