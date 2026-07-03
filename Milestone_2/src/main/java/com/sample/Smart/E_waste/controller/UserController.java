package com.sample.Smart.E_waste.controller;
import java.util.List;
import com.sample.Smart.E_waste.entity.EwasteRequest;
import com.sample.Smart.E_waste.security.JwtService;
import com.sample.Smart.E_waste.service.EwasteRequestService;
import com.sample.Smart.E_waste.service.FileStorageService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final EwasteRequestService service;
    private final JwtService jwt;
    private final FileStorageService fileStorageService;

    // ✅ Added FileStorageService
    public UserController(EwasteRequestService service,
                          JwtService jwt,
                          FileStorageService fileStorageService) {
        this.service = service;
        this.jwt = jwt;
        this.fileStorageService = fileStorageService;
    }

    // ✅ New create endpoint — supports optional image upload
    @PostMapping("/create")
    public EwasteRequest createRequest(
            @RequestParam("customerName") String customerName,
            @RequestParam("deviceType") String deviceType,
            @RequestParam("pickupAddress") String pickupAddress,
            @RequestParam("description") String description,
            @RequestParam("preferredDate") String preferredDate,
            @RequestParam("preferredSlot") String preferredSlot,
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestHeader("Authorization") String authHeader
    ) {
        // ✅ Build request object from params
        EwasteRequest request = new EwasteRequest();
        request.setCustomerName(customerName);
        request.setDeviceType(deviceType);
        request.setPickupAddress(pickupAddress);
        request.setDescription(description);
        request.setPreferredDate(LocalDate.parse(preferredDate));
        request.setPreferredSlot(preferredSlot);

        // ✅ Save image if provided
        if (file != null && !file.isEmpty()) {
            String imagePath = fileStorageService.save(file);
            request.setImagePath(imagePath);
        }

        // ✅ Get email from token
        String token = authHeader.replace("Bearer ", "");
        String email = jwt.subject(token);

        return service.createRequest(request, email);
    }
    @GetMapping("/my-requests")
    public List<EwasteRequest> myRequests(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String email = jwt.subject(token);
        return service.getMyRequests(email);
    }

    // ✅ Separate endpoint to upload image later
    @PostMapping("/upload-image/{requestId}")
    public EwasteRequest uploadImage(
            @PathVariable("requestId") Long requestId,
            @RequestParam("file") MultipartFile file,
            @RequestHeader("Authorization") String authHeader
    ) {
        String imagePath = fileStorageService.save(file);
        return service.updateImage(requestId, imagePath);
    }
}
