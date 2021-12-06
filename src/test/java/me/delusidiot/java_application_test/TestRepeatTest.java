package me.delusidiot.java_application_test;

import me.delusidiot.java_application_test.domain.Study;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TestRepeatTest {

    @RepeatedTest(5)
    void repeatedTest(){
        System.out.println("test");
    }

    @DisplayName("getInfo")
    @RepeatedTest(value = 5, name = "{displayName} - repetition {currentRepetition} of {totalRepetitions}")
    void repeatedTestGetInfo(RepetitionInfo repetitionInfo){
        System.out.println("test" + repetitionInfo.getCurrentRepetition() + ":" +repetitionInfo.getTotalRepetitions());
    }

    @DisplayName("ParameterizedTest")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @ValueSource(strings = {"one", "two", "three"})
    void parameterizedTest(String message){
        System.out.println("message = " + message);
    }

    @DisplayName("ParameterizedTest")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @NullAndEmptySource //composed annotation
    void parameterizedTest2(String message){
        System.out.println("message = " + message);
    }

    @DisplayName("ParameterizedTest")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @ValueSource(ints = {1,2,3})
    void parameterizedTest3(@ConvertWith(StudyConverter.class) Study study){
        System.out.println("study = " + study.getLimit());
    }

    static class StudyConverter extends SimpleArgumentConverter {

        @Override
        protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
            assertEquals(Study.class, targetType, "Can only convert to Study");
            try {
                return new Study(Integer.parseInt(source.toString()));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    @DisplayName("ParameterizedTest")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @CsvSource({"10, 'java'", "20, 'spring'"})
    void parameterizedTest4(Integer limit, String name){
        System.out.println(new Study(name, limit));
    }

    @DisplayName("ParameterizedTest")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @CsvSource({"10, 'java'", "20, 'spring'"})
    void parameterizedTest5(ArgumentsAccessor argumentsAccessor){
        System.out.println(new Study(argumentsAccessor.getString(1), argumentsAccessor.getInteger(0)));
    }


    @DisplayName("ParameterizedTest")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @CsvSource({"10, 'java'", "20, 'spring'"})
    void parameterizedTest6(@AggregateWith(StudyAggregator.class)Study study){
        System.out.println("study = " + study);
    }

    static class StudyAggregator implements ArgumentsAggregator{
        @Override
        public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context) throws ArgumentsAggregationException {
            return new Study(accessor.getString(1), accessor.getInteger(0));
        }
    }
}
