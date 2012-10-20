package com.volumetricpixels.utils.jarloader;

import java.io.IOException;

public class JarLoadingFailedException extends IOException {
    private static final long serialVersionUID = 8212103058890337411L;

    public JarLoadingFailedException() {
        super();
    }

    public JarLoadingFailedException(String reason) {
        super(reason);
    }

    public JarLoadingFailedException(Throwable cause) {
        super(cause);
    }

    public JarLoadingFailedException(String reason, Throwable cause) {
        super(reason, cause);
    }
}
