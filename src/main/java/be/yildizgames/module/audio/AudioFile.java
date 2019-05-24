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

import be.yildizgames.common.file.FileResource;

import java.util.Objects;

/**
 * Audio file, with a path and a type (file, vfs,...)
 *
 * @author Grégory Van den Borre
 */
public class AudioFile {

    /**
     * File type.
     */
    private final FileResource.FileType type;

    /**
     * File path and name.
     */
    public final String name;

    /**
     * Private constructor to prevent instantiation, use static factory methods instead (vfs, file).
     * @param type File type, cannot be null.
     * @param name File path and name, cannot be null.
     */
    private AudioFile(final FileResource.FileType type, final String name) {
        Objects.requireNonNull(name);
        this.type = type;
        this.name = name;
    }

    /**
     * File stored in a VFS.
     *
     * @param name File path in the vfs container, cannot be null.
     *
     * @return The file, never null.
     */
    public static AudioFile vfs(final String name) {
        return new AudioFile(FileResource.FileType.VFS, name);
    }

    /**
     * File stored in a simple file.
     *
     * @param name Physical file path, cannot be null.
     *
     * @return The file, never null.
     */
    public static AudioFile file(final String name) {
        return new AudioFile(FileResource.FileType.FILE, name);
    }

    /**
     * Check if the file is stored in a vfs.
     *
     * @return true if the file is in a VFS, false otherwise.
     */
    public final boolean isVfs() {
        return this.type == FileResource.FileType.VFS;
    }

    /**
     * Check if the file is physical.
     *
     * @return true if the file is physical, false otherwise.
     */
    public final boolean isFile() {
        return this.type == FileResource.FileType.FILE;
    }
}
