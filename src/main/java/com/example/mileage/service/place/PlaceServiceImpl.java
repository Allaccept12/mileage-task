package com.example.mileage.service.place;

import com.example.mileage.domain.place.Place;
import com.example.mileage.exception.NotFoundPlaceException;
import com.example.mileage.repository.place.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService{

    private final PlaceRepository placeRepository;

    @Override
    public Place findPlaceByPlaceId(String placeId) {
        return placeRepository.findById(placeId)
                .orElseThrow(NotFoundPlaceException::new);
    }
}
