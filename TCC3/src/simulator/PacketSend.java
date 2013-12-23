/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PacketSend.java
 *
 * Created on 11/10/2010, 21:38:29
 */
package simulator;

import java.awt.Container;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeMap;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import javax.swing.JPanel;

/**
 *
 * @author Diego Muller
 */
public class PacketSend extends javax.swing.JFrame {

    private JPanel map;
    private ArrayList<Class> arrayApplication, arrayInternet, arrayLink, arrayTransport;
    private static WorkStation destinationWS;
    private TreeMap<String, Object> workStations = new TreeMap<String, Object>();
    private String messageType;

    /**
     * Creates new form PacketSend
     *
     * @param map a reference to netmap
     * @param workstations a list with all workstations in net map
     *
     * @throws InstantiationException
     */
    public PacketSend(JPanel map, TreeMap<String, Object> workstations) throws InstantiationException {
        this.workStations = workstations;
        this.map = map;
        initComponents();

        //show notice
        messageType = "PacketSend.jlNotice.linkLayerMessage";
        jlNotice.setText(Config.getInstance().getBundle().getString(messageType));

        translate();

        //set this frame on top
        this.setAlwaysOnTop(true);

        //hide internet options
        jlInternet.setVisible(false);
        jcbInternet.setVisible(false);

        //hide tranport option
        jlTransport.setVisible(false);
        jcbTransport.setVisible(false);

        //hide application options
        jlAplication.setVisible(false);
        jcbApplication.setVisible(false);

        //disable craete button
        jbCreate.setEnabled(false);

        //set the package to search the files
        Set<String> packages = new HashSet<String>();
        packages.add("datagram");

        Set<String> locations = new HashSet<String>();

        //scan the datagarm package and read all files
        List<String> classNames = scan(Thread.currentThread().getContextClassLoader(), locations, packages);

        arrayApplication = new ArrayList<Class>();
        arrayInternet = new ArrayList<Class>();
        arrayLink = new ArrayList<Class>();
        arrayTransport = new ArrayList<Class>();

        //
        for (int i = 0; i < classNames.size(); i++) {

            try {
                String className = classNames.get(i);

                //if is a datagram class
                if (className.indexOf("Datagram") >= 0) {

                    //get class name
                    className = className.substring(0, className.indexOf(".class"));
                    className = className.replace('/', '.');

                    //instantiate the class by name
                    Class aux = Class.forName(className);

                    //get parent class
                    Class parentClass = aux.getSuperclass();


                    while (parentClass != null) {

                        //if the super class is 'DatagramApplication', is a application protocol
                        if (parentClass.getName().equalsIgnoreCase("simulator.DatagramApplication")) {
                            if (!arrayApplication.contains(aux)) {
                                arrayApplication.add(aux);
                            }
                            break;
                        }

                        //if the super class is 'DatagramInternet', is a internet protocol
                        if (parentClass.getName().equalsIgnoreCase("simulator.DatagramInternet")) {
                            if (!arrayInternet.contains(aux)) {
                                arrayInternet.add(aux);
                            }
                            break;
                        }

                        //if the super class is 'DatagramLink', is a link protocol
                        if (parentClass.getName().equalsIgnoreCase("simulator.DatagramLink")) {
                            if (!arrayLink.contains(aux)) {
                                arrayLink.add(aux);
                            }
                            break;
                        }

                        //if the super class is 'DatagramTransport', is a internet protocol
                        if (parentClass.getName().equalsIgnoreCase("simulator.DatagramTransport")) {
                            if (!arrayTransport.contains(aux)) {
                                arrayTransport.add(aux);
                            }
                            break;
                        }

                        //get the parent class
                        parentClass = parentClass.getSuperclass();
                    }
                }
            } catch (Exception e) {
                //debug error
                System.out.println(e.toString());
            }
        }

        Collection c = this.workStations.values();

        //obtain an Iterator for Collection
        Iterator itr = c.iterator();

        //add the worlsttions in the jcombobox
        while (itr.hasNext()) {
            WorkStation ws = (WorkStation) itr.next();
            jcbDestination.addItem(ws);
        }


        //add link layer protocols in jcombobox
        jcbLink.addItem(null);
        for (int i = 0; i < arrayLink.size(); i++) {
            try {
                DatagramLink datagrama = ((DatagramLink) arrayLink.get(i).newInstance());
                jcbLink.addItem(datagrama);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(PacketSend.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * Scan the filenames of the application
     * 
     * @param classLoader objdct to dinamic load class
     * @param locations array with file locations to scan
     * @param packages array with packeges to scan
     * 
     * @return a list with the filenames of the application
     */
    public static List<String> scan(ClassLoader classLoader, Set<String> locations, Set<String> packages) {

        List<String> classes = new ArrayList<String>();

        if (!(classLoader instanceof URLClassLoader)) {
            return classes;
        }

        URLClassLoader urlLoader = (URLClassLoader) classLoader;
        URL[] urls = urlLoader.getURLs();

        for (URL url : urls) {
            String path = url.getFile();
            File location;
            try {
                location = new File(url.toURI());
            } catch (URISyntaxException e) {
                return classes;
            }

            // Only process the URL if it matches one of our filter strings  
            if (matchesAny(path, locations)) {
                if (location.isDirectory()) {
                    classes.addAll(getClassesInDirectory(null, location, packages, classes));
                } else {
                    classes.addAll(getClassesInJar(location, packages, classes));
                }
            }
        }

        return classes;
    }

    /**
     * Scan class in directories
     * 
     * @param parent the parent directory
     * @param location array with file locations to scan
     * @param packagePatterns array with packeges to scan
     * @param classes list to save classes names
     * 
     * @return  a list with the filenames in defined package
     */
    public static List<String> getClassesInDirectory(String parent, File location, Set<String> packagePatterns, List<String> classes) {
        File[] files = location.listFiles();
        StringBuilder builder;

        for (File file : files) {
            builder = new StringBuilder(100);
            builder.append(parent).append("/").append(file.getName());
            String packageOrClass = (parent == null ? file.getName() : builder.toString());

            if (file.isDirectory()) {
                getClassesInDirectory(packageOrClass, file, packagePatterns, classes);
            } else if (file.getName().endsWith(".class")) {
                if (matchesAny(packageOrClass, packagePatterns)) {
                    classes.add(packageOrClass);
                }
            }
        }
        return classes;
    }

     /**
     * Scan class in jar file
     * 
     * @param location array with file locations to scan
     * @param packagePatterns array with packeges to scan
     * @param classes list to save classes names
     * 
     * @return  a list with the filenames in defined package
     */
    public static List<String> getClassesInJar(File location, Set<String> packagePatterns, List<String> classes) {

        try {
            JarFile jar = new JarFile(location);
            Enumeration entries = jar.entries();

            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                String name = entry.getName();
                if (!entry.isDirectory() && name.endsWith(".class")) {
                    if (matchesAny(name, packagePatterns)) {
                        classes.add(name);
                    }
                }
            }
        } catch (IOException ioe) {}

        return classes;
    }
    /**
     * Checks if the text is in the array or if the array is empty
     * 
     * @param text the text to searh
     * @param filters the array with filters
     * @return 
     */
    public static boolean matchesAny(String text, Set<String> filters) {
        if (filters == null || filters.isEmpty()) {
            return true;
        }
        for (String filter : filters) {
            if (text.indexOf(filter) != -1) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Returns the destiantion worstation of the packet
     * 
     * @return the destiantion worstation of the packet 
     */
    public static WorkStation getDestinationWorkStation() {
        return destinationWS;
    }

    /**
     * Translate the Frame texts
     */
    public final void translate() {
        ResourceBundle bundle = Config.getInstance().getBundle();

        this.setTitle(bundle.getString("PacketSend.title"));
        jbCancel.setText(bundle.getString("PacketSend.jbCancel.text"));
        jbCreate.setText(bundle.getString("PacketSend.jbCreate.text"));
        jlDestination.setText(bundle.getString("PacketSend.jlDestination.text"));
        jlLink.setText(bundle.getString("PacketSend.jlLink.text"));
        jlInternet.setText(bundle.getString("PacketSend.jlInternet.text"));
        jlTransport.setText(bundle.getString("PacketSend.jlTransport.text"));
        jlAplication.setText(bundle.getString("PacketSend.jlAplication.text"));
        jlNotice.setText(Config.getInstance().getBundle().getString(messageType));
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

        jcbDestination = new javax.swing.JComboBox();
        jcbApplication = new javax.swing.JComboBox();
        jcbTransport = new javax.swing.JComboBox();
        jcbInternet = new javax.swing.JComboBox();
        jcbLink = new javax.swing.JComboBox();
        jbCancel = new javax.swing.JButton();
        jlDestination = new javax.swing.JLabel();
        jlAplication = new javax.swing.JLabel();
        jlTransport = new javax.swing.JLabel();
        jlInternet = new javax.swing.JLabel();
        jlLink = new javax.swing.JLabel();
        jbCreate = new javax.swing.JButton();
        jlNotice = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("simulator/Bundle"); // NOI18N
        setTitle(bundle.getString("PacketSend.title")); // NOI18N
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.9;
        gridBagConstraints.weighty = 0.15;
        gridBagConstraints.insets = new java.awt.Insets(20, 10, 5, 10);
        getContentPane().add(jcbDestination, gridBagConstraints);

        jcbApplication.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeApplicationLayer(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.9;
        gridBagConstraints.weighty = 0.15;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 5, 10);
        getContentPane().add(jcbApplication, gridBagConstraints);

        jcbTransport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeTransportLayer(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.9;
        gridBagConstraints.weighty = 0.15;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 5, 10);
        getContentPane().add(jcbTransport, gridBagConstraints);

        jcbInternet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeInternetLayer(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.9;
        gridBagConstraints.weighty = 0.15;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 5, 10);
        getContentPane().add(jcbInternet, gridBagConstraints);

        jcbLink.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeLinkLayer(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.9;
        gridBagConstraints.weighty = 0.15;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 5, 10);
        getContentPane().add(jcbLink, gridBagConstraints);

        jbCancel.setText(bundle.getString("PacketSend.jbCancel.text")); // NOI18N
        jbCancel.setMaximumSize(new java.awt.Dimension(0, 0));
        jbCancel.setMinimumSize(new java.awt.Dimension(0, 0));
        jbCancel.setPreferredSize(new java.awt.Dimension(0, 0));
        jbCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 88;
        gridBagConstraints.ipady = 23;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 20, 5);
        getContentPane().add(jbCancel, gridBagConstraints);

        jlDestination.setText(bundle.getString("PacketSend.jlDestination.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(20, 10, 0, 0);
        getContentPane().add(jlDestination, gridBagConstraints);

        jlAplication.setText(bundle.getString("PacketSend.jlAplication.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        getContentPane().add(jlAplication, gridBagConstraints);

        jlTransport.setText(bundle.getString("PacketSend.jlTransport.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        getContentPane().add(jlTransport, gridBagConstraints);

        jlInternet.setText(bundle.getString("PacketSend.jlInternet.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        getContentPane().add(jlInternet, gridBagConstraints);

        jlLink.setText(bundle.getString("PacketSend.jlLink.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        getContentPane().add(jlLink, gridBagConstraints);

        jbCreate.setText(bundle.getString("PacketSend.jbCreate.text")); // NOI18N
        jbCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCreateActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 14;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 9, 0, 3);
        getContentPane().add(jbCreate, gridBagConstraints);

        jlNotice.setForeground(new java.awt.Color(255, 0, 51));
        jlNotice.setText("jLabel1");
        jlNotice.setMaximumSize(new java.awt.Dimension(75, 50));
        jlNotice.setPreferredSize(new java.awt.Dimension(300, 75));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        getContentPane().add(jlNotice, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelActionPerformed
        this.dispose();
}//GEN-LAST:event_jbCancelActionPerformed

    private void changeLinkLayer(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeLinkLayer
        // TODO add your handling code here:
        //remove all itens of the jcombobox
        jcbInternet.removeAllItems();
        
        //add a null item
        jcbInternet.addItem(null);
        int childrenLength = 0;
        
        if (jcbLink.getSelectedItem() != null) {
            //get link protocol selected
            DatagramLink dl = (DatagramLink) jcbLink.getSelectedItem();
            
            //interate all internet protocols
            for (int i = 0; i < arrayInternet.size(); i++) {
                try {
                    DatagramInternet di = ((DatagramInternet) arrayInternet.get(i).newInstance());
                    //get protocols parents
                    String[] datagramsParent = di.getDatagramsParent();
                    if (datagramsParent != null) {
                        for (int x = 0; x < datagramsParent.length; x++) {
                            //if the selected link protols is parent of the internet protocol, add the internet protocol in the jcombobox
                            if (datagramsParent[x].equalsIgnoreCase(dl.datagramLinkType())) {
                                jcbInternet.addItem(di);
                                childrenLength++;
                            }
                        }
                    }
                } catch (Exception ex) {
                    Logger.getLogger(PacketSend.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            //if the selected protocol have children disable crate button and forces to selected a internet protocol
            if (childrenLength > 0) {
                jcbInternet.setVisible(true);
                jlInternet.setVisible(true);
                //disable create button
                jbCreate.setEnabled(false);
                //show notice 'select a internet protocol'
                jlNotice.setVisible(true);
                messageType = "PacketSend.jlNotice.internetLayerMessage";
                jlNotice.setText(Config.getInstance().getBundle().getString(messageType));
            } else {
                //enable create button and hide notices
                jcbInternet.setVisible(false);
                jlInternet.setVisible(false);
                jbCreate.setEnabled(true);
                jlNotice.setVisible(false);
            }
        } else {
            //if no link protocol selected disable crate button
            jcbInternet.setVisible(false);
            jlInternet.setVisible(false);
            
             //show notice 'select a link protocol'
            jlNotice.setVisible(true);
            messageType = "PacketSend.jlNotice.linkLayerMessage";
            jlNotice.setText(Config.getInstance().getBundle().getString(messageType));
        }
    }//GEN-LAST:event_changeLinkLayer

    private void changeInternetLayer(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeInternetLayer
        // TODO add your handling code here:
        //remove all itens of the jcombobox
        jcbTransport.removeAllItems();
        
        //add a null item
        jcbTransport.addItem(null);
        int childrenLength = 0;
        if (jcbInternet.getSelectedItem() != null) {
            //get internet protocol selected
            DatagramInternet di = (DatagramInternet) jcbInternet.getSelectedItem();
            
            //interate all tranport protocols
            for (int i = 0; i < arrayTransport.size(); i++) {
                try {
                    DatagramTransport dt = ((DatagramTransport) arrayTransport.get(i).newInstance());
                    //get protocols parents
                    String[] datagramsParent = dt.getDatagramsParent();
                    if (datagramsParent != null) {
                        for (int x = 0; x < datagramsParent.length; x++) {
                            //if the selected internet protols is parent of the tranport protocol, add the tranport protocol in the jcombobox
                            if (datagramsParent[x].equalsIgnoreCase(di.datagramInternetType())) {
                                jcbTransport.addItem(dt);
                                childrenLength++;
                            }
                        }
                    }
                } catch (Exception ex) {
                    Logger.getLogger(PacketSend.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            //if the selected protocol have children disable crate button and forces to selected a tranport protocol
            if (childrenLength > 0) {
                jcbTransport.setVisible(true);
                jlTransport.setVisible(true);
                //disable create button
                jbCreate.setEnabled(false);
                
                //show notice 'select a tranport protocol'
                jlNotice.setVisible(true);
                messageType = "PacketSend.jlNotice.transportLayerMessage";
                jlNotice.setText(Config.getInstance().getBundle().getString(messageType));
            } else {
                //enable create button and hide notices
                jcbTransport.setVisible(false);
                jlTransport.setVisible(false);
                jbCreate.setEnabled(true);
                jlNotice.setVisible(false);
            }
        } else {
            //if no internet protocol selected disable crate button
            jcbTransport.setVisible(false);
            jlTransport.setVisible(false);
            
             //show notice 'select a internet protocol'
            jlNotice.setVisible(true);
            messageType = "PacketSend.jlNotice.internetLayerMessage";
            jlNotice.setText(Config.getInstance().getBundle().getString(messageType));
        }
    }//GEN-LAST:event_changeInternetLayer

    private void changeTransportLayer(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeTransportLayer
        // TODO add your handling code here:
        //remove all itens of the jcombobox
        jcbApplication.removeAllItems();
        //add a null item
        jcbApplication.addItem(null);
        int childrenLength = 0;
        if (jcbTransport.getSelectedItem() != null) {
            
            //get transport protocol selected
            DatagramTransport dt = (DatagramTransport) jcbTransport.getSelectedItem();
            
             //interate all tranport protocols
            for (int i = 0; i < arrayApplication.size(); i++) {
                try {
                    DatagramApplication da = ((DatagramApplication) arrayApplication.get(i).newInstance());
                    String[] datagramsParent = da.getDatagramsParent();
                    if (datagramsParent != null) {
                        for (int x = 0; x < datagramsParent.length; x++) {
                            
                            //if the selected tranport protols is parent of the application protocol, add the tranport protocol in the jcombobox
                            if (datagramsParent[x].equalsIgnoreCase(dt.datagramTransportType())) {
                                jcbApplication.addItem(da);
                                childrenLength++;
                            }
                        }
                    }
                } catch (Exception ex) {
                    Logger.getLogger(PacketSend.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            //if the selected protocol have children disable crate button and forces to selected a application protocol
            if (childrenLength > 0) {
                jcbApplication.setVisible(true);
                jlAplication.setVisible(true);
                //disable create button
                jbCreate.setEnabled(false);
                
                //show notice 'select a application protocol'
                jlNotice.setVisible(true);
                messageType = "PacketSend.jlNotice.aplicationLayerMessage";
                jlNotice.setText(Config.getInstance().getBundle().getString(messageType));
            } else {
                //enable create button and hide notices
                jcbApplication.setVisible(false);
                jlAplication.setVisible(false);
                jbCreate.setEnabled(true);
                jlNotice.setVisible(false);
            }
        } else {
            //if no tranport protocol selected disable crate button
            jcbApplication.setVisible(false);
            jlAplication.setVisible(false);
            
            //show notice 'select a tranport protocol'
            jlNotice.setVisible(true);
            messageType = "PacketSend.jlNotice.transportLayerMessage";
            jlNotice.setText(Config.getInstance().getBundle().getString(messageType));
        }
    }//GEN-LAST:event_changeTransportLayer

    private void jbCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCreateActionPerformed
        // TODO add your handling code here:
        destinationWS = (WorkStation) jcbDestination.getSelectedItem();

        String application;
        String transport;
        String internet;
        String link;

        DatagramApplication da;
        DatagramTransport dt = null;
        DatagramInternet di = null;
        DatagramLink dl = null;
        
        //create a datagram link
        if (jcbLink.getSelectedItem() != null) {
            link = "datagram.DatagramLink" + jcbLink.getSelectedItem().toString();
            try {
                dl = (DatagramLink) Class.forName(link).newInstance();
            } catch (Exception ex) {
                Logger.getLogger(PacketSend.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        //create a datagram internet
        if (jcbInternet.getSelectedItem() != null) {
            internet = "datagram.DatagramInternet" + jcbInternet.getSelectedItem().toString();
            try {
                di = (DatagramInternet) Class.forName(internet).newInstance();
                dl.encapsulateData(di);
            } catch (Exception ex) {
                Logger.getLogger(PacketSend.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        //create a datagram tranport
        if (jcbTransport.getSelectedItem() != null) {
            transport = "datagram.DatagramTransport" + jcbTransport.getSelectedItem().toString();
            try {
                dt = (DatagramTransport) Class.forName(transport).newInstance();
                di.encapsulateData(dt);
            } catch (Exception ex) {
                Logger.getLogger(PacketSend.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        //create a datagram application
        if (jcbApplication.getSelectedItem() != null) {
            application = "datagram.DatagramApplication" + jcbApplication.getSelectedItem().toString();
            try {
                da = (DatagramApplication) Class.forName(application).newInstance();
                dt.encapsulateData(da);
            } catch (Exception ex) {
                Logger.getLogger(PacketSend.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        Container pai = this.map.getParent();
        while (!(pai instanceof Menu)) {
            pai = pai.getParent();
        }
        
        //create a workstation with local informations
        Config conf = Config.getInstance();
        WorkStation sourceWorkstation = new WorkStation(conf.getIp(), conf.getNick(), conf.getMac(), conf.getPort());
        
        //create a new packet
        Packet pack = new Packet(dl, sourceWorkstation, destinationWS);
        
        //dispaly the packet details
        ((Menu) pai).createPacketDetail(pack, true);
        
        //close this frame
        this.dispose();
    }//GEN-LAST:event_jbCreateActionPerformed

    private void changeApplicationLayer(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeApplicationLayer
        // TODO add your handling code here:
        if (jcbApplication.getSelectedItem() != null) {
            //if application protocol selected enable crate button
            jbCreate.setEnabled(true);
            jlNotice.setVisible(false);
        } else {
            //else disable crate button ando show notice
            jbCreate.setEnabled(false);
            jlNotice.setVisible(true);
            messageType = "PacketSend.jlNotice.aplicationLayerMessage";
            jlNotice.setText(Config.getInstance().getBundle().getString(messageType));
        }
    }//GEN-LAST:event_changeApplicationLayer
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbCancel;
    private javax.swing.JButton jbCreate;
    private javax.swing.JComboBox jcbApplication;
    private javax.swing.JComboBox jcbDestination;
    private javax.swing.JComboBox jcbInternet;
    private javax.swing.JComboBox jcbLink;
    private javax.swing.JComboBox jcbTransport;
    private javax.swing.JLabel jlAplication;
    private javax.swing.JLabel jlDestination;
    private javax.swing.JLabel jlInternet;
    private javax.swing.JLabel jlLink;
    private javax.swing.JLabel jlNotice;
    private javax.swing.JLabel jlTransport;
    // End of variables declaration//GEN-END:variables
}
