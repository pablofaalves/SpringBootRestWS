package com.mytaxi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Search criteria has no results...")
public class EmptySearchResultException extends Exception {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

}
