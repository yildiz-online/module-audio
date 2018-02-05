/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 *  Copyright (c) 2018 Grégory Van den Borre
 *
 *  More infos available: https://www.yildiz-games.be
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

package be.yildiz.module.sound;

import be.yildiz.module.sound.dummy.DummyAudioEngine;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Grégory Van den Borre
 */
class PlaylistTest {

    @Nested
    class Constructor {

        @Test
        void happyFlow() {
            new Playlist("any1", new DummyAudioEngine());
        }

        @Test
        void withNullName() {
            assertThrows(AssertionError.class, () -> new Playlist(null, new DummyAudioEngine()));
        }

        @Test
        void withNullSoundBuilder() {
            assertThrows(AssertionError.class, () -> new Playlist("any2", null));
        }
    }

    @Nested
    class Add {

        @Test
        void happyFlow() {
            Playlist p = new Playlist("add-happyFlow", new DummyAudioEngine());
            p.addMusic(new Music("fileTest", "nameTest"));
        }

        @Test
        void withNull() {
            Playlist p = new Playlist("add-withNull", new DummyAudioEngine());
            assertThrows(AssertionError.class, () -> p.addMusic(null));
        }
    }

    @Nested
    class Next {

        @Test
        void happyFlow() {
            Playlist p = new Playlist("next-happyFlow", new DummyAudioEngine());
            p.addMusic(new Music("fileTest", "nameTest"));
            p.next();
        }

        @Test
        void withEmptyList() {
            Playlist p = new Playlist("next-withEmpty", new DummyAudioEngine());
            p.next();
        }
    }

    @Nested
    class Get {

        @Test
        void happyFlow() {
            new Playlist("get-happyFlow", new DummyAudioEngine());
            assertNotNull(Playlist.get("get-happyFlow"));
        }

        @Test
        void withNull() {
            assertThrows(AssertionError.class, () -> Playlist.get(null));
        }

        @Test
        void withNoResult() {
            assertThrows(InvalidParameterException.class, () -> Playlist.get("notExisting"));
        }
    }
}
