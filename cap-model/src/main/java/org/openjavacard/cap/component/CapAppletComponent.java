/*
 * openjavacard-tools: Development tools for JavaCard
 * Copyright (C) 2018 Ingo Albrecht <copyright@promovicz.org>
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 */

package org.openjavacard.cap.component;

import org.openjavacard.cap.base.CapComponent;
import org.openjavacard.cap.base.CapStructureReader;
import org.openjavacard.cap.file.CapComponentType;
import org.openjavacard.cap.structure.CapAppletInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;

public class CapAppletComponent extends CapComponent {

    private static final Logger LOG = LoggerFactory.getLogger(CapAppletComponent.class);

    private ArrayList<CapAppletInfo> mApplets;

    public CapAppletComponent() {
        super(CapComponentType.Applet);
    }

    public ArrayList<CapAppletInfo> getApplets() {
        return mApplets;
    }

    @Override
    public void read(CapStructureReader reader) throws IOException {
        int appletCount = reader.readU1();
        LOG.trace("reading " + appletCount + " applets");
        ArrayList<CapAppletInfo> imports = new ArrayList<>();
        for(int i = 0; i < appletCount; i++) {
            imports.add(reader.readStructure(CapAppletInfo.class));
        }
        mApplets = imports;
    }

}
