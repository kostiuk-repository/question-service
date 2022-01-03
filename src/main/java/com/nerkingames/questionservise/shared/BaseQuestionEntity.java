package com.nerkingames.questionservise.shared;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class BaseQuestionEntity {

    @Column(name = "difficulty", nullable = false)
    @Enumerated(EnumType.STRING)
    private QuestionDifficulty questionDifficulty;
}
