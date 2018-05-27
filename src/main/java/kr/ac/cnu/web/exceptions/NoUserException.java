package kr.ac.cnu.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by rokim on 2018. 5. 28..
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class NoUserException extends RuntimeException {
}
