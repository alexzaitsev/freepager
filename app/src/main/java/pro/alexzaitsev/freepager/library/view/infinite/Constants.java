package pro.alexzaitsev.freepager.library.view.infinite;

public class Constants {
	
	public enum Direction {
		TOP, RIGHT, BOTTOM, LEFT, CENTER
	};
	
	public static final int CENTRAL_PAGE_INDEX = 1;
	public static final int TOP_PAGE_INDEX = 0;
	public static final int BOTTOM_PAGE_INDEX = 2;

	/**
	 * Max number of pages in view pager.
	 */
	public static final int INF = Integer.MAX_VALUE;
	/**
	 * Start page position of view pager.
	 */
	public static final int START_INDEX = INF / 2;
}
