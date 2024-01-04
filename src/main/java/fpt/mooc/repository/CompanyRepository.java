package fpt.mooc.repository;

import fpt.mooc.entities.Company;
import fpt.mooc.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
    Company findByName(String Name);

    @Override
    <S extends Company> S save(S entity);
}
