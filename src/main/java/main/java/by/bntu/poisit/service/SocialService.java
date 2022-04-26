package main.java.by.bntu.poisit.service;

import main.java.by.bntu.poisit.model.SocialAccount;

public interface SocialService {

    String getAuthorizeUrl(); //first redirect

    SocialAccount getSocialAccount(String authToken);

}
