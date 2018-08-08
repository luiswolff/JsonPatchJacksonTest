import static org.junit.Assert.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestApplyJsonPatch {

    @Parameter
    public JsonNode node;
    @Parameter(1)
    public JsonPatch patch;
    @Parameter(2)
    public JsonNode expected;

    @Test
    public void applyPatch() throws JsonPatchException {
        JsonNode actual = patch.apply(node);
        assertEquals(expected, actual);
    }

    @Parameters
    public static Collection<Object[]> data() throws IOException {
        return Arrays.asList(new Object[][]{
                {JacksonUtils.read("source.json", JsonNode.class), JacksonUtils.read("apply/patch-add.json", JsonPatch.class), JacksonUtils.read("apply/expected-add.json", JsonNode.class)},
                {JacksonUtils.read("source.json", JsonNode.class), JacksonUtils.read("apply/patch-replace.json", JsonPatch.class), JacksonUtils.read("apply/expected-replace.json", JsonNode.class)},
                {JacksonUtils.read("source.json", JsonNode.class), JacksonUtils.read("apply/patch-remove.json", JsonPatch.class), JacksonUtils.read("apply/expected-remove.json", JsonNode.class)},
                {JacksonUtils.read("source.json", JsonNode.class), JacksonUtils.read("apply/patch-copy.json", JsonPatch.class), JacksonUtils.read("apply/expected-copy.json", JsonNode.class)},
                {JacksonUtils.read("source.json", JsonNode.class), JacksonUtils.read("apply/patch-move.json", JsonPatch.class), JacksonUtils.read("apply/expected-move.json", JsonNode.class)}
        });
    }

}
