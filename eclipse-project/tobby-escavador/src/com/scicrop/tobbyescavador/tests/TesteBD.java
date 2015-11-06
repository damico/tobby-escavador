package com.scicrop.tobbyescavador.tests;

import java.util.List;

import org.junit.Test;

public class TesteBD {

	@Test
	public void test() {
	
		List<Polygon> p = DbAdaptor.getInstance().getPolygon();
		Location locKey = new Location(-6.66460756,-66.46728516);

		Location locKey2 = new Location(-66.46728516, -6.66460756);
		
		for (Polygon polygon : p) {
			if(contains(locKey, polygon.getPol())){
				System.out.println("ESSE ID DA PROPRIEDADE"+polygon.getId());
			}
			
			if(contains(locKey2, polygon.getPol())){
				System.out.println("2222222222222222 ESSE ID DA PROPRIEDADE"+polygon.getId());
			}
		}
	}
	

	public boolean contains(Location locKey, List<Location> locLst) {

		Location lastPoint = locLst.get(locLst.size() -1);


		boolean isInside = false;
		double x = locKey.getLon();

		for (Location point : locLst) {

			double x1 = lastPoint.getLon();
			double x2 = point.getLon();
			double dx = x2 - x1;


			if (Math.abs(dx) > 180.0)
			{
				if (x > 0) {
					while (x1 < 0)
						x1 += 360;
					while (x2 < 0)
							x2 += 360;
				} else {
					while (x1 > 0) x1 -= 360;
					while (x2 > 0) x2 -= 360;
				}
				dx = x2 - x1;
			}

			if ((x1 <= x && x2 > x) || (x1 >= x && x2 < x)) {
				
				double grad = (point.getLat() - lastPoint.getLat()) / dx;
				double intersectAtLat = lastPoint.getLat() + ((x - x1) * grad);

				if (intersectAtLat > locKey.getLat()) isInside = !isInside;
			}
			lastPoint = point;
		}

		return isInside;
	}


}
