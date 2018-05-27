package kr.ac.cnu.web.exceptions;

import org.omg.SendingContext.RunTime;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by rokim on 2018. 5. 27..
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class NoLoginException extends RuntimeException {
}
