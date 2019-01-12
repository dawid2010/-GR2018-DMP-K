package com.piggy.bank.web.entities;

import lombok.*;

import javax.persistence.*;

/**
 * Class that represent connection between Access Modules and People (indirect table)
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class AccessControl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    @JoinColumn(name = "person_id")
    private Person person;

    @NonNull
    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    @JoinColumn(name = "access_module_id")
    private AccessModule accessModule;
}
