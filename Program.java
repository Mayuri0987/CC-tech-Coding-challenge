 class Program
{ 


	static int INF = 10000; 

	static class Point 
	{ 
		int x; 
		int y; 

		public Point(int x, int y) 
		{ 
			this.x = x; 
			this.y = y; 
		} 
	}; 

	//to check if point lies on borders of ploygon
	static boolean onBorder(Point p, Point q, Point r) 
	{ 
		if (q.x <= Math.max(p.x, r.x) && 
			q.x >= Math.min(p.x, r.x) && 
			q.y <= Math.max(p.y, r.y) && 
			q.y >= Math.min(p.y, r.y)) 
		{ 
			return true; 
		} 
		return false; 
	} 

	// to check orientation of points p,q,r wether it is clockwise,counterclockwise or coliner
	static int orientation(Point p, Point q, Point r) 
	{ 
		//slope of line segmensts
		int val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y); 

		if (val == 0) 
		{ 
			return 0; // colinear 
		} 
		return (val > 0) ? 1 : 2; // clock or counterclock wise 
	} 

	//to check if lines are intersecting
	static boolean doIntersect(Point p1, Point q1, 
							Point p2, Point q2) 
	{ 
		
		//Two segments (p1,q1) and (p2,q2) intersect if and only if one of the following two conditions is verified
		int o1 = orientation(p1, q1, p2); 
		int o2 = orientation(p1, q1, q2); 
		int o3 = orientation(p2, q2, p1); 
		int o4 = orientation(p2, q2, q1); 

		// General case 
		if (o1 != o2 && o3 != o4) 
		{ 
			return true; 
		} 

		// Special Cases 
		// p1, q1 and p2 are colinear and 
		// p2 lies on segment p1q1 
		if (o1 == 0 && onBorder(p1, p2, q1)) 
		{ 
			return true; 
		} 

		// p1, q1 and p2 are colinear and 
		// q2 lies on segment p1q1 
		if (o2 == 0 && onBorder(p1, q2, q1)) 
		{ 
			return true; 
		} 

		// p2, q2 and p1 are colinear and 
		// p1 lies on segment p2q2 
		if (o3 == 0 && onBorder(p2, p1, q2)) 
		{ 
			return true; 
		} 

		// p2, q2 and q1 are colinear and 
		// q1 lies on segment p2q2 
		if (o4 == 0 && onBorder(p2, q1, q2)) 
		{ 
			return true; 
		} 

		// Doesn't fall in any of the above cases 
		return false; 
	} 

	// Returns true if the point p lies 
	// inside the polygon[] with n vertices 
	static boolean isInsidePolygon(Point polygon[], int n, Point p) 
	{ 
		// There must be at least 3 vertices in polygon[] 
		if (n < 3) 
		{ 
			return false; 
		} 

		System.out.println("p.y"+ p.y);
		// Create a point for line segment from p to infinite 
		Point extreme = new Point(INF, p.y); 
		

		// Count intersections of the above line 
		// with sides of polygon 
		int count = 0, i = 0; 
		do
		{ 
			int next = (i + 1) % n; 

			// Check if the line segment from 'p' to 
			// 'extreme' intersects with the line 
			// segment from 'polygon[i]' to 'polygon[next]' 
			if (doIntersect(polygon[i], polygon[next], p, extreme)) 
			{ 
				// If the point 'p' is colinear with line 
				// segment 'i-next', then check if it lies 
				// on segment. If it lies, return true, otherwise false 
				if (orientation(polygon[i], p, polygon[next]) == 0) 
				{ 
					return onBorder(polygon[i], p, 
									polygon[next]); 
				} 

				count++; 
			} 
			i = next; 
		} while (i != 0); 

		// Return true if count is odd, false otherwise 
		return (count % 2 == 1); // Same as (count%2 == 1) 
	} 

	
	public static void main(String[] args) 
	{ 
		
		
		Point polygon1[] = {new Point(1, 0), 
							new Point(8, 3), 
							new Point(8, 8), 
							new Point(1, 5)}; 
		int n = polygon1.length; 
		System.out.println("value of n"+n);
		Point p = new Point(3, 5); 
		if (isInsidePolygon(polygon1, n, p)) 
		{ 
			System.out.println("Yes"); 
		} 
		else
		{ 
			System.out.println("No"); 
		} 
		
		

	} 
} 

