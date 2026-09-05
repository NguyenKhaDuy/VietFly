package org.example.vietfly.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tour_itineraries")
public class TourItinerariesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idTourItineraries;

    @Column(name = "title")
    private String title;

    @Column(name = "description", columnDefinition = "LONGTEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_id")
    private TourEntity tourEntity;

    @OneToMany(mappedBy = "tourItinerariesEntity", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST},orphanRemoval = true)
    private List<TourItinerariesDetailEntity> tourItinerariesDetailEntities = new ArrayList<>();

    @Column(name = "created_at")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime updatedAt;
}
