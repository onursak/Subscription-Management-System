package com.boot.dto.good;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddGoodForm {
	@Size(min = 2, max = 20, message="{exception.size}")
	private String name;
	@Min(value=0, message="{exception.min}")
	private Integer amount;
	@Size(min = 1, max = 6, message="{exception.dependent.fields}")
	private Integer goodTypeId;
}
