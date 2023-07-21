package ping.ping_server.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@JsonIgnoreProperties(value = {"password"})
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;
    @Column(columnDefinition = "nvarchar(50)")
    private String name;
    @Column(length = 11)
    private String phoneNumber;
    private String avatar;
    @Column(length = 11)
    private String phoneContact;
    private String password;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    @OneToMany(mappedBy = "customer")
    private List<PlaceSaved> lsPlaceSaved;
    public Customer() {
    }

    public Customer(Long id, String name, String phoneNumber, String avatar, String phoneContact, String password) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.avatar = avatar;
        this.phoneContact = phoneContact;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhoneContact() {
        return phoneContact;
    }

    public void setPhoneContact(String phoneContact) {
        this.phoneContact = phoneContact;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }



    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", avatar='" + avatar + '\'' +
                ", phoneContact='" + phoneContact + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
