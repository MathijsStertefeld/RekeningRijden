/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package openstreetmaps.org.openstreetmap.gui.jmapviewer.tilesources;

/**
 *
 * @author Eagle
 */
public class OfflineOsmTileSource extends AbstractOsmTileSource {

	private final int minZoom;
	private final int maxZoom;

	public OfflineOsmTileSource(String path, int minZoom, int maxZoom) {
		super("Offline from "+path, path);
		this.minZoom = minZoom;
		this.maxZoom = maxZoom;
	}

	@Override
	public int getMaxZoom() {
		return maxZoom;
	}

	@Override
	public int getMinZoom() {
		return minZoom;
	}

	@Override
	public TileUpdate getTileUpdate() {
		return TileUpdate.None;
	}

}
