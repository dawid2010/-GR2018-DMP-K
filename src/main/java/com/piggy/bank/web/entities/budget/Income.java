package com.piggy.bank.web.entities.budget;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Income implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @NotNull
    private String name;

    private Double value;

    @Enumerated(EnumType.STRING)
    @NonNull
    private Type type;


    public enum Type {
        BRONZE("Praca"),
        SILVER("Zasiłek"),
        GOLD("Lokata");

        Type(String displayName) {
            this.displayName = displayName;
        }

        private String displayName;

        public String getDisplayName() {
            return displayName;
        }
    }

    @Override
    public String toString() {
        return "Income{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value=" + value +
                ", type=" + type +
                '}';
    }
}
