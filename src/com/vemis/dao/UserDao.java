package com.vemis.dao;

import com.vemis.model.Login;
import com.vemis.model.User;

public interface UserDao {
  void register(User user);
  boolean validateUser(Login login);
}
