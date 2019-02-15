package io.toolisticon.jigsawbyap.processor;


import io.toolisticon.annotationprocessortoolkit.tools.corematcher.ValidationMessage;

/**
 * Messages used by annotation processors.
 */
public enum JigsawByApProcessorMessages implements ValidationMessage {


    ERROR_COULD_NOT_FIND_MODULE_FILE("JIGSAW_BY_AP_ERROR_001", "Could not find module files");


    /**
     * the message code.
     */
    private final String code;
    /**
     * the message text.
     */
    private final String message;

    /**
     * Constructor.
     *
     * @param code    the message code
     * @param message the message text
     */
    JigsawByApProcessorMessages(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Gets the code of the message.
     *
     * @return the message code
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Gets the message text.
     *
     * @return the message text
     */
    public String getMessage() {
        return message;
    }



}
