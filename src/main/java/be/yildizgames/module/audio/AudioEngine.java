package be.yildizgames.module.audio;

import be.yildizgames.common.file.ResourcePath;

public interface AudioEngine extends SoundBuilder {

    Playlist createPlaylist(String name);

    Playlist createPlaylist();

    /**
     * Add a path to load resources.
     *
     * @param path Path to use.
     * @return This object for chaining.
     */
    AudioEngine addResourcePath(ResourcePath path);
}
