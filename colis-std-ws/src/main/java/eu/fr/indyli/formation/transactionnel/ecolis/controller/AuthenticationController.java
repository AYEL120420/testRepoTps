package eu.fr.indyli.formation.transactionnel.ecolis.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import eu.fr.indyli.formation.business.dto.EcolisUserDto;
import eu.fr.indyli.formation.business.ecolis.exception.EcolisBusinessException;
import eu.fr.indyli.formation.business.ecolis.service.IEcolisUserService;
import eu.fr.indyli.formation.transactionnel.ecolis.form.ApiResponse;
import eu.fr.indyli.formation.transactionnel.ecolis.form.AuthToken;
import eu.fr.indyli.formation.transactionnel.ecolis.security.JwtTokenUtil;
import eu.fr.indyli.formation.transactionnel.ecolis.utils.Constants;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/token")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private IEcolisUserService userService;
    

    @RequestMapping(value = "/generate-token", method = RequestMethod.POST)
    public ApiResponse<AuthToken> register(@RequestBody EcolisUserDto loginUser) throws AuthenticationException, EcolisBusinessException {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getLogin(), loginUser.getPassword()));
        final EcolisUserDto user = userService.findByLogin(loginUser.getLogin());
        final String token = jwtTokenUtil.generateToken(user);
        return new ApiResponse<AuthToken>(200, "success",new AuthToken(token, user.getLogin()));
    }
    
    @RequestMapping(value = "/is-token-valid", method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<Boolean> register(@RequestBody AuthToken tokenValue) throws AuthenticationException, EcolisBusinessException {
    	if (StringUtils.isNoneBlank(tokenValue.getToken())){
    		tokenValue.setToken(Constants.TOKEN_PREFIX + tokenValue.getToken());
    		Boolean isTokenValid  = jwtTokenUtil.isTokenExpired(tokenValue.getToken());
    		return new ApiResponse<Boolean>(200, "success",isTokenValid);
    	} else {
    		return new ApiResponse<Boolean>(200, "success",Boolean.FALSE);
    	}
    }

}
