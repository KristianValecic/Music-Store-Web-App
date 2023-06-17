package hr.valecic.musicstorewebapp.dal.service;

import hr.valecic.musicstorewebapp.dal.repository.ItemRepository;
import hr.valecic.musicstorewebapp.dal.repository.LoginhistoryRepository;
import hr.valecic.musicstorewebapp.model.Loginhistory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LoginhistoryService {
    private final LoginhistoryRepository loginhistoryRepository;

    public List<Loginhistory> getAll() {
        return loginhistoryRepository.findAll();
    }

    public void saveLogin(Loginhistory loginhistoryEntry) {
        loginhistoryRepository.save(loginhistoryEntry);
    }
}
