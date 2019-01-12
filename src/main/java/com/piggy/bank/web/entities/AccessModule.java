package com.piggy.bank.web.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

/**
 * Class that represent application roles
 */
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class AccessModule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "access_module_id")
    private Long id;

    @NonNull
    @NotEmpty
    private String name;

    @OneToMany(mappedBy = "accessModule", cascade = CascadeType.ALL)
    private Set<AccessControl> accessControls = new HashSet<>();
}
