package WebMarket.Market.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="username")
    @NotEmpty
    @Size(min=2, max=30)
    private String username;

    @Column(name="password")
    @NotEmpty
    private String password;

    @Column(name="email")
    @Email
    private String email;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="role")
    private String role;

    public User() {}

    public User(String username, String password, String email, String phoneNumber, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

}
