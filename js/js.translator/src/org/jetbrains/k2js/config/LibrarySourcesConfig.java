/*
 * Copyright 2010-2012 JetBrains s.r.o.
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

package org.jetbrains.k2js.config;

import com.google.common.collect.Lists;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.*;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jet.lang.psi.JetFile;
import org.jetbrains.k2js.utils.JetFileUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author Pavel Talanov
 */
public class LibrarySourcesConfig extends Config {
    @NotNull
    public static final Key<String> EXTERNAL_MODULE_NAME = Key.create("externalModule");
    @NotNull
    public static final String UNKNOWN_EXTERNAL_MODULE_NAME = "<unknown>";

    @NotNull
    private static final Logger LOG = Logger.getInstance("#org.jetbrains.k2js.config.LibrarySourcesConfig");

    @NotNull
    private final List<String> files;

    private final List<String> moduleDependencies = new ArrayList<String>();

    public LibrarySourcesConfig(
            @NotNull Project project,
            @NotNull String moduleId,
            @NotNull List<String> files,
            @NotNull EcmaVersion ecmaVersion,
            boolean sourcemap
    ) {
        super(project, moduleId, ecmaVersion, sourcemap);
        this.files = files;
    }

    @NotNull
    @Override
    protected List<JetFile> generateLibFiles() {
        if (files.isEmpty()) {
            return Collections.emptyList();
        }

        final List<JetFile> psiFiles = new ArrayList<JetFile>();
        String moduleName = UNKNOWN_EXTERNAL_MODULE_NAME;
        VirtualFileSystem fileSystem = VirtualFileManager.getInstance().getFileSystem(StandardFileSystems.FILE_PROTOCOL);
        final PsiManager psiManager = PsiManager.getInstance(getProject());
        for (String path : files) {
            if (path.charAt(0) == '@') {
                moduleName = path.substring(1);
                moduleDependencies.add(moduleName);
            }
            else if (path.endsWith(".jar") || path.endsWith(".zip")) {
                try {
                    psiFiles.addAll(readZip(path));
                }
                catch (IOException e) {
                    LOG.error(e);
                }
            }
            else {
                VirtualFile file = fileSystem.findFileByPath(path);
                assert file != null;
                if (file.isDirectory()) {
                    final String currentModuleName = moduleName;
                    VfsUtilCore.visitChildrenRecursively(file, new VirtualFileVisitor() {
                        @Override
                        public boolean visitFile(@NotNull VirtualFile file) {
                            if (file.getName().endsWith(".kt")) {
                                addPsiFile(psiFiles, currentModuleName, psiManager, file);
                                return false;
                            }
                            return true;
                        }
                    });
                }
                else {
                    addPsiFile(psiFiles, moduleName, psiManager, file);
                }
            }
        }

        return psiFiles;
    }

    @NotNull
    @Override
    public List<String> getModuleDependencies() {
        return moduleDependencies;
    }

    private static void addPsiFile(
            final List<JetFile> psiFiles,
            final String moduleName,
            final PsiManager psiManager,
            final VirtualFile file
    ) {
            PsiFile psiFile = psiManager.findFile(file);
            assert psiFile != null;
            psiFile.putUserData(EXTERNAL_MODULE_NAME, moduleName);
            psiFiles.add((JetFile) psiFile);
    }

    private List<JetFile> readZip(String file) throws IOException {
        ZipFile zipFile = new ZipFile(file);
        try {
            return traverseArchive(zipFile);
        }
        finally {
            zipFile.close();
        }
    }

    @NotNull
    private List<JetFile> traverseArchive(@NotNull ZipFile file) throws IOException {
        List<JetFile> result = Lists.newArrayList();
        Enumeration<? extends ZipEntry> zipEntries = file.entries();
        while (zipEntries.hasMoreElements()) {
            ZipEntry entry = zipEntries.nextElement();
            if (!entry.isDirectory() && entry.getName().endsWith(".kt")) {
                InputStream stream = file.getInputStream(entry);
                String text = FileUtil.loadTextAndClose(stream);
                JetFile jetFile = JetFileUtils.createPsiFile(entry.getName(), text, getProject());
                jetFile.putUserData(EXTERNAL_MODULE_NAME, UNKNOWN_EXTERNAL_MODULE_NAME);
                result.add(jetFile);
            }
        }
        return result;
    }
}