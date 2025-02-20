//////package com.htps.entities;
//////
//////import java.util.ArrayList;
//////import java.util.List;
//////
//////import jakarta.persistence.CascadeType;
//////import jakarta.persistence.Column;
//////import jakarta.persistence.Entity;
//////import jakarta.persistence.EnumType;
//////import jakarta.persistence.Enumerated;
//////import jakarta.persistence.FetchType;
//////import jakarta.persistence.GeneratedValue;
//////import jakarta.persistence.GenerationType;
//////import jakarta.persistence.Id;
//////import jakarta.persistence.JoinColumn;
//////import jakarta.persistence.ManyToOne;
//////import jakarta.persistence.OneToMany;
//////import jakarta.persistence.Table;
//////import lombok.Getter;
//////import lombok.NoArgsConstructor;
//////import lombok.Setter;
//////import lombok.ToString;
//////
//////@Entity
//////@Table(name = "users")
//////@NoArgsConstructor
//////@Getter
//////@Setter
//////@ToString(callSuper = true, exclude = {"password", "orders"})
//////public class User {
//////
//////    @Id
//////    @GeneratedValue(strategy = GenerationType.IDENTITY)
//////    private Long userId;  // Primary key
//////
//////    @Column(length = 20, nullable = false)
//////    private String firstName;
//////
//////    @Column(length = 20, nullable = false)
//////    private String lastName;
//////
//////    @Column(length = 25, unique = true, nullable = false)
//////    private String email;
//////
//////    @Column(length = 500, nullable = false)
//////    private String password;
//////
//////    @Column(nullable = false)
//////    private double regAmount;
//////
//////    @Enumerated(EnumType.STRING)
//////    @Column(nullable = false)
//////    private UserRole role;
//////
//////    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
//////    private List<Orders> orders; // Consider initializing this list if needed
//////
//////    @ManyToOne
//////    @JoinColumn(name = "trainerId", referencedColumnName = "trainerId")  // Ensure it references the 'id' of the Trainer entity
//////    private Trainer trainer;
//////
//////    public User(String firstName, String lastName, String email, double regAmount, UserRole role,
//////                List<Orders> orders, Trainer trainer) {
//////        this.firstName = firstName;
//////        this.lastName = lastName;
//////        this.email = email;
//////        this.regAmount = regAmount;
//////        this.role = role;
//////        this.orders = orders != null ? orders : new ArrayList<>(); // Initialize to avoid NullPointerException
//////        this.trainer = trainer;
//////    }
//////}
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////////////////////////////////////////////////////////
////package com.htps.entities;
////
////import java.util.ArrayList;
////import java.util.List;
////
////import jakarta.persistence.CascadeType;
////import jakarta.persistence.Column;
////import jakarta.persistence.Entity;
////import jakarta.persistence.EnumType;
////import jakarta.persistence.Enumerated;
////import jakarta.persistence.FetchType;
////import jakarta.persistence.GeneratedValue;
////import jakarta.persistence.GenerationType;
////import jakarta.persistence.Id;
////import jakarta.persistence.JoinColumn;
////import jakarta.persistence.ManyToOne;
////import jakarta.persistence.OneToMany;
////import jakarta.persistence.Table;
////import lombok.Getter;
////import lombok.NoArgsConstructor;
////import lombok.Setter;
////import lombok.ToString;
////
////@Entity
////@Table(name = "users")
////@NoArgsConstructor
////@Getter
////@Setter
////@ToString(callSuper = true, exclude = {"password", "orders"})
////public class User {
////
////    @Id
////    @GeneratedValue(strategy = GenerationType.IDENTITY)
////    private Long userId; 
////
////    @Column(length = 20, nullable = false)
////    private String firstName;
////
////    @Column(length = 20, nullable = false)
////    private String lastName;
////
////    @Column(length = 25, unique = true, nullable = false)
////    private String email;  
////
////    @Column(length = 500, nullable = false)
////    private String password;
////
////    @Column(nullable = false)
////    private double regAmount;
////
////    @Enumerated(EnumType.STRING)
////    @Column(nullable = false)
////    private UserRole role;
////
////    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
////    private List<Orders> orders = new ArrayList<>();
////
////    @ManyToOne
////    @JoinColumn(name = "trainer_id", nullable = false)
////    private Trainer trainer;
////
////    public User(String firstName, String lastName, String email, double regAmount, UserRole role,
////                List<Orders> orders, Trainer trainer) {
////        this.firstName = firstName;
////        this.lastName = lastName;
////        this.email = email;
////        this.regAmount = regAmount;
////        this.role = role;
////        this.orders = orders != null ? orders : new ArrayList<>();
////        this.trainer = trainer;
////    }
////
////  
////    public String getFullName() {
////        return firstName + " " + lastName;
////    }
////}
//
//
//
//
//
//package com.htps.entities;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.EnumType;
//import jakarta.persistence.Enumerated;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.Table;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;
//
//@Entity
//@Table(name = "users")
//@NoArgsConstructor
//@Getter
//@Setter
//@ToString(callSuper = true, exclude = {"password", "orders"})
//public class User {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long userId; 
//
//    @Column(length = 20, nullable = false)
//    private String firstName;
//
//    @Column(length = 20, nullable = false)
//    private String lastName;
//
//    // Increased length to 100 to avoid data truncation
//    @Column(length = 100, unique = true, nullable = false)
//    private String email;  
//
//    @Column(length = 500, nullable = false)
//    private String password;
//
//    @Column(nullable = false)
//    private double regAmount;
//
//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    private UserRole role;
//
//    // Self-referential relationship
//    @ManyToOne
//    @JoinColumn(name = "trainer_id")
//    private User trainer;  // The trainer (who is also a User)
//
//    @OneToMany(mappedBy = "trainer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<User> trainees = new ArrayList<>(); // Users assigned to this trainer
//    
//    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Orders> orders = new ArrayList<>();
//
////    // Foreign key column named "trainer_id"
////    @ManyToOne
////    @JoinColumn(name = "trainer_id", nullable = false)
////    private Trainer trainer;
//
//    public User(String firstName, String lastName, String email, double regAmount, UserRole role,
//                List<Orders> orders, User trainer) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.regAmount = regAmount;
//        this.role = role;
//        this.orders = orders != null ? orders : new ArrayList<>();
//        this.trainer = trainer;
//    }
//  
//    public String getFullName() {
//        return firstName + " " + lastName;
//    }
//}
//

