package org.example.vietfly.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tour_price_inclusion")
public class TourPriceInclusionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idTourPriceInclusion;

    @Column(name = "nameService")
    private String nameService;

    @Column(name = "description", columnDefinition = "LONGTEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    private TypeTourPrice typeTourPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_id")
    private TourEntity tourEntity;

    @Column(name = "created_at")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime updatedAt;
}
