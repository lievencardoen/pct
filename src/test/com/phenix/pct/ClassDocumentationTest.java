/**
 * Copyright 2005-2017 Riverside Software
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package com.phenix.pct;

import static org.testng.Assert.assertTrue;

import java.io.File;

import org.testng.annotations.Test;

/**
 * Class for testing ClassDocumentation task
 * 
 * @author <a href="mailto:g.querret+PCT@gmail.com">Gilles QUERRET</a>
 */
public class ClassDocumentationTest extends BuildFileTestNg {

    @Test(groups= {"win", "v11"})
    public void test1() {
        configureProject("ClassDocumentation/test1/build.xml");
        executeTarget("test");

        File f1 = new File("ClassDocumentation/test1/doc/eu.rssw.pct.X.xml");
        assertTrue(f1.exists());
        File f2 = new File("ClassDocumentation/test1/doc/eu.rssw.pct.Y.xml");
        assertTrue(f2.exists());
        File f3 = new File("ClassDocumentation/test1/doc/eu.rssw.pct.Z.xml");
        assertTrue(f3.exists());
        File f4 = new File("ClassDocumentation/test1/doc/eu.rssw.pct.A.xml");
        assertTrue(f4.exists());
        File f5 = new File("ClassDocumentation/test1/doc/eu.rssw.pct.B.xml");
        assertTrue(f5.exists());
        File f6 = new File("ClassDocumentation/test1/doc2/dir1/test.p.xml");
        assertTrue(f6.exists());
        File f7 = new File("ClassDocumentation/test1/doc/eu.rssw.pct.TestClass.xml");
        assertTrue(f7.exists());
    }

    @Test(groups= {"v11"})
    public void test2() {
        configureProject("ClassDocumentation/test2/build.xml");
        executeTarget("test");

        File f1 = new File("ClassDocumentation/test2/doc/eu.rssw.pct.X.xml");
        assertTrue(f1.exists());
        File f2 = new File("ClassDocumentation/test2/doc/eu.rssw.pct.Y.xml");
        assertTrue(f2.exists());
        File f3 = new File("ClassDocumentation/test2/doc/eu.rssw.pct.Z.xml");
        assertTrue(f3.exists());
        File f4 = new File("ClassDocumentation/test2/doc/eu.rssw.pct.A.xml");
        assertTrue(f4.exists());
        File f5 = new File("ClassDocumentation/test2/doc/eu.rssw.pct.B.xml");
        assertTrue(f5.exists());
        File f6 = new File("ClassDocumentation/test2/doc/dir1/test.p.xml");
        assertTrue(f6.exists());
    }

    @Test(groups= {"win", "v11"})
    public void test3() {
        configureProject("ClassDocumentation/test3/build.xml");
        executeTarget("test");
    }

    @Test(groups= {"win", "v11"})
    public void test4() {
        configureProject("ClassDocumentation/test4/build.xml");
        executeTarget("test");

        File f1 = new File("ClassDocumentation/test4/doc/TestClass.xml");
        assertTrue(f1.exists());
        File f2 = new File("ClassDocumentation/test4/html/TestClass.html");
        assertTrue(f2.exists());
    }

    @Test(groups= {"win", "v11"})
    public void test5() {
        configureProject("ClassDocumentation/test5/build.xml");
        executeTarget("test");

        File f1 = new File("ClassDocumentation/test5/doc/OpenEdge.Core.InjectABL.InjectionRequest.xml");
        assertTrue(f1.exists());
        File f2 = new File("ClassDocumentation/test5/html/OpenEdge.Core.InjectABL.InjectionRequest.html");
        assertTrue(f2.exists());
    }
}
