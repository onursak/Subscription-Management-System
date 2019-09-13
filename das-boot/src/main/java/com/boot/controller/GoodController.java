package com.boot.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.boot.dto.good.AddGoodForm;
import com.boot.dto.good.GoodListView;
import com.boot.dto.response.ResponseDto;
import com.boot.service.GoodService;

import javassist.NotFoundException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class GoodController{
	
	@Autowired
	private GoodService goodService;
	
	//List all goods in the system
	@RequestMapping(value="/goods",method=RequestMethod.GET)
	public ResponseEntity<List<GoodListView>> listGoods(){
		List<GoodListView> goods = goodService.getGoods();
		return new ResponseEntity<List<GoodListView>>(goods,HttpStatus.OK);
	}
	
	//Get specific good from the system according to given good id
	@RequestMapping(value="/good",method=RequestMethod.GET)
	public ResponseEntity<GoodListView> getGood(@RequestParam("good_id") Integer goodId) throws NotFoundException{
		GoodListView good = goodService.getGood(goodId);
		return new ResponseEntity<GoodListView>(good,HttpStatus.OK);
	}
	
	//Add new good to the system
	@RequestMapping(value="/good",method=RequestMethod.POST)
	public ResponseEntity<ResponseDto> addGood(@Valid @RequestBody AddGoodForm goodForm){
		String goodName = goodService.addGood(goodForm);
		return new ResponseEntity<ResponseDto>(new ResponseDto
				("OK", 201, "Good with name " + goodName + " is created."),HttpStatus.CREATED);
	}
	
	
}
