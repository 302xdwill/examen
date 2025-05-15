package com.upeu.msauth.service;

import com.upeu.msauth.dto.AuthUserDto;
import com.upeu.msauth.dto.TokenDto;
import com.upeu.msauth.entity.AuthUser;


public interface AuthUserService {
    public AuthUser save(AuthUserDto authUserDto);


    public TokenDto login(AuthUserDto authUserDto);


    public TokenDto validate(String token);
}