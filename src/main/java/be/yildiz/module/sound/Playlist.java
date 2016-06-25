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

import be.yildiz.common.collections.Lists;
import be.yildiz.common.util.BaseRegisterable;
import be.yildiz.common.util.Registerer;

import java.util.List;

/**
 * A container to load and play music files.
 *
 * @author Grégory Van den Borre
 */
public final class Playlist extends BaseRegisterable implements EndPlayListener {

    /**
     * List of all existing play lists.
     */
    private static final Registerer<Playlist> REGISTERER = Registerer.newRegisterer();

    /**
     * List of file to play.
     */
    private final List<Music> musics = Lists.newList();
    /**
     * Create the stream files.
     */
    private final SoundBuilder builder;
    /**
     * Position in the music list of the current playing music.
     */
    private int current;
    /**
     * Currently played music.
     */
    private StreamSource currentStream;

    /**
     * Full constructor.
     *
     * @param name         Play list name, must be unique.
     * @param soundBuilder Constructor to build the audio files.
     */
    public Playlist(final String name, final SoundBuilder soundBuilder) {
        super(name);
        this.builder = soundBuilder;
        Playlist.REGISTERER.register(this);
    }

    /**
     * Retrieve a Playlist from its unique name.
     *
     * @param name Unique material name.
     * @return Found material.
     */
    public static Playlist get(final String name) {
        return Playlist.REGISTERER.get(name);
    }

    /**
     * Stop playing the current music.
     */
    public void stop() {
        if (this.currentStream != null) {
            this.currentStream.stop();
        }
    }

    /**
     * Play the next music in the list.
     */
    public void next() {
        if (this.currentStream != null) {
            this.currentStream.stop();
        }
        if (!this.musics.isEmpty()) {
            this.currentStream = this.builder.createStream(this.musics.get(this.current).getFile());
            this.current++;
            if (this.current == this.musics.size()) {
                this.current = 0;
            }
            this.currentStream.addEndPlayListener(this);
        }
    }

    @Override
    public void soundFinished() {
        this.next();
    }

    /**
     * Add a music to this playlist.
     *
     * @param music Music to add.
     */
    public void addMusic(final Music music) {
        this.musics.add(music);
    }
}
