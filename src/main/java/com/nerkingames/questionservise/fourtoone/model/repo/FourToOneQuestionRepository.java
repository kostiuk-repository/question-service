package com.nerkingames.questionservise.fourtoone.model.repo;

import com.nerkingames.questionservise.fourtoone.model.entities.FourToOneQuestionEntity;
import com.nerkingames.questionservise.shared.QuestionDifficulty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface FourToOneQuestionRepository extends JpaRepository<FourToOneQuestionEntity, UUID>,
        JpaSpecificationExecutor<FourToOneQuestionEntity> {

    @Query("select count(ftoq.id) from FourToOneQuestionEntity ftoq " +
            "left join ftoq.playedQuestionStatistics pqs " +
            "where not exists " +
            "(select pqs from PlayedQuestionStatisticEntity pqs " +
            "where pqs.fourToOnePlayedQuestions = ftoq.playedQuestionStatistics " +
            "and pqs.playerId in (:personIds)) " +
            "and ftoq.questionDifficulty = (:questionDifficulty)")
    Long countAllUniqueQuestionForGame(@Param("personIds") final Set<UUID> personIds);

    @Query("select ftoq.id from FourToOneQuestionEntity ftoq " +
            "where not exists " +
            "(select pqs from PlayedQuestionStatisticEntity pqs " +
            "where pqs.fourToOnePlayedQuestions = ftoq.playedQuestionStatistics " +
            "and pqs.playerId in (:personIds)) " +
            "and ftoq.questionDifficulty = (:questionDifficulty)")
    Set<UUID> findAllQuestionForGame(
            @Param("personIds") final Set<UUID> personIds,
            @Param("questionDifficulty") final QuestionDifficulty questionDifficulty);

    @Query("select distinct ftoq.id from FourToOneQuestionEntity ftoq " +
            "left join ftoq.playedQuestionStatistics pqs " +
            "where pqs.playerId in (:personIds) and " +
            "ftoq.questionDifficulty = (:questionDifficulty)" +
            "order by pqs.answerDate asc")
    Page<UUID> findOldestQuestionForGame(
            @Param("personIds") final Set<UUID> personIds,
            @Param("questionDifficulty") final QuestionDifficulty questionDifficulty,
            Pageable pageable);

}
