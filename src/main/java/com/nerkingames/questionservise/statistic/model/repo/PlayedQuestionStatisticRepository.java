package com.nerkingames.questionservise.statistic.model.repo;

import com.nerkingames.questionservise.statistic.model.entities.PlayedQuestionStatisticEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PlayedQuestionStatisticRepository
        extends JpaRepository<PlayedQuestionStatisticEntity, UUID> {
}
