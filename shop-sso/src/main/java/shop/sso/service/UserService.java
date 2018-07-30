package shop.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.common.util.LayerResult;
import shop.pojo.TbUser;

public interface UserService {

	LayerResult checkData(String content, Integer type);
	LayerResult createUser(TbUser user);
	LayerResult userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response);
	LayerResult getUserByToken(String token);
}
