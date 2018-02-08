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

package be.yildizgames.module.sound.dummy;

import be.yildizgames.common.file.ResourcePath;
import be.yildizgames.common.geometry.Point3D;
import be.yildizgames.module.sound.AudioEngine;
import be.yildizgames.module.sound.EndPlayListener;
import be.yildizgames.module.sound.Playlist;
import be.yildizgames.module.sound.SoundSource;

/**
 * Dummy audio engine.
 *
 * @author Grégory Van den Borre
 */
public final class DummyAudioEngine extends AudioEngine {

    @Override
    protected void closeImpl() {
        //Does nothing.
    }

    @Override
    public void update() {
        //Does nothing.
    }

    @Override
    public SoundSource createSound(final String file) {
        return new EmptySoundSource();
    }

    @Override
    public AudioEngine addResourcePath(final ResourcePath path) {
        return this;

    }

    /**
     * Dummy sound source.
     */
    public static final class EmptySoundSource implements SoundSource {
        @Override
        public void play() {
            //Does nothing.
        }

        @Override
        public void addEndPlayListener(EndPlayListener listener) {
            //Does nothing
        }

        @Override
        public void stop() {
            //Does nothing.
        }

        @Override
        public boolean isPlaying() {
            return false;
        }

        @Override
        public void setPosition(Point3D pos) {
            //Does nothing.
        }

        @Override
        public void loop() {
            //Does nothing.
        }

        @Override
        public void rewind() {
            //Does nothing.
        }

        @Override
        public void setGain(float gain) {
            //Does nothing.
        }

        @Override
        public void delete() {
            //Does nothing.
        }
    }

}
