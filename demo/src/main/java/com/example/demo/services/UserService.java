package com.example.demo.services;

import com.example.demo.models.UserCustom;
import com.example.demo.repo.IGenericDao;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
  @Autowired IGenericDao genericDao;

  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {


    String query = "Select * from USER_SECURITY u where u.name_user = :username";
    Map<String, Object> params = new HashMap<>();
    params.put("username", userName);
    List<UserCustom> userCustomList =
        this.genericDao.setClazz(UserCustom.class).findByParams(query, params);
    UserCustom userCustom =
        userCustomList.stream()
            .filter(s -> s.getNameUser().equalsIgnoreCase(userName))
            .findFirst()
            .orElse(new UserCustom());
    System.out.println(userCustom);

    return new User("matb", "{noop}12345", Collections.EMPTY_LIST);
  }
}
