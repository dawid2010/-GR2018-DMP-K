package com.piggy.bank.web.entities;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

/**
 * Class that represent singular application's person
 */
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "login"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Long id;

    private String firstName;

    private String lastName;

    @NonNull
    @NotNull
    @Size(min = 7, max = 7, message = "{error.person.login.syntax}")
    private String login;

    private String email;

    private String password;

    @Transient
    private String matchPassword;

    @Transient
    private List<Long> selectedModules = new ArrayList<>();

    @Transient
    private boolean checkBoxChangePassword;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date accountCreationDate;

    private Boolean active;

    private String token;


    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private Set<AccessControl> accessControls = new HashSet<>();

    public Person(@Size(min = 2, max = 30,
            message = "{error.person.firstName}") String firstName,
                  @Size(min = 2, max = 50,
                          message = "{error.person.lastName}") String lastName,
                  @NotNull @Size(min = 7, max = 7,
                          message = "{error.person.login.syntax}") String login,
                  String email,
                  @Size(min = 8, message = "{error.person.password.syntax}") String password,
                  Boolean active) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.email = email;
        this.password = password;
        this.active = active;
    }

    public Person(Person person) {
        this.id = person.getId();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.login = person.getLogin();
        this.email = person.getEmail();
        this.password = person.getPassword();
        this.active = person.getActive();
        this.accountCreationDate = person.getAccountCreationDate();
        this.token = person.getToken();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (id != null ? !id.equals(person.id) : person.id != null) return false;
        if (firstName != null ? !firstName.equals(person.firstName) : person.firstName != null) return false;
        if (lastName != null ? !lastName.equals(person.lastName) : person.lastName != null) return false;
        if (!login.equals(person.login)) return false;
        if (!email.equals(person.email)) return false;
        if (password != null ? !password.equals(person.password) : person.password != null) return false;
        if (accountCreationDate != null ?
                !accountCreationDate.equals(person.accountCreationDate) : person.accountCreationDate != null)
            return false;
        if (active != null ? !active.equals(person.active) : person.active != null) return false;
        return token != null ? token.equals(person.token) : person.token == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + login.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (accountCreationDate != null ? accountCreationDate.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        result = 31 * result + (token != null ? token.hashCode() : 0);
        return result;
    }
}