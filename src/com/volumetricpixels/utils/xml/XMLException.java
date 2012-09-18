package com.volumetricpixels.utils.xml;

public class XMLException extends Exception {
    private static final long serialVersionUID = 6225338821089088109L;

    public XMLException(Throwable e) {
        super(e);
    }

    public XMLException(String e) {
        super(e);
    }

    public XMLException(String e, Throwable e1) {
        super(e, e1);
    }
}
