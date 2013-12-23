/*
 * Menu.java
 *
 * Created on 13 de Outubro de 2009, 22:28
 */
package simulator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.BindException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego Muller
 */
public class Menu extends javax.swing.JFrame {

    private static Thread mythread;
    private static UnicastRequestThread unicastThread;
    private static ServerSocket serverSocket = null;
    private static Socket connectionSocket = null;
    private static NetMap map;
    private static PacketDetail detail = null;
    private static ArpTable arpTable;
    private static Netstat netstat;
    private static Properties props = new Properties();
    private static File file = new File("init_config.properties");
    private static String mac = "", ip = "", nick = "";
    private static int socketConnectionPort = 7000;
    private static WorkStation myWorkStation;
    private static boolean automaticAnswer = false;
    private static ResourceBundle bundle;

    /**
     * Creates new form Menu
     */
    public Menu() {
        initComponents();

        map = new NetMap();
        arpTable = new ArpTable();
        netstat = new Netstat(map);
        this.setLocationRelativeTo(null);
        this.setExtendedState(MAXIMIZED_BOTH);

        //Checks configuration file
        if (file.exists()) {
            //load default properties
            readPropertiesFile();
        } else {
            nick = JOptionPane.showInputDialog(Config.getInstance().getBundle().getString("Menu.dialog.name"));
            createPropertiesFile(nick, false, Locale.getDefault());
        }

        //load configurations
        Config conf = Config.getInstance();
        bundle = ResourceBundle.getBundle("simulator.Bundle", conf.getLocale());

        jcbmAutomaticAnswer.setSelected(conf.isAutoAnswer());
        defineAutoAnswer(conf.isAutoAnswer());
        if (conf.getLocale().getLanguage().equalsIgnoreCase("pt_BR")) {
            jrbiPortuguese.setSelected(true);
        }
        if (conf.getLocale().getLanguage().equalsIgnoreCase("en")) {
            jrbiEnglish.setSelected(true);
        }

        //try create a socket connection in port 7000
        try {
            serverSocket = new ServerSocket(socketConnectionPort);
        } catch (BindException ex1) {

            //if any error occurs try connect in port 8000 (Secondary Mode)
            try {
                socketConnectionPort = 8000;
                serverSocket = new ServerSocket(socketConnectionPort);
            } catch (BindException ex2) {

                //if any error occurs show errror message and close program
                JOptionPane.showMessageDialog(null, bundle.getString("Menu.message.notPossible"));
                System.exit(0);
                return;
            } catch (Exception e) {
            }

            JOptionPane.showMessageDialog(null, bundle.getString("Menu.message.secundaryMessage"));
        } catch (Exception e) {
        }

        //get local MAC adress
        mac = getMACAddress();

        //try get local IP adress
        try {
            byte[] aux = InetAddress.getLocalHost().getAddress();
            ip = (convertIP(aux));
        } catch (Exception ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }

        //if the IP adress in NULL OR localhost adress
        if (ip.equalsIgnoreCase("127.0.1.1") || ip.equalsIgnoreCase("")) {
            getNetworkInterfacesInformations();
        }

        //save the local informations in the config
        conf.setIp(ip);
        conf.setMac(mac);
        conf.setNick(nick);
        conf.setPort(socketConnectionPort);

        jdpScreen.add(map);

        //add ArpTable in NetMap
        map.setArpTable(arpTable);

        //add ARP table in desktop pane
        jdpScreen.add(arpTable);

        //add Netstat in desktop pane
        jdpScreen.add(netstat);

        //create a Workstation with local information
        myWorkStation = new WorkStation(getIP(), getNick(), getMAC(), socketConnectionPort);

        // Init panel with NetMap
        try {
            map.setIp(ip);
            map.setPort(socketConnectionPort);
            map.setMyWorkStation(myWorkStation);
            map.setSelected(true);
            map.setMaximum(true);
            map.setVisible(true);
            jrmiNetMap.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
        }

        //starts the threads
        startThreads();

        //internacionalizate the components
        internationalization();
    }

    /**
     * Starts the menu threads
     */
    private void startThreads() {
        unicastThread = new UnicastRequestThread(this);
        mythread = new Thread(unicastThread);
        mythread.start();

        MulticastRequestTread multicastThread = new MulticastRequestTread();
        Thread mythread2 = new Thread(multicastThread);
        mythread2.start();
    }

    /**
     * Returns the local address IP
     * 
     * @return the local adress IP
     */
    public static String getIP() {
        return ip;
    }

    /**
     * Returns the local address MAC
     * 
     * @return the local address MAC
     */
    public static String getMAC() {
        return mac;
    }

