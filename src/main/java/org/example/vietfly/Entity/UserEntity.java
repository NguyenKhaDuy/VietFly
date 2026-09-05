package org.example.vietfly.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jspecify.annotations.Nullable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idUser;

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    @OneToMany(mappedBy = "userEntity", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<BookingEntity> bookingEntities = new ArrayList<>();

    @OneToMany(mappedBy = "userEntity", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<BannerEntity> bannerEntities = new ArrayList<>();

    @OneToMany(mappedBy = "userEntity", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<FeedbackEntity> feedbackEntities = new ArrayList<>();

    @OneToMany(mappedBy = "userEntity", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<TourEntity> tourEntities = new ArrayList<>();

    @Column(name = "created_at")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime updatedAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public @Nullable String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }
}
