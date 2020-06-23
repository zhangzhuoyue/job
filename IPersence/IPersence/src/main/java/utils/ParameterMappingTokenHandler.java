package utils;

import pojo.ParamsMapping;

import java.util.ArrayList;
import java.util.List;


public class ParameterMappingTokenHandler implements TokenHandler {
	private List<ParamsMapping> parameterMappings = new ArrayList<ParamsMapping>();

	// context是参数名称 #{id} #{username}

	public String handleToken(String content) {
		parameterMappings.add(buildParameterMapping(content));
		return "?";
	}

	private ParamsMapping buildParameterMapping(String content) {
		ParamsMapping parameterMapping = new ParamsMapping(content);
		return parameterMapping;
	}

	public List<ParamsMapping> getParameterMappings() {
		return parameterMappings;
	}

	public void setParameterMappings(List<ParamsMapping> parameterMappings) {
		this.parameterMappings = parameterMappings;
	}

}