    /**
     * Returns the local nickanme
     * 
     * @return the local nickanme
     */
    public static String getNick() {
        return nick;
    }

    /**
     * Returns the local port
     * 
     * @return the local port
     */
    public static int getPort() {
        return socketConnectionPort;
    }

    /**
     * Gets the Networ kInterfaces Informations
     */
    public static void getNetworkInterfacesInformations() {
        Enumeration<NetworkInterface> ni;
        try {

            //get all network interfaces
            ni = NetworkInterface.getNetworkInterfaces();
            while (ni.hasMoreElements()) {
                NetworkInterface nextElement = ni.nextElement();
                byte[] macAddressBytes = nextElement.getHardwareAddress();

                //checks if the network interface has a MAC address
                if (macAddressBytes != null && !nextElement.getName().equalsIgnoreCase("lo")) {
                    String macAddress = String.format("%1$02x-%2$02x-%3$02x-%4$02x-%5$02x-%6$02x",
                            macAddressBytes[0], macAddressBytes[1],
                            macAddressBytes[2], macAddressBytes[3],
                            macAddressBytes[4], macAddressBytes[5]).toUpperCase();

                    //get the IPs adress of the interface
                    Enumeration<InetAddress> interfaces = nextElement.getInetAddresses();
                    while (interfaces.hasMoreElements()) {
                        InetAddress adress = interfaces.nextElement();

                        //checks if is a valid IPv4 adress
                        if (isValidIPv4(adress.getHostAddress())) {
                            mac = macAddress;
                            ip = adress.getHostAddress();
                            return;
                        }
                    }
                }
            }
        } catch (SocketException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Validate a IPv4 address
     * 
     * @param ip the IP to validate
     * @return true if the IP is valid or false isn't
     */
    public static boolean isValidIPv4(String ip) {
        if (ip == null || ip.isEmpty()) {
            return false;
        }
        ip = ip.trim();
        if ((ip.length() < 6) & (ip.length() > 15)) {
            return false;
        }

        try {
            Pattern pattern = Pattern.compile("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
            Matcher matcher = pattern.matcher(ip);
            return matcher.matches();
        } catch (PatternSyntaxException ex) {
            return false;
        }
    }

    /**
     * Convert the IP to dot representation
     * 
     * @param ipAddr a IP address in byte array format
     * @return the converted IP
     */
    private static String convertIP(byte[] ipAddr) {
        String ipAddrStr = "";
        for (int i = 0; i < ipAddr.length; i++) {
            if (i > 0) {
                ipAddrStr += ".";
            }
            ipAddrStr += ipAddr[i] & 0xFF;
        }
        return ipAddrStr;
    }
    
    /**
     * Read the propreties file to load the saved settings
     */
    private void readPropertiesFile() {
        // the file is in the same directory as the application
        FileInputStream fis;
        try {
            fis = new FileInputStream(file);
            //reads the data that are in the file
            props.load(fis);
            fis.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        nick = props.getProperty("station.nick");

        //save the proprities in the config
        Config conf = Config.getInstance();
        conf.setAutoAnswer(Boolean.parseBoolean(props.getProperty("station.autoanwser")));
        conf.setLocale(new Locale(props.getProperty("station.locale"), props.getProperty("station.country")));
    }

    /**
     * Save the local settings in a propreties file
     * 
     * @param nick the current nicknanme
     * @param autoanswer the current value of the autoanswer
     * @param locale the current locale
     */
    private void createPropertiesFile(String nick, boolean autoanswer, Locale locale) {
        props.setProperty("station.nick", nick);
        props.setProperty("station.autoanwser", Boolean.toString(autoanswer));
        props.setProperty("station.locale", locale.getLanguage());
        props.setProperty("station.country", locale.getCountry());

        FileOutputStream fos;
        try {
            fos = new FileOutputStream(file);
            //writes data to the file
            props.store(fos, "Default settings of the application");
            fos.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Gets the MAC address of the local Network interface
     * @return 
     */
    private String getMACAddress() {
        InetAddress localHost;
        byte[] macAddressBytes;
        String macAddress = "";

        try {
            localHost = InetAddress.getLocalHost();
            NetworkInterface netInter = NetworkInterface.getByInetAddress(localHost);
            macAddressBytes = netInter.getHardwareAddress();
            macAddress = String.format("%1$02x-%2$02x-%3$02x-%4$02x-%5$02x-%6$02x",
                    macAddressBytes[0], macAddressBytes[1],
                    macAddressBytes[2], macAddressBytes[3],
                    macAddressBytes[4], macAddressBytes[5]).toUpperCase();

        } catch (Exception uhe) {
            System.out.println(uhe.getMessage());
        }

        return macAddress;
    }

    /**
     * Thread to recive multicast requests
     */
    private class MulticastRequestTread implements Runnable {

        public void run() {
            while (true) {
                try {
                    MultiCastServer server = new MultiCastServer();

                    //receive a serialized Workstation
                    DatagramPacket datagram = server.receive();
                    String ip = datagram.getAddress().getHostAddress();
                    try {

                        //try convert a datagram in a Workstatin object
                        WorkStation wks = WorkStation.getObject(datagram.getData());

                        //checks if the recived workstation is not equals to local workstation
                        if (!ip.equalsIgnoreCase(myWorkStation.getIP()) || wks.getPort() != myWorkStation.getPort()) {

                            //checks if the workstation not already exist in the map
                            if (map.addWorkStation(ip, wks.getNick(), wks.getMAC(), wks.getPort())) {
                                //add the workstation in the ARP table
                                arpTable.addWorkStation(ip, wks.getMAC(), wks.getPort());
                            }

                            //answer the workstation with local informations
                            ConnectionSend sends = new ConnectionSend(ip, (Object) "multicastConfirm="
                                    + Menu.getNick() + ";"
                                    + Menu.getMAC() + ";" + myWorkStation.getPort(), wks.getPort());
                            try {
                                sends.start();
                            } catch (Exception e) {
                                System.out.println(e.toString());
                            }
                        }

                    } catch (ClassNotFoundException ex) {
                        System.out.println(ex.toString());
                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (IOException ex) {
                    System.out.println(ex.toString());
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * Thread to recive unicast requests
     */
    private class UnicastRequestThread implements Runnable {

        public Menu menu = null;

        /**
         * Class constructor
         * @param menu a reference to class Menu
         */
        public UnicastRequestThread(Menu menu) {
            this.menu = menu;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    Object object;
                    serverSocket.setReuseAddress(true);
                    connectionSocket = serverSocket.accept();

                    //get a unicast message
                    ObjectInputStream s = new ObjectInputStream(connectionSocket.getInputStream());
                    object = s.readObject();

                    //if the message is a string
                    if (object instanceof String) {
                        String wks = (String) object;

                        if (wks.startsWith("removeWorkStation")) {
                            removeWorkStation(wks);
                        }

                        //if recive the response from multicast request
                        if (wks.startsWith("multicastConfirm")) {
                            multicastConfirm(wks);
                        }

                        if (wks.startsWith("addWorkStation=")) {
                            addWorkStation(wks);
                        }

                        if (wks.length() >= 22) {
                            if (wks.startsWith("addWorkStationConfirm=")) {
                                addWorkStationConfirm(wks);
                            }
                        }
                    } else {

                        //test if the message is a Packet
                        if (netstat.validateReceivedPacket((Packet) object)) {

                            //if the auto ansewr is off
                            if (!automaticAnswer) {

                                //show message packet recive
                                SimulatorNotification not = new SimulatorNotification(Config.getInstance().getBundle().getString("Menu.message.newPacket"));
                                not.setLocationRelativeTo(this.menu);
                                not.showNotification();
                            } else {
                                //create and send auto anser(s)
                                createAutoAnswer((Packet) object);
                            }
                        }
                    }

                    //close socket connection
                    connectionSocket.close();
                }
            } catch (Exception e) {
                //debug error
                System.out.println("Exception " + e.getMessage());
                System.out.println("Exception Class" + e.getClass().getName());
            }
        }

        /**
         * Add the workstation that answered the multicast request
         * 
         * @param wks the workstation informations
         */
        private void multicastConfirm(String wks) {
            String nickname;
            String nrMAC;
            int porta;

            String[] informacoes = wks.split(";");

            nickname = informacoes[0].substring(informacoes[0].indexOf("=") + 1);
            nrMAC = informacoes[1];
            porta = Integer.parseInt(informacoes[2]);

            map.addWorkStation(connectionSocket.getInetAddress().getHostAddress().toString(), nickname, nrMAC, porta);
            arpTable.addWorkStation(connectionSocket.getInetAddress().getHostAddress().toString(), nrMAC, porta);

        }

        /**
         * Add the workstation that sent the unicast request
         * 
         * @param wks the workstation informations
         */
        private void addWorkStation(String wks) {
            String[] informations = wks.split(";");

            //get the informations of remote workstation
            String remoteIP = informations[0].substring(informations[0].indexOf("=") + 1);
            String remotNick = informations[1];
            String remoteMAC = informations[2];
            int remotePort = Integer.parseInt(informations[3]);

            if (!remoteIP.equals(ip) || remotePort != socketConnectionPort) {
                map.addWorkStation(remoteIP, remotNick, remoteMAC, remotePort);
                arpTable.addWorkStation(remoteIP, remoteMAC, remotePort);
            }

            try {
                //send a answer to remote workstation with local informations
                ConnectionSend envio = new ConnectionSend(remoteIP, (Object) "addWorkStationConfirm=" + Menu.getIP() + ";"
                        + Menu.getNick() + ";"
                        + Menu.getMAC() + ";" + Menu.getPort(), remotePort);
                envio.start();

                SimulatorNotification not = new SimulatorNotification(Config.getInstance().getBundle().getString("Menu.message.newStation"));
                not.setLocationRelativeTo(this.menu);
                not.showNotification();
            } catch (Exception e) {
            }
        }

        /**
         * Add the workstation that answered the unicast request
         * 
         * @param wks the workstation informations
         */
        private void addWorkStationConfirm(String wks) {
            String[] informations = wks.split(";");

            //get the informations of remote workstation
            String remoteIP = informations[0].substring(informations[0].indexOf("=") + 1);
            String remotNick = informations[1];
            String remoteMAC = informations[2];
            int remotePort = Integer.parseInt(informations[3]);
            
            map.addWorkStation(remoteIP, remotNick, remoteMAC, remotePort);
            arpTable.addWorkStation(remoteIP, remoteMAC, remotePort);
        }

        private void removeWorkStation(String wks) {
            String[] informacoes = wks.split(";");

            String remoteIP = informacoes[0].substring(informacoes[0].indexOf("=") + 1);
            int remotePort = Integer.parseInt(informacoes[1]);

            SimulatorNotification not = new SimulatorNotification(Config.getInstance().getBundle().getString("Menu.message.stationLost"));
            not.setLocationRelativeTo(this.menu);
            not.showNotification();

            arpTable.removeWorkStation(remoteIP, remotePort);
            map.removeWorkStation(remoteIP, remotePort);
        }
    }
    
    /**
     * Create the packet visualization
     * 
     * @param pk the packet to visualization
     * @param send if is possible sent the packet
     */
    public void createPacketDetail(Packet pk, boolean send) {
        jrmiPacketDetail.setSelected(true);
        detail = new PacketDetail(pk, send);
        jdpScreen.add(detail);
        detail.createPacket();

        try {
            detail.setSelected(true);
            detail.setMaximum(true);
            detail.setVisible(true);
        } catch (java.beans.PropertyVetoException e) {
        }
    }
    
    /**
     * Validate the packet and change the netstat status
     * @param pk the packet to validate
     */
    public void validateSentPacket(Packet pk) {
        netstat.validateSentPacket(pk);
    }

    /**
     * Returns the name of the connection state in the transport layer
     * 
     * @param destinationPort the destination port
     * @param destinationWorkstation the destination workstation
     * @param sourcePort the source port
     * 
     * @return the name of the state of the connection in the source port
     */
    public String getStatePort(int destinationPort, WorkStation destinationWorkstation, int sourcePort) {
        return netstat.getPortStatus(destinationPort, destinationWorkstation, sourcePort);
    }

    /**
     * Create and send the AutoAnswer to packet
     * 
     * @param pk the package that will be answered
     */
    private void createAutoAnswer(Packet pk) {
        try {
            Packet pack = pk;
            ArrayList<DatagramLink> answers = new ArrayList<DatagramLink>();
            ArrayList<DatagramLink> arrayDl;
            ArrayList<DatagramInternet> arrayDi;
            ArrayList<DatagramTransport> arrayDt;
            DatagramApplication da;
            boolean sendAnswer = true;
            
            //if the packet have a datagram link layer
            if (pack.getDatagramLink() != null) {
                
                //get a array with the answers to link layer datagram
                arrayDl = pack.getDatagramLink().getAutomaticAnswer();

                //if this datagram have a valid answer
                if (arrayDl.size() > 0) {
                    
                    for (int countDl = 0; countDl < arrayDl.size(); countDl++) {
                        DatagramLink dl = arrayDl.get(countDl);
                        
                         //if the packet have a datagram internet layer
                        if (pack.getDatagramInternet() != null) {
                            
                            //get a array with the answers to internet layer datagram
                            arrayDi = pack.getDatagramInternet().getAutomaticAnswer();
                            
                             //if this datagram have a valid answer
                            if (arrayDi.size() > 0) {
                               
                                for (int countDi = 0; countDi < arrayDi.size(); countDi++) {
                                    DatagramInternet di = arrayDi.get(countDi);
                                    //encapsulate the internet layer in the link layer
                                    dl.encapsulateData(di);

                                    //if the packet have a datagram transport layer
                                    if (pack.getDatagramTransport() != null) {
                                        DatagramTransport dt = pack.getDatagramTransport();
                                        String lastState;
                                        boolean connectionClosed = false;
                                        
                                        //id the transport protocol establishes connection
                                        if (dt.haveConnectionStates()) {
                                            
                                            //get the current status of protocol in the defined port
                                            lastState = netstat.getPortStatus(dt.getDestinationPort(), pack.getSourceWorkStation(), dt.getSourcePort());
                                            connectionClosed = netstat.isConnectionClosed();
                                        } else {
                                            lastState = "";
                                        }

                                       //if the connection is not close
                                        if (!connectionClosed) {
                                            
                                            //get a array with the answers to tranport layer datagram
                                            arrayDt = pack.getDatagramTransport().getAutomaticAnswer(lastState);
                                            
                                             //if this datagram have a valid answer
                                            if (arrayDt.size() > 0) {
                                                for (int countDt = 0; countDt < arrayDt.size(); countDt++) {
                                                    dt = arrayDt.get(countDt);

                                                    //if no connection was established and received the package on port 80 I change the source port to another free
                                                    if (lastState == null) {
                                                        int sourcePort = dt.getSourcePort();
                                                        Config conf = Config.getInstance();
                                                        //change the port if the connection is estabelished in port 80 and the autoanswer is active
                                                        if (sourcePort == 80 && conf.isAutoAnswer()) {
                                                            
                                                            //find a free port
                                                            sourcePort = netstat.findFreePort();
                                                            //if an available port
                                                            if (sourcePort > 0) {
                                                                //change the source port
                                                                dt.setSourcePort(sourcePort);
                                                            }
                                                        }
                                                    }
                                                    
                                                    //encapsulate the datagram
                                                    di.encapsulateData(dt);
                                                    if (pack.getDatagramApplication() != null) {
                                                        
                                                       //test if the application layer is enabled (in TCP protocol the state must be ESTABLISHED)
                                                        if (dt.isEnableNextPort()) {
                                                            //get the answers to application layer datagram
                                                            da = pack.getDatagramApplication().getAutomaticAnswer();
                                                        } else {
                                                            //copy the datagram applicaion
                                                            da = pack.getDatagramApplication();
                                                        }
                                                        
                                                        //if have 
                                                        if (da != null) {
                                                            //encapsulate de application layer
                                                            dt.encapsulateData(da);
                                                            
                                                            //add a copy of the datagram link in the array
                                                            answers.add(dl.clone());
                                                        } else {
                                                            sendAnswer = false;
                                                        }
                                                    } else {
                                                        answers.add(dl);
                                                    }
                                                }
                                            } else {
                                                sendAnswer = false;
                                            }
                                        } else {
                                            sendAnswer = false;
                                        }
                                    } else {
                                        answers.add(dl);
                                    }
                                }
                            } else {
                                sendAnswer = false;
                            }

                        } else {
                            answers.add(dl);
                        }
                    }
                }
            }
            
            //if have a valid answer to send
            if (sendAnswer) {
                for (int i = 0; i < answers.size(); i++) {
                    //create a new packet
                    Packet answerPack = new Packet(answers.get(i), pack.getDestinationWorkStation(), pack.getSourceWorkStation());
                    //validate the packet and change netstat status
                    validateSentPacket(answerPack);
                    //send the answer
                    ConnectionSend envio = new ConnectionSend(pack.getSourceWorkStation().getIP(), (Object) answerPack, pack.getSourceWorkStation().getPort());
                    envio.start();
                    Thread.sleep(1000);
                }
            } else {
                //refresh the netstat image
                netstat.createStates();
            }
        } catch (Exception e) {
            System.out.println("error: " + e.toString());
        }
    }

    /**
     * Show the packege detail frame
     */
    public void showPacketDetail() {
        try {
            detail.setSelected(true);
            detail.setMaximum(true);
            detail.setVisible(true);
        } catch (Exception e) {
            jrmiNetMap.setSelected(true);
        }
    }

    /**
     * Show the net map frame
     */
    public void showNetMap() {
        try {
            map.setSelected(true);
            map.setMaximum(true);
            map.setVisible(true);
        } catch (Exception e) {
            jrmiPacketDetail.setSelected(true);
        }
    }

    /**
     * Show the ARP table frame
     */
    public void showArpTable() {
        try {
            arpTable.setSelected(true);
            arpTable.setMaximum(true);
            arpTable.setVisible(true);
        } catch (Exception e) {
            jrmiArpTable.setSelected(true);
        }
    }

    /**
     * Show the netstat frame
     */
    public void showNetstat() {
        try {
            netstat.setSelected(true);
            netstat.setMaximum(true);
            netstat.setVisible(true);
        } catch (Exception e) {
            jrmiNetstat.setSelected(true);
        }
    }

    /**
     * internationalization the application
     */
    public final void internationalization() {
        this.translate();
        map.translate();
        arpTable.translate();
        netstat.translate();
        if (detail != null) {
            detail.translate();
        }
    }

    /**
     * Translate the Frame texts
     */
    public void translate() {
        Config conf = Config.getInstance();
        defineAutoAnswer(conf.isAutoAnswer());

        if (socketConnectionPort == 8000) {
            this.setTitle(bundle.getString("Menu.title.secundaryMode"));
        } else {
            this.setTitle(bundle.getString("Menu.title"));
        }

        jlProperties.setText("  MAC: " + getMAC() + "     IP: " + getIP() + "     " + bundle.getString("Menu.text.port") + ":  " + socketConnectionPort + "     Nick: " + getNick());

        jmFile.setText(bundle.getString("Menu.jmFile.text"));
        jmiExit.setText(bundle.getString("Menu.jmiExit.text"));

        jmView.setText(bundle.getString("Menu.jmView.text"));
        jrmiNetMap.setText(bundle.getString("Menu.jrmiNetMap.text"));
        jrmiPacketDetail.setText(bundle.getString("Menu.jrmiPacketDetail.text"));
        jrmiArpTable.setText(bundle.getString("Menu.jrmiArpTable.text"));
        jrmiNetstat.setText(bundle.getString("Menu.jrmiNetstat.text"));

        jmOptions.setText(bundle.getString("Menu.jmOptions.text"));
        jcbmAutomaticAnswer.setText(bundle.getString("Menu.jcbmAutomaticAnswer.text"));
        jmLanguage.setText(bundle.getString("Menu.jmLanguage.text"));
        jrbiPortuguese.setText(bundle.getString("Menu.jrbiPortuguese.text"));
        jrbiEnglish.setText(bundle.getString("Menu.jrbiEnglish.text"));

        jmHelp.setText(bundle.getString("Menu.jmHelp.text"));
        jmiAbout.setText(bundle.getString("Menu.jmiAbout.text"));
    }

    /**
     * Change the status of the auto Answer option
     * 
     * @param autoAnswer the status of the auto Answer
     */
    public final void defineAutoAnswer(boolean autoAnswer) {
        Config conf = Config.getInstance();
        if (autoAnswer) {
            automaticAnswer = true;
            conf.setAutoAnswer(true);
            jlAutomaticAnswer.setText(bundle.getString("Menu.automaticAnswerEnable.text"));
            jlAutomaticAnswer.setForeground(Color.BLUE);
        } else {
            automaticAnswer = false;
            conf.setAutoAnswer(false);
            jlAutomaticAnswer.setText(bundle.getString("Menu.automaticAnswerDisable.text"));
            jlAutomaticAnswer.setForeground(Color.RED);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        bgView = new javax.swing.ButtonGroup();
        bgLanguage = new javax.swing.ButtonGroup();
        jdpScreen = new javax.swing.JDesktopPane();
        jpStatusBar = new javax.swing.JPanel();
        jlProperties = new javax.swing.JLabel();
        jlAutomaticAnswer = new javax.swing.JLabel();
        jmbMenu = new javax.swing.JMenuBar();
        jmFile = new javax.swing.JMenu();
        jSeparator = new javax.swing.JSeparator();
        jmiExit = new javax.swing.JMenuItem();
        jmView = new javax.swing.JMenu();
        jrmiNetMap = new javax.swing.JRadioButtonMenuItem();
        jrmiPacketDetail = new javax.swing.JRadioButtonMenuItem();
        jrmiArpTable = new javax.swing.JRadioButtonMenuItem();
        jrmiNetstat = new javax.swing.JRadioButtonMenuItem();
        jmOptions = new javax.swing.JMenu();
        jcbmAutomaticAnswer = new javax.swing.JCheckBoxMenuItem();
        jmLanguage = new javax.swing.JMenu();
        jrbiPortuguese = new javax.swing.JRadioButtonMenuItem();
        jrbiEnglish = new javax.swing.JRadioButtonMenuItem();
        jmHelp = new javax.swing.JMenu();
        jmiAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("simulator/Bundle"); // NOI18N
        setTitle(bundle.getString("Menu.title")); // NOI18N
        setMinimumSize(new java.awt.Dimension(1000, 800));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                onWindowClosing(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                onWindowClosed(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jdpScreen.setBackground(new java.awt.Color(102, 102, 102));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jdpScreen, gridBagConstraints);

        jpStatusBar.setMaximumSize(new java.awt.Dimension(0, 0));
        jpStatusBar.setPreferredSize(new java.awt.Dimension(0, 0));
        jpStatusBar.setLayout(new java.awt.GridLayout(1, 0));

        jlProperties.setText("jlProperties1");
        jpStatusBar.add(jlProperties);

        jlAutomaticAnswer.setForeground(new java.awt.Color(255, 0, 0));
        jlAutomaticAnswer.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlAutomaticAnswer.setText(bundle.getString("Menu.jlAutomaticAnswer.text")); // NOI18N
        jpStatusBar.add(jlAutomaticAnswer);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jpStatusBar, gridBagConstraints);

        jmFile.setMnemonic('a');
        jmFile.setText(bundle.getString("Menu.jmFile.text")); // NOI18N
        jmFile.setActionCommand(""); // NOI18N
        jmFile.add(jSeparator);

        jmiExit.setMnemonic('i');
        jmiExit.setText(bundle.getString("Menu.jmiExit.text")); // NOI18N
        jmiExit.setActionCommand(""); // NOI18N
        jmiExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiExitActionPerformed(evt);
            }
        });
        jmFile.add(jmiExit);

        jmbMenu.add(jmFile);

        jmView.setText(bundle.getString("Menu.jmView.text")); // NOI18N
        jmView.setActionCommand(""); // NOI18N

        bgView.add(jrmiNetMap);
        jrmiNetMap.setMnemonic('m');
        jrmiNetMap.setText(bundle.getString("Menu.jrmiNetMap.text")); // NOI18N
        jrmiNetMap.setActionCommand(""); // NOI18N
        jrmiNetMap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrmiNetMapActionPerformed(evt);
            }
        });
        jmView.add(jrmiNetMap);

        bgView.add(jrmiPacketDetail);
        jrmiPacketDetail.setMnemonic('d');
        jrmiPacketDetail.setText(bundle.getString("Menu.jrmiPacketDetail.text")); // NOI18N
        jrmiPacketDetail.setActionCommand(""); // NOI18N
        jrmiPacketDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrmiPacketDetailActionPerformed(evt);
            }
        });
        jmView.add(jrmiPacketDetail);

        bgView.add(jrmiArpTable);
        jrmiArpTable.setText(bundle.getString("Menu.jrmiArpTable.text")); // NOI18N
        jrmiArpTable.setActionCommand(""); // NOI18N
        jrmiArpTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewArpTable(evt);
            }
        });
        jmView.add(jrmiArpTable);

