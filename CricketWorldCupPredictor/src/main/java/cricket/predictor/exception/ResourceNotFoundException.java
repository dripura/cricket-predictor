package cricket.predictor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception{
  
	private static final long serialVersionUID = -2135081256944997434L;

	public ResourceNotFoundException(String message){
        super(message);
    }
}
