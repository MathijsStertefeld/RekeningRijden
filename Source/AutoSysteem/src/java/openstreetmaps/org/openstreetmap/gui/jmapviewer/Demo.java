package openstreetmaps.org.openstreetmap.gui.jmapviewer;

//License: GPL. Copyright 2008 by Jan Peter Stotz
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import openstreetmaps.org.openstreetmap.gui.jmapviewer.events.JMVCommandEvent;
import openstreetmaps.org.openstreetmap.gui.jmapviewer.interfaces.JMapViewerEventListener;
import openstreetmaps.org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;
import openstreetmaps.org.openstreetmap.gui.jmapviewer.interfaces.TileLoader;
import openstreetmaps.org.openstreetmap.gui.jmapviewer.interfaces.TileSource;
import openstreetmaps.org.openstreetmap.gui.jmapviewer.tilesources.BingAerialTileSource;
import openstreetmaps.org.openstreetmap.gui.jmapviewer.tilesources.MapQuestOpenAerialTileSource;
import openstreetmaps.org.openstreetmap.gui.jmapviewer.tilesources.MapQuestOsmTileSource;
import openstreetmaps.org.openstreetmap.gui.jmapviewer.tilesources.OsmTileSource;

/**
 *
 * Demonstrates the usage of {@link JMapViewer}
 *
 * @author Jan Peter Stotz
 *
 */
public class Demo extends JFrame implements JMapViewerEventListener
{

    private static final long serialVersionUID = 1L;
    private JMapViewer map = null;
    private JLabel zoomLabel = null;
    private JLabel zoomValue = null;
    private JLabel mperpLabelName = null;
    private JLabel mperpLabelValue = null;

    public Demo()
    {
        super("JMapViewer Demo");
        setSize(400, 400);

        map = new JMapViewer();

        // Listen to the map viewer for user operations so components will
        // recieve events and update
        map.addJMVListener(this);

        // final JMapViewer map = new JMapViewer(new MemoryTileCache(),4);
        // map.setTileLoader(new OsmFileCacheTileLoader(map));
        // new DefaultMapController(map);

        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);;
        JPanel panel = new JPanel();
        JPanel panelTop = new JPanel();
        JPanel panelBottom = new JPanel();
        JPanel helpPanel = new JPanel();

        mperpLabelName = new JLabel("Meters/Pixels: ");
        mperpLabelValue = new JLabel(String.format("%s", map.getMeterPerPixel()));

        zoomLabel = new JLabel("Zoom: ");
        zoomValue = new JLabel(String.format("%s", map.getZoom()));

