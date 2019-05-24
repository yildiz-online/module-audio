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

import be.yildizgames.common.gameobject.Movable;
import be.yildizgames.common.geometry.Point3D;
import be.yildizgames.common.util.StringUtil;
import be.yildizgames.module.audio.dummy.DummyAudioEngineProvider;

import java.util.Objects;
import java.util.ServiceLoader;

/**
 * Audio Engine behavior.
 *
 * @author Grégory Van den Borre
 */
public abstract class BaseAudioEngine implements AutoCloseable, AudioEngine {

    /**
     * Logger.
     */
    private final System.Logger logger = System.getLogger(AudioFile.class.toString());

    /**
     * Currently played music.
     */
    private SoundSource musicPlaying = new EmptySoundSource();

    /**
     * User listening to the audio, used for audio 3D positioning.
     */
    protected Movable listener = new DefaultListener();

    /**
     * Constructor.
     */
    protected BaseAudioEngine() {
        super();
    }

    /**
     * Create a new instance of an audio engine implemenation.
     *
     * @return The created instance, never null.
     */
    public static BaseAudioEngine getEngine() {
        ServiceLoader<AudioEngineProvider> provider = ServiceLoader.load(AudioEngineProvider.class);
        return provider.findFirst().orElseGet(DummyAudioEngineProvider::new).getAudioEngine();
    }

    /**
     * Set a user to be considered as the audio listener, in most of case, it will be a camera.
     *
     * @param user User listener, cannot be null.
     *
     * @return This object for chaining, never null.
     */
    public final BaseAudioEngine setListener(Movable user) {
        Objects.requireNonNull(user);
        this.listener = user;
        return this;
    }

    /**
     * Test the audio positioning by playing audio with different positions.
     */
    public final void testAudio(String file) {
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

    @Override
    public final Playlist createPlaylist(final String name) {
        return new Playlist(name, this);
    }

    @Override
    public final Playlist createPlaylist() {
        return createPlaylist(StringUtil.buildRandomString("playlist"));
    }

    @Override
    public final void close() {
        this.logger.log(System.Logger.Level.INFO, "Closing audio engine...");
        this.musicPlaying.stop();
        this.closeImpl();
        this.logger.log(System.Logger.Level.INFO,"Audio engine closed.");
    }

    /**
     * Update the current audio state.
     */
    public abstract void update();

    /**
     * Implementation specific close behavior.
     */
    protected abstract void closeImpl();

    /**
     * Empty implementation of a movable.
     *
     * @author Grégory Van den borre.
     */
    private static final class DefaultListener implements Movable {

        @Override
        public void attachTo(Movable other) {
            //Does nothing.
        }

        @Override
        public void addChild(Movable other) {
            //Does nothing.
        }

        @Override
        public void removeChild(Movable child) {
            //does nothing
        }

        @Override
        public Movable getInternal() {
            return this;
        }

        @Override
        public void attachToOptional(Movable other) {
            //Does nothing.
        }

        @Override
        public void detachFromParent() {
            //does nothing
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
        public void setPosition(float posX, float posY, float posZ) {
            //does nothing
        }

        @Override
        public void setDirection(float dirX, float dirY, float dirZ) {
            //does nothing
        }

        @Override
        public void addOptionalChild(Movable child) {
            //does nothing
        }

        @Override
        public void delete() {
            //does nothing
        }
    }
}
