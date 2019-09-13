package com.boot.dto.subscriber;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddSubscriberForm {
	@Size(min=10, max=10, message = "{exception.size.exactly}")
	@NotBlank(message = "{exception.blank}")
	@Pattern(regexp = "(05|5)[0-9][0-9][1-9]([0-9]){6}", message = "{exception.pattern}")
	private String msisdnNo;
	@Size(min = 15, max = 15, message = "{exception.size.exactly}")
	private String imsi;
	@Min(value = 0, message = "{exception.min}")
	private Double balance;
	@Min(value = 0, message = "{exception.dependent.fields}")
	@NotNull(message = "{exception.null}")
	private String personId;

}
