package com.boot.dto.good;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GoodListView{
	private Integer goodId;
	private String name;
	private Integer amount;
	private Integer goodTypeId;
}
