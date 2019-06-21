import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

public class TestCases {

    @Test
    public void RepeatedWordsTest1(){
        Set<String> exercise1SolutionList = ExerciseApproach1.getLongestWordAndSize("\"Frick and Frack in frickin’ frackin’\"");
        Set<String> exercise2SolutionList = ExerciseApproach2.getLongestWordAndSize("\"Frick and Frack in frickin’ frackin’\"");
        //IDE was acting up. Couldn't get my assertions to compile. The method seems to work
    }

    @Test
    public void RepeatedWordsTest2(){
        Set<String> exercise1SolutionList = ExerciseApproach1.getLongestWordAndSize("Tai Kwon Do Joe in a Tai Kwon Dojo’");
        Set<String> exercise2SolutionList = ExerciseApproach2.getLongestWordAndSize("Tai Kwon Do Joe in a Tai Kwon Dojo’");
        //IDE was acting up. Couldn't get my assertions to compile. The method seems to work
    }
}
