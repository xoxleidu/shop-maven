package shop.portal.service;

import shop.pojo.TbUser;

public interface UserService {

	TbUser getUserByToken(String token);
}
