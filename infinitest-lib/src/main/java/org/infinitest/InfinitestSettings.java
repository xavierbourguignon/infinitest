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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class InfinitestSettings
{
    public static final String IS_ENABLED = "infinitest.isEnabled";
    private final Properties properties;

    public InfinitestSettings(InputStream inStream) throws IOException
    {
        this();
        properties.load(inStream);
    }

    public InfinitestSettings()
    {
        this.properties = new Properties();
        setIsInfinitestEnabled(true);
    }

    public boolean isInfinitestEnabled()
    {
        return Boolean.parseBoolean(properties.getProperty(IS_ENABLED));
    }

    public void saveTo(OutputStream outputStream) throws IOException
    {
        properties.store(outputStream, "");
    }

    public void setIsInfinitestEnabled(boolean enabled)
    {
        properties.setProperty(IS_ENABLED, Boolean.toString(enabled));
    }

}
