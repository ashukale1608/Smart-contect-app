package smartcontect.smartcontect.moled;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class UserData {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank(message = "not empty")
    @Size(min = 2 , max = 15)
    private String name;
    @NotBlank(message = "not empty")
    @Email
    private String email;
    @NotBlank(message = "not empty")
    @Size(min = 4 , max = 8)
    private String password;

    @OneToMany(mappedBy = "userData", cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
    private List<ContectData> contectData;  

    @Override
    public String toString() {
        return "UserData [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password
                + ", contectData=" + contectData + "]";
    }

    public List<ContectData> getContectData() {
        return contectData;
    }

    public void setContectData(List<ContectData> contectData) {
        this.contectData = contectData;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
