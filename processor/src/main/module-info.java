import io.toolisticon.jigsawbyap.processor.JigsawByApProcessor;

import javax.annotation.processing.Processor;

module jigsawqbyap.processor {
    requires jdk.compiler;
    requires jigsawbyap.api;
    requires spiap.api;
    requires annotationprocessor;
    provides Processor with JigsawByApProcessor;

}