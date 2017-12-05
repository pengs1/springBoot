package com.pss.utils;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import freemarker.template.SimpleSequence;
import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

/**
 * Custom to define a method to order the list<BigDecimal>
 * 
 * @author songsong.peng
 *
 */
public class SortMethod implements TemplateMethodModelEx {

	@Override
	public Object exec(List arguments) throws TemplateModelException {
		SimpleSequence simpleSequence = (SimpleSequence)arguments.get(0);
		TemplateBooleanModel flag = (TemplateBooleanModel)arguments.get(1);
		List<BigDecimal> sortList = simpleSequence.toList();
		Boolean sortFlag = flag.getAsBoolean();
		
		Collections.sort(sortList, new Comparator<BigDecimal>() {

			@Override
			public int compare(BigDecimal o1, BigDecimal o2) {
				if(sortFlag) {
					return o1.intValue() - o2.intValue();
				} else {
					return o2.intValue() - o1.intValue();
				}
			}
		});
		return sortList;
	}
}
