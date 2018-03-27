package org.pollux.grpcdemo.routeguide;

import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 11/8/17
 * Time: 16:25
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class RouteGuideUtil {

	private static final double COORD_FACTOR = 1e7;

	public static URL getDefaultFeaturesFile() {
//		return RouteGuideServer.class.getResource("route_guide_db.json");
		return null;
	}
}