        bgView.add(jrmiNetstat);
        jrmiNetstat.setSelected(true);
        jrmiNetstat.setText(bundle.getString("Menu.jrmiNetstat.text")); // NOI18N
        jrmiNetstat.setActionCommand("");
        jrmiNetstat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewNetstat(evt);
            }
        });
        jmView.add(jrmiNetstat);
        jrmiNetstat.getAccessibleContext().setAccessibleName("");

        jmbMenu.add(jmView);

        jmOptions.setText(bundle.getString("Menu.jmOptions.text")); // NOI18N
        jmOptions.setActionCommand(""); // NOI18N

        jcbmAutomaticAnswer.setSelected(true);
        jcbmAutomaticAnswer.setText(bundle.getString("Menu.jcbmAutomaticAnswer.text")); // NOI18N
        jcbmAutomaticAnswer.setActionCommand(""); // NOI18N
        jcbmAutomaticAnswer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setAutoAnswer(evt);
            }
        });
        jmOptions.add(jcbmAutomaticAnswer);

        jmLanguage.setText(bundle.getString("Menu.jmLanguage.text")); // NOI18N
        jmLanguage.setActionCommand(""); // NOI18N

        bgLanguage.add(jrbiPortuguese);
        jrbiPortuguese.setSelected(true);
        jrbiPortuguese.setText(bundle.getString("Menu.jrbiPortuguese.text")); // NOI18N
        jrbiPortuguese.setActionCommand("");
        jrbiPortuguese.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changePortugueseLanguage(evt);
            }
        });
        jmLanguage.add(jrbiPortuguese);

        bgLanguage.add(jrbiEnglish);
        jrbiEnglish.setText(bundle.getString("Menu.jrbiEnglish.text")); // NOI18N
        jrbiEnglish.setActionCommand("");
        jrbiEnglish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeEnglishLanguage(evt);
            }
        });
        jmLanguage.add(jrbiEnglish);

        jmOptions.add(jmLanguage);
        jmLanguage.getAccessibleContext().setAccessibleName(""); // NOI18N

        jmbMenu.add(jmOptions);

        jmHelp.setMnemonic('j');
        jmHelp.setText(bundle.getString("Menu.jmHelp.text")); // NOI18N
        jmHelp.setActionCommand("");

        jmiAbout.setMnemonic('n');
        jmiAbout.setText(bundle.getString("Menu.jmiAbout.text")); // NOI18N
        jmiAbout.setActionCommand("");
        jmiAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiAboutActionPerformed(evt);
            }
        });
        jmHelp.add(jmiAbout);

        jmbMenu.add(jmHelp);

        setJMenuBar(jmbMenu);

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void jrmiNetMapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrmiNetMapActionPerformed
    this.showNetMap();
}//GEN-LAST:event_jrmiNetMapActionPerformed

