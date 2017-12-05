package com.pss.utils;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pss.enums.ExceptionEnum;
import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;  
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateNumberModel;
import freemarker.template.TemplateScalarModel;

/**
 * Custom defind the freemark directive.
 * 
 * @author songsong.peng
 *
 */
@Component
public class ContentDirective implements TemplateDirectiveModel {
	
	private static final Logger LOGGER  = LoggerFactory.getLogger(ContentDirective.class);
	
	private static final String PARAM_NAME = "name";
	
	private static final String PARAM_AGE = "age";
	
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		LOGGER.info("Template Directive Body is:{}", body);
		if(null == body) {
			throw new TemplateModelException(ExceptionEnum.NULL_BODY.name());
		} else {
			LOGGER.info("==============Start to execute the directive==============");
			String name = getString(PARAM_NAME, params);  
			Integer age = getInt(PARAM_AGE, params);  

			if(name != null){
				env.setVariable("output", DEFAULT_WRAPPER.wrap("从ContentDirective解析类中获得的参数是："+name+", "));
			}
			if(age != null){
				env.setVariable("append", DEFAULT_WRAPPER.wrap("年龄："+age));
			}
			Writer out = env.getOut();
			//out.write("从这里输出可以再页面看到具体的内容，就像document.writer写入操作一样。<br/>");
			body.render(out);
			LOGGER.info("==============End to execute the directive==============");
		}
	}
		
	/** 
	 * Get the String type value
	 * 
	 * @param paramName 
	 * @param paramMap 
	 * @return the String value
	 * @throws TemplateModelException
	 */
	public static String getString(String paramName, Map<String, TemplateModel> paramMap) throws TemplateModelException{  
		TemplateModel model = paramMap.get(paramName);
		if(model == null){
			return null;
		}
		if(model instanceof TemplateScalarModel){
			return ((TemplateScalarModel)model).getAsString();
		}else if (model instanceof TemplateNumberModel) {  
			return ((TemplateNumberModel)model).getAsNumber().toString();
		}else{
			throw new TemplateModelException(paramName);
		}
	}

	/** 
	 * Get int type value
	 * 
	 * @param paramName 
	 * @param paramMap 
	 * @return the Integer value
	 * @throws TemplateModelException  
	 */
	public static Integer getInt(String paramName, Map<String, TemplateModel> paramMap) throws TemplateModelException{
		TemplateModel model = paramMap.get(paramName);
		if(model==null){
			return null;
		}
		if(model instanceof TemplateScalarModel){
			String str = ((TemplateScalarModel)model).getAsString();
			try {  
				return Integer.valueOf(str);
			} catch (NumberFormatException e) {
				throw new TemplateModelException(paramName);
			}
		}else if(model instanceof TemplateNumberModel){
			return ((TemplateNumberModel)model).getAsNumber().intValue();
		}else{
			throw new TemplateModelException(paramName);
		}
	}
}
