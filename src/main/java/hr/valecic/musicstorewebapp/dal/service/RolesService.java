package hr.valecic.musicstorewebapp.dal.service;

import hr.valecic.musicstorewebapp.dal.repository.RoleRepository;
import hr.valecic.musicstorewebapp.model.Roles;
import hr.valecic.musicstorewebapp.model.enums.RoleEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RolesService {

    private RoleRepository roleRepository;


    public Roles getRoleByRoleName(String userRole) {
        return roleRepository.getRolesByRolename(userRole);
    }
}
