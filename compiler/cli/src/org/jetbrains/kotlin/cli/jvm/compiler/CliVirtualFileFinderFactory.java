/*
 * Copyright 2010-2015 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.cli.jvm.compiler;

import com.intellij.psi.search.GlobalSearchScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.kotlin.load.kotlin.VirtualFileFinder;
import org.jetbrains.kotlin.load.kotlin.VirtualFileFinderFactory;

public final class CliVirtualFileFinderFactory implements VirtualFileFinderFactory {
    private final ClassPath classPath;

    public CliVirtualFileFinderFactory(@NotNull ClassPath classpath) {
        this.classPath = classpath;
    }

    @NotNull
    @Override
    public VirtualFileFinder create(@NotNull GlobalSearchScope scope) {
        return new CliVirtualFileFinder(classPath);
    }
}