package com.htps.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true, exclude = {"password", "trainees"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId; 

    @Column(length = 20, nullable = false)
    private String firstName;

    @Column(length = 20, nullable = false)
    private String lastName;

    @Column(length = 100, unique = true, nullable = false)
    private String email;  

    @Column(length = 500, nullable = false)
    private String password;

    @Column(nullable = false)
    private double regAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

//    // Self-referential relationship for trainer-user mapping
//    @ManyToOne
//    @JoinColumn(name = "trainer_id")
//    private User trainer;  // The trainer (who is also a User)
    @ManyToOne
    @JoinColumn(name = "trainer_id", referencedColumnName = "userId")
    private User trainer;


//    @OneToMany(mappedBy = "trainer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<User> trainees = new ArrayList<>(); // Users assigned to this trainer

    public User(String firstName, String lastName, String email, double regAmount, UserRole role, User trainer) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.regAmount = regAmount;
        this.role = role;
        this.trainer = trainer;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

//    // Ensure max 5 trainees per trainer
//    public void assignTrainee(User trainee) throws Exception {
//        if (this.role != UserRole.ROLE_TRAINER) {
//            throw new Exception("Only a user with ROLE_TRAINER can have trainees.");
//        }
//        if (this.trainees.size() >= 5) {
//            throw new Exception("A trainer can have at most 5 trainees.");
//        }
//        trainee.setTrainer(this);
//        this.trainees.add(trainee);
//    }
}
