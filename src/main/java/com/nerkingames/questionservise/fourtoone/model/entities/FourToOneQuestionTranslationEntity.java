package com.nerkingames.questionservise.fourtoone.model.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "four_to_one_question_translation")
public class FourToOneQuestionTranslationEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "question_language", nullable = false)
    private QuestionLanguage questionLanguage;

    @Column(name = "question", nullable = false)
    private String question;

    @OneToMany(mappedBy = "fourToOneQuestionTranslation", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<FourToOneQuestionTranslationAnswerEntity> fourToOneQuestionTranslationAnswer;

    @ManyToOne
    @JoinColumn(name = "four_to_one_question_id")
    private FourToOneQuestionEntity fourToOneQuestion;

}