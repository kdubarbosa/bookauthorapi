package com.example.bookauthorapi.exception;

import java.io.Serial;

public class LivroNotFound extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 6769829250639411880L;

    public LivroNotFound() {
        super();
    }


    public LivroNotFound(String s, Throwable cause) {
        super(s, cause);
    }

    public LivroNotFound(Throwable cause) {
        super(cause);
    }

    public LivroNotFound(String s) {
        super(s);
    }

}
