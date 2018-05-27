package kr.ac.cnu.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by rokim on 2018. 5. 26..
 */
@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class NotEnoughBalanceException extends RuntimeException{
}
