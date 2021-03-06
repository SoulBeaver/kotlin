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

package org.jetbrains.kotlin.idea.highlighter;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.test.InnerTestClasses;
import org.jetbrains.kotlin.test.JUnit3RunnerWithInners;
import org.jetbrains.kotlin.test.JetTestUtils;
import org.jetbrains.kotlin.test.TestMetadata;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.regex.Pattern;

/** This class is generated by {@link org.jetbrains.kotlin.generators.tests.TestsPackage}. DO NOT MODIFY MANUALLY */
@SuppressWarnings("all")
@TestMetadata("idea/testData/diagnosticMessage")
@TestDataPath("$PROJECT_ROOT")
@RunWith(JUnit3RunnerWithInners.class)
public class DiagnosticMessageTestGenerated extends AbstractDiagnosticMessageTest {
    public void testAllFilesPresentInDiagnosticMessage() throws Exception {
        JetTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("idea/testData/diagnosticMessage"), Pattern.compile("^(.+)\\.kt$"), false);
    }

    @TestMetadata("assignedButNeverAccessedVariable.kt")
    public void testAssignedButNeverAccessedVariable() throws Exception {
        String fileName = JetTestUtils.navigationMetadata("idea/testData/diagnosticMessage/assignedButNeverAccessedVariable.kt");
        doTest(fileName);
    }

    @TestMetadata("cannotInferVisibility.kt")
    public void testCannotInferVisibility() throws Exception {
        String fileName = JetTestUtils.navigationMetadata("idea/testData/diagnosticMessage/cannotInferVisibility.kt");
        doTest(fileName);
    }

    @TestMetadata("cannotOverrideInvisibleMember.kt")
    public void testCannotOverrideInvisibleMember() throws Exception {
        String fileName = JetTestUtils.navigationMetadata("idea/testData/diagnosticMessage/cannotOverrideInvisibleMember.kt");
        doTest(fileName);
    }

    @TestMetadata("conflictingOverloadsClass.kt")
    public void testConflictingOverloadsClass() throws Exception {
        String fileName = JetTestUtils.navigationMetadata("idea/testData/diagnosticMessage/conflictingOverloadsClass.kt");
        doTest(fileName);
    }

    @TestMetadata("conflictingOverloadsDefaultPackage.kt")
    public void testConflictingOverloadsDefaultPackage() throws Exception {
        String fileName = JetTestUtils.navigationMetadata("idea/testData/diagnosticMessage/conflictingOverloadsDefaultPackage.kt");
        doTest(fileName);
    }

    @TestMetadata("conflictingSubstitutions.kt")
    public void testConflictingSubstitutions() throws Exception {
        String fileName = JetTestUtils.navigationMetadata("idea/testData/diagnosticMessage/conflictingSubstitutions.kt");
        doTest(fileName);
    }

    @TestMetadata("constructorsRedeclaration.kt")
    public void testConstructorsRedeclaration() throws Exception {
        String fileName = JetTestUtils.navigationMetadata("idea/testData/diagnosticMessage/constructorsRedeclaration.kt");
        doTest(fileName);
    }

    @TestMetadata("constructorsRedeclarationTopLevel.kt")
    public void testConstructorsRedeclarationTopLevel() throws Exception {
        String fileName = JetTestUtils.navigationMetadata("idea/testData/diagnosticMessage/constructorsRedeclarationTopLevel.kt");
        doTest(fileName);
    }

    @TestMetadata("differentNamesForSameParameter.kt")
    public void testDifferentNamesForSameParameter() throws Exception {
        String fileName = JetTestUtils.navigationMetadata("idea/testData/diagnosticMessage/differentNamesForSameParameter.kt");
        doTest(fileName);
    }

    @TestMetadata("extensionInClassReference.kt")
    public void testExtensionInClassReference() throws Exception {
        String fileName = JetTestUtils.navigationMetadata("idea/testData/diagnosticMessage/extensionInClassReference.kt");
        doTest(fileName);
    }

    @TestMetadata("functionPlaceholder.kt")
    public void testFunctionPlaceholder() throws Exception {
        String fileName = JetTestUtils.navigationMetadata("idea/testData/diagnosticMessage/functionPlaceholder.kt");
        doTest(fileName);
    }

    @TestMetadata("inaccessibleOuterClassExpression.kt")
    public void testInaccessibleOuterClassExpression() throws Exception {
        String fileName = JetTestUtils.navigationMetadata("idea/testData/diagnosticMessage/inaccessibleOuterClassExpression.kt");
        doTest(fileName);
    }

    @TestMetadata("invisibleMember.kt")
    public void testInvisibleMember() throws Exception {
        String fileName = JetTestUtils.navigationMetadata("idea/testData/diagnosticMessage/invisibleMember.kt");
        doTest(fileName);
    }

    @TestMetadata("multipleDefaultsFromSupertypes.kt")
    public void testMultipleDefaultsFromSupertypes() throws Exception {
        String fileName = JetTestUtils.navigationMetadata("idea/testData/diagnosticMessage/multipleDefaultsFromSupertypes.kt");
        doTest(fileName);
    }

    @TestMetadata("nameInConstraintIsNotATypeParameter.kt")
    public void testNameInConstraintIsNotATypeParameter() throws Exception {
        String fileName = JetTestUtils.navigationMetadata("idea/testData/diagnosticMessage/nameInConstraintIsNotATypeParameter.kt");
        doTest(fileName);
    }

    @TestMetadata("nestedClassAcessedViaInstanceReference.kt")
    public void testNestedClassAcessedViaInstanceReference() throws Exception {
        String fileName = JetTestUtils.navigationMetadata("idea/testData/diagnosticMessage/nestedClassAcessedViaInstanceReference.kt");
        doTest(fileName);
    }

    @TestMetadata("nestedClassShouldBeQualified.kt")
    public void testNestedClassShouldBeQualified() throws Exception {
        String fileName = JetTestUtils.navigationMetadata("idea/testData/diagnosticMessage/nestedClassShouldBeQualified.kt");
        doTest(fileName);
    }

    @TestMetadata("noneApplicable.kt")
    public void testNoneApplicable() throws Exception {
        String fileName = JetTestUtils.navigationMetadata("idea/testData/diagnosticMessage/noneApplicable.kt");
        doTest(fileName);
    }

    @TestMetadata("noneApplicableGeneric.kt")
    public void testNoneApplicableGeneric() throws Exception {
        String fileName = JetTestUtils.navigationMetadata("idea/testData/diagnosticMessage/noneApplicableGeneric.kt");
        doTest(fileName);
    }

    @TestMetadata("numberValueTypes.kt")
    public void testNumberValueTypes() throws Exception {
        String fileName = JetTestUtils.navigationMetadata("idea/testData/diagnosticMessage/numberValueTypes.kt");
        doTest(fileName);
    }

    @TestMetadata("renderCollectionOfTypes.kt")
    public void testRenderCollectionOfTypes() throws Exception {
        String fileName = JetTestUtils.navigationMetadata("idea/testData/diagnosticMessage/renderCollectionOfTypes.kt");
        doTest(fileName);
    }

    @TestMetadata("typeInferenceCannotCaptureTypes.kt")
    public void testTypeInferenceCannotCaptureTypes() throws Exception {
        String fileName = JetTestUtils.navigationMetadata("idea/testData/diagnosticMessage/typeInferenceCannotCaptureTypes.kt");
        doTest(fileName);
    }

    @TestMetadata("typeInferenceExpectedTypeMismatch.kt")
    public void testTypeInferenceExpectedTypeMismatch() throws Exception {
        String fileName = JetTestUtils.navigationMetadata("idea/testData/diagnosticMessage/typeInferenceExpectedTypeMismatch.kt");
        doTest(fileName);
    }

    @TestMetadata("typeMismatchWithNothing.kt")
    public void testTypeMismatchWithNothing() throws Exception {
        String fileName = JetTestUtils.navigationMetadata("idea/testData/diagnosticMessage/typeMismatchWithNothing.kt");
        doTest(fileName);
    }

    @TestMetadata("typeParameterAsReified.kt")
    public void testTypeParameterAsReified() throws Exception {
        String fileName = JetTestUtils.navigationMetadata("idea/testData/diagnosticMessage/typeParameterAsReified.kt");
        doTest(fileName);
    }

    @TestMetadata("unusedParameter.kt")
    public void testUnusedParameter() throws Exception {
        String fileName = JetTestUtils.navigationMetadata("idea/testData/diagnosticMessage/unusedParameter.kt");
        doTest(fileName);
    }

    @TestMetadata("unusedValue.kt")
    public void testUnusedValue() throws Exception {
        String fileName = JetTestUtils.navigationMetadata("idea/testData/diagnosticMessage/unusedValue.kt");
        doTest(fileName);
    }

    @TestMetadata("unusedVariable.kt")
    public void testUnusedVariable() throws Exception {
        String fileName = JetTestUtils.navigationMetadata("idea/testData/diagnosticMessage/unusedVariable.kt");
        doTest(fileName);
    }

    @TestMetadata("upperBoundViolated.kt")
    public void testUpperBoundViolated() throws Exception {
        String fileName = JetTestUtils.navigationMetadata("idea/testData/diagnosticMessage/upperBoundViolated.kt");
        doTest(fileName);
    }

    @TestMetadata("wrongReturnTypeInImplementation.kt")
    public void testWrongReturnTypeInImplementation() throws Exception {
        String fileName = JetTestUtils.navigationMetadata("idea/testData/diagnosticMessage/wrongReturnTypeInImplementation.kt");
        doTest(fileName);
    }
}
