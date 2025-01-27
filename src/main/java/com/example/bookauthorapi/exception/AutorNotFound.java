package com.example.bookauthorapi.exception;

import java.io.Serial;

public class AutorNotFound extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 6769829250639411880L;

    public AutorNotFound() {
        super();
    }


    public AutorNotFound(String s, Throwable cause) {
        super(s, cause);
    }

    public AutorNotFound(Throwable cause) {
        super(cause);
    }

    public AutorNotFound(String s) {
        super(s);
    }

}
