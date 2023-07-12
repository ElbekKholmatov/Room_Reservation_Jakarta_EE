package dev.jason.roomreservation.domains;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Reservation implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "seat_number")
    private Integer seatNumber;
    @Column(nullable = false, name = "reservation_date")
    private LocalDate reservationDate;
    @JoinColumn(nullable = false)
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private User user;
    @JoinColumn(nullable = false)
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Room room;
    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "timestamp default current_timestamp", name = "created_at")
    private LocalDateTime createdAt;
    @Column(nullable = false, columnDefinition = "boolean default false", name = "is_expired")
    private boolean isExpired;

}


/*
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"seat_number", "user_id", "room_id", "reservation_date"})})
public class Reservation implements BaseEntity {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
@Column(nullable = false, name = "seat_number")
private Integer seatNumber;
@Column(nullable = false, name = "reservation_date")
private LocalDate reservationDate;
@JoinColumn(nullable = false)
@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
private User user;
@JoinColumn(nullable = false)
@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
private Room room;
@CreationTimestamp
@Column(nullable = false, columnDefinition = "timestamp default current_timestamp", name = "created_at")
private LocalDateTime createdAt;
@Column(nullable = false, columnDefinition = "boolean default false", name = "is_expired")
private boolean isExpired;

}
*/
