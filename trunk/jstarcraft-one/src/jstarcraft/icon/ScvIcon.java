package jstarcraft.icon;

import java.awt.Image;

import jstarcraft.core.GridMapRender;
import jstarcraft.net.datagram.IconInfo;
import jstarcraft.tile.Scv;


public class ScvIcon extends BaseIcon {

	public ScvIcon(Image iconImage) {
		super(iconImage);
	}

	public void onClicked(GridMapRender gridMapRender) {

		Scv tile = (Scv) gridMapRender.getCurrentTile();
		if (gridMapRender.checkResource(resource)) {
			tile.readyBuild(this);
			gridMapRender.getNetWorkManager().readyBuild(
					new IconInfo(tile.getUUID(), gridMapRender.getGridMap()
							.getIconKey(this)));
		}
	}

}
