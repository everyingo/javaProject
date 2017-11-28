package com.mine.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.mine.model.Survey;
import com.mine.token.SurveyToken;

/**
 * 
 * @author Administrator
 *
 */
public class SurveyparkDataSourceRouter extends AbstractRoutingDataSource{

	@Override
	protected Object determineCurrentLookupKey() {
		/**
		 * 获得当前token
		 */
		SurveyToken token=SurveyToken.getToken();
		if(token!=null){
			Survey survey=token.getSurvey();
			SurveyToken.unbindToken();
			return (survey.getId()%2==0)?"odd":"even";
		}
		return null;
	}

}
