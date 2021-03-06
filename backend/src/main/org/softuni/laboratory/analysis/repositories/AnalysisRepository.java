package org.softuni.laboratory.analysis.repositories;

import org.softuni.laboratory.analysis.models.entities.Analysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalysisRepository extends JpaRepository<Analysis, String> {
    Analysis findByName(String name);
}
