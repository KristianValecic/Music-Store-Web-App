package hr.valecic.musicstorewebapp.dal.service;

import hr.valecic.musicstorewebapp.dal.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PurchaseService {
    private PurchaseRepository purchaseRepository;
}
