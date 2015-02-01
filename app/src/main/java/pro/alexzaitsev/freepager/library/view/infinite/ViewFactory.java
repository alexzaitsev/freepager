package pro.alexzaitsev.freepager.library.view.infinite;

import android.view.View;

/**
 * 
 * @author A.Zaitsev
 *
 */
public interface ViewFactory {

	View makeView(int vertical, int horizontal);

}