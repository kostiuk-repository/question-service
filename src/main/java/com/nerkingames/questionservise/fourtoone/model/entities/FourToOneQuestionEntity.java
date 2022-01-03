package com.nerkingames.questionservise.fourtoone.model.entities;

import com.nerkingames.questionservise.shared.BaseQuestionEntity;
import com.nerkingames.questionservise.statistic.model.entities.PlayedQuestionStatisticEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "four_to_one_question")
public class FourToOneQuestionEntity extends BaseQuestionEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @OneToMany(mappedBy = "fourToOneQuestion", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<FourToOneQuestionTranslationEntity> fourToOneQuestionTranslations;

    @ManyToMany(mappedBy = "fourToOnePlayedQuestions")
    private Set<PlayedQuestionStatisticEntity> playedQuestionStatistics;

}
