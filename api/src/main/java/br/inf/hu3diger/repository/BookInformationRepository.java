package br.inf.hu3diger.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.inf.hu3diger.model.BookInformation;

@Repository
public interface BookInformationRepository extends CrudRepository<BookInformation, Long> {

}
