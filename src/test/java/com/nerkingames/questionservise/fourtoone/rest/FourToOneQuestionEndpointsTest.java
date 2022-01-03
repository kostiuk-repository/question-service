package com.nerkingames.questionservise.fourtoone.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nerkingames.questionservise.QuestionServiseApplication;
import com.nerkingames.questionservise.fourtoone.model.entities.FourToOneQuestionEntity;
import com.nerkingames.questionservise.fourtoone.model.entities.FourToOneQuestionTranslationAnswerEntity;
import com.nerkingames.questionservise.fourtoone.model.entities.FourToOneQuestionTranslationEntity;
import com.nerkingames.questionservise.fourtoone.model.entities.QuestionLanguage;
import com.nerkingames.questionservise.fourtoone.model.repo.FourToOneQuestionRepository;
import com.nerkingames.questionservise.statistic.model.entities.PlayedQuestionStatisticEntity;
import com.nerkingames.questionservise.statistic.model.repo.PlayedQuestionStatisticRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.OffsetDateTime;
import java.util.*;

import static com.nerkingames.questionservise.shared.QuestionDifficulty.EASY;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = QuestionServiseApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.yaml")
@EnableSpringDataWebSupport
class FourToOneQuestionEndpointsTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private FourToOneQuestionRepository fourToOneQuestionRepository;

    @Autowired
    private PlayedQuestionStatisticRepository playedQuestionStatisticRepository;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void shouldFindIdsForGame()
            throws Exception {
        //given
        Set<UUID> personIds = getPersonIds(2);
        createAndSavePQSE_N_Times(personIds, createAndSaveFTQE_N_Times(10), 5);
        //when
        MvcResult mvcResult = mvc.perform(
                get("/question"))
                .andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        System.out.println(contentAsString);
    }

    private Set<UUID> getPersonIds(int i) {
        Set<UUID> pIds = new HashSet<>();
        for (int j = 0; j < i; j++) {
            pIds.add(UUID.randomUUID());
        }
        return pIds;
    }

    private void createAndSavePQSE_N_Times(Set<UUID> personIds, List<FourToOneQuestionEntity> fourToOneQuestionEntities, int saveTimes) {
        for (int i = 0; i < saveTimes; i++) {
            playedQuestionStatisticRepository
                    .save(PlayedQuestionStatisticEntity.builder()
                            .fourToOnePlayedQuestions(Set.of(fourToOneQuestionEntities.get(i)))
                            .answerResult(true)
                            .playerId(personIds.stream().findAny().get())
                            .answerDate(OffsetDateTime.now())
                            .timeForAnswer(22_000L)
                            .build());
        }
    }

    private List<FourToOneQuestionEntity> createAndSaveFTQE_N_Times(int saveTimes) {
        List<FourToOneQuestionEntity> setOfIds = new ArrayList<>();
        for (int i = 0; i < saveTimes; i++) {
            FourToOneQuestionEntity question =
                    fourToOneQuestionRepository.save(
                            FourToOneQuestionEntity.builder()
                                    .questionDifficulty(EASY)
                                    .fourToOneQuestionTranslations(Set.of(
                                            FourToOneQuestionTranslationEntity
                                                    .builder()
                                                    .questionLanguage(QuestionLanguage.EN)
                                                    .question("Test")
                                                    .fourToOneQuestionTranslationAnswer(Set.of(
                                                            FourToOneQuestionTranslationAnswerEntity
                                                                    .builder()
                                                                    .answer("Test")
                                                                    .isCorrect(true)
                                                                    .build()
                                                    ))
                                                    .build()
                                    ))
                                    .build());
            setOfIds.add(question);
        }
        return setOfIds;
    }


}