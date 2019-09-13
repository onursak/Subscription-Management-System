package com.boot.dto.offer;

import java.time.LocalDateTime;
import java.util.Set;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.boot.validators.ValidDate;
import com.boot.validators.ValidPeriod;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ValidDate(startDate = "startDate",endDate = "endDate", message = "End date cannot be before start date!")
@ValidPeriod(subscriptionPeriod = "subscriptionPeriod", chargingPeriod = "chargingPeriod")
public class AddOfferForm{
	@Size(min = 3, max = 20, message = "{exception.size}")
	@NotBlank(message = "{exception.blank}")
	private String name;
	
	@NotNull(message = "{exception.null}")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
	@Future(message = "{exception.date.future}")
	private LocalDateTime startDate;
	
	@NotNull(message = "{exception.null}")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
	@Future(message = "{exception.date.future}")
	private LocalDateTime endDate;
	
	@Min(value = 0, message = "{exception.min}")
	@NotNull(message = "{exception.null}")
	private Integer chargingPeriod;
	@NotNull(message = "{exception.null}")
	private Boolean recurring;
	@NotNull(message = "{exception.null}")
	private Boolean secondSubscription;
	@Min(value = 0, message = "{exception.min}")
	@NotNull(message = "{exception.null}")
	private Integer subscriptionPeriod;
	@Min(value = 0, message = "{exception.min}")
	@NotNull(message = "{exception.null}")
	private Double price;
	private String description;
	
	@Size(min = 1, message = "{exception.min}")
	private Set<Integer> goodIds;

}
