package com.nerkingames.questionservise.fourtoone.rest;

import com.nerkingames.questionservise.shared.QuestionDifficulty;
import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class QuestionSearchParams {

    private Set<UUID> personIds;
    private QuestionDifficulty questionDifficulty;
}
