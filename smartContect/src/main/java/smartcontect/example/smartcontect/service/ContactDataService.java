package smartcontect.example.smartcontect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import jakarta.validation.Valid;
import smartcontect.example.smartcontect.dav.UserDataRepo;
import smartcontect.example.smartcontect.moled.ContectData;
import smartcontect.example.smartcontect.moled.UserData;
import java.util.Collections;

@Service
public class ContactDataService {

    @Autowired
    private UserDataRepo userDataRepo;

    public String addNewContact(@Valid ContectData contectData, BindingResult result) {
        UserData newUserDataToAddContect = UserDataService.getNewUserDataToAddContect();

        try {
            if (result.hasErrors()) {
                System.out.println("in error");
                System.out.println(result);
                return "toAddContact";
            }
            contectData.setUserData(newUserDataToAddContect);
            newUserDataToAddContect.setContectData(Collections.singletonList(contectData));
            userDataRepo.save(newUserDataToAddContect);
            return "toAddContact";
        } catch (Exception e) {
            System.out.println(e);
            return "toAddContact";
        }
    }
}
