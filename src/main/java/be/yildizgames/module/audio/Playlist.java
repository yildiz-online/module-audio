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

import be.yildizgames.common.util.BaseRegisterable;
import be.yildizgames.common.util.Registerer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A container to load and play music files.
 *
 * @author Grégory Van den Borre
 */
public class Playlist extends BaseRegisterable implements EndPlayListener {

    /**
     * Logger.
     */
    private final System.Logger logger = System.getLogger(Playlist.class.toString());

    /**
     * List of all existing play lists.
     */
    private static final Registerer<Playlist> REGISTERER = Registerer.newRegisterer();

    /**
     * List of file to play.
     */
    private final List<Music> musics = new ArrayList<>();

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
    private SoundSource currentStream = new EmptySoundSource();

    /**
     * Full constructor.
     *
     * @param name         Play list name, must be unique.
     * @param soundBuilder Constructor to build the audio files.
     */
    Playlist(final String name, final SoundBuilder soundBuilder) {
        super(name);
        Objects.requireNonNull(soundBuilder);
        this.builder = soundBuilder;
        Playlist.REGISTERER.register(this);
    }

    /**
     * Retrieve a Playlist from its unique name.
     *
     * @param name Unique playlist name.
     * @return Found playlist.
     */
    public static Playlist get(final String name) {
        Objects.requireNonNull(name);
        return Playlist.REGISTERER.get(name);
    }

    /**
     * Stop playing the current music.
     */
    public final void stop() {
        this.currentStream.stop();
    }

    /**
     * Play the playNext music in the list.
     */
    public final void playNext() {
        this.currentStream.stop();
        if (!this.musics.isEmpty()) {
            try {
                this.currentStream = this.builder.createSound(this.musics.get(this.current).getFile());
                this.currentStream.play();
                this.current++;
                if (this.current == this.musics.size()) {
                    this.current = 0;
                }
                this.currentStream.addEndPlayListener(this);
            } catch (SoundCreationException e) {
                this.logger.log(System.Logger.Level.ERROR, "Error creating audio:", e);
            }
        }
    }

    @Override
    public final void soundFinished() {
        this.playNext();
    }

    /**
     * Add a music to this playlist.
     *
     * @param music Music to add.
     * @return This object for chaining.
     */
    public final Playlist addMusic(final Music music) {
        assert music != null;
        this.musics.add(music);
        return this;
    }
}
