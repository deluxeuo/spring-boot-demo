package io.platformbuilders.springbootdemo.cliente.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>Super classe para as esceções lançados pelo framework.</p>
 *
 * <p>Essa classe prevê um atributo <strong>statusCode</strong> que deve ser definido em suas subclasses e representa
 * o código HTTP a ser repassado ao frontend. O tratamento de erros padrão da classe <strong>RestBase</strong>
 * utiliza esse atributo na preparação do Response retornado ao frontend.</p>
 *
 * @author Vitor Saad
 * @see <a href="https://httpstatuses.com/">https://httpstatuses.com/</a>
 * @since 1.0.0
 */
@Slf4j
@ResponseStatus(value=HttpStatus.UNPROCESSABLE_ENTITY)
public class NegocioException extends GeneralException {

    private static final long serialVersionUID = 7750277264481007108L;

    /**
     * Construtor padrão da exceção.
     * @param statusCode código de erro HTTP
     */
    public NegocioException() {
        super();
    }

    /**
     * Construtor onde pode ser informada a mensagem a ser apresentada.
     * @param statusCode código de erro HTTP
     * @param msg mensagem do erro
     */
    public NegocioException(String msg) {
        super(msg);
    }

    /**
     * Construtor onde pode ser informada a causa da exceção.
     * @param statusCode código de erro HTTP
     * @param cause causa origem da exceção lançada
     */
    public NegocioException(Throwable cause) {
        super(cause);
        log.error("Exception {} has occurred" , new Exception(cause));
    }

    /**
     * Construtor onde pode ser informada a causa e a mensagem da exceção.
     * @param statusCode código de erro HTTP
     * @param msg mensagem do erro
     * @param cause causa origem da exceção lançada
     */
    public NegocioException(String msg, Throwable cause) {
        super(msg, cause);
        log.error("Exception {} has occurred" , new Exception(cause));
    }

}
