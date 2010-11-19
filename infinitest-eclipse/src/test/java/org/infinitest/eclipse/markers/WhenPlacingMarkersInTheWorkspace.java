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
package org.infinitest.eclipse.markers;

import static org.infinitest.util.EventFakeSupport.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.eclipse.core.resources.IWorkspaceRoot;
import org.infinitest.eclipse.workspace.ResourceLookup;
import org.infinitest.testrunner.TestEvent;
import org.junit.Before;
import org.junit.Test;

public class WhenPlacingMarkersInTheWorkspace
{
    private TestEvent event;
    private ResourceLookup lookup;
    private IWorkspaceRoot workspaceRoot;

    @Before
    public void inContext()
    {
        event = createEvent("methodName", "message");
        lookup = mock(ResourceLookup.class);
        workspaceRoot = mock(IWorkspaceRoot.class);
    }

    @Test
    public void shouldAlwaysReturnTheWorkspaceResource()
    {
        when(lookup.workspaceRoot()).thenReturn(workspaceRoot);
        MarkerPlacer placer = new MarkerPlacer(lookup);

        MarkerPlacement placement = placer.findPlacement(event);

        assertSame(workspaceRoot, placement.getResource());
    }
}