package com.klu.touristapi.controller;

import com.klu.touristapi.model.ApiError;
import com.klu.touristapi.model.Tourist;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tourists")
public class TouristController {

    // Demo token for this classroom practical only. Do not hard-code real secrets.
    private static final String VALID_TOKEN = "BORDER123";

    private final List<Tourist> tourists = List.of(
            new Tourist(1, "Rahul", "India", "P12345"),
            new Tourist(2, "John", "USA", "P67890"),
            new Tourist(3, "David", "UK", "P54321")
    );

    @GetMapping("/{id}")
    public ResponseEntity<?> getTourist(
            @PathVariable int id,
            @RequestHeader(value = "X-Auth-Token", required = false) String token,
            HttpServletRequest request) {

        // S1: Validate the custom token header.
        if (token == null || !VALID_TOKEN.equals(token)) {
            ApiError error = new ApiError(
                    500,
                    "Internal Server Error",
                    token == null ? "Missing X-Auth-Token header" : "Invalid X-Auth-Token value",
                    request.getRequestURI()
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }

        // S2: Fetch one tourist by ID.
        for (Tourist tourist : tourists) {
            if (tourist.getId() == id) {
                return ResponseEntity.ok(tourist);
            }
        }

        ApiError error = new ApiError(
                404,
                "Not Found",
                "Tourist with ID " + id + " was not found",
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
