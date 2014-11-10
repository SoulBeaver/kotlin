/*
 * Copyright 2010-2014 JetBrains s.r.o.
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

package org.jetbrains.jet.plugin.completion

import com.intellij.codeInsight.completion.*
import com.intellij.psi.PsiClass
import org.jetbrains.jet.asJava.KotlinLightClass
import org.jetbrains.jet.lang.descriptors.ClassKind
import org.jetbrains.jet.lang.psi.JetFile
import org.jetbrains.jet.lang.resolve.java.JavaResolverUtils
import org.jetbrains.jet.lang.resolve.lazy.ResolveSessionUtils
import org.jetbrains.jet.lang.resolve.name.FqName
import org.jetbrains.jet.lang.types.lang.KotlinBuiltIns
import org.jetbrains.jet.plugin.caches.JetFromJavaDescriptorHelper
import org.jetbrains.jet.plugin.project.ProjectStructureUtil
import org.jetbrains.jet.plugin.project.ResolveSessionForBodies
import org.jetbrains.jet.plugin.caches.KotlinIndicesHelper
import org.jetbrains.jet.lang.descriptors.DeclarationDescriptor

class AllClassesCompletion(val parameters: CompletionParameters,
                           val resolveSession: ResolveSessionForBodies,
                           val prefixMatcher: PrefixMatcher,
                           val kindFilter: (ClassKind) -> Boolean,
                           val visibilityFilter: (DeclarationDescriptor) -> Boolean) {
    fun collect(result: LookupElementsCollector) {
        val builtIns = KotlinBuiltIns.getInstance().getNonPhysicalClasses().filter { kindFilter(it.getKind()) && prefixMatcher.prefixMatches(it.getName().asString()) }
        result.addDescriptorElements(builtIns, suppressAutoInsertion = true)

        val file = parameters.getOriginalFile()
        val project = file.getProject()
        val searchScope = file.getResolveScope()
        val helper = KotlinIndicesHelper(project, resolveSession, searchScope, visibilityFilter)
        result.addDescriptorElements(helper.getClassDescriptors({ prefixMatcher.prefixMatches(it) }, kindFilter),
                                     suppressAutoInsertion = true)

        if (!ProjectStructureUtil.isJsKotlinModule(file as JetFile)) {
            addAdaptedJavaCompletion(result)
        }
    }

    /**
     * Add java elements with performing conversion to kotlin elements if necessary.
     */
    private fun addAdaptedJavaCompletion(collector: LookupElementsCollector) {
        AllClassesGetter.processJavaClasses(parameters, prefixMatcher, true, { psiClass ->
            if (psiClass!! !is KotlinLightClass) { // Kotlin non-compiled class should have already been added as kotlin element before
                if (JavaResolverUtils.isCompiledKotlinClass(psiClass)) {
                    addLookupElementForCompiledKotlinClass(psiClass, collector)
                }
                else if (!JavaResolverUtils.isCompiledKotlinPackageClass(psiClass)) {
                    val kind = when {
                        psiClass.isAnnotationType() -> ClassKind.ANNOTATION_CLASS
                        psiClass.isInterface() -> ClassKind.TRAIT
                        psiClass.isEnum() -> ClassKind.ENUM_CLASS
                        else -> ClassKind.CLASS
                    }
                    if (kindFilter(kind)) {
                        collector.addElementWithAutoInsertionSuppressed(KotlinLookupElementFactory.createLookupElementForJavaClass(psiClass))
                    }
                }
            }
        })
    }

    private fun addLookupElementForCompiledKotlinClass(aClass: PsiClass, collector: LookupElementsCollector) {
        if (kindFilter(JetFromJavaDescriptorHelper.getCompiledClassKind(aClass))) {
            val qualifiedName = aClass.getQualifiedName()
            if (qualifiedName != null) {
                val descriptors = ResolveSessionUtils.getClassDescriptorsByFqName(resolveSession.getModuleDescriptor(), FqName(qualifiedName)).filter(visibilityFilter)
                collector.addDescriptorElements(descriptors, suppressAutoInsertion = true)
            }
        }
    }
}