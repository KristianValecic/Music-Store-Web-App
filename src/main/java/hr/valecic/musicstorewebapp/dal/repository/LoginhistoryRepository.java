package hr.valecic.musicstorewebapp.dal.repository;

import hr.valecic.musicstorewebapp.model.Loginhistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginhistoryRepository extends JpaRepository<Loginhistory, Long> {

}
