package com.pss.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.pss.domain.User;

import freemarker.template.SimpleSequence;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

public class SortUser implements TemplateMethodModelEx {

	@Override
	public Object exec(List arguments) throws TemplateModelException {
		// get the paramter
		SimpleSequence simpleSequence = (SimpleSequence)arguments.get(0);
		// convert object to list
		List<User> userList = simpleSequence.toList();
		
		Collections.sort(userList, new Comparator<User>() {

			@Override
			public int compare(User o1, User o2) {
				return o1.getAge() - o2.getAge();
			}
			
		});
		return userList;
	}

}
