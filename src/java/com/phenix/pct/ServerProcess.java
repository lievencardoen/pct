/*
 * Copyright  2000-2004 The Apache Software Foundation
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

import java.io.File;
import java.util.List;

import org.apache.tools.ant.BuildException;

public class ServerProcess extends PCTRun {
    private String activateProc = null;
    private String deactivateProc = null;
    private String connectProc = null;
    private String disconnectProc = null;
    private String startupProc = null;
    private String shutdownProc = null;

    public ServerProcess() {
        super(false, false);
    }

    public void setActivateProc(String activateProc) {
        this.activateProc = activateProc;
    }

    public void setConnectProc(String connectProc) {
        this.connectProc = connectProc;
    }

    public void setDeactivateProc(String deactivateProc) {
        this.deactivateProc = deactivateProc;
    }

    public void setDisconnectProc(String disconnectProc) {
        this.disconnectProc = disconnectProc;
    }

    public void setShutdownProc(String shutdownProc) {
        this.shutdownProc = shutdownProc;
    }

    public void setStartupProc(String startupProc) {
        this.startupProc = startupProc;
    }

    public void setProcedure(String procedure) {
        throw new BuildException("Unknown attribute");
    }

    public void setGraphicalMode(boolean graphMode) {
        throw new BuildException("Unknown attribute");
    }

    public void setDebugPCT(boolean debugPCT) {
        throw new BuildException("Unknown attribute");
    }

    public void setBaseDir(File baseDir) {
        throw new BuildException("Unknown attribute");
    }

    public void setFailOnError(boolean failOnError) {
        throw new BuildException("Unknown attribute");
    }

    public String getActivateProc() {
        return activateProc;
    }

    public String getConnectProc() {
        return connectProc;
    }

    public String getDeactivateProc() {
        return deactivateProc;
    }

    public String getDisconnectProc() {
        return disconnectProc;
    }

    public String getShutdownProc() {
        return shutdownProc;
    }

    public String getStartupProc() {
        return startupProc;
    }

    protected List<String> getCmdLineParameters() {
        List<String> list = super.getCmdLineParameters();

        if (dbConnList != null) {
            for (PCTConnection conn : dbConnList) {
                list.addAll(conn.getConnectParametersList());
            }
        }

        return list;
    }

    /**
     * Get the current propath as a path-separated list
     * 
     * @return String
     */
    public String getPropath() {
        if (propath == null)
            return "";

        StringBuffer propathList = new StringBuffer("");
        String[] lst = propath.list();
        for (int k = 0; k < lst.length; k++) {
            propathList.append(lst[k]);
            if (k < lst.length - 1)
                propathList.append(File.pathSeparatorChar);

        }
        return propathList.toString();
    }
}
