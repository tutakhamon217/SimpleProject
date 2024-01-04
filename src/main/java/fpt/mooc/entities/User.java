package fpt.mooc.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id", columnDefinition = "INT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_name", columnDefinition = "VARCHAR(40)", unique = true)
    private String userName;

    @Column(name = "pass_word", columnDefinition = "VARCHAR(250)")
    private String passWord;

    @Column(name = "full_name", columnDefinition = "VARCHAR(40)")
    private String fullName;

    @Column(name = "address", columnDefinition = "VARCHAR(40)")
    private String address;

    @Column(name = "phone", columnDefinition = "VARCHAR(20)")
    private String phone;

    @Column(name = "sex")
    private Integer sex;

    @Column(name = "birth_day")
    private LocalDate birthDay;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "User_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_code", referencedColumnName = "code")}
    )
    private Set<Authority> authorities = new HashSet<>();

    public User(String userName, String passWord, String fullName, String address, String phone, Integer sex, LocalDate birthDay, Set<Authority> authorities) {
        this.userName = userName;
        this.passWord = passWord;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.sex = sex;
        this.birthDay = birthDay;
        this.authorities = authorities;
    }
}
