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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tour")
public class TourEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idTour;

    @Column(name = "name_tour")
    private String nameTour;

    @Column(name = "description", columnDefinition = "LONGTEXT")
    private String description;

    @Column(name = "time")
    private String time;

    @Column(name = "time_depart")
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime timeDepart;

    @Column(name = "date_depart")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateDepart;

    @Column(name = "max_people")
    private Long maxPeople;

    @Column(name = "price_adult")
    private Float priceAdult;

    @Column(name = "price_children")
    private Float priceChildren;

    @Column(name = "contact_phone")
    private String contactPhone;

    @Column(name = "contact_email")
    private String contactEmail;

    @Enumerated(EnumType.STRING)
    private Weather weather;

    @Enumerated(EnumType.STRING)
    private StatusTour statusTour;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @OneToMany(mappedBy = "tourEntity", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST},orphanRemoval = true)
    private List<TourImageEntity> tourImageEntities = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_category")
    private CategoryEntity categoryEntity;

    @OneToMany(mappedBy = "tourEntity", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST},orphanRemoval = true)
    private List<TourItinerariesEntity> tourItinerariesEntities = new ArrayList<>();

    @OneToMany(mappedBy = "tourEntity", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST},orphanRemoval = true)
    private List<TourPriceInclusionEntity> tourPriceInclusionEntities = new ArrayList<>();

    @Column(name = "created_at")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime updatedAt;
}
