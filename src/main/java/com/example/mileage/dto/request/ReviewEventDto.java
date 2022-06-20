package com.example.mileage.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@Data
public class ReviewEventDto {

    @NotBlank
    private String type;

    @NotBlank
    private String action;

    @NotBlank
    private String reviewId;

    @Size(max = 2000, message = "{validation.content.size.too_long}")
    private String content;

    private List<String> attachedPhotoIds;

    @NotBlank
    private String userId;

    @NotBlank
    private String placeId;


}
