package dev.jason.roomreservation.domains;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Room implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false, columnDefinition = "integer default 0")
    private Integer capacity;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Field field;
}
