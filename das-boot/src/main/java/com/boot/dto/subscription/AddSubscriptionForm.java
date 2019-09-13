package com.boot.dto.subscription;

import java.time.LocalDateTime;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.boot.enums.Status;
import com.boot.validators.ValidDate;
import com.boot.validators.ValidStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ValidDate(startDate = "startDate", endDate = "endDate", message = "Start Date cannot be after End Date!")
@ValidStatus
public class AddSubscriptionForm {
	
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
	
	@Enumerated(EnumType.STRING)
	private Status status;       // ACTIVE, TERMINATED, SUSPENDED
	
	@Min(value = 0, message = "{exception.min}")
	private Double cost;
	
	@Min(value = 0, message = "{exception.min}")
	private Integer discountAmount;
	
	@Min(value = 0, message = "{exception.dependent.fields}")
	private Integer subscriberId;
	
	@Min(value = 0, message = "{exception.dependent.fields}")
	private Integer offerId;
	

}
