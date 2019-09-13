package com.boot.dto.offer;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOfferForm extends AddOfferForm{
	@NotNull(message= "{exception.null}")
	private Integer offerId;
}