private void jrmiPacketDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrmiPacketDetailActionPerformed
    this.showPacketDetail();
}//GEN-LAST:event_jrmiPacketDetailActionPerformed

private void jmiAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiAboutActionPerformed
    About info = new About();
    jdpScreen.add(info, BorderLayout.CENTER);
    info.setVisible(true);
}//GEN-LAST:event_jmiAboutActionPerformed

private void jmiExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiExitActionPerformed
    System.exit(0);
}//GEN-LAST:event_jmiExitActionPerformed

    private void viewArpTable(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewArpTable
        // TODO add your handling code here:
        showArpTable();
    }//GEN-LAST:event_viewArpTable

    private void viewNetstat(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewNetstat
        // TODO add your handling code here:
        showNetstat();
    }//GEN-LAST:event_viewNetstat

    private void setAutoAnswer(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setAutoAnswer
        // TODO add your handling code here:
        defineAutoAnswer(jcbmAutomaticAnswer.isSelected());
    }//GEN-LAST:event_setAutoAnswer

    private void changePortugueseLanguage(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changePortugueseLanguage
        // TODO add your handling code here:
        Config conf = Config.getInstance();
        conf.setLocale(new Locale("pt_BR", "BR"));
        bundle = conf.getBundle();
        internationalization();
    }//GEN-LAST:event_changePortugueseLanguage

    private void changeEnglishLanguage(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeEnglishLanguage
        // TODO add your handling code here:
        Config conf = Config.getInstance();
        conf.setLocale(new Locale("en", "US"));
        bundle = conf.getBundle();
        internationalization();
    }//GEN-LAST:event_changeEnglishLanguage

    private void onWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_onWindowClosed
        // TODO add your handling code here:
    }//GEN-LAST:event_onWindowClosed

    private void onWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_onWindowClosing
        // TODO add your handling code here:
        Config conf = Config.getInstance();
        createPropertiesFile(conf.getNick(), conf.isAutoAnswer(), conf.getLocale());
    }//GEN-LAST:event_onWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgLanguage;
    private javax.swing.ButtonGroup bgView;
    private javax.swing.JSeparator jSeparator;
    private javax.swing.JCheckBoxMenuItem jcbmAutomaticAnswer;
    private javax.swing.JDesktopPane jdpScreen;
    private javax.swing.JLabel jlAutomaticAnswer;
    private javax.swing.JLabel jlProperties;
    private javax.swing.JMenu jmFile;
    private javax.swing.JMenu jmHelp;
    private javax.swing.JMenu jmLanguage;
    private javax.swing.JMenu jmOptions;
    private javax.swing.JMenu jmView;
    private javax.swing.JMenuBar jmbMenu;
    private javax.swing.JMenuItem jmiAbout;
    private javax.swing.JMenuItem jmiExit;
    private javax.swing.JPanel jpStatusBar;
    private javax.swing.JRadioButtonMenuItem jrbiEnglish;
    private javax.swing.JRadioButtonMenuItem jrbiPortuguese;
    private javax.swing.JRadioButtonMenuItem jrmiArpTable;
    private javax.swing.JRadioButtonMenuItem jrmiNetMap;
    private javax.swing.JRadioButtonMenuItem jrmiNetstat;
    private javax.swing.JRadioButtonMenuItem jrmiPacketDetail;
    // End of variables declaration//GEN-END:variables
}
