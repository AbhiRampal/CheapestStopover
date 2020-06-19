package com.rampal.abhi.controller;

import com.rampal.abhi.model.HotelOffers;
import com.rampal.abhi.model.RoomResponse;
import com.rampal.abhi.service.AuthService;
import com.rampal.abhi.service.BookingsService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value=BookingsController.class)
@AutoConfigureMockMvc
class BookingControllerTests {

	@Autowired
	private BookingsController bookingsController;

	@Autowired
	private BookingsService bookingsService;

	@Autowired
	AuthService authService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
		assertThat(bookingsController).isNotNull();
	}

	@Test
	void returnsSomething() throws Exception {
		mockMvc.perform(get("/api/getCheapestRooms")
				.param("cityCode", "PAR")
				.param("checkInDate", "2020-06-16")
				.param("checkOutDate", "2020-06-17")
				.contentType("application/json"))
				.andExpect(status().isOk());
	}

	@Test
	void testPost() throws Exception {
		mockMvc.perform(post("/api/getCheapestRooms")
				.param("cityCode", "PAR")
				.param("checkInDate", "2020-06-16")
				.param("checkOutDate", "2020-06-17")
				.contentType("application/json"))
				.andExpect(status().isForbidden());
	}

	@Test
	void testGetHotelOffersParis(){
		List<RoomResponse> hotelOffersList = bookingsService.getHotelOffers("NYC",
				"2020-06-16", "2020-06-17");
		assertNotNull(hotelOffersList);
		assertFalse(hotelOffersList.isEmpty());
		for (RoomResponse room: hotelOffersList){
			assertThat(room.getAddress().contains("PAR"));
		}
	}

	@Test
	void testGetHotelOffersLondon(){
		List<RoomResponse> hotelOffersList = bookingsService.getHotelOffers("LON",
				"2020-06-16", "2020-06-17");
		assertNotNull(hotelOffersList);
		for (RoomResponse room: hotelOffersList){
			assertThat(room.getAddress().contains("LON"));
		}
	}

	@Test
	void testGetBearerAuth() throws IOException {
		String auth = authService.getBearerAuth();
		assertNotNull(auth);
	}
}
