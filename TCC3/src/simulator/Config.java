/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Cristiano
 */
public class Config {

    private Locale locale;
    private String mac;
    private String ip;
    private String nick;
    private int port;
    private boolean autoAnswer = false;
    private static Config instance = new Config();
    private ResourceBundle bundle;

    /**
     *
     */
    private Config() {
        locale = Locale.getDefault();
        bundle = ResourceBundle.getBundle("simulator.Bundle", locale);
    }

    /**
     * Returns the object instance
     *
     * @return the intance of the object
     */
    public static Config getInstance() {
        return instance;
    }

    /**
     * Sets the current locale
     *
     * @param locale the current locale
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
        bundle = ResourceBundle.getBundle("simulator.Bundle", this.locale);
    }

    /**
     * Returns the current locale
     *
     * @return the current locale
     */
    public Locale getLocale() {
        return locale;
    }

    /**
     * Returns the current MAC adress
     *
     * @return he MAC adress
     */
    public String getMac() {
        return mac;
    }

    /**
     * Sets de current MAC adress
     *
     * @param mac theMAC adress
     */
    public void setMac(String mac) {
        this.mac = mac;
    }

    /**
     * Returns the current IP adress
     *
     * @return the IP adress
     */
    public String getIp() {
        return ip;
    }

    /**
     * Sets de current IP adress
     *
     * @param ip the IP adress
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * Return the current nickname
     *
     * @return the nickname
     */
    public String getNick() {
        return nick;
    }

    /**
     * Sets the current nickname
     *
     * @param nick the nickname
     */
    public void setNick(String nick) {
        this.nick = nick;
    }

    /**
     * Return the current sockets connection port
     *
     * @return the socket connection port
     */
    public int getPort() {
        return port;
    }

    /**
     * Sets the current sockets connection port
     *
     * @param port the socket connection port
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * Returns if the autoanswer is enabled
     *
     * @return the autoanswer status
     */
    public boolean isAutoAnswer() {
        return autoAnswer;
    }

    /**
     * Sets the autoanswer status
     *
     * @param autoAnswer the autoanswer status
     */
    public void setAutoAnswer(boolean autoAnswer) {
        this.autoAnswer = autoAnswer;
    }

    /**
     * Return the current ResourceBundle
     *
     * @return the current ResourceBundle
     */
    public ResourceBundle getBundle() {
        return bundle;
    }

    /**
     * Sets the current ResourceBundle
     *
     * @param bundle the current ResourceBundle
     */
    public void setBundle(ResourceBundle bundle) {
        this.bundle = bundle;
    }
}
