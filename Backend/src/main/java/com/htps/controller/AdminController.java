package com.htps.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.htps.dto.ApiResponse;
import com.htps.dto.UserDTO;
import com.htps.entities.Diet;
import com.htps.entities.Exercise;
import com.htps.entities.Payment;
import com.htps.entities.Products;
import com.htps.entities.User;
import com.htps.entities.UserRole;
import com.htps.service.AdminService;
import com.htps.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/htps/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    // ====================================================
    // User Management Endpoints
    // ====================================================

    /**
     * Get all users with the role ROLE_USER.
     * Returns a list of UserDTO with trainer information if assigned.
     */
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getUsersByRole(UserRole.ROLE_USER);
        return ResponseEntity.ok(users);
    }

    /**
     * Get a specific user by userId.
     */
    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(adminService.getUserById(userId));
    }

    /**
     * Delete a user by userId.
     */
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long userId) {
        adminService.deleteUser(userId);
        return ResponseEntity.ok(new ApiResponse("User deleted successfully"));
    }

    /**
     * Assign a trainer to a user.
     * Expects userId and trainerId as query parameters.
     */
    @PostMapping("/users/assign-trainer")
    public ResponseEntity<ApiResponse> assignTrainer(
            @RequestParam Long userId, 
            @RequestParam Long trainerId) {
        try {
            userService.assignTrainer(userId, trainerId);
            return ResponseEntity.ok(new ApiResponse("Trainer assigned successfully."));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse("Error: " + e.getMessage()));
        }
    }

    // ====================================================
    // Diet Management Endpoints
    // ====================================================

    /**
     * Get all diets.
     */
    @GetMapping("/diets")
    public ResponseEntity<List<Diet>> getAllDiets() {
        return ResponseEntity.ok(adminService.getAllDiets());
    }

    /**
     * Get a specific diet by dietId.
     */
    @GetMapping("/diets/{dietId}")
    public ResponseEntity<Diet> getDietById(@PathVariable Long dietId) {
        return ResponseEntity.ok(adminService.getDietById(dietId));
    }

    // ====================================================
    // Exercise Management Endpoints
    // ====================================================

    /**
     * Get all exercises.
     */
    @GetMapping("/exercises")
    public ResponseEntity<List<Exercise>> getAllExercises() {
        return ResponseEntity.ok(adminService.getAllExercises());
    }

    /**
     * Get a specific exercise by exerciseId.
     */
    @GetMapping("/exercises/{exerciseId}")
    public ResponseEntity<Exercise> getExerciseById(@PathVariable Long exerciseId) {
        return ResponseEntity.ok(adminService.getExerciseById(exerciseId));
    }

    /**
     * Update an existing exercise.
     */
    @PutMapping("/exercises")
    public ResponseEntity<ApiResponse> updateExercise(@RequestBody Exercise exercise) {
        adminService.updateExercise(exercise);
        return ResponseEntity.ok(new ApiResponse("Exercise updated successfully"));
    }

    /**
     * Delete an exercise by exerciseId.
     */
    @DeleteMapping("/exercises/{exerciseId}")
    public ResponseEntity<ApiResponse> deleteExercise(@PathVariable Long exerciseId) {
        adminService.deleteExercise(exerciseId);
        return ResponseEntity.ok(new ApiResponse("Exercise deleted successfully"));
    }

    // ====================================================
    // Payment Management Endpoints
    // ====================================================

    /**
     * Get all payments.
     */
    @GetMapping("/payments")
    public ResponseEntity<List<Payment>> getAllPayments() {
        return ResponseEntity.ok(adminService.getAllPayments());
    }

    /**
     * Get a specific payment by paymentId.
     */
    @GetMapping("/payments/{paymentId}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long paymentId) {
        return ResponseEntity.ok(adminService.getPaymentById(paymentId));
    }

    // ====================================================
    // Product Management Endpoints
    // ====================================================

    /**
     * Get all products.
     */
    @GetMapping("/products")
    public ResponseEntity<List<Products>> getAllProducts() {
        return ResponseEntity.ok(adminService.getAllProducts());
    }

    /**
     * Get a specific product by productId.
     */
    @GetMapping("/products/{productId}")
    public ResponseEntity<Products> getProductById(@PathVariable Long productId) {
        return ResponseEntity.ok(adminService.getProductById(productId));
    }

    /**
     * Add a new product.
     */
    @PostMapping("/products")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody Products product) {
        adminService.addProduct(product);
        return ResponseEntity.ok(new ApiResponse("Product added successfully"));
    }

    /**
     * Update an existing product.
     * Ensures consistency by setting the productId from the path variable.
     */
    @PutMapping("/products/{productId}")
    public ResponseEntity<ApiResponse> updateProduct(
            @PathVariable Long productId,
            @RequestBody Products product) {

        product.setProductId(productId);
        adminService.updateProduct(product);
        return ResponseEntity.ok(new ApiResponse("Product updated successfully"));
    }

    /**
     * Delete a product by productId.
     */
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId) {
        adminService.deleteProduct(productId);
        return ResponseEntity.ok(new ApiResponse("Product deleted successfully"));
    }
}
