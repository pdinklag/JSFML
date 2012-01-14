/*
 * JSFML - A Java binding to the Simple and Fast Multimedia Library (SFML)
 * Copyright (c) 2011 by Patrick Dinklage (pdinklag@googlemail.com)
 *
 * This software is provided 'as-is', without any express or implied warranty.
 * In no event will the authors be held liable for any damages arising from the use of this software.
 *
 * Permission is granted to anyone to use this software for any purpose,
 * including commercial applications, and to alter it and redistribute it freely,
 * subject to the following restrictions:
 *
 * 1. The origin of this software must not be misrepresented;
 * you must not claim that you wrote the original software.
 * If you use this software in a product, an acknowledgment
 * in the product documentation would be appreciated but is not required.
 *
 * 2. Altered source versions must be plainly marked as such,
 * and must not be misrepresented as being the original software.
 *
 * 3. This notice may not be removed or altered from any source distribution.
 */

package org.jsfml;

/**
 * Provides operations on native SFML objects.
 * <p/>
 * Use these methods at your own risk, they <i>will</i> break stuff if you do not know exactly what you are doing.
 */
public final class UnsafeOperations {
    /**
     * Manually sets the pointer to a native SFML object in the JNI heap.
     *
     * @param object The SFML object wrapper.
     * @param ptr    The new SFML object pointer.
     */
    public static void setSFMLObjectPointer(SFMLNativeObject object, long ptr) {
        object.setPointer(ptr);
    }

    /**
     * Flags an SFML object as Java managed or unmanaged. Java managed objects will be destroyed using the
     * <code>nativeDelete</code> method when this object gets finalized.
     *
     * @param object  The SFML object wrapper.
     * @param managed Whether or not this object is managed by JSFML.
     */
    public static void manageSFMLObject(SFMLNativeObject object, boolean managed) {
        object.setJavaManaged(managed);
    }

    private UnsafeOperations() {
    }
}
