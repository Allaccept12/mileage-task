package com.example.mileage.service.place;

import com.example.mileage.domain.place.Place;
import com.example.mileage.exception.ErrorCode;
import com.example.mileage.exception.exceptions.NotFoundPlaceException;
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
                .orElseThrow(() -> new NotFoundPlaceException(ErrorCode.NOT_FOUND_PLACE));
    }
}
