package be.yildiz.module.sound;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

/**
 * @author Grégory Van den Borre
 */
@RunWith(Enclosed.class)
public class MusicTest {

    public static class Constructor {

        @Test
        public void happyFlow() {
            Music m = new Music("testFile", "testName");
            Assert.assertEquals("testFile", m.getFile());
            Assert.assertEquals("testName", m.getName());
        }

        @Test(expected = IllegalArgumentException.class)
        public void withNullFile() {
            new Music(null, "testName");
        }

        @Test(expected = IllegalArgumentException.class)
        public void withNullName() {
            new Music("testFile", null);
        }
    }
}
