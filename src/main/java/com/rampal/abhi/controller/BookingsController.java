package com.rampal.abhi.controller;

import com.rampal.abhi.model.*;
import com.rampal.abhi.service.BookingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BookingsController {

    @Autowired
    BookingsService bookingsService;

    @GetMapping("/getCheapestRooms")
    public List<RoomResponse> getCheapestRooms(@RequestParam(value = "cityCode") String cityCode,
                                               @RequestParam(value = "checkInDate", required=false) String checkInDate,
                                               @RequestParam(value = "checkOutDate", required = false) String checkOutDate) throws Exception {
        // Sending get request
        List<RoomResponse> roomResponses = bookingsService.getHotelOffers(cityCode, checkInDate, checkOutDate);
        return roomResponses;
    }


}
