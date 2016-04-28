package com.phenix.pct;

import java.io.File;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.Mapper;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.ResourceCollection;
import org.apache.tools.ant.util.FileNameMapper;

public class CompilationWrapper extends PCT implements IRunAttributes, ICompilationAttributes {
    private GenericExecuteOptions runAttributes;
    private CompilationAttributes compAttributes;
    private Mapper mapperElement;
    private int numThreads;

    public CompilationWrapper() {
        compAttributes = new CompilationAttributes(getProject());
        runAttributes = new GenericExecuteOptions(getProject());
    }

    @Override
    public void execute() throws BuildException {
        if ((numThreads <= 1) && (mapperElement == null)) {
            PCTCompile task = new PCTCompile();
            task.bindToOwner(this);
            if (getDlcHome() != null) {
                task.setDlcHome(getDlcHome());
            }
            task.setRunAttributes(runAttributes);
            task.setCompilationAttributes(compAttributes);
            task.execute();
        } else {
            PCTBgCompile task = new PCTBgCompile();
            task.bindToOwner(this);
            if (getDlcHome() != null) {
                task.setDlcHome(getDlcHome());
            }
            task.setRunAttributes(runAttributes);
            task.setCompilationAttributes(compAttributes);
            task.setMapper(mapperElement);
            if (numThreads > 1) {
                task.setNumThreads(numThreads);
            }
            task.execute();
       }
    }

    // Multi-threading management
    public void setNumThreads(int numThreads) {
        if (numThreads < 1) {
            throw new BuildException("Invalid value for numThreads : " + numThreads);
        }
        this.numThreads = numThreads;
    }

    /**
     * Add a nested filenamemapper.
     * 
     * @param fileNameMapper the mapper to add.
     */
    public void add(FileNameMapper fileNameMapper) {
        createMapper().add(fileNameMapper);
    }

    /**
     * Define the mapper to map source to destination files.
     * 
     * @return a mapper to be configured.
     * @exception BuildException if more than one mapper is defined.
     */
    public Mapper createMapper() throws BuildException {
        if (mapperElement != null) {
            throw new BuildException("Cannot define more than one mapper", getLocation());
        }
        mapperElement = new Mapper(getProject());
        return mapperElement;
    }

    // **************
    // Legacy methods

    @Deprecated
    public void addPCTConnection(PCTConnection dbConn) {
        runAttributes.addDBConnection(dbConn);
    }

    // ******************************
    // ICompilationAttributes methods
    
    @Override
    public void setMinSize(boolean minSize) {
        compAttributes.setMinSize(minSize);
    }

    @Override
    public void setStringXref(boolean stringXref) {
        compAttributes.setStringXref(stringXref);
    }

    @Override
    public void setAppendStringXref(boolean appendStringXref) {
        compAttributes.setAppendStringXref(appendStringXref);
    }

    @Override
    public void setSaveR(boolean saveR) {
        compAttributes.setSaveR(saveR);
    }

    @Override
    public void setForceCompile(boolean forceCompile) {
        compAttributes.setForceCompile(forceCompile);
    }

    @Override
    public void setListing(boolean listing) {
        compAttributes.setListing(listing);
    }

    @Override
    public void setListingSource(String source) {
        compAttributes.setListingSource(source);
    }

    @Override
    public void setIgnoredIncludes(String pattern) {
        compAttributes.setIgnoredIncludes(pattern);
    }

    @Override
    public void setPreprocess(boolean preprocess) {
        compAttributes.setPreprocess(preprocess);
    }

    @Override
    public void setPreprocessDir(File dir) {
        compAttributes.setPreprocessDir(dir);
    }

    @Override
    public void setDebugListing(boolean debugListing) {
        compAttributes.setDebugListing(debugListing);
    }

    @Override
    public void setDebugListingDir(File debugListingDir) {
        compAttributes.setDebugListingDir(debugListingDir);
    }

    @Override
    public void setFlattenDebugListing(boolean flatten) {
        compAttributes.setFlattenDebugListing(flatten);
    }

    @Override
    public void setNoXref(boolean noXref) {
        compAttributes.setNoXref(noXref);
    }

    @Override
    public void setNoParse(boolean noParse) {
        compAttributes.setNoParse(noParse);
    }

    @Override
    public void setMultiCompile(boolean multiCompile) {
        compAttributes.setMultiCompile(multiCompile);
    }

    @Override
    public void setStreamIO(boolean streamIO) {
        compAttributes.setStreamIO(streamIO);
    }

    @Override
    public void setv6Frame(boolean v6Frame) {
        compAttributes.setv6Frame(v6Frame);
    }

    @Override
    public void setKeepXref(boolean keepXref) {
        compAttributes.setKeepXref(keepXref);
    }

    @Override
    public void setXmlXref(boolean xmlXref) {
        compAttributes.setXmlXref(xmlXref);
    }

    @Override
    public void setXRefDir(File xrefDir) {
        compAttributes.setXRefDir(xrefDir);
    }

    @Override
    public void setMD5(boolean md5) {
        compAttributes.setMD5(md5);
    }

    @Override
    public void setRunList(boolean runList) {
        compAttributes.setRunList(runList);
    }

    @Override
    public void setDestDir(File destDir) {
        compAttributes.setDestDir(destDir);
    }

    @Override
    public void setXCode(boolean xcode) {
        compAttributes.setXCode(xcode);
    }

    @Override
    public void setXCodeKey(String xcodeKey) {
        compAttributes.setXCodeKey(xcodeKey);
    }

    @Override
    public void setLanguages(String languages) {
        compAttributes.setLanguages(languages);
    }

