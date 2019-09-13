package com.boot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.dto.good.AddGoodForm;
import com.boot.dto.good.GoodListView;
import com.boot.exceptions.InvalidValueException;
import com.boot.model.Good;
import com.boot.model.GoodType;
import com.boot.repository.GoodRepository;
import com.boot.repository.GoodTypeRepository;

import javassist.NotFoundException;



@Service
public class GoodService {
	@Autowired
	private GoodRepository goodRepository;
	@Autowired
	private GoodTypeRepository goodTypeRepository;
	
	/**
	 * This function gets specific good for good details.
	 * @throws NotFoundException 
	 */
	public GoodListView getGood(Integer goodId) throws NotFoundException {
		Good good = goodRepository.findGoodByGoodId(goodId);
		if(good == null) {
			throw new NotFoundException("The good with id: " + goodId + " not found!");
		}
		GoodListView goodView = new GoodListView();
		BeanUtils.copyProperties(good, goodView);
		goodView.setGoodTypeId(good.getGoodType().getGoodTypeId());
		return goodView;
	}
	
	/**
	 * This function adds new good to the system.
	 * @throws InvalidValueException 
	 */
	public String addGood(AddGoodForm  goodForm) {
		GoodType goodtype = goodTypeRepository.getOne(goodForm.getGoodTypeId());
		Good newGood = new Good();
		BeanUtils.copyProperties(goodForm, newGood);
		newGood.setGoodType(goodtype);
		Good saved = goodRepository.save(newGood);
		return saved.getName();
	}
	

	public List<GoodListView> getGoods() {
		List<Good> goods = goodRepository.findAll();
		List<GoodListView> goodViews = new ArrayList<GoodListView>();
		for(Good g: goods) {
			GoodListView goodView = new GoodListView();
			BeanUtils.copyProperties(g, goodView);
			goodView.setGoodTypeId(g.getGoodType().getGoodTypeId());
			goodViews.add(goodView);
		}
		return goodViews;
	}
	
	
}
