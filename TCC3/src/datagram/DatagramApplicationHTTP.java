/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datagram;

import simulator.DatagramApplication;
import java.io.Serializable;

/**
 *
 * @author Diego Muller
 */
public class DatagramApplicationHTTP extends DatagramApplication implements Serializable, Cloneable {
    
    private String httpHeader;
    
    /**
     *
     */
    public DatagramApplicationHTTP() {

        this.httpHeader = "GET /somedir/page.html HTTP/1.1\n"
                + "Host: www.univates.br\n"
                + "Conection: close\n"
                + "User-agent: Mozilla/20.0\n"
                + "Accept-language: en";
        this.setData("");
    }

    /**
     * Return the protocol name (HTTP)
     *
     * @return the protocol name
     */
    public String datagramApplicationType() {
        return "HTTP";
    }

    /**
     * Return an array with the name of this class parents protocols
     *
     * @return an array of parents protocols
     */
    public String[] getDatagramsParent() {
        return new String[]{"TCP"};
    }

    /**
     * Returns the HTTP header
     *
     * @return the HTTP header
     */
    public String getHTTPHeader() {
        return httpHeader;
    }

    /**
     * Sets the contents of the HTTP header
     *
     * @param httpHeader the contents of the HTTP header
     */
    public void setHTTPHeader(String httpHeader) {
        this.httpHeader = httpHeader;
    }

    /**
     * Returns an DatagramApplication object that represents a response to 
     * the current datagram.
     *
     * @return a response to the current datagram
     */
    @Override
    public DatagramApplication getAnswer() {
        DatagramApplicationHTTP answer = new DatagramApplicationHTTP();
        return answer;
    }

    /**
     * Returns an DatagramApplication Object that represents a automatic 
     * response to the current datagram.
     *
     * @return a DatagramApplication Object with the answer to the current 
     * datagram
     */
    @Override
    public DatagramApplication getAutomaticAnswer() {
        DatagramApplicationHTTP answer = (DatagramApplicationHTTP) getAnswer();

        //if HTTP response returns NULL
        if (this.getHTTPHeader().trim().startsWith("HTTP")) {
            return null;
        } else {
            //takes the first word
            if (this.getHTTPHeader().trim().startsWith("GET")) {
                answer.httpHeader = "HTTP/1.0 200 OK\n"
                        + "Date: Mon, 1 Mar 2010 01:22:22 GMT\n"
                        + "Server: Apache/1.2.5\n"
                        + "Last-Modified: Thu, 30 Oct 2010 10:03:37 GMT\n"
                        + "Etag: \"130offe-81-3883bbe9\"\n"
                        + "Content-Lenght: 129\n"
                        + "Accept-Ranges: bytes\n"
                        + "Connection: close\n"
                        + "Content-Type: text/plain";
            } else {
                answer.httpHeader = "HTTP/1.0 400 Bad Request\n"
                        + "Date: Mon, 1 Mar 2010 01:22:22 GMT\n"
                        + "Server: Apache/1.2.5\n"
                        + "Last-Modified: Thu, 30 Oct 2010 10:03:37 GMT\n"
                        + "Etag: \"130offe-81-3883bbe9\"\n"
                        + "Content-Lenght: 129\n"
                        + "Accept-Ranges: bytes\n"
                        + "Connection: close\n"
                        + "Content-Type: text/plain";
            }
        }

        return answer;
    }

    /**
     * Returns a copy of the object.
     *
     * @return a copy of the object.
     * @throws CloneNotSupportedException
     */
    @Override
    public DatagramApplicationHTTP clone() throws CloneNotSupportedException {
        DatagramApplicationHTTP clone;
        clone = (DatagramApplicationHTTP) super.clone();

        return clone;
    }
}
