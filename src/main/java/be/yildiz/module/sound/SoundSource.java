/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 * Copyright (c) 2017 Grégory Van den Borre
 *
 * More infos available: https://www.yildiz-games.be
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without
 * limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 * OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE  SOFTWARE.
 */

package be.yildiz.module.sound;


import be.yildizgames.common.geometry.Point3D;

/**
 * A simple sound.
 *
 * @author Grégory Van den Borre
 */
public interface SoundSource {

    /**
     * Start playing the source associated to this object.
     */
    void play();

    void addEndPlayListener(EndPlayListener listener);

    /**
     * Stop playing this source.
     */
    void stop();

    /**
     * @return true if the sound is playing or paused, false otherwise.
     */
    boolean isPlaying();

    /**
     * Set the sound position in the 3d space.
     *
     * @param pos New position.
     */
    void setPosition(final Point3D pos);

    /**
     * The sound will be played in loop until stop is called.
     */
    void loop();

    /**
     * Reset the sound at its beginning.
     */
    void rewind();

    void setGain(float gain);

    void delete();

}
