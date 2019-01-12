package com.piggy.bank.web.entities.budget;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Class that represents budget schema for usable trainings
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "name"
        })
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Outcome{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @NotNull
    @Size(min = 3, max = 200, message = "{error.budget.adding.name.Size}")
    private String name;

    private Double value;

    @Enumerated(EnumType.STRING)
    @NonNull
    private Type type;

    public enum Type {
        BRONZE("Żywność"),
        SILVER("Transport"),
        GOLD("Chemia");

        Type(String displayName) {
            this.displayName = displayName;
        }

        private String displayName;

        public String getDisplayName() {
            return displayName;
        }
    }

    public Outcome(@Size(min = 3, max = 30, message = "{error.budget.adding.name.Size}") String name,
                   Double value, Type type) {
        this.name = name;
        this.value = value;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Outcome{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value=" + value +
                ", type=" + type +
                '}';
    }
}
