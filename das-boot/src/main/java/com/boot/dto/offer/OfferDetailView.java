package com.boot.dto.offer;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.boot.dto.good.GoodListView;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OfferDetailView {
	private Integer offerId;
	private String name;
	
	@JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
	private LocalDateTime startDate;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
	private LocalDateTime endDate;
	
	private Integer chargingPeriod;
	private Boolean recurring;
	private Boolean secondSubscription;
	private Integer subscriptionPeriod;
	private Double price;
	private String description;
	private Set<GoodListView> goods;
	
}
