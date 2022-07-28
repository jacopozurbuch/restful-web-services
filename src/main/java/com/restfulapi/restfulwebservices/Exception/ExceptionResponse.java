package com.restfulapi.restfulwebservices.Exception;

import java.util.Date;

public class ExceptionResponse {
    
    private Date date;
    private String message;
    private String details;

    public ExceptionResponse(Date date, String message, String details){
        this.date=date;
        this.message=message;
        this.details=details;
    }

    public Date getDate(){
        return this.date;
    }

    public String getMessage(){
        return this.message;
    }

    public String getDetails(){
        return this.details;
    }


}
