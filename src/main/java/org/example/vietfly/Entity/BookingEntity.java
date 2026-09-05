package org.example.vietfly.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "booking")
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idBooking;

    @Column(name = "fullnane")
    private String fullnane;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "destination")
    private String destination;

    @Column(name = "time_depart")
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime timeDepart;

    @Column(name = "date_depart")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateDepart;

    @Column(name = "number_people")
    private Long numberPeople;

    @Column(name = "description", columnDefinition = "LONGTEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    private StatusTour statusTour;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @Column(name = "created_at")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime updatedAt;
}
