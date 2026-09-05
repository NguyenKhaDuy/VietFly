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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tour_image")
public class TourImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idImage;

    @Column(name = "imgae_url")
    private String imgaeUrl;

    @Column(name = "is_thumbnail")
    private boolean isThumbnail;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tour")
    private TourEntity tourEntity;

    @Column(name = "created_at")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime updatedAt;
}
