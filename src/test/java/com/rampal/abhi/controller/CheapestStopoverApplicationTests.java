package com.rampal.abhi.controller;

import com.rampal.abhi.model.HotelOffers;
import com.rampal.abhi.model.Offer;
import com.rampal.abhi.service.AuthService;
import com.rampal.abhi.service.BookingsService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.access.SecurityConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value=BookingsController.class,  excludeAutoConfiguration = {SecurityAutoConfiguration.class})
@AutoConfigureMockMvc
class CheapestStopoverApplicationTests {

	@Autowired
	private BookingsController bookingsController;

	@Autowired
	private BookingsService bookingsService;

	@Autowired
	AuthService authService;

	@Autowired
	private MockMvc mvc;

	@Test
	void contextLoads() {
		assertThat(bookingsController).isNotNull();
	}

	@Test
	void returnsSomething() throws Exception {
		mvc.perform(get("/api/getCheapestRooms")
				.param("cityCode", "PAR")
				.param("checkInDate", "2020-06-16")
				.param("checkOutDate", "2020-06-17"))
				.andExpect(status().isOk());
	}

	@Test
	void testGetHotelOffersParis(){
		List<HotelOffers> hotelOffersList = bookingsService.getHotelOffers("PAR",
				"2020-06-16", "2020-06-17");
		assertNotNull(hotelOffersList);
		assertFalse(hotelOffersList.isEmpty());
		for (HotelOffers offer: hotelOffersList){
			assertEquals(offer.getHotel().getCityCode(), "PAR");
		}
	}

	@Test
	void testGetHotelOffersLondon(){
		List<HotelOffers> hotelOffersList = bookingsService.getHotelOffers("LON",
				"2020-06-16", "2020-06-17");
		assertNotNull(hotelOffersList);
		for (HotelOffers offer: hotelOffersList){
			assertEquals(offer.getHotel().getCityCode(), "LON");
		}
	}

	@Test
	void testGetBearerAuth() throws IOException {
		String auth = authService.getBearerAuth();
		assertNotNull(auth);
	}
}
