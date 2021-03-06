/*
 * This file is part of Infinitest.
 *
 * Copyright (C) 2010
 * "Ben Rady" <benrady@gmail.com>,
 * "Rod Coffin" <rfciii@gmail.com>,
 * "Ryan Breidenbach" <ryan.breidenbach@gmail.com>, et al.
 *
 * Infinitest is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Infinitest is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Infinitest.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.infinitest;

import static java.util.Arrays.*;
import static org.infinitest.util.FakeEnvironments.*;
import static org.infinitest.util.InfinitestTestUtils.*;
import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.infinitest.filter.FilterStub;
import org.infinitest.parser.ClassFileTestDetector;
import org.junit.Test;

import com.fakeco.fakeproduct.TestFakeProduct;

public class WhenSelectingWhichTestsToRun
{
    @Test
    public void shouldIgnoreTestsInDependentProjects()
    {
        List<File> outputDirs = asList(new File("target/classes"));
        List<File> classDirsInClasspath = fakeBuildPaths();
        String rawClasspath = fakeClasspath().getCompleteClasspath();
        StandaloneClasspath classpath = new StandaloneClasspath(outputDirs, classDirsInClasspath, rawClasspath);

        ClassFileTestDetector testDetector = new ClassFileTestDetector(new FilterStub());
        testDetector.setClasspathProvider(classpath);
        File classFileNotInOutputDirectory = getFileForClass(TestFakeProduct.class);
        assertTrue(testDetector.findTestsToRun(asList(classFileNotInOutputDirectory)).isEmpty());
    }
}
