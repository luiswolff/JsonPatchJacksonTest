import static org.junit.Assert.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonpatch.diff.JsonDiff;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestCalculateDiff {

    @Parameter
    public JsonNode source;
    @Parameter(1)
    public JsonNode target;
    @Parameter(2)
    public JsonNode expected;

    @Test
    public void calculateDiff() {
        JsonNode actual = JsonDiff.asJson(source, target);
        assertEquals(expected, actual);
    }

    @Parameters
    public static Collection<Object[]> data() throws IOException {
        return Arrays.asList(new Object[][]{
                {JacksonUtils.read("source.json", JsonNode.class), JacksonUtils.read("diff/added-node.json", JsonNode.class), JacksonUtils.read("diff/expected-added.json", JsonNode.class)}
        });
    }
}
