package com.project.service.UserService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {
    private Integer hotelId;
    private String name;
    private String location;
    private String about;
}
