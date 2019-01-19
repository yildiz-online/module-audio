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

package be.yildizgames.module.audio.dummy;

import be.yildizgames.common.file.ResourcePath;
import be.yildizgames.module.audio.BaseAudioEngine;
import be.yildizgames.module.audio.EmptySoundSource;
import be.yildizgames.module.audio.SoundSource;

/**
 * Dummy audio engine.
 *
 * @author Grégory Van den Borre
 */
final class DummyAudioEngine extends BaseAudioEngine {

    DummyAudioEngine() {
        super();
    }

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
    public BaseAudioEngine addResourcePath(final ResourcePath path) {
        return this;

    }

}
