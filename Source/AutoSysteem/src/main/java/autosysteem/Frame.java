/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autosysteem;

import com.marbl.administration.domain.Car;
import com.marbl.administration.domain.Driver;
import com.marbl.autosysteem.Session;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import domain.CarHolder;
import domain.Osmosis;
import domain.Simulator;
import domain.Vehicle;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.text.DefaultCaret;
import openstreetmaps.org.openstreetmap.gui.CarGraphic;
import openstreetmaps.org.openstreetmap.gui.jmapviewer.JMapViewer;
import openstreetmaps.org.openstreetmap.gui.jmapviewer.OsmTileLoader;
import openstreetmaps.org.openstreetmap.gui.jmapviewer.events.JMVCommandEvent;
import openstreetmaps.org.openstreetmap.gui.jmapviewer.interfaces.JMapViewerEventListener;
import openstreetmaps.org.openstreetmap.gui.jmapviewer.tilesources.OsmTileSource;
import org.openstreetmap.osmosis.core.domain.v0_6.Node;
import services.AdministrationService;

/**
 *
 * @author Eagle
 */
public class Frame extends javax.swing.JFrame implements JMapViewerEventListener
{

    private static final int DESIRED_FOCUS_ZOOM = 10;
    private static final int DESIRED_ROUTE_WIDTH = 5;
    private static final Color DESIRED_DEFAULT_CAR_COLOR = Color.gray;
    private static final Color DESIRED_HIGHLIGHT_CAR_COLOR = Color.BLUE;
    private static final long CAR_SIMULATION_STEP = 500;
    private JMapViewer map;
    private double meterPerPixel;
    private double zoom;
    private Simulator sim;
    private Driver loggedInDriver;
    private AdministrationService adminService;

    /**
     * Creates new form Frame
     */
    public Frame()
    {
        super("Autosysteem");
        adminService = new AdministrationService();
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        setLayout(new BorderLayout());
        initComponents();
        // Add the map to the form and fill in settings
        addMap();

        //Simulation happens here
        sim = new Simulator(jsInterval.getValue(), CAR_SIMULATION_STEP, this);

        for (Vehicle c : CarHolder.getCars())
        {
            currentCarComboBox.addItem(c.getCarTrackerId());
        }

        //File has been found. No errors.
        if (Osmosis.init())
        {
            validate();
        } else
        {
            JOptionPane.showMessageDialog(null, "Kan bestand 'bigroads.xml' niet vinden. Wegennetwerk niet ingeladen. Vraag naar Leslie of Alexander.");
        }

        //Autoscrolls output pane
        DefaultCaret caret = (DefaultCaret) outputPane.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        mapPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        infoLabel = new javax.swing.JLabel();
        ownerLabel = new javax.swing.JLabel();
        numberOfCarsLabel = new javax.swing.JLabel();
        currentCarComboBox = new javax.swing.JComboBox();
        infoLabel2 = new javax.swing.JLabel();
        infoLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jsInterval = new javax.swing.JSlider();
        jScrollPane1 = new javax.swing.JScrollPane();
        outputPane = new javax.swing.JTextArea();
        lbInterval = new javax.swing.JLabel();
        btStartSimulation = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        cbShowRoute = new javax.swing.JCheckBox();
        fitToScreenBtn = new javax.swing.JButton();
        logOutBtn = new javax.swing.JButton();
        lbCurrentCarSpeed = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        btAddCar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(140, 218, 195));
        setResizable(false);

        mapPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        javax.swing.GroupLayout mapPanelLayout = new javax.swing.GroupLayout(mapPanel);
        mapPanel.setLayout(mapPanelLayout);
        mapPanelLayout.setHorizontalGroup(
            mapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 516, Short.MAX_VALUE)
        );
        mapPanelLayout.setVerticalGroup(
            mapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        infoLabel.setText("Eigenaar: ");

        ownerLabel.setText("jLabel1");

        numberOfCarsLabel.setText("jLabel1");

        currentCarComboBox.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                currentCarComboBoxActionPerformed(evt);
            }
        });

        infoLabel2.setText("Aantal auto's:");

        infoLabel3.setText("Huidige auto");

        jsInterval.setMaximum(10000);
        jsInterval.setMinimum(100);
        jsInterval.setValue(1000);
        jsInterval.addChangeListener(new javax.swing.event.ChangeListener()
        {
            public void stateChanged(javax.swing.event.ChangeEvent evt)
            {
                jsIntervalStateChanged(evt);
            }
        });

        outputPane.setEditable(false);
        outputPane.setColumns(20);
        outputPane.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        outputPane.setRows(5);
        jScrollPane1.setViewportView(outputPane);

        lbInterval.setText("-");

        btStartSimulation.setText("Start Simulatie");
        btStartSimulation.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btStartSimulationActionPerformed(evt);
            }
        });

        jLabel2.setText("Timestep");

        cbShowRoute.setText("Toon route");
        cbShowRoute.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                cbShowRouteActionPerformed(evt);
            }
        });

        fitToScreenBtn.setText("Aanpassen aan scherm");
        fitToScreenBtn.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                fitToScreenBtnActionPerformed(evt);
            }
        });

        logOutBtn.setText("Uitloggen");
        logOutBtn.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                logOutBtnActionPerformed(evt);
            }
        });

        lbCurrentCarSpeed.setText("0");

        jLabel4.setText("Huidige snelheid:");

        jLabel5.setText("km/u");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbInterval, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jsInterval, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btStartSimulation, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(infoLabel)
                                    .addComponent(infoLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ownerLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(numberOfCarsLabel, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(infoLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(currentCarComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(cbShowRoute)))
                        .addContainerGap())))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbCurrentCarSpeed)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(fitToScreenBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(logOutBtn)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(infoLabel)
                    .addComponent(ownerLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numberOfCarsLabel)
                    .addComponent(infoLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(currentCarComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(infoLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbShowRoute)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCurrentCarSpeed)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(74, 74, 74)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btStartSimulation)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jsInterval, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbInterval, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2))
                        .addGap(27, 27, 27)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fitToScreenBtn)
                    .addComponent(logOutBtn))
                .addContainerGap())
        );

        jMenu1.setText("Simulatie");

        btAddCar.setText("Auto toevoegen...");
        btAddCar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btAddCarActionPerformed(evt);
            }
        });
        jMenu1.add(btAddCar);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mapPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(mapPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public JMapViewer getMap()
    {
        return map;
    }

    public void setOutputText(String text)
    {
        this.outputPane.append(text);
    }

    private void btAddCarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btAddCarActionPerformed
    {//GEN-HEADEREND:event_btAddCarActionPerformed

        // Get all the cars from the current logged in driver;
        Collection<Car> cars = adminService.getCarsFromUser(loggedInDriver);
        ArrayList<String> carStrings = new ArrayList<String>();

        // Add carStrings in format: "Volkswagen Beetle (AA-11-BB)"
        for (Car c : cars)
        {
            carStrings.add(c.getBrand() + " " + c.getModel() + " (" + c.getLicensePlate() + ")");
        }
        String chosenCarString = (String) JOptionPane.showInputDialog(this, "Selecteer een auto.", "Auto selectie", JOptionPane.QUESTION_MESSAGE, null, carStrings.toArray(), carStrings.get(0));

        if (chosenCarString != null && chosenCarString.length() != 0)
        {
            // Create a car object.
            Car selectedCar = null;
            for (Car c : cars)
            {
                String carString = c.getBrand() + " " + c.getModel() + " (" + c.getLicensePlate() + ")";
                if (carString.equals(chosenCarString))
                {
                    selectedCar = c;
                }
            }

            // Add the license plate to the currentCarComboBox
            if (selectedCar != null)
            {
                String selectedCarString = selectedCar.getBrand() + " " + selectedCar.getModel() + " (" + selectedCar.getLicensePlate() + ")";
                currentCarComboBox.addItem(selectedCarString);
                currentCarComboBox.setSelectedItem(selectedCar);
            }

            String routeAmountString = JOptionPane.showInputDialog(null, "Hoeveel wegen moet de route van deze auto bevatten?");

            if (routeAmountString != null && routeAmountString.length() != 0)
            {


                Pattern p = Pattern.compile("[A-Z,a-z,&%$#@!()*^]");
                Matcher m = p.matcher(routeAmountString);
                if (m.find())
                {
                    JOptionPane.showMessageDialog(null, "Dit is geen geldig getal.", null, JOptionPane.ERROR_MESSAGE);
                } else
                {
                    int routeAmount = Integer.parseInt(routeAmountString);
                    ArrayList<Node> route = Osmosis.plotPath(routeAmount);
                    Vehicle v = new Vehicle(selectedCar, route);

                    sim.addCar(v, route);
                    map.addCarGraphic(v.getCarGraphic());
                }
            } else
            {
                JOptionPane.showMessageDialog(this, "Er is geen getal ingevuld.", "Fout bij aanmaken route", JOptionPane.ERROR_MESSAGE);
            }
        }
        validate();
    }//GEN-LAST:event_btAddCarActionPerformed

    private void btStartSimulationActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btStartSimulationActionPerformed
    {//GEN-HEADEREND:event_btStartSimulationActionPerformed
        if (sim != null)
        {
            if (!sim.isRunning())
            {
                if (CarHolder.getCars().size() > 0)
                {
                    sim.start();
                    btStartSimulation.setText("Stop Simulatie");
                } else
                {
                    JOptionPane.showMessageDialog(null, "Er zijn nog geen auto's in de simulatie geplaatst.", null, JOptionPane.WARNING_MESSAGE);
                }

            } else
            {
                sim.stop();
                btStartSimulation.setText("Start Simulatie");

                if (sendSession(sim.generateSession()))
                {
                    JOptionPane.showMessageDialog(null, "Simulatie gestopt. De verplaatsingsgegevens van de auto's zijn verstuurd.");
                } else
                {
                    JOptionPane.showMessageDialog(null, "Simulatie gestopt.");
                }

            }
        }
    }//GEN-LAST:event_btStartSimulationActionPerformed

    private void jsIntervalStateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_jsIntervalStateChanged
    {//GEN-HEADEREND:event_jsIntervalStateChanged
        sim.changeTimestepInterval(jsInterval.getValue());
        double seconds = jsInterval.getValue() / 1000;
        lbInterval.setText(String.valueOf(seconds));
    }//GEN-LAST:event_jsIntervalStateChanged

    private void currentCarComboBoxActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_currentCarComboBoxActionPerformed
    {//GEN-HEADEREND:event_currentCarComboBoxActionPerformed
        map.removeAllMapPolygons();
        if (currentCarComboBox != null && currentCarComboBox.getSelectedItem() != null)
        {
            for (Vehicle vehicle : CarHolder.getCars())
            {
                CarGraphic c = vehicle.getCarGraphic();
                String carString = vehicle.getBrand() + " " + vehicle.getModel() + " (" + vehicle.getLicensePlate() + ")";
                if (((String) currentCarComboBox.getSelectedItem()).equals(carString))
                {
                    //Car is selected, update everything
                    map.setDisplayPositionByLatLon(vehicle.getPosition().getLatitudeInDegrees(), vehicle.getPosition().getLongitudeInDegrees(), DESIRED_FOCUS_ZOOM);
                    c.highlight(DESIRED_HIGHLIGHT_CAR_COLOR);
                    lbCurrentCarSpeed.setText(vehicle.getCarSpeedInKM() + "");
                    map.repaint();

                    if (cbShowRoute.isSelected())
                    {
                        map.drawRoute(vehicle.getRoute().getRoute());
                    }
                } else
                {
                    c.highlight(DESIRED_DEFAULT_CAR_COLOR);
                    map.repaint();
                }
            }
        }
    }//GEN-LAST:event_currentCarComboBoxActionPerformed

    private void cbShowRouteActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cbShowRouteActionPerformed
    {//GEN-HEADEREND:event_cbShowRouteActionPerformed

        if (cbShowRoute.isSelected())
        {
            //If box is checked, and if car is not null/cartracker is not null
            if (currentCarComboBox != null && currentCarComboBox.getSelectedItem() != null)
            {
                for (Vehicle car : sim.getGarage().getCars())
                {
                    //Get the cars route
                    ArrayList<Node> route = car.getRoute().getRoute();
                    String carString = car.getBrand() + " " + car.getModel() + " (" + car.getLicensePlate() + ")";
                    if (((String) currentCarComboBox.getSelectedItem()).equals(carString))
                    {
                        //And draw the route
                        map.drawRoute(route);
                    } else
                    {
                        //If car does not equal the thing
                        map.removeAllMapPolygons();
                    }
                }
            }
        } //Check box is deselected
        else
        {
            map.removeAllMapPolygons();
        }

    }//GEN-LAST:event_cbShowRouteActionPerformed

    private void fitToScreenBtnActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_fitToScreenBtnActionPerformed
    {//GEN-HEADEREND:event_fitToScreenBtnActionPerformed
        map.setDisplayToFitMapElements(false, false, false, true);
    }//GEN-LAST:event_fitToScreenBtnActionPerformed

    private void logOutBtnActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_logOutBtnActionPerformed
    {//GEN-HEADEREND:event_logOutBtnActionPerformed
        LogInFrame frame = new LogInFrame();
        frame.setVisible(true);
        this.loggedInDriver = null;
        this.dispose();
        
    }//GEN-LAST:event_logOutBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem btAddCar;
    private javax.swing.JButton btStartSimulation;
    private javax.swing.JCheckBox cbShowRoute;
    private javax.swing.JComboBox currentCarComboBox;
    private javax.swing.JButton fitToScreenBtn;
    private javax.swing.JLabel infoLabel;
    private javax.swing.JLabel infoLabel2;
    private javax.swing.JLabel infoLabel3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSlider jsInterval;
    private javax.swing.JLabel lbCurrentCarSpeed;
    private javax.swing.JLabel lbInterval;
    private javax.swing.JButton logOutBtn;
    private javax.swing.JPanel mapPanel;
    private javax.swing.JLabel numberOfCarsLabel;
    private javax.swing.JTextArea outputPane;
    private javax.swing.JLabel ownerLabel;
    // End of variables declaration//GEN-END:variables

    private void updateZoomParameters()
    {
        meterPerPixel = map.getMeterPerPixel();
        zoom = map.getZoom();
    }

    @Override
    public void processCommand(JMVCommandEvent command)
    {
        if (command.getCommand().equals(JMVCommandEvent.COMMAND.ZOOM)
                || command.getCommand().equals(JMVCommandEvent.COMMAND.MOVE))
        {
            updateZoomParameters();
        }
    }

    /**
     * Adds the map to 
     */
    public void addMap()
    {
        // Instantiate the map
        map = new JMapViewer();

        // Listen to the map viewer for user operations so components will
        // recieve events and update
        map.addJMVListener(this);

        // Set the tile source:
        // Other options are: 
        // OsmTileSource.CycleMap(), new BingAerialTileSource(), new MapQuestOsmTileSource(), new MapQuestOpenAerialTileSource()
        map.setTileSource(new OsmTileSource.Mapnik());

        // Set the tileloader
        map.setTileLoader(new OsmTileLoader(map));

        // Set the zoom controls to show on the map
        map.setZoomContolsVisible(true);

        // Set the layout for the map panel
        mapPanel.setLayout(new BorderLayout());

        // Add the JMapViewer component to the panel
        mapPanel.add(map, BorderLayout.CENTER);

        mapPanel.validate();
    }

    public void setDriver(Driver driver)
    {
        ownerLabel.setText(driver.getFirstName() + " " + driver.getLastName());
        numberOfCarsLabel.setText(Integer.toString(adminService.getCarsFromUser(driver).size()));
        loggedInDriver = driver;
    }

    public boolean sendSession(Session s)
    {

        if (CarHolder.getCars().size() > 0)
        {
            this.outputPane.removeAll();

            JOptionPane.showMessageDialog(null, "Simulatie gestopt. Verplaatsingen worden verstuurd naar Verplaatsingsysteem.");
            ClientConfig config = new DefaultClientConfig();
            Client client = Client.create(config);

            this.setOutputText("Verbinding maken met verplaatsingsysteem...\n\n");
            WebResource service = client.resource("http://192.168.30.187:8080/VerplaatsingSysteemWeb/");
            this.setOutputText("Versturen gegevens...\n\n");
            service.path("resources").path("session").post(s);
            this.setOutputText("Klaar.\n\n");
            return true;

        } else
        {
            return false;
        }

    }
}