    @Override
    public void setTextGrowth(int growthFactor) {
        compAttributes.setTextGrowth(growthFactor);
    }

    @Override
    public void setProgPerc(int progPerc) {
        compAttributes.setProgPerc(progPerc);
    }

    @Override
    public void setRequireFullKeywords(boolean requireFullKeywords) {
        compAttributes.setRequireFullKeywords(requireFullKeywords);
    }

    @Override
    public void setRequireFieldQualifiers(boolean requireFieldQualifiers) {
        compAttributes.setRequireFieldQualifiers(requireFieldQualifiers);
    }

    @Override
    public void setRequireFullNames(boolean requireFullNames) {
        compAttributes.setRequireFullNames(requireFullNames);
    }

    @Override
    public void setStopOnError(boolean stopOnError) {
        compAttributes.setStopOnError(stopOnError);
    }

    @Override
    public void add(ResourceCollection rc) {
        compAttributes.add(rc);
    }

    @Override
    public void addConfiguredOEFileset(OpenEdgeFileSet oefs) {
        compAttributes.add(oefs.getCompilationFileSet(getProject()));
    }

    // End of ICompilationAttributes methods
    // *************************************

    // **********************
    // IRunAttributes methods

    @Override
    public void addDBConnection(PCTConnection dbConn) {
        runAttributes.addDBConnection(dbConn);
    }

    @Override
    public void addDBConnectionSet(DBConnectionSet set) {
        runAttributes.addDBConnectionSet(set);
    }

    @Override
    public void addDBAlias(DBAlias alias) {
        runAttributes.addDBAlias(alias);
    }

    @Override
    public void addOption(PCTRunOption option) {
        runAttributes.addOption(option);
    }

    @Override
    public void addParameter(RunParameter param) {
        runAttributes.addParameter(param);
    }

    @Override
    public void addOutputParameter(OutputParameter param) {
        runAttributes.addOutputParameter(param);
    }

    @Override
    public void addPropath(Path propath) {
        runAttributes.addPropath(propath);
    }

    @Override
    public void setParamFile(File pf) {
        runAttributes.setParamFile(pf);
    }

    @Override
    public void setNumSep(String numsep) {
        runAttributes.setNumSep(numsep);
    }

    @Override
    public void setNumDec(String numdec) {
        runAttributes.setNumDec(numdec);
    }

    @Override
    public void setDebugPCT(boolean debugPCT) {
        runAttributes.setDebugPCT(debugPCT);
    }

    @Override
    public void setCompileUnderscore(boolean compUnderscore) {
        runAttributes.setCompileUnderscore(compUnderscore);
    }

    @Override
    public void setDirSize(int dirSize) {
        runAttributes.setDirSize(dirSize);
    }

    @Override
    public void setGraphicalMode(boolean graphMode) {
        runAttributes.setGraphicalMode(graphMode);
    }

    @Override
    public void setIniFile(File iniFile) {
        runAttributes.setIniFile(iniFile);
    }

    @Override
    public void setCpStream(String cpStream) {
        runAttributes.setCpStream(cpStream);
    }

    @Override
    public void setCpInternal(String cpInternal) {
        runAttributes.setCpInternal(cpInternal);
    }

    @Override
    public void setCpCase(String cpCase) {
        runAttributes.setCpCase(cpCase);
    }

    @Override
    public void setCpColl(String cpColl) {
        runAttributes.setCpColl(cpColl);
    }

    @Override
    public void setInputChars(int inputChars) {
        runAttributes.setInputChars(inputChars);
    }

    @Override
    public void setDateFormat(String dateFormat) {
        runAttributes.setDateFormat(dateFormat);
    }

    @Override
    public void setCenturyYearOffset(int centuryYearOffset) {
        runAttributes.setCenturyYearOffset(centuryYearOffset);
    }

    @Override
    public void setToken(int token) {
        runAttributes.setToken(token);
    }

    @Override
    public void setMaximumMemory(int maximumMemory) {
        runAttributes.setMaximumMemory(maximumMemory);
    }

    @Override
    public void setStackSize(int stackSize) {
        runAttributes.setStackSize(stackSize);
    }

    @Override
    public void setTTBufferSize(int ttBufferSize) {
        runAttributes.setTTBufferSize(ttBufferSize);
    }

    @Override
    public void setMsgBufferSize(int msgBufSize) {
        runAttributes.setMsgBufferSize(msgBufSize);
    }

    @Override
    public void setDebugReady(int debugReady) {
        runAttributes.setDebugReady(debugReady);
    }

    @Override
    public void setTempDir(File tempDir) {
        runAttributes.setTempDir(tempDir);
    }

    @Override
    public void setBaseDir(File baseDir) {
        runAttributes.setBaseDir(baseDir);
    }

    @Override
    public void setResultProperty(String resultProperty) {
        runAttributes.setResultProperty(resultProperty);
    }

    @Override
    public void addProfiler(Profiler profiler) {
        runAttributes.addProfiler(profiler);
    }

    @Override
    public void setFailOnError(boolean failOnError) {
        runAttributes.setFailOnError(failOnError);
    }

    @Override
    public void setAssemblies(File assemblies) {
        runAttributes.setAssemblies(assemblies);
    }

    @Override
    public void setProcedure(String procedure) {
        throw new BuildException("Can't set procedure attribute here");
    }

    @Override
    public void setParameter(String param) {
        throw new BuildException("Can't set parameter attribute here");
    }

    @Override
    public void setRelativePaths(boolean rel) {
        runAttributes.setRelativePaths(rel);
    }

    // End of IRunAttributes methods
    // *****************************

}