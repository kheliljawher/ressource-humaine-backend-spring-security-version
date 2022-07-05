package com.example.spring_security_version.controller;

import com.example.spring_security_version.entity.Utilisateur;
import com.example.spring_security_version.repository.CandidatRepository;
import com.example.spring_security_version.repository.UtilisateurRepository;
import com.example.spring_security_version.secutity.JwtResponse;
import com.example.spring_security_version.secutity.SignInRequest;
import com.example.spring_security_version.secutity.TokenUtil;
import com.example.spring_security_version.secutity.UserService;
import com.example.spring_security_version.service.CandidatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.example.spring_security_version.entity.Candidat;


@RestController
@RequestMapping(value = "/user")
@CrossOrigin("*")

public class AuthController {

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CandidatRepository candidatRepository;

    @PostMapping(value = {"","/"})
    public JwtResponse signIn(@RequestBody SignInRequest signInRequest){

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userService.loadUserByUsername(signInRequest.getUsername());
        String token = tokenUtil.generateToken(userDetails);
        String refreshtToken = tokenUtil.generateRefreshtToken(userDetails);
        Utilisateur utilisateur = utilisateurRepository.findByLogin(signInRequest.getUsername());
        JwtResponse response = new JwtResponse(token,refreshtToken,utilisateur);
        return response;

    }

}
