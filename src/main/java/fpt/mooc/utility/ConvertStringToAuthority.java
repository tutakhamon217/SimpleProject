package fpt.mooc.utility;

import fpt.mooc.entities.Authority;
import fpt.mooc.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class ConvertStringToAuthority {
    @Autowired
    private AuthorityRepository authorityRepository;

    public Set<Authority> convertStringToAuthority(Set authorities) {
        Set<String> strRoles = authorities;
        Set<Authority> roles = new HashSet<>();
        try {
            if (strRoles == null) {
                Authority userRole = authorityRepository.findByCode("ROLE_EMPLOYEE")
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(userRole);
            } else {
                strRoles.forEach(role -> {
                    switch (role) {
                        case "ROLE_ADMIN":
                            Authority GVBMRole = authorityRepository.findByCode("ROLE_ADMIN")
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(GVBMRole);
                            break;
                        case "ROLE_MANAGER":
                            Authority GVCNRole = authorityRepository.findByCode("ROLE_MANAGER")
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(GVCNRole);
                            break;
                        case "ROLE_EMPLOYEE":
                            Authority HPRole = authorityRepository.findByCode("ROLE_EMPLOYEE")
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(HPRole);
                            break;
                    }
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return roles;
    }
}
