package com.quebic.searchbox.exception;

import java.util.Arrays;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.quebic.searchbox.common.ErrorMessage;

/**
 * 
 * @author Tharanga Thennakoon
 * @see <a href="https://github.com/loviworld/searchbox-core">https://github.com/loviworld/searchbox-core</a>
 *
 */
@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class InputParmsNotFound extends RuntimeException{

	private static final long serialVersionUID = -771085429591457630L;
	
	public InputParmsNotFound(String... parms) {
		super(String.format(ErrorMessage.input_request_parms_not_found, Arrays.asList(parms), 0));
	}

}
