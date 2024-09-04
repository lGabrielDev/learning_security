package br.com.lGabrielDev.praticando.errors;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class ErroPadrao {
    //attributes
    String timestamp;
    Integer status;
    String errorMessage;
    
    

    //constructors
    public ErroPadrao(LocalDateTime timestamp, Integer status, String errorMessage ){

        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("yyyy/MM/dd - hh:mm:ss a");
        String dataEHoraFormatadinhos = formatador.format(timestamp);


        this.timestamp = dataEHoraFormatadinhos;
        this.status = status;
        this.errorMessage = errorMessage;
    }



    //getters and setters
    public String getTimestamp() {
        return timestamp;
    }



    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }



    public Integer getStatus() {
        return status;
    }



    public void setStatus(Integer status) {
        this.status = status;
    }



    public String getErrorMessage() {
        return errorMessage;
    }



    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
