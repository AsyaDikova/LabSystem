package org.softuni.laboratory.occurrence.repositories;

import org.softuni.laboratory.occurrence.models.entities.Occurrence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OccurrenceRepository extends JpaRepository<Occurrence, String> {
}