        add(panel, BorderLayout.NORTH);
        add(helpPanel, BorderLayout.SOUTH);
        panel.setLayout(new BorderLayout());
        panel.add(panelTop, BorderLayout.NORTH);
        panel.add(panelBottom, BorderLayout.SOUTH);
        JLabel helpLabel = new JLabel("Use right mouse button to move,\n "
                + "left double click or mouse wheel to zoom.");
        helpPanel.add(helpLabel);
        JButton button = new JButton("setDisplayToFitMapMarkers");
        button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                map.setDisplayToFitMapMarkers();
            }
        });
        JComboBox tileSourceSelector = new JComboBox(new TileSource[]
                {
                    new OsmTileSource.Mapnik(),
                    new OsmTileSource.CycleMap(), new BingAerialTileSource(), new MapQuestOsmTileSource(), new MapQuestOpenAerialTileSource()
                });
        tileSourceSelector.addItemListener(new ItemListener()
        {
            public void itemStateChanged(ItemEvent e)
            {
                map.setTileSource((TileSource) e.getItem());
            }
        });
        JComboBox tileLoaderSelector;
        try
        {
            tileLoaderSelector = new JComboBox(new TileLoader[]
                    {
                        new OsmFileCacheTileLoader(map),
                        new OsmTileLoader(map)
                    });
        }
        catch (IOException e)
        {
            tileLoaderSelector = new JComboBox(new TileLoader[]
                    {
                        new OsmTileLoader(map)
                    });
        }
        tileLoaderSelector.addItemListener(new ItemListener()
        {
            public void itemStateChanged(ItemEvent e)
            {
                map.setTileLoader((TileLoader) e.getItem());
            }
        });
        map.setTileLoader((TileLoader) tileLoaderSelector.getSelectedItem());
        panelTop.add(tileSourceSelector);
        panelTop.add(tileLoaderSelector);
        final JCheckBox showMapMarker = new JCheckBox("Map markers visible");
        showMapMarker.setSelected(map.getMapMarkersVisible());
        showMapMarker.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                map.setMapMarkerVisible(showMapMarker.isSelected());
            }
        });
        panelBottom.add(showMapMarker);
        final JCheckBox showTileGrid = new JCheckBox("Tile grid visible");
        showTileGrid.setSelected(map.isTileGridVisible());
        showTileGrid.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                map.setTileGridVisible(showTileGrid.isSelected());
            }
        });
        panelBottom.add(showTileGrid);
        final JCheckBox showZoomControls = new JCheckBox("Show zoom controls");
        showZoomControls.setSelected(map.getZoomContolsVisible());
        showZoomControls.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                map.setZoomContolsVisible(showZoomControls.isSelected());
            }
        });
        panelBottom.add(showZoomControls);
        final JCheckBox scrollWrapEnabled = new JCheckBox("Scrollwrap enabled");
        scrollWrapEnabled.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                map.setScrollWrapEnabled(scrollWrapEnabled.isSelected());
            }
        });
        panelBottom.add(scrollWrapEnabled);
        panelBottom.add(button);

        panelTop.add(zoomLabel);
        panelTop.add(zoomValue);
        panelTop.add(mperpLabelName);
        panelTop.add(mperpLabelValue);

        add(map, BorderLayout.CENTER);

        //
        map.addMapMarker(new MapMarkerDot(51.4560332, 5.5380103));
        map.addMapMarker(new MapMarkerDot(51.455726, 5.5326495));
        
        Coordinate one = new Coordinate(51.4560332, 5.5380103);
        Coordinate two = new Coordinate(51.455726, 5.5326495);
        
        List<Coordinate> route = new ArrayList<Coordinate>(Arrays.asList(one, two, two));
        map.addMapPolygon(new MapPolygonImpl(route));
//        map.addMapMarker(new MapMarkerDot(49.91, 8.24));
//        map.addMapMarker(new MapMarkerDot(49.71, 8.64));
//        map.addMapMarker(new MapMarkerDot(48.71, -1));
//        map.addMapMarker(new MapMarkerDot(49.8588, 8.643));

        // map.setDisplayPositionByLatLon(49.807, 8.6, 11);
        // map.setTileGridVisible(true);

        map.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if (e.getButton() == MouseEvent.BUTTON1)
                {
                    map.getAttribution().handleAttribution(e.getPoint(), true);
                }
            }
        });

        map.addMouseMotionListener(new MouseAdapter()
        {
            @Override
            public void mouseMoved(MouseEvent e)
            {
                boolean cursorHand = map.getAttribution().handleAttributionCursor(e.getPoint());
                if (cursorHand)
                {
                    map.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
                else
                {
                    map.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            }
        });
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        // java.util.Properties systemProperties = System.getProperties();
        // systemProperties.setProperty("http.proxyHost", "localhost");
        // systemProperties.setProperty("http.proxyPort", "8008");
        new Demo().setVisible(true);
    }

    private void updateZoomParameters()
    {
        if (mperpLabelValue != null)
        {
            mperpLabelValue.setText(String.format("%s", map.getMeterPerPixel()));
        }
        if (zoomValue != null)
        {
            zoomValue.setText(String.format("%s", map.getZoom()));
        }
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
}
