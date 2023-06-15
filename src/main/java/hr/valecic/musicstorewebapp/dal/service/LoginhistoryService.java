package hr.valecic.musicstorewebapp.dal.service;

import hr.valecic.musicstorewebapp.dal.repository.ItemRepository;
import hr.valecic.musicstorewebapp.dal.repository.LoginhistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginhistoryService {
    private final LoginhistoryRepository loginhistoryRepository;

}
