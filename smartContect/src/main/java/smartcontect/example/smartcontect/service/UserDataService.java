package smartcontect.example.smartcontect.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;
import smartcontect.example.smartcontect.dav.UserDataRepo;
import smartcontect.example.smartcontect.moled.UserData;

@Service
public class UserDataService {
    @Autowired
    UserDataRepo userDataRepo;

    UserData newUserDataToAddContect;

    public String createNewUser(@Valid UserData userData, BindingResult result) {
        UserData newUserDataByEmail = userDataRepo.findByEmail(userData.getEmail());
        if (newUserDataByEmail == null) {
            userDataRepo.save(userData);
            this.newUserDataToAddContect = userData;
            return "showAllContact";
        } else {
            String loginUserPassword = newUserDataByEmail.getPassword();
            if (result.hasErrors()) {
                return "signUp";
            } else if (loginUserPassword != userData.getPassword()) {
                userDataRepo.save(userData);
                this.newUserDataToAddContect = userData;
                return "showAllContact";
            } else if (newUserDataByEmail.getName().equals(userData.getName())
                    && newUserDataByEmail.getEmail().equals(userData.getEmail())
                    && newUserDataByEmail.getPassword().equals(userData.getPassword())) {
                this.newUserDataToAddContect = newUserDataByEmail ;
                return "showAllContact";
            } else {
                return "signUp";
            }
        }
    }

    public String logInUser(@Valid UserData userData, BindingResult result) {
        UserData newUserDataByEmail = userDataRepo.findByEmail(userData.getEmail());
       
        if (userData.getPassword().isEmpty() || userData.getEmail().isEmpty()){
            return "logIn";
        }else if (newUserDataByEmail == null) {
            return "signUp";
        } 
        if (result.hasErrors()) {
            if (userData.getName() == null && userData.getEmail() != null && userData.getPassword() != null) {                
                if (newUserDataByEmail.getEmail().equals(userData.getEmail())
                        && newUserDataByEmail.getPassword().equals(userData.getPassword())) {
                    this.newUserDataToAddContect = newUserDataByEmail ;
                    return "showAllContact";
                } 
            }
        } 
        return "logIn";      
    }

    public ResponseEntity<?> deletedAllData() {
        userDataRepo.deleteAll();
        return new ResponseEntity<>("successfully", HttpStatus.OK);
    }

    public ResponseEntity<Optional<UserData>> deletedUserDataById(int id) {
        Optional<UserData> userDataToDeleted = userDataRepo.findById(id);
        userDataRepo.deleteById(id);
        return new ResponseEntity<>(userDataToDeleted , HttpStatus.OK);
    }
}
