/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author Diego Muller
 */
public class WorkStation implements Serializable {

    private String ip;
    private String nick;
    private String mac;
    private int port = 7000;

    /**
     * Create a workstation
     */
    public WorkStation() {
    }
    
    /**
     * Create a workstation
     * 
     * @param ip the IP address
     * @param nick the nickname
     * @param mac the MAC address
     * @param port the connection port
     */
    public WorkStation(String ip, String nick, String mac, int port) {
        this.ip = ip;
        this.nick = nick;
        this.mac = mac;
        this.port = port;
    }

   /**
    * Returns the IP address
    * 
    * @return the IP address
    */
    public String getIP() {
        return ip;
    }

    /**
     * Sets the IP address
     * 
     * @param ip the IP address
     */
    public void setIP(String ip) {
        this.ip = ip;
    }

    /**
     * Returns the nickname
     * 
     * @return the nickname
     */
    public String getNick() {
        return nick;
    }

    /**
     * Sets the nickname
     * 
     * @param nick the nickname
     */
    public void setNick(String nick) {
        this.nick = nick;
    }

    /**
     * Sets the MAC address
     * 
     * @return the MAC
     */
    public String getMAC() {
        return mac;
    }

    /**
     * Sets the MAC address
     * 
     * @param mac the MAC address
     */
    public void setMAC(String mac) {
        this.mac = mac;
    }

    /**
     * @return the port
     */
    public int getPort() {
        return port;
    }

    /**
     * Sets the connection port
     * 
     * @param port the connection port
     */
    public void setPort(int port) {
        this.port = port;
    }
    
    /**
     * Serializate the object
     * 
     * @return a array byte with serialized object
     * 
     * @throws java.io.IOException 
     */
    public byte[] getbytes() throws java.io.IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(this);
        oos.flush();
        oos.close();
        bos.close();
        byte[] data = bos.toByteArray();
        return data;

    }
    
    /**
     * Deserialize the aaray byte in a Workstation object
     * 
     * @param vet a array byte with serialized object
     * @return the Deserialized Workstation
     * 
     * @throws java.io.IOException
     * @throws ClassNotFoundException 
     */
    public static WorkStation getObject(byte[] vet) throws java.io.IOException, ClassNotFoundException {
        WorkStation ret;
        ByteArrayInputStream bos = new ByteArrayInputStream(vet);
        ObjectInputStream ois = new ObjectInputStream(bos);
        ret = (WorkStation) ois.readObject();
        ois.close();
        bos.close();
        
        return ret;
    }
    
    @Override
    public String toString(){
        return this.ip + ":" + this.port + " - " + this.nick;
    }
}
