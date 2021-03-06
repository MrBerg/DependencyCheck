/*
 * This file is part of dependency-check-core.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright (c) 2012 Jeremy Long. All Rights Reserved.
 */
package org.owasp.dependencycheck.analyzer;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.owasp.dependencycheck.BaseTest;
import org.owasp.dependencycheck.dependency.Dependency;

/**
 *
 * @author Jeremy Long
 */
public class DependencyBundlingAnalyzerTest extends BaseTest {

    /**
     * Test of getName method, of class DependencyBundlingAnalyzer.
     */
    @Test
    public void testGetName() {
        DependencyBundlingAnalyzer instance = new DependencyBundlingAnalyzer();
        String expResult = "Dependency Bundling Analyzer";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAnalysisPhase method, of class DependencyBundlingAnalyzer.
     */
    @Test
    public void testGetAnalysisPhase() {
        DependencyBundlingAnalyzer instance = new DependencyBundlingAnalyzer();
        AnalysisPhase expResult = AnalysisPhase.PRE_FINDING_ANALYSIS;
        AnalysisPhase result = instance.getAnalysisPhase();
        assertEquals(expResult, result);
    }

    /**
     * Test of analyze method, of class DependencyBundlingAnalyzer.
     */
    @Test
    public void testAnalyze() throws Exception {
//        Dependency ignore = null;
//        Engine engine = null;
//        DependencyBundlingAnalyzer instance = new DependencyBundlingAnalyzer();
//        instance.analyze(ignore, engine);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of isCore method, of class DependencyBundlingAnalyzer.
     */
    @Test
    public void testIsCore() {
        Dependency left = new Dependency();
        Dependency right = new Dependency();

        left.setFileName("axis2-kernel-1.4.1.jar");
        right.setFileName("axis2-adb-1.4.1.jar");
        DependencyBundlingAnalyzer instance = new DependencyBundlingAnalyzer();
        boolean expResult = true;
        boolean result = instance.isCore(left, right);
        assertEquals(expResult, result);

        left.setFileName("struts-1.2.7.jar");
        right.setFileName("file.tar.gz\\file.tar\\struts.jar");

        expResult = true;
        result = instance.isCore(left, right);
        assertEquals(expResult, result);
    }

    @Test
    public void testFirstPathIsShortest() {
        DependencyBundlingAnalyzer instance = new DependencyBundlingAnalyzer();

        String left = "./a/c.jar";
        String right = "./d/e/f.jar";
        boolean expResult = true;
        boolean result = instance.firstPathIsShortest(left, right);
        assertEquals(expResult, result);

        left = "./a/b/c.jar";
        right = "./d/e/f.jar";
        expResult = true;
        result = instance.firstPathIsShortest(left, right);
        assertEquals(expResult, result);

        left = "./d/b/c.jar";
        right = "./a/e/f.jar";
        expResult = false;
        result = instance.firstPathIsShortest(left, right);
        assertEquals(expResult, result);

        left = "./a/b/c.jar";
        right = "./d/f.jar";
        expResult = false;
        result = instance.firstPathIsShortest(left, right);
        assertEquals(expResult, result);

        left = "./a/b/c.jar";
        right = "./a/b/c.jar";
        expResult = true;
        result = instance.firstPathIsShortest(left, right);
        assertEquals(expResult, result);

    }

}
