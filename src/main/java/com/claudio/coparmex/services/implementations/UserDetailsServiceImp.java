package com.claudio.coparmex.services.implementations;

import com.claudio.coparmex.models.entities.Partner;
import com.claudio.coparmex.models.entities.UserM;
import com.claudio.coparmex.services.contracts.PartnerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    PartnerDAO partnerDAOService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Partner partner = partnerDAOService.findByUser(username).get();
        return UserM.build(partner);
    }
}
