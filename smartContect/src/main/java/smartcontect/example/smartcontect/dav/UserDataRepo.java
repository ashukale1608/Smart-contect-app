package smartcontect.example.smartcontect.dav;

import org.springframework.data.jpa.repository.JpaRepository;

import smartcontect.example.smartcontect.moled.UserData;

public interface UserDataRepo extends JpaRepository < UserData , Integer >{

    UserData findByName(String name);

    UserData findByEmail(String email);

}
