package org.whymca.carmood;

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.Token;

public class StereoMoodOAuthApi extends DefaultApi10a {

	@Override
	public String getAccessTokenEndpoint() {
		return "http://www.stereomood.com/api/oauth/access_token";
	}

	@Override
	public String getAuthorizationUrl(Token arg0) {
		return "http://www.stereomood.com/api/oauth/authorize?oauth_token=" + arg0.getToken();
	}

	@Override
	public String getRequestTokenEndpoint() {
		return "http://www.stereomood.com/api/oauth/request_token";
	}
	
	

}
