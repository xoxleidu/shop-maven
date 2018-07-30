package shop.portal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import shop.common.util.HttpClientUtil;
import shop.common.util.LayerResult;
import shop.pojo.TbUser;
import shop.portal.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Value("${SSO_BASE_URL}")
	public String SSO_BASE_URL;
	@Value("${SSO_USER_TOKEN}")
	public String SSO_USER_TOKEN;
	@Value("${SSO_PAGE_LOGIN}")
	public String SSO_PAGE_LOGIN;
	@Value("${SSO_DOMAIN_BASE_USRL}")
	public String SSO_DOMAIN_BASE_USRL;
	
	@Override
	public TbUser getUserByToken(String token) {
		try {
			String doGet = HttpClientUtil.doGet(SSO_BASE_URL + SSO_USER_TOKEN + token);
			LayerResult data = LayerResult.formatToPojo(doGet, TbUser.class);
			if (data.getStatus() == 200) {
				TbUser user = (TbUser) data.getData();
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
