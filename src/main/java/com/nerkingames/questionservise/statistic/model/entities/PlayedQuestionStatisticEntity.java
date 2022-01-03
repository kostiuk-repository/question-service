package com.nerkingames.questionservise.statistic.model.entities;

import com.nerkingames.questionservise.fourtoone.model.entities.FourToOneQuestionEntity;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.convert.Jsr310Converters;

import javax.persistence.*;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "played_question_statistic")
public class PlayedQuestionStatisticEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "player_id" , nullable = false)
    private UUID playerId;

    @Column(name = "answer_result" , nullable = false)
    private boolean answerResult;

    @Column(name = "answer_date" , nullable = false)
    private OffsetDateTime answerDate;

    @Column(name = "time_for_answer" , nullable = false)
    private Long timeForAnswer;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "played_question_statistic_four_to_one_played_questions",
            joinColumns = @JoinColumn(name = "played_question_statistic_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "four_to_one_question_id", referencedColumnName = "id"))
    private Set<FourToOneQuestionEntity> fourToOnePlayedQuestions;
}
