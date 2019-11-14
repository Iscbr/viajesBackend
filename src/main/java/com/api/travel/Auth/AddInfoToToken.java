package com.api.travel.Auth;

import com.api.travel.Entity.Usuario;
import com.api.travel.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class AddInfoToToken implements TokenEnhancer {

    private final UsuarioService usuarioService;

    @Autowired
    public AddInfoToToken(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        Usuario usuario = usuarioService.getByEmail(oAuth2Authentication.getName());

        HashMap<String, Object> addInfo = new HashMap<>();
        addInfo.put("name", usuario.getNombre());
        addInfo.put("second_name", usuario.getApellidos());
        addInfo.put("active", usuario.getActivo());

        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(addInfo);

        return oAuth2AccessToken;
    }
}
