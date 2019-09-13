package com.boot.dto.subscriber;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddBalanceForm {
	@Size(min = 10, max=10, message= "{exception.size.exactly}")
	@Pattern(regexp = "(05|5)[0-9][0-9][1-9]([0-9]){6}", message = "{exception.pattern}")
	private String msisdnNo;
	@Min(value = 0, message = "{exception.min}")
	@NotNull(message = "{exception.null}")
	private Double incBalance;	
}
