//        This file is part of the Yildiz-Online project, licenced under the MIT License
//        (MIT)
//
//        Copyright (c) 2016 Grégory Van den Borre
//
//        More infos available: http://yildiz.bitbucket.org
//
//        Permission is hereby granted, free of charge, to any person obtaining a copy
//        of this software and associated documentation files (the "Software"), to deal
//        in the Software without restriction, including without limitation the rights
//        to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//        copies of the Software, and to permit persons to whom the Software is
//        furnished to do so, subject to the following conditions:
//
//        The above copyright notice and this permission notice shall be included in all
//        copies or substantial portions of the Software.
//
//        THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//        IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//        FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//        AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//        LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//        OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
//        SOFTWARE.

package be.yildiz.module.sound;

import be.yildiz.common.gameobject.Movable;
import be.yildiz.common.log.Logger;
import be.yildiz.common.vector.Point3D;

/**
 * SoundEngine behavior.
 *
 * @author Grégory Van den Borre
 */
public abstract class SoundEngine implements AutoCloseable, SoundBuilder {

    /**
     * User listening to the audio, used for audio 3D positioning.
     */
    protected Movable listener = new DefaultListener();
    /**
     * Currently played music.
     */
    private SoundSource musicPlaying = new DummyAudioEngine.EmptySoundSource();

    /**
     * Set a user to be considered as the audio listener.
     *
     * @param user User listener.
     */
    public final void setListener(Movable user) {
        this.listener = user;
    }

    /**
     * Update the current audio state.
     */
    public abstract void update();

    /**
     * Test the audio positioning by playing sound with different positions.
     */
    public final void testAudio() {
        SoundSource source = this.createSound("test.wav");
        this.listener.setPosition(5, 0, 5);
        source.play();
        this.listener.setPosition(-5, 0, 5);
        source.play();
        this.listener.setPosition(5, 0, -5);
        source.play();
        this.listener.setPosition(-5, 0, -5);
        source.play();
    }

    /**
     * Build a play list of music to be played.
     *
     * @param name Play list name.
     * @return The built play list.
     */
    public abstract Playlist createPlaylist(String name);

    /**
     * Add a path to load resources.
     *
     * @param path Path to use.
     */
    public abstract void addResourcePath(String path);

    @Override
    public final void close() {
        Logger.info("Closing audio engine...");
        this.musicPlaying.stop();
        this.closeImpl();
        Logger.info("Audio engine closed.");
    }

    protected abstract void closeImpl();

    /**
     * Empty implementation of a movable.
     *
     * @Author Grégory Van den borre.
     */
    private static final class DefaultListener implements Movable {

        @Override
        public void attachTo(Movable other) {
            //Does nothing.
        }

        @Override
        public void detach(Movable other) {
            //Does nothing.
        }

        @Override
        public void addChild(Movable other) {
            //Does nothing.
        }

        @Override
        public void attachToOptional(Movable other) {
            //Does nothing.
        }

        @Override
        public Point3D getPosition() {
            return Point3D.ZERO;
        }

        @Override
        public void setPosition(Point3D newPosition) {
            //Does nothing.
        }

        @Override
        public Point3D getAbsolutePosition() {
            return this.getPosition();
        }

        @Override
        public void setAbsolutePosition(Point3D pos) {
            //Does nothing.
        }

        @Override
        public Point3D getDirection() {
            return Point3D.BASE_DIRECTION;
        }

        @Override
        public void setDirection(Point3D newDirection) {
            //Does nothing.
        }

        @Override
        public Point3D getAbsoluteDirection() {
            return this.getDirection();
        }

        @Override
        public void delete() {
            //Does nothing.
        }
    }
}
