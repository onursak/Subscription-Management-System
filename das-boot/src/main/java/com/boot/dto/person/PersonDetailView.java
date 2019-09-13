package com.boot.dto.person;

import java.util.List;

import com.boot.dto.subscriber.SubscribersOfPersonView;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonDetailView extends PersonListView{
	List<SubscribersOfPersonView> subscribers;
}
