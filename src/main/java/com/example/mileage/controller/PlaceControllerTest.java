package com.example.mileage.controller;


import com.example.mileage.domain.place.Place;
import com.example.mileage.repository.place.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PlaceControllerTest {

    private final PlaceRepository placeRepository;

    @PostMapping("/api/place/{id}/{placeName}")
    public ResponseEntity<String> createPlace(@PathVariable("id") String placeId,
                                              @PathVariable("placeName") String placeName) {
        Place place = new Place(placeId, placeName);
        placeRepository.save(place);
        return ResponseEntity.ok().body("성공");
    }
}
