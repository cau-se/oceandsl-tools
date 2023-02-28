package org.oceandsl.tools.esm.stages;

import java.io.File;
import java.util.List;
import java.util.Set;

import teetime.stage.basic.AbstractTransformation;

import org.oceandsl.tools.esm.util.FileContents;
import org.oceandsl.tools.esm.util.FileContentsEntry;
import org.oceandsl.tools.esm.util.FileContentsStageOutput;

import cau.agse.hs.staticfortran.model.FortranModule;
import cau.agse.hs.staticfortran.model.FortranProject;

public class FileContentsExtractor extends AbstractTransformation<List<File>, FileContentsStageOutput> {

    @Override
    protected void execute(final List<File> files) throws Exception {
        final FortranProject projectModel = new FortranProject();
        final FileContents fileContents = new FileContents();
        for (final File file : files) {
            projectModel.addModule(file.toPath());
        }
        for (final FortranModule module : projectModel) {
            final FileContentsEntry entry = new FileContentsEntry();
            entry.setFile(module.getModuleName());
            final Set<String> funcs = module.computeFunctionDeclarations();

            for (final String s : funcs) {
                entry.setIdentifier(this.getId());
                entry.setType("FUNCTION");
            }
            final Set<String> subRouts = module.computeSubroutineDeclarations();

            for (final String s : subRouts) {
                entry.setIdentifier(this.getId());
                entry.setType("SUBROUTINE");
            }
            fileContents.add(entry);
        }
        // forward the contents
        this.outputPort.send(new FileContentsStageOutput(fileContents, files));

    }
}
