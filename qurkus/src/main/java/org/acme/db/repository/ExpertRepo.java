package org.acme.db.repository;

import org.acme.db.Expert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpertRepo extends JpaRepository<Expert, Long> {

    Expert getByField(String field);
}
