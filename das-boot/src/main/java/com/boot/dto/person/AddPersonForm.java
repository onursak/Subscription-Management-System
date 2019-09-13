package com.boot.dto.person;

import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.boot.enums.Gender;
import com.boot.validators.ValidAge;
import com.boot.validators.ValidEnum;
import com.boot.validators.ValidName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddPersonForm {
	@NotEmpty(message = "{exception.empty}")
	@Size(min = 11, max = 11, message="{exception.size.exactly}")
	private String personId;
	@Size(min = 1, max = 30, message = "{exception.size}")
	@ValidName
	private String name;
	@Size(min = 1, max = 30, message = "{exception.size}")
	@ValidName
	private String surname;
	
	@JsonDeserialize(using = LocalDateDeserializer.class)
    @DateTimeFormat(pattern = "dd-MM-yyyy", iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@Past(message = "{exception.date.past}")
	@ValidAge
	private LocalDate birthdate;
	//@Size(min = 4, max = 6, message = "{exception.size}")
	@Enumerated(EnumType.STRING)
	@ValidEnum(message = "Invalid gender type!")
	private Gender gender;
}
