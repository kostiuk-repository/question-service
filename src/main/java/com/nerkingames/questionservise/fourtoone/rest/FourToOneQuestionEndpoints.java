package com.nerkingames.questionservise.fourtoone.rest;

import com.nerkingames.questionservise.fourtoone.model.entities.FourToOneQuestionEntity;
import com.nerkingames.questionservise.fourtoone.model.repo.FourToOneQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("question")
public class FourToOneQuestionEndpoints {

    private final FourToOneQuestionRepository fourToOneQuestionRepository;

    @GetMapping("/game-specific")
    private Set<UUID> getIdsForGame(
            @RequestBody QuestionSearchParams questionSearchParams) {
        return fourToOneQuestionRepository.findAllQuestionForGame(
                questionSearchParams.getPersonIds(),
                questionSearchParams.getQuestionDifficulty());
    }

    @GetMapping
    private List<FourToOneQuestionEntity> getIdsForGame() {
        return fourToOneQuestionRepository.findAll();
    }
}
