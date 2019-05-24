/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 *  Copyright (c) 2019 Grégory Van den Borre
 *
 *  More infos available: https://engine.yildiz-games.be
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 *  documentation files (the "Software"), to deal in the Software without restriction, including without
 *  limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 *  of the Software, and to permit persons to whom the Software is furnished to do so,
 *  subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all copies or substantial
 *  portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 *  WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 *  OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM,
 *  DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE  SOFTWARE.
 *
 */
package be.yildizgames.module.audio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * @author Grégory Van den Borre
 */
public class AudioFileTest {

    @Nested
    public class Vfs {

        @Test
        public void happyFlow() {
            AudioFile file = AudioFile.vfs("test");
            Assertions.assertEquals("test", file.name);
            Assertions.assertTrue(file.isVfs());
            Assertions.assertFalse(file.isFile());
        }

        @Test
        public void nullName() {
            Assertions.assertThrows(NullPointerException.class, () -> AudioFile.vfs(null));
        }
    }

    @Nested
    public class File {

        @Test
        public void happyFlow() {
            AudioFile file = AudioFile.file("test");
            Assertions.assertEquals("test", file.name);
            Assertions.assertTrue(file.isFile());
            Assertions.assertFalse(file.isVfs());
        }

        @Test
        public void nullName() {
            Assertions.assertThrows(NullPointerException.class, () -> AudioFile.file(null));
        }
    }

}
