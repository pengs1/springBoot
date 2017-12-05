package com.pss.utils;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.pss.enums.ExceptionEnum;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateNumberModel;
import freemarker.template.TemplateScalarModel;

@Component
public class PengsDirective implements TemplateDirectiveModel {

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		if(null == body) {
			throw new TemplateModelException(ExceptionEnum.NULL_BODY.name());
		} else {
			
			String name = getString(params, "gender");
			Integer age = getInteger(params, "id");
			loopVars[0] = DEFAULT_WRAPPER.wrap(name);
			loopVars[1] = DEFAULT_WRAPPER.wrap(age);
			
			body.render(env.getOut());
		}
	}

	private Integer getInteger(Map<String, TemplateModel> params, String paramName) throws TemplateModelException {
		TemplateModel ageModel = params.get(paramName);
		if(null == ageModel) {
			return null;
		}
		
		if(ageModel instanceof TemplateScalarModel) {
			try {
				return Integer.valueOf(((TemplateScalarModel)ageModel).getAsString());
			} catch (NumberFormatException e) {
				throw new TemplateModelException(paramName);
			}
		} else if(ageModel instanceof TemplateNumberModel) {
			return ((TemplateNumberModel)ageModel).getAsNumber().intValue();
		} else {
			throw new TemplateModelException(paramName);
		}
	}

	private String getString(Map<String, TemplateModel> params, String paramName) throws TemplateModelException {
		TemplateModel nameModel = params.get(paramName);
		if(null == nameModel) {
			return null;
		}
		
		if(nameModel instanceof TemplateScalarModel) {
			return ((TemplateScalarModel) nameModel).getAsString();
		} else if(nameModel instanceof TemplateNumberModel) {
			return ((TemplateNumberModel)nameModel).getAsNumber().toString();
		} else {
			throw new TemplateModelException(paramName);
		}
	}

}
